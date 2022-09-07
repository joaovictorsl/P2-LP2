package estacionaqui.tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import estacionaqui.Estacionamento;

public class EstacionamentoTest {

  private Estacionamento estacionamentoBase;

  @BeforeEach
  public void setUp() {
    estacionamentoBase = new Estacionamento();
  }

  @Test
  void testAddVagas() {
    // Adicionando vaga com ou sem link do google maps.
    assertTrue(estacionamentoBase.addVagas("Rua dr Joao Moura, Sao Jose", 13.75));
    assertTrue(estacionamentoBase.addVagas("Rua dr Joao Moura, Sao Jose", "https://goo.gl/teste", 13.75));
    // Tentando adicionar com estacionamento cheio.
    for (int i = 2; i < 100; i++) {
      estacionamentoBase.addVagas("teste", "opa", 18.0);
    }
    assertFalse(estacionamentoBase.addVagas("eita", "tá cheio", 100));
  }

  @Test
  void testInformeVagaLivre() {
    // Verificar se vaga específica está livre.
    estacionamentoBase.addVagas("essa", "aqui", 20);
    estacionamentoBase.addVagas("aquela", "dali", 90);
    assertEquals(1, estacionamentoBase.informeVagaLivre("aquela", 90));

    // Única vaga livre é a 83.
    estacionamentoBase.mudarStatusVaga(0);
    estacionamentoBase.mudarStatusVaga(1);
    for (int i = 2; i < 100; i++) {
      estacionamentoBase.addVagas("teste", "opa", 18.3);
      if (i != 83)
        estacionamentoBase.mudarStatusVaga(i);
    }
    assertEquals(83, estacionamentoBase.informeVagaLivre());

    // Vaga 23 agora é livre.
    estacionamentoBase.mudarStatusVaga(23);
    assertEquals(23, estacionamentoBase.informeVagaLivre());

    // Todas as vagas ocupadas.
    estacionamentoBase.mudarStatusVaga(83);
    estacionamentoBase.mudarStatusVaga(23);
    assertEquals(-1, estacionamentoBase.informeVagaLivre());
  }

  @Test
  void testListaVagas() {
    // Listagem sem vagas cadastradas.
    assertEquals("", estacionamentoBase.listaVagas());
    // Listagem com vagas cadastradas.
    estacionamentoBase.addVagas("Rua dr Joao Moura, Sao Jose", 13.75);
    estacionamentoBase.addVagas("Rua Sei Lá, São João", "https://goo.gl/maps/olokinhoMeu", 10);
    estacionamentoBase.mudarStatusVaga(1);
    assertEquals(
        "0 - Rua dr Joao Moura, Sao Jose - https:// - LIVRE\n1 - Rua Sei Lá, São João - https://goo.gl/maps/olokinhoMeu - OCUPADA\n",
        estacionamentoBase.listaVagas());
  }

  @Test
  void testMudarStatusVaga() {
    estacionamentoBase.addVagas("Rua dr Joao Moura, Sao Jose", 13.75);
    estacionamentoBase.mudarStatusVaga(0);
    assertEquals("0 - Rua dr Joao Moura, Sao Jose - https:// - OCUPADA\n", estacionamentoBase.listaVagas());
    assertEquals(-1, estacionamentoBase.informeVagaLivre());
  }

  @Test
  void testSimularPrecoDaVaga() {
    estacionamentoBase.addVagas("Rua dr Joao Moura, Sao Jose", 13.75);
    // Com 3 horas
    assertEquals(10.37, estacionamentoBase.simularPrecoDaVaga(0, 3));
    // Com 0 hora
    assertEquals(1.37, estacionamentoBase.simularPrecoDaVaga(0, 0));
    // Com hora inválida.
    IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
        () -> estacionamentoBase.simularPrecoDaVaga(0, -1));
    assertEquals("Quantidade de horas deve ser maior ou igual a zero.", thrown.getMessage());
  }

  @Test
  void testAdicionarComentario() {
    // Enchendo comentários.
    for (int i = 0; i < 5; i++) {
      estacionamentoBase.adicionarComentario("Eu", "Isso é teste" + (i + 1));
    }
    String esperado = "Isso é teste5 (Eu)\nIsso é teste4 (Eu)\nIsso é teste3 (Eu)\nIsso é teste2 (Eu)\nIsso é teste1 (Eu)\n";
    assertEquals(esperado, estacionamentoBase.listaComentarios());
    // Adicionando comentário em comentários cheio.
    estacionamentoBase.adicionarComentario("Eu", "Isso é teste6");
    esperado = "Isso é teste6 (Eu)\nIsso é teste5 (Eu)\nIsso é teste4 (Eu)\nIsso é teste3 (Eu)\nIsso é teste2 (Eu)\n";
    assertEquals(esperado, estacionamentoBase.listaComentarios());
  }

  @Test
  void testListaComentarios() {
    for (int i = 0; i < 5; i++) {
      estacionamentoBase.adicionarComentario("Eu", "Isso é teste" + (i + 1));
    }

    String esperado = "Isso é teste5 (Eu)\nIsso é teste4 (Eu)\nIsso é teste3 (Eu)\nIsso é teste2 (Eu)\nIsso é teste1 (Eu)\n";
    assertEquals(esperado, estacionamentoBase.listaComentarios());
  }

  @Test
  void testRelatorioDeVagas() {
    // Vaga 0 ocupada 1 vez e vaga 1 ocupada duas vezes.
    estacionamentoBase.addVagas("teste", 20);
    estacionamentoBase.addVagas("epa", 40);
    estacionamentoBase.mudarStatusVaga(0);
    estacionamentoBase.mudarStatusVaga(1);
    estacionamentoBase.mudarStatusVaga(1);
    estacionamentoBase.mudarStatusVaga(1);
    assertEquals("Vaga 0 - 1\nVaga 1 - 2\n", estacionamentoBase.relatorioDeVagas());
  }
}
