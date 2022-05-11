import java.util.Scanner;

public class EstabilizandoAlturaDoAviao {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int idealHeight = sc.nextInt();
    int actualHeight = sc.nextInt();
    while (actualHeight != idealHeight) {
      int newHeight = sc.nextInt();
      if (newHeight == idealHeight) {
        break;
      }
      if (Math.abs(idealHeight - actualHeight) > Math.abs(idealHeight - newHeight)) {
        System.out.println("ADEQUADO");
      } else {
        System.out.println("PERIGO");
      }
      actualHeight = newHeight;
    }
    System.out.println("OK");
  }
}
