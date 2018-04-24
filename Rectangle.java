public class Rectangle {
  int width;   // 幅
  int height;  // 高さ
  int x;
  int y;
  final int INITIAL_WIDTH = 10;
  final int INITIAL_HEIGHT = 20;
  // コンストラクタ
  Rectangle() {
    setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
  }
  Rectangle(int w, int h) {
    setSize(w, h);
  }
  Rectangle(int x, int y, int w, int h) {
    setLocation(x, y);
    setSize(w, h);
  }
  // メソッド
  void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }
  void setSize(int width, int height) {
    if(width < 0) {
      width = 0;
    }
    if(height < 0) {
      height = 0;
    }
    this.width = width;
    this.height = height;
  }
  int getArea() {
    return width * height;
  }
  @Override
  public String toString() {
    return "[" + x + ", " + x + ", " + width + ", " + height + "]";
  }
  Rectangle intersect(Rectangle r) {
    int sx = Math.max(this.x, r.x);
    int sy = Math.max(this.y, r.y);
    int ex = Math.min(this.x + this.width, r.x + r.width);
    int ey = Math.min(this.y + this.height, r.y + r.height);
    int newwidth = ex - sx;
    int newheight = ey - sy;
    if (newwidth > 0 && newheight > 0) {
      return new Rectangle(sx, sy, newwidth, newheight);
    } else {
      return null;
    }
  }
  // mainメソッド
  public static void main(String[] args) {
    Rectangle a, b, c, d, e;
    a = new Rectangle(0, 0, 20, 10);
    b = new Rectangle(5, 5, 20, 10);
    c = new Rectangle(20, 10, 20, 10);
    d = new Rectangle(-10, -20, 100, 200);
    e = new Rectangle(21, 11, 20, 10);
    System.out.println("a = " + a);
    System.out.println("b = " + b);
    System.out.println("c = " + c);
    System.out.println("d = " + d);
    System.out.println("e = " + e);
    System.out.println("a と a の重なり = " + a.intersect(a));
    System.out.println("a と b の重なり = " + a.intersect(b));
    System.out.println("a と c の重なり = " + a.intersect(c));
    System.out.println("a と d の重なり = " + a.intersect(d));
    System.out.println("a と e の重なり = " + a.intersect(e));
  }
}
