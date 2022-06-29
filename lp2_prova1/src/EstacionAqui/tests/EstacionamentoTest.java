package EstacionAqui.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import EstacionAqui.Estacionamento;

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
  }

  @Test
  void testInformeVagaLivre() {
    // Única vaga livre é a 83.
    for (int i = 0; i < 100; i++) {
      estacionamentoBase.addVagas("teste", "opa", 18.3);
      if (i != 83)
        estacionamentoBase.mudarStatusVaga(i);
    }

    assertEquals(83, estacionamentoBase.informeVagaLivre());

    // Vaga 23 agora é livre.
    estacionamentoBase.mudarStatusVaga(23);
    assertEquals(23, estacionamentoBase.informeVagaLivre());
  }

  @Test
  void testListaVagas() {
    // Testando listagem de vagas.
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
  }

  @Test
  void testSimularPrecoDaVaga() {
    estacionamentoBase.addVagas("Rua dr Joao Moura, Sao Jose", 13.75);
    assertEquals(10.37, estacionamentoBase.simularPrecoDaVaga(0, 3));
  }
}
