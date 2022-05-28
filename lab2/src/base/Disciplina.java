package src.base;

import java.util.Arrays;

/**
 * Disciplina é uma classe na qual o aluno gerencia sua situação de notas em uma
 * disciplina.
 * 
 * @author João Victor de Souza Lucena
 */

public class Disciplina {

  // Nome da disciplina
  private String nome;
  // Horas de estudo dedicadas à disciplina.
  private int horasDeEstudo;
  // Array com as notas da disciplina.
  private double[] notas;
  // Array com o peso de cada uma das notas da disciplina.
  private int[] pesoDasNotas;

  /**
   * Esse construtor serve para deixar o número de notas padrão como 4 quando
   * apenas o nome da disciplina for passado.
   * 
   * @param nome nome da disciplina
   */
  public Disciplina(String nome) {
    this(nome, 4);
  }

  /**
   * Constrói uma disciplina com nome, numero de notas e define os pesos de todas
   * as notas para 1.
   * 
   * @param numeroDeNotas quantidade de notas
   */
  public Disciplina(String nome, int numeroDeNotas) {
    this.nome = nome;
    notas = new double[numeroDeNotas];
    pesoDasNotas = new int[numeroDeNotas];
    Arrays.fill(pesoDasNotas, 1);
  }

  /**
   * Define o nome a quantidade de notas e seus pesos.
   * 
   * @param pesoDasNotas array de tamanho numeroDeNotas com cada elemento
   *                     representando o peso da respectiva nota.
   */
  public Disciplina(String nome, int numeroDeNotas, int[] pesoDasNotas) {
    this.nome = nome;
    notas = new double[numeroDeNotas];
    this.pesoDasNotas = pesoDasNotas;
  }

  /**
   * Soma horas nas horas de estudo dedicadas à disciplina.
   * 
   * @param horasParaCadastrar horas que serão somadas nas horas de estudo
   *                           dedicadas à disciplina.
   */
  public void cadastraHoras(int horasParaCadastrar) {
    horasDeEstudo += horasParaCadastrar;
  }

  /**
   * Cadastra nota em uma posição especificada do array de notas.
   * 
   * @param nota               posição da nota no array de notas.
   * @param valorParaCadastrar valor para ser guardado na posição nota do array de
   *                           notas.
   */
  public void cadastraNota(int nota, double valorParaCadastrar) {
    int index = nota - 1;
    notas[index] = valorParaCadastrar;
  }

  /**
   * @return situação de aprovação do aluno. False = não aprovado.
   */
  public boolean aprovado() {
    if (getMedia() >= 7)
      return true;

    return false;
  }

  /**
   * Calcula a média ponderada do aluno levando em consideração o pesoDasNotas
   * como pesos.
   * 
   * @return média do aluno na disciplina.
   */
  private double getMedia() {
    double notasPonderadas = 0;
    int somaDosPesos = 0;

    for (int i = 0; i < notas.length; i++) {
      notasPonderadas += notas[i] * pesoDasNotas[i];
      somaDosPesos += pesoDasNotas[i];
    }

    return notasPonderadas / somaDosPesos;
  }

  /**
   * Exemplo:
   * Disciplina calculo = new Disciplina("Cálculo");
   * calculo.cadastraHoras(56);
   * System.out.println(calculo.toString());
   * OUTPUT:
   * Cálculo 56 0.0 [0.0, 0.0, 0.0, 0.0]
   * 
   * @return String que representa a disciplina como uma String.
   */
  public String toString() {
    return nome + " " + horasDeEstudo + " " + getMedia() + " " + Arrays.toString(notas);
  }

}
