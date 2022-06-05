import src.base.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Supplier;

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
  private ArrayList<Disciplina> disciplina;
  private ArrayList<RegistroTempoOnline> regTempOnline;

  public CoisaCLI() {
    sc = new Scanner(System.in);
    printer = new PrettyPrinter();
    atividades = new AtividadesComplementares();
    descanso = new Descanso();
    disciplina = new ArrayList<Disciplina>();
    regTempOnline = new ArrayList<RegistroTempoOnline>();
  }

  public void run() {
    boolean stayIn = true;
    boolean invalidEntry = false;

    do {
      printer.clear();

      if (invalidEntry) {
        System.out.println("Entrada inválida, tente novamente.");
        invalidEntry = false;
      }

      printer.printMainMenu();
      String escolha = sc.nextLine();

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
    boolean stayIn = true;
    boolean invalidEntry = false;

    do {
      printer.clear();

      if (invalidEntry) {
        System.out.println("Entrada inválida, tente novamente.");
        invalidEntry = false;
      }

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
          printer.waitForEnter(sc);
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

  public void teste(Supplier<Boolean> differentPart) {
    boolean invalidEntry = false;

    do {
      printer.clear();

      if (invalidEntry) {
        System.out.println("Entrada inválida, tente novamente.");
        invalidEntry = false;
      }

      invalidEntry = differentPart.get();

    } while (invalidEntry);
  }

  public void handleDescanso() {
    boolean stayIn = true;
    boolean invalidEntry = false;

    do {
      printer.clear();

      if (invalidEntry) {
        System.out.println("Entrada inválida, tente novamente.");
        invalidEntry = false;
      }

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
          printer.waitForEnter(sc);
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

  public void handleDisciplina() {
    boolean stayIn = true;
    boolean invalidEntry = false;

    do {
      printer.clear();

      if (invalidEntry) {
        System.out.println("Entrada inválida, tente novamente.");
        invalidEntry = false;
      }

      printer.printDisciplinaMenu();
      String escolha = sc.nextLine();
      String nome;
      int numeroDeNotas;
      printer.clear();

      switch (escolha) {
        case "1":
          System.out.println("Digite o nome da disciplina:");
          nome = sc.nextLine();
          System.out.println("Digite a quantidade de notas da disciplina:");
          numeroDeNotas = Integer.parseInt(sc.nextLine());

          int[] pesoDasNotas = new int[numeroDeNotas];
          for (int i = 0; i < numeroDeNotas; i++) {
            System.out.println("Digite o peso da nota " + (i + 1) + ":");
            int peso = Integer.parseInt(sc.nextLine());
            pesoDasNotas[i] = peso;
          }

          Disciplina novaDisciplina = new Disciplina(nome, numeroDeNotas, pesoDasNotas);
          for (int i = 0; i < numeroDeNotas; i++) {
            System.out.println("Digite a nota " + (i + 1) + ":");
            double nota = Double.parseDouble(sc.nextLine());
            novaDisciplina.cadastraNota(i + 1, nota);
          }

          disciplina.add(novaDisciplina);
          regTempOnline.add(new RegistroTempoOnline(nome));
          break;
        case "2":
          handleGerenciarDisciplina();
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

  public void handleGerenciarDisciplina() {
    boolean stayIn = true;
    boolean invalidEntry = false;

    do {
      printer.clear();

      if (invalidEntry) {
        System.out.println("Entrada inválida, tente novamente.");
        invalidEntry = false;
      }

      if (disciplina.size() == 0) {
        System.out.println("Sem disciplinas cadastradas.");
        printer.waitForEnter(sc);
        break;
      }

      System.out.println("Selecione uma disciplina ou digite 0 para retornar:");
      printer.printDisciplinasList(disciplina);
      int escolha = Integer.parseInt(sc.nextLine());

      if (escolha > 0 && escolha <= disciplina.size()) {
        Disciplina selecionada = disciplina.get(escolha - 1);
        gerenciarDisciplinaSelecionada(selecionada);
      } else if (escolha == 0) {
        stayIn = false;
      } else {
        invalidEntry = true;
      }

    } while (stayIn);

  }

  public void gerenciarDisciplinaSelecionada(Disciplina selecionada) {
    boolean stayIn = true;
    boolean invalidEntry = false;
    int valor;

    do {
      printer.clear();

      if (invalidEntry) {
        System.out.println("Entrada inválida, tente novamente.");
        invalidEntry = false;
      }

      printer.printGerenciarDisciplinaMenu(selecionada);
      int escolha = Integer.parseInt(sc.nextLine());
      printer.clear();

      switch (escolha) {
        case 1:
          String aprovacao = selecionada.aprovado() ? " Aprovado" : " Reprovado";
          System.out.println(selecionada.toString() + aprovacao);
          printer.waitForEnter(sc);
          break;
        case 2:
          System.out.println("Digite a nota para alterar:");
          escolha = Integer.parseInt(sc.nextLine());
          System.out.println("Digite o novo valor da nota:");
          valor = Integer.parseInt(sc.nextLine());
          selecionada.cadastraNota(escolha, valor);
          break;
        case 3:
          System.out.println("Digite a nota que terá seu peso alterado:");
          escolha = Integer.parseInt(sc.nextLine());
          System.out.println("Digite o novo valor do peso:");
          valor = Integer.parseInt(sc.nextLine());
          selecionada.definePesoDeNota(escolha, valor);
          break;
        case 4:
          System.out.println("Quantidade de horas para adicionar:");
          valor = Integer.parseInt(sc.nextLine());
          selecionada.cadastraHoras(valor);
          break;
        case 0:
          stayIn = false;
          break;

        default:
          invalidEntry = true;
          break;
      }
    } while (stayIn);
  }

  public void handleRegistroTempoOnline() {
    boolean stayIn = true;
    boolean invalidEntry = false;

    do {
      printer.clear();

      if (invalidEntry) {
        System.out.println("Entrada inválida, tente novamente.");
        invalidEntry = false;
      }

      if (regTempOnline.size() == 0) {
        System.out.println("Sem disciplinas cadastradas.");
        printer.waitForEnter(sc);
        break;
      }

      System.out.println("Selecione uma disciplina ou digite 0 para retornar:");
      printer.printRegTempOnlineList(regTempOnline);
      int escolha = Integer.parseInt(sc.nextLine());

      if (escolha > 0 && escolha <= regTempOnline.size()) {
        RegistroTempoOnline selecionado = regTempOnline.get(escolha - 1);
        handleRegTempOnlineSelecionado(selecionado);
      } else if (escolha == 0) {
        stayIn = false;
      } else {
        invalidEntry = true;
      }

    } while (stayIn);
  }

  public void handleRegTempOnlineSelecionado(RegistroTempoOnline selecionado) {
    boolean stayIn = true;
    boolean invalidEntry = false;

    do {
      printer.clear();

      if (invalidEntry) {
        System.out.println("Entrada inválida, tente novamente.");
        invalidEntry = false;
      }

      printer.printRegistroTempoOnlineMenu(selecionado);
      int escolha = Integer.parseInt(sc.nextLine());
      printer.clear();

      int valor;

      switch (escolha) {
        case 1:
          String aprovacao = selecionado.atingiuMetaTempoOnline() ? " (meta atingida)" : " (meta não atingida)";
          System.out.println(selecionado.toString() + aprovacao);
          printer.waitForEnter(sc);
          break;
        case 2:
          System.out.println("Digite o tempo para adicionar:");
          valor = Integer.parseInt(sc.nextLine());
          selecionado.adicionaTempoOnline(valor);
          break;
        case 3:
          System.out.println("Digite o novo tempo esperado da disciplina:");
          valor = Integer.parseInt(sc.nextLine());
          selecionado.defineTempoEsperadoOnline(valor);
          break;
        case 0:
          stayIn = false;
          break;

        default:
          invalidEntry = true;
          break;
      }
    } while (stayIn);
  }

}
