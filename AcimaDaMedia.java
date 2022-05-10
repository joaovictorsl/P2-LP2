import java.util.Scanner;

public class AcimaDaMedia {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String[] input = sc.nextLine().split(" ");
    float sum = 0;
    for (String numAsString : input) {
      int num = Integer.parseInt(numAsString);
      sum += num;
    }
    float mean = sum / input.length;
    String toPrint = "";
    for (int i = 0; i < input.length; i++) {
      int num = Integer.parseInt(input[i]);
      if (num > mean) {
        char space = i == input.length - 1 ? '\0' : ' ';
        toPrint += Integer.toString(num);
        if (space == ' ') {
          toPrint += space;
        }
      }
    }
    System.out.println(toPrint);
  }
}
