package com.matheusgr.lunr.documento;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import biblitex.TransformaTexto;

/**
 * Documento representa um conjunto textual que será indexado pelo Lunr.
 */
public abstract class Documento {

  protected String id;
  protected String conteudo;
  protected String limpo;
  protected Map<String, String> metadados;
  protected String[] termos;

  /**
   * Construtor base para documentos.
   */
  public Documento(String id, String conteudo) {
    this.id = id;
    this.conteudo = conteudo;
    this.limpo = (new TransformaTexto()).transforma(TransformaTexto.Algoritmos.clean, conteudo).strip();
  }

  /**
   * Retorna a quantidade de texto útil, sendo definido como a quantidade de
   * caracteres úteis (sem caracteres estranhos e sem espaços) sobre o total de
   * caracteres original (incluindo espaços).
   * 
   * @return Percentual de texto útil (entre 0 e 1, inclusives).
   */
  public double metricaTextoUtil() {
    long extractedSize = (new TransformaTexto()).transforma(TransformaTexto.Algoritmos.cleanSpaces, this.limpo)
        .length();
    return (1.0 * extractedSize) / this.conteudo.length();
  }

  /**
   * Retorna a identificação do documento. A documentação é definida pelo próprio
   * documento e é uma String sem formatação específica. Todo documento gerado
   * pelo Lunr começa com o símbolo "_". O identificador não pode ser vazio ou
   * nulo.
   * 
   * @return Identificação do documento.
   * @throws NullPointerException Caso o ID seja nulo.
   */
  public String getId() {
    return this.id;
  }

  /**
   * Retorna os termos do texto em formato de String. Não podem existir termos
   * vazios (só de espaços, tabulações ou string vazia).
   * 
   * @return Array de termos extraídos do documento.
   */
  public String[] getTermos() {
    if (this.termos == null) {
      this.termos = (new TransformaTexto()).transforma(TransformaTexto.Algoritmos.cleanLines, this.limpo).split(" ");
      Arrays.sort(this.termos);
    }
    return this.termos;
  }

  /**
   * Retorna o conteúdo do documento.
   * 
   * @return Conteúdo do documento.
   */
  public String getConteudo() {
    return this.conteudo;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (this == obj)
      return true;
    if (getClass() != obj.getClass())
      return false;
    Documento other = (Documento) obj;
    return Objects.equals(id, other.id);
  }

  @Override
  public String toString() {
    return "===" + this.id + System.lineSeparator() + this.limpo;
  }

  /**
   * Retorna metadados referente ao documento. Detalhes como seu tipo, autor, ou
   * características próprias do documento.
   * 
   * Idealmente, todo metadado terá como características:
   * 
   * LINHAS, TAMANHO (número de caracteres), METADATADATE (hora atual do sistema
   * na criação dos metadados, em ms), TIPO.
   * 
   * @return Mapa com os metadados descrito em forma textual.
   */
  public Map<String, String> getMetadados() {
    if (this.metadados != null) {
      return this.metadados;
    }
    this.metadados = new HashMap<String, String>();
    this.metadados.put("LINHAS", "" + this.conteudo.chars().filter((value) -> '\n' == value).count());
    this.metadados.put("TAMANHO", "" + this.limpo.length());
    this.metadados.put("METADATADATE", "" + System.currentTimeMillis());
    return this.metadados;
  }

}
