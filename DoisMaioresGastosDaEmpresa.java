import java.util.Scanner;

public class DoisMaioresGastosDaEmpresa {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String[] values = sc.nextLine().split(" ");
    int highest = Integer.parseInt(values[0]);
    int highestIndex = 0;

    for (int i = 0; i < values.length; i++) {
      int num = Integer.parseInt(values[i]);
      if (num > highest) {
        highest = num;
        highestIndex = i;
      }
    }

    values[highestIndex] = "0";
    highestIndex = 0;

    int secondHighest = Integer.parseInt(values[0]);

    for (int i = 0; i < values.length; i++) {
      int num = Integer.parseInt(values[i]);
      if (num > secondHighest) {
        secondHighest = num;
      }
    }
    System.out.println(secondHighest + highest);
  }
}
