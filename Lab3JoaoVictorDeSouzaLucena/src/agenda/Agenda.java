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
  private static final int TAMANHO_FAVORITOS = 10;

  private Contato[] contatos;
  private Contato[] listaDeFavoritos;

  /**
   * Cria uma agenda.
   */
  public Agenda() {
    this.contatos = new Contato[TAMANHO_AGENDA];
    this.listaDeFavoritos = new Contato[TAMANHO_FAVORITOS];
  }

  /**
   * Acessa a lista de contatos mantida.
   * 
   * @return O array de contatos.
   */
  public Contato[] getContatos() {
    return this.contatos.clone();
  }

  public boolean removerContato(int posicao) {
    Contato paraRemover = contatos[posicao];

    if (paraRemover == null)
      return false;

    contatos[posicao] = null;
    int indexFav = ehFavorito(paraRemover);

    if (indexFav != -1)
      listaDeFavoritos[indexFav] = null;

    return true;
  }

  /**
   * Acessa a lista de favoritos.
   * 
   * @return array de favoritos.
   */
  public Contato[] getListaDeFavoritos() {
    return listaDeFavoritos;
  }

  /**
   * Acessa os dados de um contato específico.
   * 
   * @param posicao Posição do contato na agenda.
   * @return Dados do contato. Null se não há contato na posição.
   */
  public Contato getContato(int posicao) {
    return contatos[posicao - 1];
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
  public boolean cadastraContato(int posicao, String nome, String sobrenome, String telefone) {
    Contato novoContato = new Contato(nome, sobrenome, telefone);

    if (contatoRepetido(novoContato)) {
      return false;
    }

    this.contatos[posicao - 1] = novoContato;
    return true;
  }

  public String adicionaFavorito(int posicao, Contato contato) {
    if (posicao > 10 || posicao < 1) {
      return "POSICAO INVÁLIDA";
    }

    if (contato == null) {
      return "CONTATO INVÁLIDO";
    }

    if (contatoRepetido(contato, listaDeFavoritos)) {
      return "CONTATO JÁ CADASTRADO";
    }

    listaDeFavoritos[posicao - 1] = contato;
    return "CONTATO FAVORITADO NA POSIÇÃO " + posicao + "!";
  }

  public int ehFavorito(Contato contato) {
    for (int i = 0; i < listaDeFavoritos.length; i++) {
      Contato favorito = listaDeFavoritos[i];

      if (contato.equals(favorito))
        return i;
    }
    return -1;
  }

  public void carregaContato(int posicao, String nome, String sobrenome, String telefone) {
    Contato novoContato = new Contato(nome, sobrenome, telefone);
    this.contatos[posicao - 1] = novoContato;
  }

  public boolean contatoRepetido(Contato tempContato) {
    return contatoRepetido(tempContato, contatos);
  }

  private boolean contatoRepetido(Contato tempContato, Contato[] listaDeContato) {
    for (Contato contato : listaDeContato) {
      if (contato != null && contato.equals(tempContato))
        return true;
    }

    return false;
  }

  public void adicionarTag(String[] posicaoContatos, String tag, int posicaoTag) {
    for (String posicaoAsString : posicaoContatos) {
      int index = Integer.parseInt(posicaoAsString) - 1;
      Contato recebeTag = contatos[index];
      recebeTag.setTag(posicaoTag, tag);
    }
  }

}
