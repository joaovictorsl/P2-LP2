package app.base;

/**
 * RegistroTempoOnline serve para registrar o temopo online que um estudante
 * dedica a uma disciplina. O número padrão de horas esperadas é 120.
 * 
 * @author João Victor de Souza Lucena
 */

public class RegistroTempoOnline {

  // Nome da disciplina que o tempo está sendo dedicado.
  private String nomeDaDisciplina;
  // Tempo investido/dedicado
  private int tempoInvestidoOnline;
  // Tempo que se espera que tenha dedicado à disciplina.
  private int tempoEsperadoOnline;

  /**
   * Construtor define 120 como número padrão de tempo esperado online.
   */
  public RegistroTempoOnline(String nomeDaDisciplina) {
    this(nomeDaDisciplina, 120);
  }

  /**
   * Define nome e tempo esperado online de uma disciplina.
   * 
   * @param tempoEsperadoOnline tempo que se espera que o aluno dedique à
   *                            disciplina.
   */
  public RegistroTempoOnline(String nomeDaDisciplina, int tempoEsperadoOnline) {
    this.nomeDaDisciplina = nomeDaDisciplina;
    this.tempoEsperadoOnline = tempoEsperadoOnline;
  }

  /**
   * Soma tempo ao tempoInvestidoOnline.
   */
  public void adicionaTempoOnline(int tempoParaAdicionar) {
    if (tempoParaAdicionar < 0)
      return;

    tempoInvestidoOnline += tempoParaAdicionar;
  }

  /**
   * Define o field tempoEsperadoOnline.
   */
  public void defineTempoEsperadoOnline(int novoParaTempoEsperado) {
    if (novoParaTempoEsperado < 0)
      return;

    tempoEsperadoOnline = novoParaTempoEsperado;
  }

  /**
   * @return um booleano referente a se o aluno atingiu ou não a meta de tempo
   *         online.
   */
  public boolean atingiuMetaTempoOnline() {
    if (tempoInvestidoOnline >= tempoEsperadoOnline)
      return true;

    return false;
  }

  /**
   * @return nome da disciplina a qual o registro de tempo online se refere.
   */
  public String getNomeDaDisciplina() {
    return nomeDaDisciplina;
  }

  /**
   * Exemplo:
   * RegistroTempoOnline exemplo = new RegistroTempoOnline("Teste");
   * System.out.println(exemplo.toString());
   * OUTPUT:
   * Teste 0/120
   */
  public String toString() {
    return nomeDaDisciplina + " " + tempoInvestidoOnline + "/" + tempoEsperadoOnline;
  }

}
