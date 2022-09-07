package petadoption;

/**
 * Dog
 */
public class Dog {

  private String nome;
  private String nomeDoTutor;
  private double quantRacao;

  public Dog(String nome, String nomeDoTutor, double quantRacao, boolean restricoes) {
    this.nome = nome;
    this.nomeDoTutor = nomeDoTutor;
    this.quantRacao = restricoes ? quantRacao * 2 : quantRacao;
  }

  public double getQuantRacao() {
    return quantRacao;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj.getClass() != getClass())
      return false;

    Dog dog = (Dog) obj;

    if (dog.nome == nome && dog.nomeDoTutor == nomeDoTutor)
      return true;
    return false;
  }

  @Override
  public String toString() {
    return "Dog [" + "nome=" + nome + ", tutor=" + nomeDoTutor + "]";
  }

}