package Lab2_JOAO_VICTOR_DE_SOUZA_LUCENA.src.CLI;

import java.util.Scanner;
import java.util.ArrayList;
import Lab2_JOAO_VICTOR_DE_SOUZA_LUCENA.src.base.*;

/**
 * PrettyPrinter é utilizada para organizar a maneira de printar informações no
 * console.
 */

public class PrettyPrinter {

  String separator;
  MenuHolder menuHolder;

  public PrettyPrinter() {
    menuHolder = new MenuHolder();
    separator = "-----------------------------------------";
  }

  private void print(String[] main) {
    System.out.println(separator);
    for (String line : main) {
      System.out.println(line);
    }
    System.out.println(separator);
  }

  public void waitForEnter(Scanner sc) {
    System.out.println("Pressione enter para continuar.");
    sc.nextLine();
  }

  public void printDisciplinasList(ArrayList<Disciplina> disciplinas) {
    for (int i = 0; i < disciplinas.size(); i++) {
      Disciplina opcao = disciplinas.get(i);
      System.out.println((i + 1) + " - " + opcao.getNome());
    }
  }

  public void printRegTempOnlineList(ArrayList<RegistroTempoOnline> registros) {
    for (int i = 0; i < registros.size(); i++) {
      RegistroTempoOnline opcao = registros.get(i);
      System.out.println((i + 1) + " - " + opcao.getNomeDaDisciplina());
    }
  }

  public void clear() {
    for (int i = 0; i < 50; i++) {
      System.out.println("");
    }
  }

  public void printMainMenu() {
    print(menuHolder.getMainMenu());
  }

  public void printAtvComplMenu() {
    print(menuHolder.getAtvComplMenu());
  }

  public void printDisciplinaMenu() {
    print(menuHolder.getDisciplinaMenu());
  }

  public void printGerenciarDisciplinaMenu(Disciplina disciplina) {
    print(menuHolder.getGerenciarDisciplinaMenu(disciplina));
  }

  public void printRegistroTempoOnlineMenu(RegistroTempoOnline reg) {
    print(menuHolder.getRegistroTempoOnlineMenu(reg));
  }

  public void printDescansoMenu() {
    print(menuHolder.getDescansoMenu());
  }

}
