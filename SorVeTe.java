import java.util.Scanner;

public class SorVeTe {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    float pos1 = sc.nextFloat();
    float vel1 = sc.nextFloat();
    float pos2 = sc.nextFloat();
    float vel2 = sc.nextFloat();
    float time = sc.nextFloat();
    pos1 += vel1 * time;
    pos2 += vel2 * time;
    System.out.printf("%.0f", Math.abs(pos1 - pos2));
  }
}
