package agenda.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import agenda.Agenda;
import agenda.Contato;
import agenda.LeitorDeAgenda;

public class AgendaTest {

  private Agenda agendaBase;

  @BeforeEach
  void prepraraAgenda() {
    this.agendaBase = new Agenda();
  }

  @Test
  void testAdicionaFavorito() {
    agendaBase.adicionaFavorito(1, agendaBase.getContato(1));
    assertEquals(agendaBase.getContato(1), agendaBase.getListaDeFavoritos()[0]);
    agendaBase.adicionaFavorito(2, agendaBase.getContato(1));
    assertEquals(null, agendaBase.getListaDeFavoritos()[1]);
  }

  @Test
  void testAdicionarTag() {

  }

  @Test
  void testCadastraContato() {
    agendaBase.cadastraContato(1, "João Victor", "Lucena", "123456789");
    assertEquals("João Victor Lucena", agendaBase.getContato(1).getNomeCompleto());
  }

  @Test
  void testCarregaContato() {
    agendaBase.carregaContato(10, "Henrique", "Lucena", "123456789");
    assertEquals("Henrique Lucena", agendaBase.getContato(10).getNomeCompleto());
  }

  @Test
  void testContatoRepetido() {
    Contato matheus = new Contato("Matheus", "Gaudencio", "789604238");
    Contato contatoTemp = new Contato("Matheus", "Gaudencio", "123456789");

    agendaBase.cadastraContato(1, "Matheus", "Gaudencio", "789604238");

    assertEquals(matheus, agendaBase.getContato(1));
    assertEquals(true, agendaBase.contatoRepetido(contatoTemp));
  }

  @Test
  void testEhFavorito() {

  }

  @Test
  void testRemoverContato() {
    agendaBase.adicionaFavorito(1, agendaBase.getContato(1));
    agendaBase.removerContato(1);
    assertEquals(null, agendaBase.getContato(1));
    assertEquals(null, agendaBase.getListaDeFavoritos()[0]);
  }
}
