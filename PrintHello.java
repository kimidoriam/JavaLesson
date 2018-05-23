class LabelPrinter extends Thread {
  String label = "no label";

  LabelPrinter(String label) {
    this.label = label;
  }

  @Override
  public void run() {
    while (true) {
      System.out.println(label);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
      }
    }
  }
}

public class PrintHello {
  public static void main(String[] args) {
    LabelPrinter th = new LabelPrinter("こんにちは！");
    th.start();
  }
}
