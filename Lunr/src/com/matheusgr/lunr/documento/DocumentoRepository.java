package com.matheusgr.lunr.documento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Repositório de documentos. O repositório pode ter opreações simples de busca,
 * mas a lógica de ranking, limitação e ordenação deve ficar em outra entidade.
 * 
 * O ID de um documento é único.
 */
class DocumentoRepository {

  private List<Documento> documentos;
  private ValidadorDocumentos validador;

  /**
   * Construção padrão do repositório de documentos.
   */
  public DocumentoRepository() {
    this.documentos = new ArrayList<Documento>();
    this.validador = new ValidadorDocumentos();
  }

  /**
   * Adiciona o documento. O documento é validado para garantir a consistência do
   * documento (sem termos e id vazios).
   * 
   * @param d Documento a ser adicionado.
   */
  public void adiciona(Documento d) {
    this.validador.validacao(d.getId(), d.getTermos());
    this.documentos.add(d);
  }

  /**
   * Recupera um documento do repositório.
   * 
   * @param id ID do documento.
   * @return Documento, caso exista.
   */
  public Optional<Documento> recupera(String id) {
    this.validador.validacao(id);

    for (int i = 0; i < this.documentos.size(); i++) {
      if (id.equals(this.documentos.get(i).getId())) {
        return Optional.of(this.documentos.get(i));
      }
    }

    return Optional.empty();
  }

  /**
   * Retorna o total de documentos cadastrados.
   * 
   * @return O total de documentos cadastrados.
   */
  public int totalDocumentos() {
    return this.documentos.size();
  }

  /**
   * Realiza uma busca pelos termos.
   * 
   * @param termo Termo a ser buscado.
   * @return Conjunto de documentos com o termo.
   */
  public Set<Documento> busca(String termo) {
    return this.documentos.stream()
        .filter((documento) -> Arrays.binarySearch(documento.getTermos(), termo) >= 0)
        .collect(Collectors.toSet());
  }

  /**
   * Realiza uma busca pelos metadados.
   * 
   * @param metadados metadados a serem buscados.
   * @return Conjunto de documentos com os metadados.
   */
  public Set<Documento> busca(Map<String, String> metadados) {
    return this.documentos.stream()
        .filter((documento) -> {
          Map<String, String> metadadosDoDocumento = documento.getMetadados();

          for (String key : metadados.keySet()) {
            if (!metadadosDoDocumento.get(key).equals(metadados.get(key)))
              return false;
          }
          return true;
        })
        .collect(Collectors.toSet());
  }

}
