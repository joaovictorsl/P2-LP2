package com.matheusgr.similaridade;

import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import com.matheusgr.lunr.documento.Documento;
import com.matheusgr.lunr.documento.DocumentoService;

/**
 * Componente para tratamento da lógica de negócio relativa a similaridade.
 */
public class SimilaridadeService {

  private DocumentoService documentoService;

  /**
   * Inicialização da lógica de serviço.
   * 
   * @param documentoService DocumentoService a ser utilizado pelo
   *                         SimilaridadeService.
   */
  public SimilaridadeService(DocumentoService documentoService) {
    this.documentoService = documentoService;
  }

  /**
   * Calcula e retorna a similaridade.
   * 
   * Para o cálculo da similaridade:
   * <ul>
   * <li>Pega o documento 1</li>
   * <li>Pega o documento 2</li>
   * <li>Pega os termos do documento 1 e coloca em um conjunto (Termos1)</li>
   * <li>Pega os termos do documento 2 e coloca em um conjunto (Termos2)</li>
   * <li>Calcula a interseção entre Termos1 e Termos2 (Inters)</li>
   * <li>Calcula a união entre Termos1 e Termos2 (Uniao)</li>
   * <li>A similaridade é o tamanho de Inters sobre o tamanho do conjunto
   * Uniao</li>
   * </ul>
   * 
   * @param docId1 Documento 1.
   * @param docId2 Documento 2.
   * @return Valor de similaridade (entre 0 e 1, inclusives) representando a
   *         semelhança entre os documentos.
   */
  public double similaridade(String docId1, String docId2) {
    Optional<Documento> docOpt1 = documentoService.recuperaDocumento(docId1);
    Optional<Documento> docOpt2 = documentoService.recuperaDocumento(docId2);
    Documento doc1, doc2;

    if (docOpt1.isEmpty() || docOpt2.isEmpty()) {
      throw new NoSuchElementException("Documento não existe.");
    } else {
      doc1 = docOpt1.get();
      doc2 = docOpt2.get();
    }

    Set<String> docWordSet1 = new HashSet<>(Arrays.asList(doc1.getTermos()));
    Set<String> docWordSet2 = new HashSet<>(Arrays.asList(doc2.getTermos()));
    Set<String> uniao = new HashSet<>(docWordSet1);
    uniao.addAll(docWordSet2);

    Set<String> intersecao = new HashSet<>(docWordSet1);
    intersecao.retainAll(docWordSet2);

    return (double) intersecao.size() / uniao.size();
  }

}
