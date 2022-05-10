import java.util.Scanner;

public class Calculadora {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    char operation = sc.next().charAt(0);
    if (!operatorIsValid(operation)) {
      System.out.println("ENTRADA INVALIDA");
      System.exit(0);
    }
    float num1 = sc.nextFloat();
    float num2 = sc.nextFloat();
    float result = 0;
    switch (operation) {
      case '-':
        result = num1 - num2;
        break;
      case '+':
        result = num1 + num2;
        break;
      case '*':
        result = num1 * num2;
        break;
      case '/':
        if (num2 == 0) {
          System.out.println("ERRO");
          System.exit(0);
        }
        result = num1 / num2;
        break;
    }
    System.out.println("RESULTADO: " + result);
  }

  public static boolean operatorIsValid(char operation) {
    switch (operation) {
      case '-':
        return true;
      case '+':
        return true;
      case '*':
        return true;
      case '/':
        return true;
      default:
        return false;
    }
  }
}
