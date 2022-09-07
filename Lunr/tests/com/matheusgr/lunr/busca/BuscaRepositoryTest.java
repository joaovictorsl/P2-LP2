package com.matheusgr.lunr.busca;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.matheusgr.lunr.BaseTest;
import com.matheusgr.lunr.documento.DocumentoDTO;

public class BuscaRepositoryTest extends BaseTest {

  @Test
  void testAdicionaRecuperaBusca() {
    BuscaRepository br = new BuscaRepository();
    Map<String, String> metadados = new HashMap<String, String>();
    metadados.put("TIPO", "java");
    Busca busca = new BuscaAvancada(metadados);
    br.adicionaBusca(busca, new DocumentoDTO[] { documentoController.recuperarDocumento(JAVA_ID).get() });
    String[][] resultado = br.recuperar(0).debug();
    assertArrayEquals(new String[][] { { "METADADO 1", "TIPO", "java" } }, resultado);

    IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
        () -> br.recuperar(1));
    assertEquals("Histórico inexistente: 1", thrown.getMessage());
  }
}
