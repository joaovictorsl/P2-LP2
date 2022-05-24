import java.util.Scanner;

public class ComandoDeRepeticoesPorCondicao {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int sum = 0;
    while (true) {
      int value = sc.nextInt();
      if (value == -1) {
        break;
      }
      if (value % 2 == 0) {
        sum += value;
      }
    }
    System.out.println(sum);
  }
}
