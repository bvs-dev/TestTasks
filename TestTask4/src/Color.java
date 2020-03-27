public enum Color {

  RED("red"),
  GREEN("green"),
  YELLOW("yellow");

  private String colorText;
  Color(String colorText) {
    this.colorText = colorText;
  }

  String getColorText() {
    return colorText;
  }

}
