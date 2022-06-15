package agenda;

import java.util.Arrays;

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
  public boolean cadastraContato(int posicao, String nome, String sobrenome, String telefone) {
    Contato novoContato = new Contato(nome, sobrenome, telefone);

    if(contatoRepetido(novoContato)) {
      System.out.println("CONTATO JÁ CADASTRADO");
      return false;
    }
    
    this.contatos[posicao] = novoContato;
    return true;
  }

  public String adicionaFavorito(int posicao, Contato contato) {
    if(posicao > 10 || posicao < 1) {
      return "POSICAO INVÁLIDA";
    }

    if(contato == null) {
      return "CONTATO INVÁLIDO";
    }

    if(contatoRepetido(contato, listaDeFavoritos)) {
      return "CONTATO JÁ CADASTRADO";
    }

    listaDeFavoritos[posicao] = contato;
    return "CONTATO FAVORITADO NA POSIÇÃO " + posicao
  }

  public boolena ehFavorito(Contato contato) {
    for(Contato favorito : listaDeFavoritos) {
      if(contato.equals(favorito))
        return true;
    }
    return false;
  }

  public void carregaContato(int posicao, String nome, String sobrenome, String telefone) {
    Contato novoContato = new Contato(nome, sobrenome, telefone);
    this.contatos[posicao] = novoContato;
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

}
