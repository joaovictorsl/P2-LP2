package EstacionAqui;

/**
 * Vaga
 */
public class Vaga {

  private String endereco;
  private String linkDoGoogleMaps;
  private int numeracao;
  private double area;
  private boolean ocupada;

  public Vaga(int numeracao, String endereco, String linkDoGoogleMaps, double area) {
    this.numeracao = numeracao;
    this.endereco = endereco;
    this.linkDoGoogleMaps = linkDoGoogleMaps;
    this.area = area;
    this.ocupada = false;
  }

  public double precoDaVaga(int qntHoras) throws IllegalArgumentException {
    if (qntHoras <= 0)
      throw new IllegalArgumentException("Quantidade de horas deve ser positivo.");

    double preco = qntHoras * 3 + 0.1 * area;

    // Truncando.
    preco = Math.floor(preco * 100) / 100;
    return preco;
  }

  public boolean taOcupada() {
    return ocupada;
  }

  public int getNumeracao() {
    return numeracao;
  }

  @Override
  public String toString() {
    return numeracao + " - " + endereco + " - " + linkDoGoogleMaps + " - " + (ocupada ? "OCUPADA" : "LIVRE");
  }

  public void mudarStatus() {
    ocupada = !ocupada;
  }

}