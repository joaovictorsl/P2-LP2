import java.util.Scanner;

public class Wally {
  public static void main(String[] args) {
    String wallyNewName = "wally";
    Scanner sc = new Scanner(System.in);
    while (true) {
      String[] nameArray = sc.nextLine().split(" ");
      if (nameArray[0].equals("wally")) {
        break;
      }
      boolean hasChanged = false;
      for (String name : nameArray) {
        if (name.length() == 5) {
          wallyNewName = name;
          hasChanged = true;
        }
      }
      if (!hasChanged) {
        System.out.println('?');
      } else {
        System.out.println(wallyNewName);
      }
    }
  }
}
