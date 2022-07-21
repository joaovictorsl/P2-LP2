package com.matheusgr.similaridade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.matheusgr.lunr.BaseTest;

class SimilaridadeServiceTest extends BaseTest {
  @Test
  void testSimilaridade() {
    double similaridade = similaridadeController.similaridade(TEXTO1_ID, TEXTO2_ID);
    assertEquals(0.5, similaridade);
  }
}
