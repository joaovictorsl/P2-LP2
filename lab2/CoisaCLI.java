import src.base.*;

import java.util.Scanner;

import src.CLI.*;

/**
 * CoisaCLI é a classe responsável por ordenar a interação do usuário com o
 * Coisa.
 * 
 * @author João Victor de Souza Lucena
 */
public class CoisaCLI {

  private PrettyPrinter printer;

  public CoisaCLI() {
    printer = new PrettyPrinter();
  }

  public void run() {
    boolean stayIn = true;
    do {
      Scanner sc = new Scanner(System.in);

      printer.printMainMenu();
      String escolha = sc.nextLine();
      sc.close();

      switch (escolha) {
        case "1":
          printer.printAtvComplMenu();
          handleAtvCompl();
          break;
        case "2":
          printer.printDescansoMenu();
          handleDescanso();
          break;
        case "3":
          printer.printDisciplinaMenu();
          handleDisciplina();
          break;
        case "4":
          printer.printRegistroTempoOnlineMenu();
          handleRegistroTempoOnline();
          break;

        default:
          stayIn = false;
          break;
      }
    } while (stayIn);
  }

  public void handleAtvCompl() {
    Scanner sc = new Scanner(System.in);
    String escolha = sc.nextLine();
  }

  public void handleDescanso() {
    Scanner sc = new Scanner(System.in);
    String escolha = sc.nextLine();
  }

  public void handleDisciplina() {
    Scanner sc = new Scanner(System.in);
    String escolha = sc.nextLine();
  }

  public void handleRegistroTempoOnline() {
    Scanner sc = new Scanner(System.in);
    String escolha = sc.nextLine();
  }

}
