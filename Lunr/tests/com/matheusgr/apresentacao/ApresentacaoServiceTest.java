package com.matheusgr.apresentacao;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import com.matheusgr.lunr.BaseTest;

public class ApresentacaoServiceTest extends BaseTest {
  @Test
  void testApresentaEmCaixaAlta() {
    String resultado = apresentacaoController.apresenta(JAVA_ID, "CAIXA ALTA");
    String conteudoJava = documentoController.recuperarDocumento(JAVA_ID).get().getConteudo();
    assertEquals(conteudoJava.toUpperCase(), resultado);
  }

  @Test
  void testApresentacaoInvalida() {
    IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
        () -> apresentacaoController.apresenta(JAVA_ID, "INVÁLIDO"));
    assertEquals("Tipo de apresentação inválida.", thrown.getMessage());

    thrown = assertThrows(IllegalArgumentException.class,
        () -> apresentacaoController.apresenta(JAVA_ID, "INVÁLIDO", 4));
    assertEquals("Tipo de apresentação inválida.", thrown.getMessage());
  }

  @Test
  void testApresentaPrimeirasLinhas() {
    String resultado = apresentacaoController.apresenta(HTML_ID, "PRIMEIRAS", 4);
    assertEquals("<!doctype html>\r\n"
        + "<html>\r\n"
        + "<head>\r\n"
        + "    <title>Example Domain</title>\r\n", resultado);
  }

  @Test
  void testApresentaUltimasLinhas() {
    String resultado = apresentacaoController.apresenta(HTML_ID, "ULTIMAS", 4);
    assertEquals("    <p><a href=\"https://www.iana.org/domains/example\">More information...</a></p>\r\n"
        + "</div>\r\n"
        + "</body>\r\n"
        + "</html>\r\n", resultado);
  }

  @Test
  void testNumeroDeLinhasInvalido() {
    // Valores negativos são inválidos.
    IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
        () -> apresentacaoController.apresenta(HTML_ID, "ULTIMAS", -1));
    assertEquals("Valores negativos não são permitidos.", thrown.getMessage());
  }

  @Test
  void testRelacionadosComID() {
    // Inexistente
    NoSuchElementException thrown = assertThrows(NoSuchElementException.class,
        () -> apresentacaoController.apresenta("NãoExiste", "CAIXA ALTA"));
    assertEquals("Documento não existe.", thrown.getMessage());
    // Inválido
    IllegalArgumentException thrown2 = assertThrows(IllegalArgumentException.class,
        () -> apresentacaoController.apresenta("", "ULTIMAS"));
    assertEquals("ID não pode ser vazio", thrown2.getMessage());
    // Nulo
    NullPointerException thrown3 = assertThrows(NullPointerException.class,
        () -> apresentacaoController.apresenta(null, "PRIMEIRAS"));
    assertEquals("ID não pode ser nulo", thrown3.getMessage());
  }
}
