import java.util.Scanner;

public class PassouOuNao {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    float num1 = sc.nextFloat();
    float num2 = sc.nextFloat();
    if (((num1 + num2) / 2) >= 7) {
      System.out.println("pass: True!");
    } else {
      System.out.println("pass: False!");
    }
  }
}
