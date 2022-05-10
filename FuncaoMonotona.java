import java.util.Scanner;

public class FuncaoMonotona {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    float value = sc.nextFloat();
    float nextValue = sc.nextFloat();
    char situation = value > nextValue ? '-' : '+';
    value = nextValue;
    for (int i = 0; i < 2; i++) {
      nextValue = sc.nextFloat();
      if (value > nextValue) {
        judge(situation, '-');
      } else if (value < nextValue) {
        judge(situation, '+');
      } else {
        exit();
      }
      value = nextValue;
    }
    if (situation == '+') {
      System.out.println("POSSIVELMENTE ESTRITAMENTE CRESCENTE");
    } else {
      System.out.println("POSSIVELMENTE ESTRITAMENTE DECRESCENTE");
    }
  }

  public static void exit() {
    System.out.println("FUNCAO NAO ESTRITAMENTE CRES/DECR");
    System.exit(0);
  }

  public static void judge(char expectedSituation, char situation) {
    if (!(expectedSituation == situation)) {
      exit();
    }
    ;
  }
}
