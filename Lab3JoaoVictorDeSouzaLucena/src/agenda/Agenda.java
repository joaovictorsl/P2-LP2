package agenda;

/**
 * Uma agenda que mantém uma lista de contatos com posições. Podem existir 100
 * contatos.
 * 
 * @author nazareno
 *
 */
public class Agenda {

  // Tamanho máximo da agenda.
  private static final int TAMANHO_AGENDA = 100;
  // Tamanho máximo da lista de favoritos da agenda.
  private static final int TAMANHO_FAVORITOS = 10;

  // Contatos da agenda.
  private Contato[] contatos;
  // Lista de contatos favoritos da agenda.
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

  /**
   * Remove um contato da agenda (o substitui por null) no index posicao - 1.
   * 
   * @param posicao posicao do contato no array de contatos da agenda.
   * @return true se funcionar, falso se falhar.
   */
  public boolean removerContato(int posicao) throws IllegalArgumentException {
    if (posicao < 1 || posicao > 100)
      throw new IllegalArgumentException("POSIÇÃO INVÁLIDA!\n");

    Contato paraRemover = contatos[posicao - 1];

    if (paraRemover == null)
      return false;

    contatos[posicao - 1] = null;
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
  public Contato getContato(int posicao) throws IllegalArgumentException {
    if (posicao < 1 || posicao > 100)
      throw new IllegalArgumentException("POSIÇÃO INVÁLIDA!\n");

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
  public String cadastraContato(int posicao, String nome, String sobrenome, String telefone) {
    if (posicao < 1 || posicao > 100)
      return "POSIÇÃO INVÁLIDA!\n";

    try {
      Contato novoContato = new Contato(nome, sobrenome, telefone);

      if (contatoRepetido(novoContato)) {
        return "CONTATO JÁ CADASTRADO\n";
      }

      this.contatos[posicao - 1] = novoContato;
      return "CADASTRO REALIZADO\n";

    } catch (IllegalArgumentException e) {
      return e.getLocalizedMessage();
    }
  }

  /**
   * Adiciona um contato à listaDeFavoritos no index posicao -1. Não adiciona
   * contatos repetidos e sobrescreve o favorito se a posicao já estiver ocupada.
   * 
   * @param posicao posição do contato.
   * @param contato o contato a ser favoritado.
   * @return situação final em forma de String.
   */
  public String adicionaFavorito(int posicao, Contato contato) {
    if (posicao > 10 || posicao < 1) {
      return "POSIÇÃO INVÁLIDA!\n";
    }

    if (contato == null) {
      return "CONTATO INVÁLIDO!\n";
    }

    if (contatoRepetido(contato, listaDeFavoritos)) {
      return "CONTATO JÁ CADASTRADO!\n";
    }

    listaDeFavoritos[posicao - 1] = contato;
    return "CONTATO FAVORITADO NA POSIÇÃO " + posicao + "!\n";
  }

  /**
   * Verifica a existência de um contato na lista de favoritos.
   * 
   * @param contato contato para verificar se existe na lista de favoritos.
   * @return index do contato na lista de favoritos ou -1 se não existir o contato
   *         na lista de favoritos.
   */
  public int ehFavorito(Contato contato) {
    for (int i = 0; i < listaDeFavoritos.length; i++) {
      Contato favorito = listaDeFavoritos[i];

      if (favorito != null && contato.equals(favorito))
        return i;
    }
    return -1;
  }

  /**
   * Carrega os contatos na agenda (utilizado pelo leitor de agenda na
   * inicialização da agenda).
   * 
   * @param posicao   posição do contato no array de contatos.
   * @param nome      Nome do contato
   * @param sobrenome Sobrenome do contato
   * @param telefone  Telefone do contato
   */
  public void carregaContato(int posicao, String nome, String sobrenome, String telefone) {
    Contato novoContato = new Contato(nome, sobrenome, telefone);
    this.contatos[posicao - 1] = novoContato;
  }

  /**
   * Chama o método contatoRepetido(Contato contatoParaVerificar, Contato[]
   * listaDeContato) com o array de contatos como o parâmetro listaDeContato.
   * 
   * @param contatoParaVerificar contato para verificar se é ou não repetido no
   *                             array de contatos da agenda.
   * @return retorna o retorno de contatoRepetido(Contato contatoParaVerificar,
   *         Contato[] listaDeContato)
   */
  public boolean contatoRepetido(Contato contatoParaVerificar) {
    return contatoRepetido(contatoParaVerificar, contatos);
  }

  /**
   * Verifica se o contatoParaVerificar existe na listaDeContato.
   * 
   * @param contatoParaVerificar contato para ver se existe na listaDeContato.
   * @param listaDeContato       lista para ver se o contatoParaVerificar existe
   *                             nela.
   * @return true se existir, falso se não.
   */
  private boolean contatoRepetido(Contato contatoParaVerificar, Contato[] listaDeContato) {
    for (Contato contato : listaDeContato) {
      if (contato != null && contato.equals(contatoParaVerificar))
        return true;
    }

    return false;
  }

  /**
   * Adiciona uma tag no index posicaoTag - 1 das tags de cada um dos contatos em
   * posicaoContatos.
   * 
   * @param posicaoContatos lista de contatos que receberão a tag.
   * @param tag             tag que será adicionada aos contatos.
   * @param posicaoTag      posição em que a tag será adicionada no array de tags
   *                        de cada contato.
   */
  public void adicionarTag(String[] posicaoContatos, String tag, int posicaoTag) throws IllegalArgumentException {
    for (String posicaoAsString : posicaoContatos) {
      int index = Integer.parseInt(posicaoAsString) - 1;

      if (index < 0 || index > 99)
        throw new IllegalArgumentException("POSIÇÃO INVÁLIDA!\n");

      Contato recebeTag = contatos[index];
      recebeTag.setTag(posicaoTag, tag);
    }
  }

}
