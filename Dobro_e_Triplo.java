import java.util.Scanner;

public class Dobro_e_Triplo {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int input = Integer.parseInt(sc.nextLine());
    System.out.println("dobro: " + input * 2 + ", triplo: " + input * 3);
  }
}
