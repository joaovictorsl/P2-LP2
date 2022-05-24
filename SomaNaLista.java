import java.util.Scanner;

public class SomaNaLista {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int[] values = new int[5];

    for (int i = 0; i < 5; i++) {
      values[i] = sc.nextInt();
    }

    int indexOne = sc.nextInt();
    int indexTwo = sc.nextInt();

    System.out.println(values[indexOne] + values[indexTwo]);
  }
}
