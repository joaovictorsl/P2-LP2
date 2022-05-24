import java.util.Scanner;
import java.util.ArrayList;

public class MaiorMenorPalavra {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ArrayList<String> words = new ArrayList<String>();
    int highest = Integer.MIN_VALUE;
    int lowest = Integer.MAX_VALUE;
    for (int i = 0; i < 5; i++) {
      String newWord = sc.nextLine();
      words.add(newWord);
      if (newWord.length() > highest) {
        highest = newWord.length();
      }
      if (newWord.length() < lowest) {
        lowest = newWord.length();
      }
    }
    for (String word : words) {
      if (word.length() == lowest) {
        System.out.println(word);
        break;
      }
    }
    for (String word : words) {
      if (word.length() == highest) {
        System.out.println(word);
        break;
      }
    }
  }
}
