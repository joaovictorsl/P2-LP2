import java.util.Scanner;

public class InicioDaVogal {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    for (int i = 0; i < 5; i++) {
      String output = "n";
      if (sc.nextLine().charAt(0) == 'a') {
        output = "s";
      }
      System.out.println(output);
    }
  }
}
