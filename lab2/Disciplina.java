package lab2;

import java.util.Arrays;

/**
 * Disciplina
 * 
 * @author João Victor de Souza Lucena
 */

public class Disciplina {
  private String nome;
  private int horasDeEstudo;
  private double[] notas = new double[4];

  public Disciplina(String nome) {
    this.nome = nome;
  }

  public void cadastraHoras(int horasParaCadastrar) {
    horasDeEstudo += horasParaCadastrar;
  }

  public void cadastraNota(int nota, double valorParaCadastrar) {
    int index = nota - 1;
    notas[index] = valorParaCadastrar;
  }

  public boolean aprovado() {
    if (getMedia() >= 7)
      return true;
    return false;
  }

  private double getMedia() {
    double somaDasNotas = notas[0] + notas[1] + notas[2] + notas[3];
    return somaDasNotas / 4;
  }

  public String toString() {
    return nome + " " + horasDeEstudo + " " + getMedia() + " " + Arrays.toString(notas);
  }
}