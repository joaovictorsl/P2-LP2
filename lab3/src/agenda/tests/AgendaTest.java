package lab3.src.agenda.tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import agenda.Agenda;
import agenda.Contato;

public class AgendaTest {

  private Agenda agendaBase;

  @BeforeEach
  void prepraraAgenda() {
    this.agendaBase = new Agenda();
  }

  @Test
  void testAdicionaFavorito() {
    // Adiciona favorito com sucesso.
    agendaBase.cadastraContato(1, "JV", "JayVi", "1234567");
    String resultado = agendaBase.adicionaFavorito(1, agendaBase.getContato(1));
    assertEquals("JV JayVi", agendaBase.getContato(1).getNomeCompleto());
    assertEquals(agendaBase.getContato(1), agendaBase.getListaDeFavoritos()[0]);
    assertEquals("CONTATO FAVORITADO NA POSIÇÃO 1!\n", resultado);
    // Não adiciona favorito repetido.
    resultado = agendaBase.adicionaFavorito(2, agendaBase.getContato(1));
    assertEquals(null, agendaBase.getListaDeFavoritos()[1]);
    assertEquals("CONTATO JÁ CADASTRADO!\n", resultado);
    // Não adiciona favorito em posição inválida.
    resultado = agendaBase.adicionaFavorito(11, agendaBase.getContato(1));
    assertEquals("POSIÇÃO INVÁLIDA!\n", resultado);
    // Não adiciona favorito nulo.
    resultado = agendaBase.adicionaFavorito(10, agendaBase.getContato(83));
    assertEquals("CONTATO INVÁLIDO!\n", resultado);
  }

  @Test
  void testAdicionarTag() {
    // Posição válida
    agendaBase.cadastraContato(1, "A", "B", "123");
    agendaBase.adicionarTag(new String[] { "1" }, "opa", 1);
    assertEquals("opa", agendaBase.getContato(1).getTags()[0]);
    // Posição inválida
    agendaBase.cadastraContato(2, "B", "C", "123456");
    IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
        () -> agendaBase.adicionarTag(new String[] { "1" }, "EITA", 0));
    assertEquals("POSIÇÃO INVÁLIDA!\n", thrown.getMessage());
    // Múltiplos contatos
    agendaBase.adicionarTag(new String[] { "1", "2" }, "RAPAAAIZ", 2);
    assertEquals("RAPAAAIZ", agendaBase.getContato(1).getTags()[1]);
    assertEquals("RAPAAAIZ", agendaBase.getContato(2).getTags()[1]);
  }

  @Test
  void testCadastraContato() {
    // Posição vazia
    agendaBase.cadastraContato(1, "Henrique", "Arruda", "12345");
    assertEquals("Henrique Arruda", agendaBase.getContato(1).getNomeCompleto());
    // Cadastrar em posição ocupada
    agendaBase.cadastraContato(1, "João Victor", "Lucena", "123456789");
    assertEquals("João Victor Lucena", agendaBase.getContato(1).getNomeCompleto());
    // Cadastrar contato com mesmo nome e sobrenome
    agendaBase.cadastraContato(2, "João Victor", "Lucena", "987654321");
    assertEquals(null, agendaBase.getContato(2));
    // Cadastra na posição limite superior
    agendaBase.cadastraContato(100, "Rejane", "Lucena", "987654321");
    assertEquals("Rejane Lucena", agendaBase.getContato(100).getNomeCompleto());
    // Cadastra depois do limite superior
    String resultado = agendaBase.cadastraContato(101, "João", "Arruda", "987654321");
    assertEquals("POSIÇÃO INVÁLIDA!\n", resultado);
    // Cadastra antes do limite inferior
    resultado = agendaBase.cadastraContato(0, "João Victor", "Lucena", "987654321");
    assertEquals("POSIÇÃO INVÁLIDA!\n", resultado);
    // Telefone vazio
    resultado = agendaBase.cadastraContato(20, "André", "Ferraz", "");
    assertEquals("CONTATO INVÁLIDO!\n", resultado);
    // Nome vazio
    resultado = agendaBase.cadastraContato(19, "", "Ferraz", "123455");
    assertEquals("CONTATO INVÁLIDO!\n", resultado);
  }

  @Test
  void testCarregaContato() {
    // Carrega os contatos do arquivo csv (Método utilizado pelo LeitorDeAgenda após
    // ler o arquivo csv).
    agendaBase.carregaContato(10, "Henrique", "Lucena", "123456789");
    assertEquals("Henrique Lucena", agendaBase.getContato(10).getNomeCompleto());
  }

  @Test
  void testContatoRepetido() {
    // Verifica se os contatos repetidos são identificados através do método
    // contatoRepetido.
    Contato matheus = new Contato("Matheus", "Gaudencio", "789604238");
    Contato contatoTemp = new Contato("Matheus", "Gaudencio", "123456789");

    agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "789604238");

    assertEquals(matheus, agendaBase.getContato(1));
    assertEquals(true, agendaBase.contatoRepetido(contatoTemp));
  }

  @Test
  void testEhFavorito() {
    // Testa se um contato favorito é identificado como favorito
    agendaBase.cadastraContato(1, "A ", "B", "12345");
    agendaBase.adicionaFavorito(1, agendaBase.getContato(1));
    assertEquals(0, agendaBase.ehFavorito(agendaBase.getContato(1)));
  }

  @Test
  void testRemoverContato() {
    // Remove dos contatos e favoritos
    agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "789604238");
    agendaBase.adicionaFavorito(1, agendaBase.getContato(1));
    agendaBase.removerContato(1);
    assertEquals(null, agendaBase.getContato(1));
    assertEquals(null, agendaBase.getListaDeFavoritos()[0]);
    // Remove em posição inválida
    IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
        () -> agendaBase.removerContato(101));
    assertTrue(thrown.getMessage().equals("POSIÇÃO INVÁLIDA!\n"));
    // Remove em posição vazia
    assertEquals(false, agendaBase.removerContato(99));
  }
}
