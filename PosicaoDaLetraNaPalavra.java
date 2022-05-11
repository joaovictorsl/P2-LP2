import java.util.Scanner;

public class PosicaoDaLetraNaPalavra {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    char letter = sc.nextLine().charAt(0);
    String word = sc.nextLine();
    int lastIndex = -1;
    for (int i = 0; i < word.length(); i++) {
      if (word.charAt(i) == letter) {
        lastIndex = i;
      }
    }
    System.out.println(lastIndex);
  }
}