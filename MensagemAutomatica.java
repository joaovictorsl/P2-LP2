import java.util.Scanner;

public class MensagemAutomatica {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String name = sc.nextLine();
    System.out.printf(
        "Exuberante estudante %s, lhe tenho na mais alta estima ao ler a sua mensagem e responder que a resposta da sua pergunta encontra-se no site da disciplina.\nCordialmente,\nMatheus G.\n",
        name);
  }
}
