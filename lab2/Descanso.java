package lab2;

/**
 * A classe Descanso serve para descrever o descanso do aluno.
 * 
 * @author João Victor de Souza Lucena
 */

public class Descanso {
  private int horasDeDescanso;
  private int numerosDeSemana = 1;

  public void defineHorasDescanso(int horasDeDescanso) {
    this.horasDeDescanso = horasDeDescanso;
  }

  public void defineNumeroSemanas(int numerosDeSemana) {
    this.numerosDeSemana = numerosDeSemana;
  }

  public String getStatusGeral() {
    double horasDescansoPorSemana = horasDeDescanso / numerosDeSemana;
    String status = "cansado";
    if (horasDescansoPorSemana >= 26) {
      status = "descansado";
    }
    return status;
  }

}