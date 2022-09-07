package com.matheusgr.apresentacao;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.matheusgr.lunr.documento.Documento;
import com.matheusgr.lunr.documento.DocumentoService;

/**
 * Componente para tratamento da lógica de negócio relativa
 * a apresentação de documentos.
 */
public class ApresentacaoService {

  private DocumentoService documentoService;

  /**
   * Inicialização da lógica de serviço.
   * 
   * @param documentoService DocumentoService a ser utilizado pelo
   *                         ApresentacaoService.
   */
  public ApresentacaoService(DocumentoService documentoService) {
    this.documentoService = documentoService;
  }

  /**
   * Realiza a apresentação do documento indicado.
   * 
   * 
   * @param docId1           Documento a ser apresentado.
   * @param tipoApresentacao modo de apresentacao a ser aplicado sobre o
   *                         documento.
   */
  public String apresenta(String docId, String tipoApresentacao) {
    Optional<Documento> docOpt = this.documentoService.recuperaDocumento(docId);
    if (docOpt.isEmpty())
      throw new NoSuchElementException("Documento não existe.");

    Documento doc = docOpt.get();
    String toReturn = "";
    switch (tipoApresentacao.toUpperCase()) {
      case "CAIXA ALTA":
        toReturn = doc.getConteudo().toUpperCase();
        break;

      default:
        throw new IllegalArgumentException("Tipo de apresentação inválida.");
    }
    return toReturn;
  }

  public String apresenta(String docId, String tipoApresentacao, int numeroDeLinhas) {
    if (numeroDeLinhas < 0)
      throw new IllegalArgumentException("Valores negativos não são permitidos.");

    Optional<Documento> docOpt = this.documentoService.recuperaDocumento(docId);
    if (docOpt.isEmpty())
      throw new NoSuchElementException("Documento não existe.");

    Documento doc = docOpt.get();
    String toReturn = "";
    switch (tipoApresentacao.toUpperCase()) {
      case "PRIMEIRAS":
        toReturn = getPrimeirasNLinhasDe(doc, numeroDeLinhas);
        break;
      case "ULTIMAS":
        toReturn = getUltimasNLinhasDe(doc, numeroDeLinhas);
        break;

      default:
        throw new IllegalArgumentException("Tipo de apresentação inválida.");
    }
    return toReturn;

  }

  private String getPrimeirasNLinhasDe(Documento doc, int n) {
    String[] linhas = doc.getConteudo().split("\n");
    String toReturn = "";
    for (int i = 0; i < n; i++) {
      toReturn += linhas[i] + "\n";
    }
    return toReturn;
  }

  private String getUltimasNLinhasDe(Documento doc, int n) {
    String[] linhas = doc.getConteudo().split("\n");
    String toReturn = "";
    for (int i = linhas.length - n; i < linhas.length; i++) {
      toReturn += linhas[i] + "\n";
    }
    return toReturn;
  }

}
