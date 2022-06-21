package agenda;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interface com menus texto para manipular uma agenda de contatos.
 * 
 * @author nazarenoandrade
 *
 */
public class MainAgenda {

  public static void main(String[] args) {
    Agenda agenda = new Agenda();

    System.out.println("Carregando agenda inicial");
    try {
      /*
       * Essa é a maneira de lidar com possíveis erros por falta do arquivo.
       */
      carregaAgenda("agenda_inicial.csv", agenda);
    } catch (FileNotFoundException e) {
      System.err.println("Arquivo não encontrado: " + e.getMessage());
    } catch (IOException e) {
      System.err.println("Erro lendo arquivo: " + e.getMessage());
    }

    Scanner scanner = new Scanner(System.in);
    String escolha = "";
    while (true) {
      escolha = menu(scanner);
      comando(escolha, agenda, scanner);
    }

  }

  /**
   * Exibe o menu e captura a escolha do/a usuário/a.
   * 
   * @param scanner Para captura da opção do usuário.
   * @return O comando escolhido.
   */
  private static String menu(Scanner scanner) {
    System.out.print(
        "(C)adastrar Contato\n" +
            "(L)istar Contatos\n" +
            "(E)xibir Contato\n" +
            "(F)avoritos\n" +
            "(A)dicionar Favorito\n" +
            "(T)ags\n" +
            "(R)emover Contato\n" +
            "(S)air\n" +
            "\n" +
            "Opção> ");
    return scanner.next().toUpperCase();
  }

  /**
   * Interpreta a opção escolhida por quem está usando o sistema.
   * 
   * @param opcao   Opção digitada.
   * @param agenda  A agenda que estamos manipulando.
   * @param scanner Objeto scanner para o caso do comando precisar de mais input.
   */
  private static void comando(String opcao, Agenda agenda, Scanner scanner) {
    switch (opcao) {
      case "C":
        cadastraContato(agenda, scanner);
        break;
      case "L":
        listaContatos(agenda);
        break;
      case "E":
        exibeContato(agenda, scanner);
        break;
      case "F":
        exibeFavoritos(agenda);
        break;
      case "A":
        adicionaFavorito(agenda, scanner);
        break;
      case "T":
        adicionaTag(agenda, scanner);
        break;
      case "R":
        removerContato(agenda, scanner);
        break;
      case "S":
        sai();
        break;
      default:
        System.out.println("OPÇÃO INVÁLIDA!");
    }
  }

  private static void removerContato(Agenda agenda, Scanner scanner) {
    System.out.println("Contato(s)> ");
    int posicao = scanner.nextInt();
    if (posicao <= 0 || posicao > 100) {
      System.out.println("POSIÇÃO INVÁLIDA!");
      return;
    }

    boolean success = agenda.removerContato(posicao);

    if (!success)
      System.out.println("POSIÇÃO INVÁLIDA!");
  }

  private static void adicionaTag(Agenda agenda, Scanner scanner) {
    System.out.println("Contato(s)> ");
    String[] posicaoContatos = scanner.nextLine().split(" ");

    System.out.println("Tag> ");
    String tag = scanner.nextLine();

    System.out.println("Posição tag> ");
    int posicaoTag = scanner.nextInt();

    agenda.adicionarTag(posicaoContatos, tag, posicaoTag);
  }

  private static void exibeFavoritos(Agenda agenda) {
    System.out.println();
    Contato[] listaDeFavoritos = agenda.getListaDeFavoritos();

    for (int i = 0; i < listaDeFavoritos.length; i++) {
      Contato favorito = listaDeFavoritos[i];

      if (favorito == null)
        continue;

      System.out.println(formataContato(i + 1, favorito));
    }
  }

  private static void adicionaFavorito(Agenda agenda, Scanner scanner) {
    System.out.print("Contato> ");
    int posicaoContato = scanner.nextInt();
    Contato contato = agenda.getContato(posicaoContato);

    System.out.print("Posicao> ");
    int posicao = scanner.nextInt();

    String result = agenda.adicionaFavorito(posicao, contato);
    System.out.println(result);
  }

  /**
   * Imprime lista de contatos da agenda.
   * 
   * @param agenda A agenda sendo manipulada.
   */
  private static void listaContatos(Agenda agenda) {
    System.out.println("\nLista de contatos: ");
    Contato[] contatos = agenda.getContatos();
    for (int i = 0; i < contatos.length; i++) {
      if (contatos[i] != null) {
        System.out.println(formataContato(i + 1, contatos[i]));
      }
    }
  }

  /**
   * Imprime os detalhes de um dos contatos da agenda.
   * 
   * @param agenda  A agenda.
   * @param scanner Scanner para capturar qual contato.
   */
  private static void exibeContato(Agenda agenda, Scanner scanner) {
    System.out.print("Contato> ");
    int posicao = scanner.nextInt();
    Contato contato = agenda.getContato(posicao);

    if (contato == null) {
      System.out.println("POSIÇÃO INVÁLIDA!");
      return;
    }

    System.out.println();

    if (agenda.ehFavorito(contato) != -1)
      System.out.print("❤️  ");

    System.out.println(contato.getNomeCompleto());
    System.out.println(contato.getTelefone());
    String[] tags = contato.getTags();

    for (int i = 0; i < tags.length; i++) {
      String tag = tags[i];

      if (tag == null)
        continue;

      System.out.print(tag);

      if (i != tags.length - 1)
        System.out.print(" ");
    }

    System.out.println();
  }

  /**
   * Formata um contato para impressão na interface.
   * 
   * @param posicao A posição do contato (que é exibida)/
   * @param contato O contato a ser impresso.
   * @return A String formatada.
   */
  private static String formataContato(int posicao, Contato contato) {
    return posicao + " - " + contato.getNomeCompleto();
  }

  /**
   * Cadastra um contato na agenda.
   * 
   * @param agenda  A agenda.
   * @param scanner Scanner para pedir informações do contato.
   */
  private static void cadastraContato(Agenda agenda, Scanner scanner) {
    System.out.print("\nPosição> ");
    int posicao = scanner.nextInt();
    if (posicao <= 0 || posicao > 100) {
      System.out.println("POSIÇÃO INVÁLIDA");
      return;
    }

    scanner.nextLine();

    System.out.print("Nome> ");
    String nome = scanner.nextLine();
    if (nome.isEmpty()) {
      System.out.println("CONTATO INVÁLIDO");
      return;
    }

    System.out.print("Sobrenome> ");
    String sobrenome = scanner.nextLine();

    System.out.print("Telefone> ");
    String telefone = scanner.nextLine();
    if (telefone.isEmpty()) {
      System.out.println("CONTATO INVÁLIDO");
      return;
    }

    boolean success = agenda.cadastraContato(posicao, nome, sobrenome, telefone);

    if (success)
      System.out.println("CADASTRO REALIZADO");
    else
      System.out.println("CONTATO JÁ CADASTRADO");
  }

  /**
   * Sai da aplicação.
   */
  private static void sai() {
    System.out.println("\nVlw flw o/");
    System.exit(0);
  }

  /**
   * Lê uma agenda de um arquivo csv.
   * 
   * @param arquivoContatos O caminho para o arquivo.
   * @param agenda          A agenda que deve ser populada com os dados.
   * @throws IOException Caso o arquivo não exista ou não possa ser lido.
   */
  private static void carregaAgenda(String arquivoContatos, Agenda agenda) throws FileNotFoundException, IOException {
    LeitorDeAgenda leitor = new LeitorDeAgenda();

    int carregados = leitor.carregaContatos(arquivoContatos, agenda);
    System.out.println("Carregamos " + carregados + " registros.");
  }
}
