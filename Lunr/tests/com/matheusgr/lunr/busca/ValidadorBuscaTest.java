package com.matheusgr.lunr.busca;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidadorBuscaTest {
  private ValidadorBusca validador;

  @BeforeEach
  public void setUp() {
    this.validador = new ValidadorBusca();
  }

  @Test
  void testValidaTermo() {
    String[] arrayNulo = null;
    NullPointerException throwNullPointerException = assertThrows(NullPointerException.class,
        () -> validador.valida(arrayNulo));
    assertEquals("Conteúdo não pode ser nulo", throwNullPointerException.getMessage());

    String[] arrayValorNulo = new String[] { null, null };
    IllegalArgumentException thrownIllegalArgument = assertThrows(IllegalArgumentException.class,
        () -> validador.valida(arrayValorNulo));
    assertEquals("Pelo menos um termo não deve ser vazio", thrownIllegalArgument.getMessage());

    arrayValorNulo[0] = "oi";
    assertDoesNotThrow(() -> validador.valida(arrayValorNulo));
  }

  @Test
  void testValidaMetadado() {
    Map<String, String> metadadoNulo = null;
    NullPointerException throwNullPointerException = assertThrows(NullPointerException.class,
        () -> validador.valida(metadadoNulo));
    assertEquals("Metadados não pode ser nulo", throwNullPointerException.getMessage());

    Map<String, String> metadadosValorNulo = new HashMap<String, String>();
    metadadosValorNulo.put("TESTE", null);
    IllegalArgumentException thrownIllegalArgument = assertThrows(IllegalArgumentException.class,
        () -> validador.valida(metadadosValorNulo));
    assertEquals("Nenhum metadado pode ser vazio", thrownIllegalArgument.getMessage());

    metadadosValorNulo.put("TESTE", "OI");
    assertDoesNotThrow(() -> validador.valida(metadadosValorNulo));
  }

  @Test
  void testValidaNumero() {
    IllegalArgumentException thrownIllegalArgument = assertThrows(IllegalArgumentException.class,
        () -> validador.valida(-1));
    assertEquals("Apenas números positivos são válidos para histórico de busca", thrownIllegalArgument.getMessage());
  }
}
