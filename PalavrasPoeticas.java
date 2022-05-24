import java.util.Scanner;

/**
 * PalavrasPoeticas
 */
public class PalavrasPoeticas {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String word1 = sc.nextLine();
    String word2 = sc.nextLine();
    String output = "N";
    if (arePoetic(word1, word2)) {
      output = "S";
    }
    System.out.println(output);
    sc.close();
  }

  public static boolean arePoetic(String word1, String word2) {
    return word1.charAt(0) == word2.charAt(0) && word1.charAt(word1.length() - 1) == word2.charAt(word2.length() - 1);
  }
}