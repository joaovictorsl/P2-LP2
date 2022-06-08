package agenda;

/**
 * Contato
 */
public class Contato {

  private String nome;
  private String sobrenome;
  private String telefone;

  public Contato(String nome, String sobrenome, String telefone) {
    this.nome = nome;
    this.sobrenome = sobrenome;
    this.telefone = telefone;
  }

  public String getNomeCompleto() {
    return nome + " " + sobrenome;
  }

  @Override
  public String toString() {
    String toReturn = "";
    toReturn += nome + "\n";
    toReturn += sobrenome + "\n";
    toReturn += telefone + "\n";
    return toReturn;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Contato))
      return false;

    Contato other = (Contato) obj;
    return other.nome.equals(nome) && other.sobrenome.equals(sobrenome);
  }
}
