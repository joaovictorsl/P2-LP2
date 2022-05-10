import java.util.Scanner;
import java.util.ArrayList;

public class AlunosENotas {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    double highest = Double.NEGATIVE_INFINITY, lowest = Double.POSITIVE_INFINITY, sum = 0, mean;
    int over = 0, below = 0;
    ArrayList<Double> scoreList = new ArrayList<Double>();
    while (true) {
      String[] input = sc.nextLine().split(" ");
      if (input[0].equals("-")) {
        break;
      }
      double score = Double.parseDouble(input[1]);
      if (highest < score) {
        highest = score;
      }
      if (lowest > score) {
        lowest = score;
      }
      scoreList.add(score);
      sum += score;
    }
    mean = sum / scoreList.size();
    for (double score : scoreList) {
      if (score >= 700) {
        over += 1;
      } else if (score < 700) {
        below += 1;
      }
    }
    System.out.printf("maior: %.0f\nmenor: %.0f\nmedia: %s\nacima: %d\nabaixo: %d", highest, lowest,
        Double.toString(mean).split("\\.")[0], over,
        below);
  }
}
