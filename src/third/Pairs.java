package third;

import java.util.Arrays;

public class Pairs {

  public static void printPairs(int[] arrayA, int x) {
    Arrays.sort(arrayA);
    int left = 0;
    int right = arrayA.length - 1;

    while (left < right) {
      int sum = arrayA[left] + arrayA[right];
      if (sum == x) {
        System.out.println(arrayA[left] + " " + arrayA[right]);
        left++;
        right--;
      } else if (sum < x) {
        left++;
      } else {
        right--;
      }
    }
  }

}
