package com.matheusgr.similaridade;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import com.matheusgr.lunr.BaseTest;

class SimilaridadeServiceTest extends BaseTest {
  @Test
  void testSimilaridade() {
    double similaridade = similaridadeController.similaridade(TEXTO1_ID, TEXTO2_ID);
    assertEquals(0.5, similaridade);
  }

  @Test
  void testNadaSimilar() {
    double similaridade = similaridadeController.similaridade(TEXTO1_ID, TEXTO3_ID);
    assertEquals(0, similaridade);
  }

  @Test
  void testTudoSimilar() {
    double similaridade = similaridadeController.similaridade(TEXTO3_ID, TEXTO3_ID);
    assertEquals(1, similaridade);
  }

  @Test
  void testRelacionadosComID() {
    // Inexistente
    NoSuchElementException thrown = assertThrows(NoSuchElementException.class,
        () -> similaridadeController.similaridade("NãoExiste", TEXTO2_ID));
    assertEquals("Documento não existe.", thrown.getMessage());
    // Inválido
    IllegalArgumentException thrown2 = assertThrows(IllegalArgumentException.class,
        () -> similaridadeController.similaridade("", TEXTO2_ID));
    assertEquals("ID não pode ser vazio", thrown2.getMessage());
    // Nulo
    NullPointerException thrown3 = assertThrows(NullPointerException.class,
        () -> similaridadeController.similaridade(null, TEXTO2_ID));
    assertEquals("ID não pode ser nulo", thrown3.getMessage());
  }
}
