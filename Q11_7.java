public class Q11_7 {
  int width, height;
  Q11_7(int w, int h) {
    width = w;
    height = h;
  }
  @Override
  public String toString() {
    return "Rectangle(" + width + ", " + height + ")";
  }
  public static void main(String[] args) {
    Q11_7 r1 = new Q11_7(123, 45);
    System.out.println(r1);
  }
}
