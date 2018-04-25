
public class Q12_4_Rectangle {
  int width;
  int height;

  Q12_4_Rectangle() {
    setSize(0, 0);
  }

  Q12_4_Rectangle(int width, int height) {
    setSize(width, height);
  }

  void setSize(int width, int height) {
    this.width = width;
    this.height = height;
  }

  @Override
  public String toString() {
    return "[" + width + ", " + height + "]";
  }
}

class PlacedRectangle extends Rectangle {
  int x;
  int y;

  PlacedRectangle() {
    setLocation(0, 0);
  }

  PlacedRectangle(int x, int y) {
    setLocation(x, y);
  }

  PlacedRectangle(int x, int y, int width, int height) {
    super(width, height);
    setLocation(x, y);
  }

  void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "[ (" + x + ", " + y + ") " + super.toString() + "]";
  }
}
