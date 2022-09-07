package com.matheusgr.lunr.documento;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.matheusgr.lunr.BaseTest;

class DocumentoTest extends BaseTest {

  @Test
  void testAusente() {
    var documentoOpt = this.documentoController.recuperarDocumento("IDNaoExistente");
    assertTrue(documentoOpt.isEmpty());
  }

  @Test
  void testHTML() {
    var documentoOpt = this.documentoController.recuperarDocumento(HTML_ID);
    assertTrue(documentoOpt.isPresent());
    var doc = documentoOpt.get();
    assertEquals(HTML_ID, doc.getId(), "ID padrão do HTML");
    assertEquals("158", "" + doc.getTermos().length);
    assertEquals("46", doc.getMetadados().get("LINHAS"));
    assertEquals("html", doc.getMetadados().get("TIPO"));
    assertEquals("24", doc.getMetadados().get("BRUTE_TAGS"));
    assertTrue(doc.getMetadados().get("HEAD").length() > 10);
    assertEquals(0.59, doc.metricaTextoUtil(), 0.01);
  }

  @Test
  void testJava() {
    var documentoOpt = this.documentoController.recuperarDocumento(JAVA_ID);
    assertTrue(documentoOpt.isPresent());
    var doc = documentoOpt.get();
    assertEquals(JAVA_ID, doc.getId(), "ID padrão do Java");
    assertEquals(110, doc.getTermos().length);
    assertEquals("43", doc.getMetadados().get("LINHAS"));
    assertEquals("java", doc.getMetadados().get("TIPO"));
    assertEquals("10", doc.getMetadados().get("IMPORTS"));
    assertEquals("TRUE", doc.getMetadados().get("AUTHOR"));
    assertEquals(0.54, doc.metricaTextoUtil(), 0.01);
  }

  @Test
  void testTexto1() {
    var documentoOpt = this.documentoController.recuperarDocumento(TEXTO1_ID);
    assertTrue(documentoOpt.isPresent());
    var doc = documentoOpt.get();
    assertEquals(TEXTO1_ID, doc.getId(), "ID padrão do Texto");
    assertEquals(8, doc.getTermos().length, "Tamanho de 107 termos");
    assertArrayEquals(new String[] { "DUAS", "apenas", "arquivo", "linhas", "simples", "texto", "um", "use" },
        doc.getTermos());
    assertEquals(0.80, doc.metricaTextoUtil(), 0.01);
  }

  @Test
  void testEquals() {
    var documentoOpt = documentoController.recuperarDocumento(HTML_ID);
    assertTrue(documentoOpt.isPresent());
    var doc = documentoOpt.get();
    assertTrue(doc.equals(doc));
    var sameHtmlDoc = documentoController.recuperarDocumento(HTML_ID).get();
    assertTrue(doc.equals(sameHtmlDoc));
  }

  @Test
  void testConcatena() {
    String conteudoEsperado = "um arquivo! texto simples.\r\nuse DUAS linhas apenas.um arquivo! texto simples.\r\nuse TRÊS linhas agora.\r\nMAIS AVANÇO!";
    String idEsperado = "_MERGE789|ABC";
    String idNovo = documentoController.concatenaDocumentos(TEXTO1_ID, TEXTO2_ID);
    assertEquals(idEsperado, idNovo);
    assertEquals(conteudoEsperado, documentoController.recuperarDocumento(idEsperado).get().getConteudo());
  }

  @Test
  void testTotalDocumentos() {
    assertEquals(5, documentoController.totalDocumentos());
    documentoController.adicionaDocumentoTxt("novoDocumentoTesteTotalDocumentos",
        "Testando total de documentos, antes era 5 agr deve ser 6.");
    assertEquals(6, documentoController.totalDocumentos());
  }

  @Test
  void testSumariza() {
    Set<String> setResultado = new HashSet<String>();
    setResultado.add("testando");
    setResultado.add("repetidas");
    setResultado.add("palavras");
    setResultado.add("feijão");
    setResultado.add("abobrinha");
    assertEquals(setResultado,
        Stream.of(documentoController.sumariza(TEXTO3_ID)).collect(Collectors.toSet()));
  }
}
