package agenda.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
  void testEquals() {
    assertEquals(contatoJoao, contatoJoao);
    assertNotEquals(contatoJoao, contatoHenrique);
  }

  @Test
  void testSetTag() {
    assertEquals(null, contatoJoao.getTags()[0]);

    contatoJoao.setTag(1, "ufcg");
    assertEquals("ufcg", contatoJoao.getTags()[0]);
    contatoJoao.setTag(1, "ccc");
    assertEquals("ccc", contatoJoao.getTags()[0]);
  }

  @Test
  void testToString() {
    assertEquals("João Victor\nLucena\n(83) 99999-9999\n", contatoJoao.toString());
  }
}
