package EstacionAqui.tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import EstacionAqui.Vaga;

public class VagaTest {

  private Vaga vagaBase;

  @BeforeEach
  public void setUp() {
    vagaBase = new Vaga(0, "Rua dr Joao Moura, Sao Jose", "https://goo.gl/maps/raoKQyjFmS7kfKnu8", 13.75);
  }

  @Test
  void testMudarStatus() {
    // Mudando status após criação da vaga.
    vagaBase.mudarStatus();
    assertTrue(vagaBase.taOcupada());
    // Mudando após mudança.
    vagaBase.mudarStatus();
    assertFalse(vagaBase.taOcupada());
  }

  @Test
  void testPrecoDaVaga() {
    // Simulando preço de uma vaga de 13.75 m² por 3 horas.
    assertEquals(10.37, vagaBase.precoDaVaga(3));
    // Simulando por um valor inválido de horas.
    IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> vagaBase.precoDaVaga(-1));
    assertEquals("Quantidade de horas deve ser positivo.", thrown.getMessage());
  }

  @Test
  void testToString() {
    // Vaga livre.
    assertEquals("0 - Rua dr Joao Moura, Sao Jose - https://goo.gl/maps/raoKQyjFmS7kfKnu8 - LIVRE",
        vagaBase.toString());
    // Vaga ocupada.
    vagaBase.mudarStatus();
    assertEquals("0 - Rua dr Joao Moura, Sao Jose - https://goo.gl/maps/raoKQyjFmS7kfKnu8 - OCUPADA",
        vagaBase.toString());
  }
}
