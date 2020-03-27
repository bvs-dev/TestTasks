import java.util.Scanner;

class TrafficLightsManager implements Runnable {

  private final TrafficLights trafficLights;
  private String message;

  void setMessage(String message) {
    this.message = message;
  }

  TrafficLightsManager() {
    this.trafficLights = new TrafficLights(this);
  }

  @Override
  public void run() {
    new Thread(trafficLights).start();
    String input;
    Scanner sc = new Scanner(System.in);

    System.out.println(
        "color - get current color\ntap - switch color to red\nmaintenance - only yellow color\nexit - for exit :)");
    while (!(input = sc.nextLine()).equalsIgnoreCase("exit")) {
      if (input.equalsIgnoreCase("tap")) {

        trafficLights.tapButton();
        synchronized (trafficLights) {
          try {
            trafficLights.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        System.out.println(message);

      } else if (input.equalsIgnoreCase("color")) {
        System.out.println("Traffic lights color is " + trafficLights.getColor());
      } else if (input.equalsIgnoreCase("maintenance")) {
        trafficLights.startMaintenance();
        System.out.println("Maintenance has been started");
      } else if (input.equalsIgnoreCase("maintenance off")) {
        trafficLights.endMaintenance();
        System.out.println("Maintenance has been ended");
      }
      sc.close();
      trafficLights.interrupt();
    }
  }
}
