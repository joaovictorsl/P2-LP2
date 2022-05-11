import java.util.Scanner;

public class AbaixoAssinado {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int amount = 0;
    int totalScore = 0;
    while (true) {
      String[] nameAndScore = sc.nextLine().split(" ");
      if (nameAndScore[0].equals("fim")) {
        break;
      }
      amount += 1;
      totalScore += Integer.parseInt(nameAndScore[1]);
    }
    System.out.println(amount);
    System.out.println(totalScore / amount);
  }
}
