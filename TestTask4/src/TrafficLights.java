public class TrafficLights implements Runnable {

  private final TrafficLightsManager trafficLightsManager;
  private volatile boolean isInterrupted = false;
  private volatile boolean isTappedButton = false;
  private volatile boolean isMaintenance = false;
  private long startTimeGreen = System.currentTimeMillis();
  private long startTimeYellow;
  private long startTimeRed;
  private Color color = Color.GREEN;

  TrafficLights(TrafficLightsManager trafficLightsManager) {
    this.trafficLightsManager = trafficLightsManager;
  }

  String getColor() {
    return color.getColorText();
  }

  @Override
  public void run() {
    while (!isInterrupted) {

      synchronized (this) {
        if (!isMaintenance) {
          if (isTappedButton) {
            long difGreen = (System.currentTimeMillis() - startTimeGreen);
            int remainTimeGreen = (int) (30_000 - difGreen) / 1000;
            if (difGreen >= 30_000 && color == Color.GREEN) {
              color = Color.YELLOW;
              System.out.println("YELLOW");
              startTimeYellow = startTimeYellow == 0 ? System.currentTimeMillis() : startTimeYellow;
            } else {
              trafficLightsManager.setMessage("Will be switched in " + remainTimeGreen + " sec.");
            }
            long difYellow =
                startTimeYellow != 0 ? System.currentTimeMillis() - startTimeYellow : 0;
            long difRed = startTimeRed != 0 ? System.currentTimeMillis() - startTimeRed : 0;
            if (difYellow >= 2_000 && color == Color.YELLOW) {
              if (startTimeGreen == 0) {
                color = Color.GREEN;
                System.out.println("GREEN");
                startTimeGreen = System.currentTimeMillis();
                isTappedButton = false;
              } else {
                color = Color.RED;
                System.out.println("RED");
                startTimeRed = System.currentTimeMillis();
                int remainTimeRed = (int) (15_000 - difRed) / 1000;
                trafficLightsManager.setMessage("Red will ended in " + remainTimeRed + " sec.");
              }
              startTimeYellow = 0;
            }
            if (difRed >= 15_000) {
              color = Color.YELLOW;
              startTimeYellow = System.currentTimeMillis();
              startTimeRed = 0;
              System.out.println("YELLOW");
              startTimeGreen = 0;
            }
          }
        } else {
          color = Color.YELLOW;
          trafficLightsManager.setMessage("Maintenance....");
        }
        notify();
      }
    }
  }

  void interrupt() {
    isInterrupted = true;
  }

  void tapButton() {
    isTappedButton = true;
  }

  void startMaintenance() {
    isMaintenance = true;
  }

  void endMaintenance() {
    isMaintenance = false;
  }

}
