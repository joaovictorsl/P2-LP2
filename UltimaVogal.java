import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;

public class UltimaVogal {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String word;
    HashSet<Character> vogalHash = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    for (int i = 0; i < 5; i++) {
      word = sc.nextLine();
      char letter = word.charAt(word.length() - 1);
      if (vogalHash.contains(letter)) {
        System.out.printf("%c", letter);
      }
    }

  }
}
