import java.util.Scanner;

public class GastosDe4Semanas {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    for (int i = 0; i < 4; i++) {
      String[] values = sc.nextLine().split(" ");
      int totalSpent = 0;
      int highest = Integer.parseInt(values[0]);
      int lowest = Integer.parseInt(values[0]);

      for (String numAsString : values) {
        int num = Integer.parseInt(numAsString);

        if (highest < num) {
          highest = num;
        } else if (lowest > num) {
          lowest = num;
        }

        totalSpent += num;
      }
      System.out.printf("%d %d %d\n", totalSpent, lowest, highest);
    }

  }
}