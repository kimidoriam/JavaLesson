package updowngame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BetContinuation {
  private boolean shouldContinueBet; // 正解したときのベット継続判定

  public BetContinuation() {
    this.shouldContinueBet = true;
  }

  public boolean getShouldContinueBet() {
    return shouldContinueBet;
  }

  public void setShouldContinueBet(boolean shouldContinueBet) {
    this.shouldContinueBet = shouldContinueBet;
  }

  public void shouldContinueBet() {
    String choice = ""; // 選択肢

    while (!isValidChoice(choice)) {

      try {
        System.out.println("現在のベットでゲームを続けますか？(y/n)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        choice = line;

        if (!(choice.equals("y") || choice.equals("n"))) {
          System.out.println("yかnを入力してください。");
        }
      } catch (IOException e) {
        System.out.println(e);
      }
    }

    if (choice.equals("y")) {
      shouldContinueBet = true;
    } else {
      shouldContinueBet = false;
    }
  }

  public boolean isValidChoice(String choice) {
    return (choice.equals("y") || choice.equals("n"));
  }
}
