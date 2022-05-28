package src.CLI;

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
    clear();
    System.out.println(separator);
    for (String line : main) {
      System.out.println(line);
    }
    System.out.println(separator);
  }

  public void clear() {
    for (int i = 0; i < 50; i++) {
      System.out.println("");
    }
  }

  public void printMainMenu() {
    print(menuHolder.mainMenu);
  }

  public void printAtvComplMenu() {
    print(menuHolder.atvComplMenu);
  }

  public void printDisciplinaMenu() {
    print(menuHolder.disciplinaMenu);
  }

  public void printRegistroTempoOnlineMenu() {
    print(menuHolder.registroTempoOnlineMenu);
  }

  public void printDescansoMenu() {
    print(menuHolder.descansoMenu);
  }

}
