package src.base;

/**
 * A classe Descanso serve para descrever o descanso do aluno e julgar se ele
 * está ou não cansado baseado na razão horas de descanso por semana. Por padrão
 * numerosDeSemana é iniciado com 1, status é iniciado como cansado e emoji é
 * iniciado como vazio.
 * 
 * @author João Victor de Souza Lucena
 */

public class Descanso {

  // Horas de descanso em numerosDeSemana semanas.
  private int horasDeDescanso;
  // Quantidade de semanas em que horasDeDescanso serão distribuídas.
  private int numerosDeSemana;
  // Status do aluno.
  private String status;
  // Emoji definido pelo aluno.
  private String emoji;

  /**
   * Construtor inicia numerosDeSemana para 1, dessa forma não há risco de ocorrer
   * division by zero exception ao calcular o status sem definir numeroDeSemanas.
   */
  public Descanso() {
    numerosDeSemana = 1;
    status = "cansado";
    emoji = "";
  }

  /**
   * Método para definir horas de descanso.
   * 
   * @param horasDeDescanso quantidade de horas de descanso para as
   *                        numerosDeSemana semanas.
   */
  public void defineHorasDescanso(int horasDeDescanso) {
    if (horasDeDescanso < 0)
      return;

    this.horasDeDescanso = horasDeDescanso;
    status = calcStatusGeral();
  }

  /**
   * Método para definir numero de semanas em que horas de descanso será
   * distribuída.
   * 
   * @param numerosDeSemana quantidade de semanas em que as horas de descaso serão
   *                        distribuídas.
   */
  public void defineNumeroSemanas(int numerosDeSemana) {
    if (numerosDeSemana <= 0)
      return;

    this.numerosDeSemana = numerosDeSemana;
    status = calcStatusGeral();
  }

  /**
   * Método para definição de emoji.
   */
  public void definirEmoji(String emoji) {
    this.emoji = emoji;
  }

  /**
   * Método para cálculo do status do aluno. Esse método é chamado pelos métodos
   * "defineNumeroSemanas" e "defineHorasDescanso" para atualizar o status toda
   * vez que houver uma alteração nas horasDeDescanso ou numeroDeSemanas.
   * 
   * @return novo valor do status
   */
  private String calcStatusGeral() {
    double horasDescansoPorSemana = horasDeDescanso / numerosDeSemana;
    String statusAnterior = status;

    String novoStatus = horasDescansoPorSemana >= 26 ? "descansado" : "cansado";
    emoji = novoStatus.equals(statusAnterior) ? emoji : "";

    return novoStatus;
  }

  /**
   * @return status acompanhado do emoji se ele for diferente de "", caso ele seja
   *         igual a "" apenas o status é retornado.
   */
  public String getStatusGeral() {
    String toReturn = status;
    if (!emoji.equals("")) {
      toReturn += " - " + emoji;
    }
    return toReturn;
  }

}
