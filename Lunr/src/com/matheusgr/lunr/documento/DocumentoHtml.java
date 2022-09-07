package com.matheusgr.lunr.documento;

import java.util.Map;

import biblitex.TransformaTexto;

/**
 * DocumentoHTML representa e extrai dados de um HTML.
 * 
 * Os termos extraídos são obtidos dos textos dos nós do HTML. São ignorados
 * nome de tags ou de propriedades. Por não ser um texto bem formulado, limpa-se
 * da melhor forma possível o HTML.
 * 
 * Os metadados são obtidos de características do documento, mas de detalhes
 * descritos na tag HEAD.
 */
class DocumentoHtml extends Documento {

  private static final String HEAD_METADADO = "HEAD";

  /**
   * Construtor padrão. Realiza o processamento de extração do HTML.
   * 
   * @param id       ID do documento a ser criado.
   * @param conteudo HTML do documento a ser criado.
   */
  public DocumentoHtml(String id, String conteudo) {
    super(id, conteudo);
    var transformaTexto = new TransformaTexto();
    var txt = transformaTexto.transforma(TransformaTexto.Algoritmos.html, conteudo);
    this.limpo = transformaTexto.transforma(TransformaTexto.Algoritmos.clean, txt).strip();
  }

  @Override
  public String toString() {
    return "===" + this.id + System.lineSeparator() + this.getMetadados().get(HEAD_METADADO)
        + System.lineSeparator() + "===" + this.limpo;
  }

  @Override
  public Map<String, String> getMetadados() {
    this.metadados = addExtractedHeadTo(super.getMetadados());
    this.metadados.put("TIPO", "" + "html");
    return this.metadados;
  }

  /*
   * Os metadados do HTML incluem, especificamente:
   * - Quantidade de tags, estimada a partir da quantidade de símbolos <
   * - Todo o corpo da tag <head>
   */
  private Map<String, String> addExtractedHeadTo(Map<String, String> metadados) {
    metadados.put("BRUTE_TAGS", "" + this.conteudo.chars().filter((value) -> '<' == value).count());
    String meta = "";
    int headStart = this.conteudo.toLowerCase().indexOf("<head>");
    if (headStart != -1) {
      int headEnd = this.conteudo.toLowerCase().indexOf("</head>");
      if (headEnd != -1) {
        meta = this.conteudo.substring(headStart, headEnd);
      } else {
        meta = "Incomplete head.";
      }
    } else {
      meta = "No head found.";
    }
    metadados.put(HEAD_METADADO, meta);
    return metadados;
  }
}
