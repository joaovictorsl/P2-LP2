import java.util.Scanner;

public class SomandoPares {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String[] numAsStringArray = sc.nextLine().split(" ");
    int sum = 0;
    for (String numAsString : numAsStringArray) {
      int num = Integer.parseInt(numAsString);
      if (num % 2 == 0) {
        sum += num;
      }
    }
    System.out.println(sum);
  }
}
