package src.CLI;

import java.util.Scanner;

import src.base.*;

/**
 * PrettyPrinter
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

  public void waitForEnter() {
    System.out.println("Pressione enter para continuar.");
    Scanner sc = new Scanner(System.in);
    sc.nextLine();
    sc.close();
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

  public void printRegistroTempoOnlineMenu() {
    print(menuHolder.getRegistroTempoOnlineMenu());
  }

  public void printDescansoMenu() {
    print(menuHolder.getDescansoMenu());
  }

}
