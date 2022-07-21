package com.matheusgr.lunr.busca;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.matheusgr.lunr.documento.Documento;
import com.matheusgr.lunr.documento.DocumentoService;

/**
 * BuscaAvancada realiza uma operação de busca a partir de metadados.
 * 
 * Dado os metadados, deve se buscar por tais documentos e retornar apenas os
 * documentos que satisfizerem todos o metadados.
 * 
 * @author João Victor de Souza Lucena
 */
class BuscaAvancada implements Busca {

  private Map<String, String> metadados;

  /**
   * Construtor padrão com os metadados a serem encontrados.
   * 
   * @param metadados Metadados a serem pesquisados.
   * 
   */
  public BuscaAvancada(Map<String, String> metadados) {
    (new ValidadorBusca()).valida(metadados);
    this.metadados = metadados;
  }

  /**
   * Realiza a busca a partir da consulta ao DocumentoService.
   * 
   * O DocumentoService realiza apenas operações simples de busca, mas sem
   * ordenação ou tratamento da lógica de relevância.
   * 
   * @param ds DocumentoService a ser utilizado para busca.
   * @return Map com documento como chave e inteiro como valor representado a sua
   *         relevância, que no caso são todos iguais a 1, pois nesse tipo de
   *         busca não
   *         há ordenação entre os documentos.
   */
  public Map<Documento, Integer> busca(DocumentoService ds) {
    Map<Documento, Integer> respostaDocumento = new HashMap<Documento, Integer>();
    for (Documento d : ds.busca(metadados)) {
      respostaDocumento.put(d, 1);
    }
    return respostaDocumento;
  }

  /**
   * Descreve uma consulta através dos metadados utilizados nela.
   * 
   * @return Descrição da busca, onde cada linha representa um parâmetro de busca
   *         e as colunas representam um detelhamento de cada parâmetro.
   */
  public String[][] descreveConsulta() {
    String[][] resultado = new String[this.metadados.size()][];
    Iterator<String> keyIterator = this.metadados.keySet().iterator();

    for (int i = 0; i < resultado.length; i++) {
      String key = keyIterator.next();
      resultado[i] = new String[] { "METADADO " + (i + 1), key, this.metadados.get(key) };
    }
    return resultado;
  }

}
