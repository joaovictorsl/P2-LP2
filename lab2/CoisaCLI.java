package lab2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Supplier;

import lab2.src.CLI.*;
import lab2.src.base.*;

/**
 * CoisaCLI é a classe responsável por ordenar a interação do usuário com o
 * Coisa e representar a ideia de fluxo da CLI.
 * 
 * @author João Victor de Souza Lucena
 */

public class CoisaCLI {

  private PrettyPrinter printer;
  private Scanner sc;
  private AtividadesComplementares atividades;
  private Descanso descanso;
  private ArrayList<Disciplina> listaDeDisciplinas;
  private ArrayList<RegistroTempoOnline> listaDeRegTempOnline;

  public CoisaCLI() {
    sc = new Scanner(System.in);
    printer = new PrettyPrinter();
    atividades = new AtividadesComplementares();
    descanso = new Descanso();
    listaDeDisciplinas = new ArrayList<Disciplina>();
    listaDeRegTempOnline = new ArrayList<RegistroTempoOnline>();
  }

  /**
   * Inicia a CLI.
   */
  public void run() {
    stayInLoop(this::mainMenu);
  }

  /**
   * stayInLoop foi criado para evitar a repetição do código dentro dele em cada
   * método.
   * 
   * @param execute parte individual de cada método a ser executada dentro do
   *                stayInLoop. Esse parâmetro retorna o valor de stayIn e
   *                invalidEntry em um array de booleanos após a execução da parte
   *                individual.
   */
  public void stayInLoop(Supplier<Boolean[]> execute) {
    boolean stayIn = true;
    boolean invalidEntry = false;

    do {
      printer.clear();

      if (invalidEntry) {
        System.out.println("Entrada inválida, tente novamente.");
        invalidEntry = false;
      }

      try {
        Boolean[] stayInAndInvalidEntry = execute.get();

        stayIn = stayInAndInvalidEntry[0];
        invalidEntry = stayInAndInvalidEntry[1];
      } catch (Exception e) {
        invalidEntry = true;
      }

    } while (stayIn);
  }

  /**
   * Mesma coisa do método stayInLoop sendo que recebendo uma função com um
   * parâmetro de tipo T e retorno do tipo Boolean[].
   * 
   * @param <T>     tipo do argumento do execute.
   * @param execute parte individual para ser executada no stayInLoop.
   * @param arg     argumento do execute.
   */
  public <T> void stayInLoop(Function<T, Boolean[]> execute, T arg) {
    boolean stayIn = true;
    boolean invalidEntry = false;

    do {
      printer.clear();

      if (invalidEntry) {
        System.out.println("Entrada inválida, tente novamente.");
        invalidEntry = false;
      }

      try {
        Boolean[] stayInAndInvalidEntry = execute.apply(arg);

        stayIn = stayInAndInvalidEntry[0];
        invalidEntry = stayInAndInvalidEntry[1];
      } catch (Exception e) {
        invalidEntry = true;
      }

    } while (stayIn);
  }

  public Boolean[] mainMenu() {
    Boolean[] stayInAndInvalidEntry = new Boolean[2];
    stayInAndInvalidEntry[0] = true;
    stayInAndInvalidEntry[1] = false;

    printer.printMainMenu();
    String escolha = sc.nextLine();

    switch (escolha) {
      case "1":
        stayInLoop(this::handleAtvCompl);
        break;
      case "2":
        stayInLoop(this::handleDescanso);
        break;
      case "3":
        stayInLoop(this::handleDisciplina);
        break;
      case "4":
        stayInLoop(this::handleRegistroTempoOnline);
        break;
      case "0":
        stayInAndInvalidEntry[0] = false;
        break;

      default:
        stayInAndInvalidEntry[1] = true;
        break;
    }

    return stayInAndInvalidEntry;
  }

  public Boolean[] handleAtvCompl() {
    Boolean[] stayInAndInvalidEntry = new Boolean[2];
    stayInAndInvalidEntry[0] = true;
    stayInAndInvalidEntry[1] = false;

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
        System.out.println("Total de créditos " + atividades.contaCreditos());
        printer.waitForEnter(sc);
        break;
      case "0":
        stayInAndInvalidEntry[0] = false;
        break;

      default:
        stayInAndInvalidEntry[1] = true;
        break;
    }

    return stayInAndInvalidEntry;
  }

  public Boolean[] handleDescanso() {
    Boolean[] stayInAndInvalidEntry = new Boolean[2];
    stayInAndInvalidEntry[0] = true;
    stayInAndInvalidEntry[1] = false;

    printer.printDescansoMenu();
    String escolha = sc.nextLine();
    int semanas, horas;
    String emoji;
    printer.clear();

    switch (escolha) {
      case "1":
        System.out.println("Digite a quantidade de horas:");
        horas = Integer.parseInt(sc.nextLine());
        if (horas < 0) {
          printer.clear();
          System.out.println("Horas não podem ser negativas.");
          printer.waitForEnter(sc);
        } else {
          descanso.defineHorasDescanso(horas);
        }
        break;
      case "2":
        System.out.println("Digite a quantidade de semanas:");
        semanas = Integer.parseInt(sc.nextLine());
        if (semanas <= 0) {
          printer.clear();
          System.out.println("Número de semanas deve ser maior que zero.");
          printer.waitForEnter(sc);
        } else {
          descanso.defineNumeroSemanas(semanas);
        }
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
        stayInAndInvalidEntry[0] = false;
        break;

      default:
        stayInAndInvalidEntry[1] = true;
        break;
    }

    return stayInAndInvalidEntry;
  }

  public Boolean[] handleDisciplina() {
    Boolean[] stayInAndInvalidEntry = new Boolean[2];
    stayInAndInvalidEntry[0] = true;
    stayInAndInvalidEntry[1] = false;

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

        listaDeDisciplinas.add(novaDisciplina);
        listaDeRegTempOnline.add(new RegistroTempoOnline(nome));
        break;
      case "2":
        stayInLoop(this::handleGerenciarDisciplina);
        break;
      case "0":
        stayInAndInvalidEntry[0] = false;
        break;

      default:
        stayInAndInvalidEntry[1] = true;
        break;
    }

    return stayInAndInvalidEntry;
  }

  public Boolean[] handleGerenciarDisciplina() {
    Boolean[] stayInAndInvalidEntry = new Boolean[2];
    stayInAndInvalidEntry[0] = true;
    stayInAndInvalidEntry[1] = false;

    if (listaDeDisciplinas.size() == 0) {
      System.out.println("Sem disciplinas cadastradas.");
      printer.waitForEnter(sc);
      return stayInAndInvalidEntry;
    }

    System.out.println("Selecione uma disciplina ou digite 0 para retornar:");
    printer.printDisciplinasList(listaDeDisciplinas);
    int escolha = Integer.parseInt(sc.nextLine());

    if (escolha > 0 && escolha <= listaDeDisciplinas.size()) {
      Disciplina selecionada = listaDeDisciplinas.get(escolha - 1);
      Function<Disciplina, Boolean[]> func = this::gerenciarDisciplinaSelecionada;
      stayInLoop(func, selecionada);
    } else if (escolha == 0) {
      stayInAndInvalidEntry[0] = false;
    } else {
      stayInAndInvalidEntry[1] = true;
    }

    return stayInAndInvalidEntry;
  }

  public Boolean[] gerenciarDisciplinaSelecionada(Disciplina selecionada) {
    Boolean[] stayInAndInvalidEntry = new Boolean[2];
    stayInAndInvalidEntry[0] = true;
    stayInAndInvalidEntry[1] = false;

    printer.printGerenciarDisciplinaMenu(selecionada);
    int escolha = Integer.parseInt(sc.nextLine());
    int valor;
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
        if (valor < 0) {
          printer.clear();
          System.out.println("Horas não podem ser negativas.");
          printer.waitForEnter(sc);
        } else {
          selecionada.cadastraHoras(valor);
        }
        break;
      case 0:
        stayInAndInvalidEntry[0] = false;
        break;

      default:
        stayInAndInvalidEntry[1] = true;
        break;
    }

    return stayInAndInvalidEntry;
  }

  public Boolean[] handleRegistroTempoOnline() {
    Boolean[] stayInAndInvalidEntry = new Boolean[2];
    stayInAndInvalidEntry[0] = true;
    stayInAndInvalidEntry[1] = false;

    if (listaDeRegTempOnline.size() == 0) {
      System.out.println("Sem disciplinas cadastradas.");
      printer.waitForEnter(sc);
      return stayInAndInvalidEntry;
    }

    System.out.println("Selecione uma disciplina ou digite 0 para retornar:");
    printer.printRegTempOnlineList(listaDeRegTempOnline);
    int escolha = Integer.parseInt(sc.nextLine());

    if (escolha > 0 && escolha <= listaDeRegTempOnline.size()) {
      RegistroTempoOnline selecionado = listaDeRegTempOnline.get(escolha - 1);
      Function<RegistroTempoOnline, Boolean[]> func = this::handleRegTempOnlineSelecionado;
      stayInLoop(func, selecionado);
    } else if (escolha == 0) {
      stayInAndInvalidEntry[0] = false;
    } else {
      stayInAndInvalidEntry[1] = true;
    }

    return stayInAndInvalidEntry;
  }

  public Boolean[] handleRegTempOnlineSelecionado(RegistroTempoOnline selecionado) {
    Boolean[] stayInAndInvalidEntry = new Boolean[2];
    stayInAndInvalidEntry[0] = true;
    stayInAndInvalidEntry[1] = false;

    printer.printRegistroTempoOnlineMenu(selecionado);
    int escolha = Integer.parseInt(sc.nextLine());
    int valor;
    printer.clear();

    switch (escolha) {
      case 1:
        String aprovacao = selecionado.atingiuMetaTempoOnline() ? " (meta atingida)" : " (meta não atingida)";
        System.out.println(selecionado.toString() + aprovacao);
        printer.waitForEnter(sc);
        break;
      case 2:
        System.out.println("Digite o tempo para adicionar:");
        valor = Integer.parseInt(sc.nextLine());
        if (valor < 0) {
          printer.clear();
          System.out.println("Tempo não pode ser negativo.");
          printer.waitForEnter(sc);
        } else {
          selecionado.adicionaTempoOnline(valor);
        }
        break;
      case 3:
        System.out.println("Digite o novo tempo esperado da disciplina:");
        valor = Integer.parseInt(sc.nextLine());
        if (valor < 0) {
          printer.clear();
          System.out.println("Tempo não pode ser negativo.");
          printer.waitForEnter(sc);
        } else {
          selecionado.defineTempoEsperadoOnline(valor);
        }
        break;
      case 0:
        stayInAndInvalidEntry[0] = false;
        break;

      default:
        stayInAndInvalidEntry[1] = true;
        break;
    }

    return stayInAndInvalidEntry;
  }

}
