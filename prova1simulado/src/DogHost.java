package prova1simulado.src;

import java.util.ArrayList;

/**
 * DogHost
 */
public class DogHost {

  private int maxCachorros;
  private double maxRacaoServidaPorDia;
  private String nome;
  private ArrayList<Dog> pets;

  public DogHost(int maxCachorros, String nome, double maxRacaoServidaPorDia) {
    this.maxCachorros = maxCachorros;
    this.nome = nome;
    this.maxRacaoServidaPorDia = maxRacaoServidaPorDia;
    this.pets = new ArrayList<Dog>();
  }

  public boolean adicionaDog(String nome, String tutor, int qtdRacao) {
    return adicionaDog(nome, tutor, qtdRacao, null);
  }

  public boolean adicionaDog(String nome, String tutor, int qtdRacao, String tipoRestricao) {
    boolean restricao = tipoRestricao == null ? false : true;
    Dog tempDog = new Dog(nome, tutor, qtdRacao, restricao);

    if (pets.size() == maxCachorros)
      return false;

    if (qtdRacao + quantTotalRacaoDiaria() > maxRacaoServidaPorDia)
      return false;

    pets.add(tempDog);

    return true;
  }

  String listaDogs() {
    String toReturn = "";
    toReturn += nome + ":\n";
    for (Dog cachorro : pets) {
      toReturn += cachorro + "\n";
    }
    return toReturn;
  }

  public double consultaValorHospedagem(Dog toto, int dias) throws IllegalArgumentException {
    if (!pets.contains(toto))
      throw new IllegalArgumentException();

    return toto.getQuantRacao() * 0.4 * dias;
  }

  public double quantTotalRacaoDiaria() {
    double total = 0;

    for (Dog cachorro : pets) {
      total += cachorro.getQuantRacao();
    }

    return total;
  }

  public double getValorTotalDeHospedagemDiaria() {
    double total = 0;

    for (Dog cachorro : pets) {
      total += consultaValorHospedagem(cachorro, 1);
    }

    return total;
  }

}