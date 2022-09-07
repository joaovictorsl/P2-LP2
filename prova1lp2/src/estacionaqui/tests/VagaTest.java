package estacionaqui.tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import estacionaqui.Vaga;

public class VagaTest {

  private Vaga vagaBase;

  @BeforeEach
  public void setUp() {
    vagaBase = new Vaga(0, "Rua dr Joao Moura, Sao Jose", "https://goo.gl/maps/raoKQyjFmS7kfKnu8", 13.75);
  }

  @Test
  void testEquals() {
    // Criando nova vaga e verificando se ela é igual a vagaBase.
    Vaga novaVaga = new Vaga(1, "Rua dr Joao Moura, Sao Jose", "https://", 13.75);
    assertTrue(novaVaga.equals(vagaBase));
    // Criando nova vaga e verificando se ela é diferente da vagaBase.
    novaVaga = new Vaga(1, "Rua Sei Lá, Sao João", "https://", 13.75);
    assertFalse(novaVaga.equals(vagaBase));
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
    assertEquals("Quantidade de horas deve ser maior ou igual a zero.", thrown.getMessage());
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
