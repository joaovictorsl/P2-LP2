package com.matheusgr.lunr.documento;

import java.util.Map;

import biblitex.TransformaTexto;

/**
 * Documento de texto simples. Não precisa de tratamento complexos nem tem
 * metadados próprios.
 */
class DocumentoTexto extends Documento {

  /**
   * Construtor padrão do documento de texto.
   * 
   * @param id       ID do documento.
   * @param conteudo Conteúdo do documento.
   */
  public DocumentoTexto(String id, String conteudo) {
    super(id, conteudo);
    this.limpo = (new TransformaTexto()).transforma(TransformaTexto.Algoritmos.clean, conteudo).strip();
  }

  @Override
  public Map<String, String> getMetadados() {
    this.metadados = super.getMetadados();
    this.metadados.put("TIPO", "" + "txt");
    return this.metadados;
  }

}
