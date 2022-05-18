import java.util.Scanner;

public class JogoDeAdivinhacao {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int correctNumber = sc.nextInt();
    while (true) {
      int guessedNumber = sc.nextInt();
      if (guessedNumber > correctNumber) {
        System.out.println("MAIOR");
      } else if (guessedNumber < correctNumber) {
        System.out.println("MENOR");
      } else {
        System.out.println("ACERTOU");
        break;
      }
    }
  }
}