package EstacionAqui;

/**
 * Estacionamento
 */
public class Estacionamento {

  private Vaga[] vagas;
  private int nextId;

  public Estacionamento() {
    vagas = new Vaga[100];
    nextId = 0;
  }

  public boolean addVagas(String endereco, double area) {
    return addVagas(endereco, "https://", area);
  }

  public boolean addVagas(String endereco, String linkDoGoogleMaps, double area) {
    if (nextId > 99)
      return false;

    vagas[nextId] = new Vaga(nextId, endereco, linkDoGoogleMaps, area);
    nextId++;
    return true;
  }

  public void mudarStatusVaga(int index) throws IllegalArgumentException {
    if (!indexValido(index))
      throw new IllegalArgumentException("Index inválido.");
    System.out.println(index);
    vagas[index].mudarStatus();
  }

  public double simularPrecoDaVaga(int index, int horas) throws IllegalArgumentException {
    if (!indexValido(index))
      throw new IllegalArgumentException("Index inválido.");

    return vagas[index].precoDaVaga(horas);
  }

  private boolean indexValido(int index) {
    return index >= 0 && index <= 99;
  }

  public int informeVagaLivre() {
    for (Vaga vaga : vagas) {
      if (!vaga.taOcupada())
        return vaga.getNumeracao();
    }
    return -1;
  }

  public String listaVagas() {
    String toReturn = "";

    for (Vaga vaga : vagas) {
      if (vaga == null)
        continue;

      toReturn += vaga + "\n";
    }

    return toReturn;
  }

}