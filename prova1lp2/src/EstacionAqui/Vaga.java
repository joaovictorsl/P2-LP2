package prova1lp2.src.EstacionAqui;

/**
 * Vaga
 *
 * @author João Victor de Souza Lucena
 */
public class Vaga {

  private int numeracao;
  private String endereco;
  private String linkDoGoogleMaps;
  private double area;
  private boolean ocupada;
  private int qntVezesOcupada;

  public Vaga(int numeracao, String endereco, String linkDoGoogleMaps, double area) {
    this.numeracao = numeracao;
    this.endereco = endereco;
    this.linkDoGoogleMaps = linkDoGoogleMaps;
    this.area = area;
    this.ocupada = false;
  }

  public double precoDaVaga(int qntHoras) throws IllegalArgumentException {
    if (qntHoras < 0)
      throw new IllegalArgumentException("Quantidade de horas deve ser maior ou igual a zero.");

    double preco = qntHoras * 3 + 0.1 * area;

    // Truncando.
    preco = Math.floor(preco * 100) / 100;
    return preco;
  }

  public int getQuantVezesOcupada() {
    return qntVezesOcupada;
  }

  public boolean taOcupada() {
    return ocupada;
  }

  public int getNumeracao() {
    return numeracao;
  }

  public String getEndereco() {
    return endereco;
  }

  public double getArea() {
    return area;
  }

  @Override
  public String toString() {
    return numeracao + " - " + endereco + " - " + linkDoGoogleMaps + " - " + (ocupada ? "OCUPADA" : "LIVRE");
  }

  @Override
  public boolean equals(Object o) {
    if (o.getClass() != getClass())
      return false;

    Vaga outra = (Vaga) o;

    if (outra.getEndereco() == endereco && outra.getArea() == area)
      return true;

    return false;
  }

  public void mudarStatus() {
    if (!ocupada)
      qntVezesOcupada++;

    ocupada = !ocupada;
  }

}
