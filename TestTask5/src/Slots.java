import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class Slots {

  private static final String info =
      "Type \"spin\" to try your luck! \nType \"bye\" to exit the game";

  static void run() {
    String input;
    System.out.println("Hello! " + info);

    Scanner sc = new Scanner(System.in);
    while (!(input = sc.nextLine()).equalsIgnoreCase("bye")) {
      if (input.equalsIgnoreCase("spin")) {
        spin();
      } else {
        System.out.println("I don't know this command :(");
      }
      System.out.println(info);
    }
    System.out.println("Bye! Come back and try your luck again!");
    sc.close();
  }

  private static void spin() {
    int[][] slots = {{getRandom(), getRandom(), getRandom()},
        {getRandom(), getRandom(), getRandom()},
        {getRandom(), getRandom(), getRandom()}};

    printArray(slots);
    if (isWinningRow(slots[0]) || isWinningRow(slots[1]) || isWinningRow(slots[2])) {
      System.out.println(
          "~~~~~~~~~~~~~~~~~~~~~~~\n" + "Congratulations! YOU WON!\n" + "~~~~~~~~~~~~~~~~~~~~~~~\n"
              + info);
    } else {
      System.out.println("It's ok, try again :)");
    }
  }

  private static int getRandom() {
    Random random = new Random();
    return random.nextInt(10);
  }

  private static boolean isWinningRow(int[] row) {
    return Arrays.stream(row).distinct().count() == 1;
  }

  private static void printArray(int[][] array) {
    for (int[] ints : array) {
      System.out.println("-------------");
      System.out.println(
          Arrays.toString(ints).replace("[", "| ").replace(",", " |").replace("]", " |"));
    }
    System.out.println("-------------");
  }

}
