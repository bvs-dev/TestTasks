package first;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TramTicketChecker {

  public static List<Integer> getLuckyTickets(List<Integer> tickets) {
    return tickets.stream()
        .filter(TramTicketChecker::isLuckyTicket)
        .collect(Collectors.toList());
  }

  private static boolean isLuckyTicket(Integer ticket) {
    return Stream.of(ticket)
        .map(number -> {
          int[] digits = convertIntegerToDigits(number);
          if (digits.length != 6) {
            try {
              throw new Exception("Ticket number must be 6");
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
          return digits;
        })
        .anyMatch(digits -> isLuckyMsk(digits) && isLuckySpb(digits));
  }

  private static int[] convertIntegerToDigits(int number) {
    char[] chars = String.valueOf(number).toCharArray();
    int[] digits = new int[chars.length];

    for (int i = 0; i < chars.length; i++) {
      digits[i] = Character.getNumericValue(chars[i]);
    }
    return digits;
  }

  private static boolean isLuckyMsk(int[] digits) {
    return (digits[0] + digits[1] + digits[2]) == (digits[3] + digits[4] + digits[5]);
  }

  private static boolean isLuckySpb(int[] digits) {
    return (digits[0] + digits[2] + digits[4]) == (digits[1] + digits[3] + digits[5]);
  }
}
