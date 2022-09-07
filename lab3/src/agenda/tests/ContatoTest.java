package lab3.src.agenda.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import agenda.Contato;

public class ContatoTest {

  private Contato contatoJoao;
  private Contato contatoHenrique;

  @BeforeEach
  void preparaContatos() {
    this.contatoJoao = new Contato("João Victor", "Lucena", "(83) 99999-9999");
    this.contatoHenrique = new Contato("Henrique", "Souza", "(83) 99999-9999");
  }

  @Test
  void testConstrutor() {
    // Levanta erro com nome vazio.
    IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
        () -> new Contato("", "Sobrenome", "123456789"));
    assertEquals("CONTATO INVÁLIDO!\n", thrown.getMessage());
    // Levanta erro com telefone vazio.
    thrown = assertThrows(IllegalArgumentException.class,
        () -> new Contato("Nome", "Sobrenome", ""));
    assertEquals("CONTATO INVÁLIDO!\n", thrown.getMessage());
  }

  @Test
  void testEquals() {
    // Testa igualdade entre contatos.
    assertEquals(contatoJoao, contatoJoao);
    assertNotEquals(contatoJoao, contatoHenrique);
  }

  @Test
  void testSetTag() {
    // Tags são iniciada como nulo.
    assertEquals(null, contatoJoao.getTags()[0]);
    // Define tag corretamente.
    contatoJoao.setTag(1, "ufcg");
    assertEquals("ufcg", contatoJoao.getTags()[0]);
    // Sobrescreve tag.
    contatoJoao.setTag(1, "ccc");
    assertEquals("ccc", contatoJoao.getTags()[0]);
    // Define tag em posição inválida.
    IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> contatoJoao.setTag(0, "ccc"));
    assertEquals("POSIÇÃO INVÁLIDA!\n", thrown.getMessage());
  }

  @Test
  void testToString() {
    // Representação correta do contato.
    assertEquals("João Victor\nLucena\n(83) 99999-9999\n", contatoJoao.toString());
  }
}
