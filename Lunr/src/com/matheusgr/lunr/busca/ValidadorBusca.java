package com.matheusgr.lunr.busca;

import java.util.Objects;
import java.util.Map;

import com.matheusgr.lunr.ValidadorPadrao;

/**
 * Classe para validação da busca, suas entidades e parâmetros.
 */
class ValidadorBusca extends ValidadorPadrao {

  /**
   * Valida os termos de busca. Os termos de busca não podem ser objetos nulos
   * e pelo menos um termo deve ser não vazio.
   * 
   * @param termos Termos a serem buscados.
   */
  public void valida(String[] termos) {
    Objects.requireNonNull(termos, "Conteúdo não pode ser nulo");
    for (String t : termos) {
      if (t != null && !t.isBlank()) {
        return;
      }
    }
    throw new IllegalArgumentException("Pelo menos um termo não deve ser vazio");
  }

  /**
   * Valida os metadados de busca. Os metadados de busca não podem ser objetos
   * nulos e cada metadado não pode ter valor vazio.
   * 
   * @param metadados Metadados a serem buscados.
   */
  public void valida(Map<String, String> metadados) {
    Objects.requireNonNull(metadados, "Metadados não pode ser nulo");
    for (String key : metadados.keySet()) {
      if (metadados.get(key) == null || metadados.get(key).isBlank()) {
        throw new IllegalArgumentException("Nenhum metadado pode ser vazio");
      }
    }
  }

  /**
   * Valida o número do histórico de busca. Deve ser positivo.
   * 
   * @param numeroBusca Número de busca a ser validado.
   */
  public void valida(int numeroBusca) {
    if (numeroBusca < 0) {
      throw new IllegalArgumentException("Apenas números positivos são válidos para histórico de busca");
    }
  }

}
