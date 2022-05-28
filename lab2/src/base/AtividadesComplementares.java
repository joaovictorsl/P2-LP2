package src.base;

import java.util.ArrayList;

/**
 * AtividadesComplementares é uma classe para o estudante administrar suas
 * atividades complementares e os créditos adquiridos através delas.
 * 
 * @author João Victor de Souza Lucena
 */

public class AtividadesComplementares {

  // Hora total adquiridas através de cursos
  private double horasDeCursoTotal;
  // Creditos totais adquiridos através de estágios.
  private int creditosEstagio;
  // Creditos totais adquiridos através de projetos.
  private int creditosProjeto;
  // Histórico com horas de estágios e seus meses
  private ArrayList<int[]> historicoDeEstagios;
  // Histórico com meses de projetos
  private ArrayList<Integer> historicoDeProjetos;

  /**
   * Construtor inicializa historicoDeEstagios e historicoDeProjetos. O resto dos
   * atributos podem ser inicializados com seus valores padrões.
   */
  public AtividadesComplementares() {
    historicoDeEstagios = new ArrayList<int[]>();
    historicoDeProjetos = new ArrayList<Integer>();
  }

  /**
   * Método serve para definir o valor padrão de 4 meses quando apenas as horas
   * forem passadas para o método adicionarEstagio.
   * 
   * @param horas quantidade de horas para adicionar.
   */
  public void adicionarEstagio(int horas) {
    adicionarEstagio(horas, 4);
  }

  /**
   * Adiciona o estágio ao histórico e adiciona os creditos adquiridos nele. Além
   * disso limita o número máximo de estágios para 9.
   * 
   * @param horas quantidade de horas para adicionar.
   * @param meses quantidade de meses, a qual limita a quantidade de créditos que
   *              o aluno pode ganhar pelas horas a cada 4 meses o aluno pode
   *              ganhar +5 créditos no máximo.
   */
  public void adicionarEstagio(int horas, int meses) {
    if (historicoDeEstagios.size() == 9)
      return;

    historicoDeEstagios.add(new int[] { horas, meses });

    creditosEstagio += calcQuantCreditoEstagios(horas, meses);
  }

  /**
   * Calcula a quantidade de creditos providos pelo estágio.
   * 
   * @param horas quantidade de horas do estágio.
   * @param meses duração do estágio em meses.
   */
  public int calcQuantCreditoEstagios(int horas, int meses) {
    int creditos = horas / 300 * 5;
    int quantCreditosMax = meses / 4 * 5;
    return creditos > quantCreditosMax ? quantCreditosMax : creditos;
  }

  /**
   * Adiciona o projeto ao histórico e adiciona os creditos adquiridos nele. Além
   * disso limita o número máximo de projetos para 16.
   * 
   * @param meses duração do projeto em meses.
   */
  public void adicionarProjeto(int meses) {
    if (historicoDeProjetos.size() == 16)
      return;

    historicoDeProjetos.add(meses);

    creditosProjeto += meses / 3 * 2;
  }

  /**
   * Adiciona o a quantidade de horas do curso ao total de horas de cursos e
   * adiciona os creditos adquiridos nele.
   * 
   * @param horas quantidade de horas para adicionar ao total de horas de cursos.
   */
  public void adicionarCurso(double horas) {
    horasDeCursoTotal += horas;
  }

  /**
   * Calcula os creditos adquiridos através de cursos. Esse cálculo não pode ser
   * feito toda vez ao adicionar curso, pois as horas que sobram são consideradas
   * para gerarem créditos se atigirem 30 horas, diferentemente de projetos e
   * estágios que os meses e horas são descartados respectivamente.
   * 
   * @return total de créditos adquiridos por cursos.
   */
  private int getCreditosCurso() {
    return (int) (horasDeCursoTotal / 30);
  }

  /**
   * @return somatório de créditos.
   */
  public int contaCreditos() {
    return creditosProjeto + getCreditosCurso() + creditosEstagio;
  }

  /**
   * @return array de String com cada estágio com suas horas e meses,
   *         projeto com seus meses e horas totais de curso além dos créditos
   *         fornecidos por cada uma das três maneiras de adquirir créditos.
   */
  public String[] pegaAtividades() {
    String[] atividadesFormatadas = new String[historicoDeEstagios.size() + historicoDeProjetos.size() + 4];
    int posicoesOcupadas = 0;

    for (int[] horaComMes : historicoDeEstagios) {
      atividadesFormatadas[posicoesOcupadas++] = "Estagio " + horaComMes[0] + " " + horaComMes[1];
    }

    for (int num : historicoDeProjetos) {
      atividadesFormatadas[posicoesOcupadas++] = "Projeto " + num;
    }

    atividadesFormatadas[posicoesOcupadas++] = "Cursos " + horasDeCursoTotal;

    atividadesFormatadas[posicoesOcupadas++] = "Creditos_Estagio " + creditosEstagio;
    atividadesFormatadas[posicoesOcupadas++] = "Creditos_Projeto " + creditosProjeto;
    atividadesFormatadas[posicoesOcupadas] = "Creditos_Cursos " + horasDeCursoTotal + " " + getCreditosCurso();

    return atividadesFormatadas;
  }

}
