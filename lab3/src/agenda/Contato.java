package agenda;

/**
 * Contato possui nome, sobrenome, telefone e uma lista de tags.
 * 
 * @author João Victor de Souza Lucena
 */
public class Contato {

  // Nome do contato
  private String nome;
  // Sobrenome do contato
  private String sobrenome;
  // Telefone do contato
  private String telefone;
  // Tags que o contato possui
  private String[] tags;

  /**
   * Construtor do contato. A lista de tags possui no máximo 5 tags.
   * 
   * @param nome      nome do contato
   * @param sobrenome sobrenome do contato
   * @param telefone  telefone do contato
   */
  public Contato(String nome, String sobrenome, String telefone) throws IllegalArgumentException {
    if (nome.isBlank() || telefone.isBlank()) {
      throw new IllegalArgumentException("CONTATO INVÁLIDO!\n");
    }

    this.nome = nome;
    this.sobrenome = sobrenome;
    this.telefone = telefone;
    this.tags = new String[5];
  }

  /**
   * @return concatenação do nome + sobrenome do contato.
   */
  public String getNomeCompleto() {
    return nome + " " + sobrenome;
  }

  /**
   * @return telefone do contato.
   */
  public String getTelefone() {
    return telefone;
  }

  /**
   * Define uma tag na lista de tags do contato no index posicao - 1.
   * 
   * @param posicao  posição em que a tag será adicionada na lista de tags.
   * @param tagToAdd a tag em si para ser adicionada.
   */
  public void setTag(int posicao, String tagToAdd) throws IllegalArgumentException {
    if (posicao < 1 || posicao > 5)
      throw new IllegalArgumentException("POSIÇÃO INVÁLIDA!\n");

    tags[posicao - 1] = tagToAdd;
  }

  /**
   * @return a lista de tags.
   */
  public String[] getTags() {
    return tags;
  }

  @Override
  public String toString() {
    String toReturn = "";
    toReturn += nome + "\n";
    toReturn += sobrenome + "\n";
    toReturn += telefone + "\n";
    return toReturn;
  }

  /**
   * @return true se nome e sobrenome dos contatos forem iguais, falso em caso
   *         contrário.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj.getClass() != getClass())
      return false;

    Contato other = (Contato) obj;
    String outroNomeCompleto = other.getNomeCompleto();
    return getNomeCompleto().equals(outroNomeCompleto);
  }
}
