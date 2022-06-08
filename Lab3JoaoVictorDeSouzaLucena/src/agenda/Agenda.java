package agenda;

/**
 * Uma agenda que mantém uma lista de contatos com posições. Podem existir 100
 * contatos.
 * 
 * @author nazareno
 *
 */
public class Agenda {

  private static final int TAMANHO_AGENDA = 100;

  private Contato[] contatos;

  /**
   * Cria uma agenda.
   */
  public Agenda() {
    this.contatos = new Contato[TAMANHO_AGENDA];
  }

  /**
   * Acessa a lista de contatos mantida.
   * 
   * @return O array de contatos.
   */
  public Contato[] getContatos() {
    return this.contatos.clone();
  }

  /**
   * Acessa os dados de um contato específico.
   * 
   * @param posicao Posição do contato na agenda.
   * @return Dados do contato. Null se não há contato na posição.
   */
  public Contato getContato(int posicao) {
    return contatos[posicao];
  }

  /**
   * Cadastra um contato em uma posição. Um cadastro em uma posição que já existe
   * sobrescreve o anterior.
   * 
   * @param posicao   Posição do contato.
   * @param nome      Nome do contato.
   * @param sobrenome Sobrenome do contato.
   * @param telefone  Telefone do contato.
   */
  public void cadastraContato(int posicao, String nome, String sobrenome, String telefone) {
    Contato novoContato = new Contato(nome, sobrenome, telefone);
    this.contatos[posicao] = novoContato;
  }

  public boolean contatoRepetido(String nome, String sobrenome) {
    Contato tempContato = new Contato(nome, sobrenome, "12345678");

    for (Contato contato : contatos) {
      if (contato.equals(tempContato))
        return true;
    }

    return false;
  }

}
