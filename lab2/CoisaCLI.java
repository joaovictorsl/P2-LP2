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
  private Scanner sc;
  private AtividadesComplementares atividades;
  private Descanso descanso;
  private Disciplina[] disciplina;
  private RegistroTempoOnline[] regTempOnline;

  public CoisaCLI() {
    sc = new Scanner(System.in);
    printer = new PrettyPrinter();
    atividades = new AtividadesComplementares();
    descanso = new Descanso();
    disciplina = new Disciplina[10];
    regTempOnline = new RegistroTempoOnline[10];
  }

  public void run() {
    boolean stayIn = true;
    boolean invalidEntry = false;

    do {
      printer.clear();

      if (invalidEntry)
        System.out.println("Entrada inválida, tente novamente.");
      invalidEntry = false;

      printer.printMainMenu();
      String escolha = sc.nextLine();
      printer.clear();

      switch (escolha) {
        case "1":
          handleAtvCompl();
          break;
        case "2":
          handleDescanso();
          break;
        case "3":
          handleDisciplina();
          break;
        case "4":
          handleRegistroTempoOnline();
          break;
        case "0":
          stayIn = false;
          break;

        default:
          invalidEntry = true;
          break;
      }
    } while (stayIn);
  }

  public void handleAtvCompl() {
    boolean invalidEntry = false;
    do {

      if (invalidEntry)
        System.out.println("Entrada inválida, tente novamente.");

      printer.printAtvComplMenu();
      String escolha = sc.nextLine();
      int meses, horas;
      double horasDeCurso;
      printer.clear();

      switch (escolha) {
        case "1":
          System.out.println("Total de horas do estágio:");
          horas = Integer.parseInt(sc.nextLine());
          System.out.println("Total de meses do estágio:");
          meses = Integer.parseInt(sc.nextLine());
          atividades.adicionarEstagio(horas, meses);
          break;
        case "2":
          System.out.println("Digite os meses do projeto:");
          meses = Integer.parseInt(sc.nextLine());
          atividades.adicionarProjeto(meses);
          break;
        case "3":
          System.out.println("Horas de curso para adicionar:");
          horasDeCurso = Double.parseDouble(sc.nextLine());
          atividades.adicionarCurso(horasDeCurso);
          break;
        case "4":
          for (String line : atividades.pegaAtividades()) {
            System.out.println(line);
          }
          System.out.println("Pressione enter para continuar.");
          sc.nextLine();
          break;

        default:
          invalidEntry = true;
          break;
      }
      printer.clear();
    } while (invalidEntry);

  }

  public void handleDescanso() {
    boolean invalidEntry = false;
    do {
      if (invalidEntry)
        System.out.println("Entrada inválida, tente novamente.");

      printer.printDescansoMenu();
      String escolha = sc.nextLine();
      int semanas, horas;
      String emoji;
      printer.clear();

      switch (escolha) {
        case "1":
          System.out.println("Digite a quantidade de horas:");
          horas = Integer.parseInt(sc.nextLine());
          descanso.defineHorasDescanso(horas);
          break;
        case "2":
          System.out.println("Digite a quantidade de semanas:");
          semanas = Integer.parseInt(sc.nextLine());
          descanso.defineNumeroSemanas(semanas);
          break;
        case "3":
          System.out.println("Digite o emoji desejado:");
          emoji = sc.nextLine();
          descanso.definirEmoji(emoji);
          break;
        case "4":
          System.out.println(descanso.getStatusGeral());
          System.out.println("Pressione enter para continuar.");
          sc.nextLine();
          break;

        default:
          invalidEntry = true;
          break;
      }
      printer.clear();
    } while (invalidEntry);
  }

  public void handleDisciplina() {
    printer.printDisciplinaMenu();
    String escolha = sc.nextLine();
  }

  public void handleRegistroTempoOnline() {
    printer.printRegistroTempoOnlineMenu();
    String escolha = sc.nextLine();
  }

}
