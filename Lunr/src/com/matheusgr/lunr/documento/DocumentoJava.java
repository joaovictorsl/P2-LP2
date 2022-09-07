package com.matheusgr.lunr.documento;

import java.util.Map;

import biblitex.TransformaTexto;

/**
 * Documento base java. As palavras-chave da linguagem ainda são preservadas
 * (como public, private, etc), mas elimina-se documentação e comentários.
 */
class DocumentoJava extends Documento {

  /**
   * Cria o DocumentoJava, extraindo o texo base.
   * 
   * @param id       ID do documento a ser criado.
   * @param conteudo Código java original.
   */
  public DocumentoJava(String id, String conteudo) {
    super(id, conteudo);
    var transformaTexto = new TransformaTexto();
    var txt = transformaTexto.transforma(TransformaTexto.Algoritmos.java, conteudo);
    this.limpo = transformaTexto.transforma(TransformaTexto.Algoritmos.clean, txt).strip();
  }

  @Override
  public Map<String, String> getMetadados() {
    this.metadados = addExtractedDataTo(super.getMetadados());
    this.metadados.put("TIPO", "" + "java");
    return this.metadados;
  }

  /*
   * Metadados particulares de java: número de imports e autor da classe.
   */
  private Map<String, String> addExtractedDataTo(Map<String, String> metadados) {
    metadados.put("IMPORTS", "" + ((this.limpo.length() - this.limpo.replaceAll("import ", "").length()) / 7));
    metadados.put("AUTHOR", "" + (this.conteudo.indexOf("@author") != -1 ? "TRUE" : ""));
    return metadados;
  }

}
