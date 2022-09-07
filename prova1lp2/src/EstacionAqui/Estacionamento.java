package prova1lp2.src.EstacionAqui;

/**
 * Estacionamento
 *
 * @author João Victor de Souza Lucena
 */
public class Estacionamento {

  private Vaga[] vagas;
  private int proximoId;
  private Comentario[] comentarios;

  public Estacionamento() {
    vagas = new Vaga[100];
    comentarios = new Comentario[5];
    proximoId = 0;
  }

  public boolean addVagas(String endereco, double area) {
    return addVagas(endereco, "https://", area);
  }

  public boolean addVagas(String endereco, String linkDoGoogleMaps, double area) {
    if (proximoId == vagas.length)
      return false;

    vagas[proximoId] = new Vaga(proximoId, endereco, linkDoGoogleMaps, area);
    proximoId++;
    return true;
  }

  public void mudarStatusVaga(int index) throws IllegalArgumentException {
    if (!vagaValida(index))
      throw new IllegalArgumentException("Vaga inválida.");

    vagas[index].mudarStatus();
  }

  public double simularPrecoDaVaga(int index, int horas) throws IllegalArgumentException {
    if (!vagaValida(index))
      throw new IllegalArgumentException("Vaga inválida.");

    return vagas[index].precoDaVaga(horas);
  }

  private boolean vagaValida(int index) {
    return (index >= 0 && index < vagas.length) && (vagas[index] != null);
  }

  public int informeVagaLivre() {
    for (Vaga vaga : vagas) {
      if (vaga != null && !vaga.taOcupada())
        return vaga.getNumeracao();
    }

    return -1;
  }

  public int informeVagaLivre(String endereco, double area) {
    Vaga tempVaga = new Vaga(0, endereco, "https://", area);

    for (Vaga vaga : vagas) {
      if (tempVaga.equals(vaga))
        return vaga.getNumeracao();
    }

    return -1;
  }

  public void adicionarComentario(String autor, String mensagem) {
    Comentario novoComentario = new Comentario(autor, mensagem);

    for (int i = comentarios.length - 1; i > 0; i--) {
      comentarios[i] = comentarios[i - 1];
    }

    comentarios[0] = novoComentario;
  }

  public String listaComentarios() {
    String toReturn = "";

    for (Comentario comentario : comentarios) {
      if (comentario != null)
        toReturn += comentario + "\n";
    }

    return toReturn;
  }

  public String relatorioDeVagas() {
    String toReturn = "";

    for (Vaga vaga : vagas) {
      if (vaga != null)
        toReturn += "Vaga " + vaga.getNumeracao() + " - " + vaga.getQuantVezesOcupada() + "\n";
    }

    return toReturn;
  }

  public String listaVagas() {
    String toReturn = "";

    for (Vaga vaga : vagas) {
      if (vaga != null)
        toReturn += vaga + "\n";
    }

    return toReturn;
  }

}