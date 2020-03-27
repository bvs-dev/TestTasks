import second.Coins;
import first.TramTicketChecker;
import java.util.ArrayList;
import third.Pairs;

public class Main {

  public static void main(String[] args) {
    ArrayList<Integer> ticketsList = new ArrayList<>();
    ticketsList.add(123321);
    ticketsList.add(123456);
    ticketsList.add(231231);
    ticketsList.add(267555);

    System.out.println(TramTicketChecker.getLuckyTickets(ticketsList));

    Coins.findAndPrintCoinsCombinations();

    int[] array = {-120, 20, 17, 3, 18, 15, 1, 11, 5, 0};
    Pairs.printPairs(array, 20);
  }
}
