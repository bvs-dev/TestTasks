package second;

public class Coins {

  public static void findAndPrintCoinsCombinations() {
    for (int fifties = 0; fifties < 4; fifties++) {
      for (int twenties = 0; twenties < 3; twenties++) {
        for (int tens = 0; tens < 3; tens++) {
          int sum = fifties * 50 + twenties * 20 + tens * 10;
          if (sum == 70 || sum == 80) {
            printCombinations(fifties, twenties, tens);
          }
        }
      }
    }
  }

  private static void printCombinations(int fifties, int twenties, int tens) {
    for (int i = 0; i <fifties; i++) {
      System.out.print("50 ");
    }

    for (int i = 0; i <twenties; i++) {
      System.out.print("20 ");
    }

    for (int i = 0; i <tens; i++) {
      System.out.print("10 ");
    }

    System.out.println();
  }

}
