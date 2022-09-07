package com.matheusgr.lunr.documento;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DocumentoRepositoryTest {

  private DocumentoRepository dr;

  @BeforeEach
  public void setUp() {
    this.dr = new DocumentoRepository();
  }

  @Test
  void testAdiciona() {
    assertEquals(0, this.dr.totalDocumentos());
    this.dr.adiciona(new DocumentoTexto("teste", "conteudo"));
    assertEquals(1, this.dr.totalDocumentos());
  }

  @Test
  void testBusca() {
    DocumentoTexto doc1 = new DocumentoTexto("peste", "mundo fundo");
    DocumentoTexto doc2 = new DocumentoTexto("teste", "conteudo fundo");
    this.dr.adiciona(doc1);
    this.dr.adiciona(doc2);
    Set<Documento> esperado = new HashSet<Documento>();
    esperado.add(doc1);
    assertEquals(esperado, this.dr.busca("mundo"));
    esperado.add(doc2);
    assertEquals(esperado, this.dr.busca("fundo"));
    Map<String, String> metadados = new HashMap<String, String>();
    metadados.put("TIPO", "txt");
    assertEquals(esperado, this.dr.busca(metadados));
  }

  @Test
  void testRecupera() {
    DocumentoTexto doc1 = new DocumentoTexto("peste", "mundo fundo");
    this.dr.adiciona(doc1);
    assertEquals(doc1, this.dr.recupera("peste").get());
  }
}
