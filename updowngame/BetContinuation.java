package updowngame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ベットの継続を決めるクラスです。
 */
public class BetContinuation {
  private boolean shouldContinueBet; // 正解したときのベット継続判定

  public BetContinuation() {
    this.shouldContinueBet = false;
  }

  public boolean shouldContinueBet() {
    return shouldContinueBet;
  }

  public void setShouldContinueBet(boolean shouldContinueBet) {
    this.shouldContinueBet = shouldContinueBet;
  }

  /**
   * ベットを継続するかどうか選択するメソッドです。
   */
  public void askContinueBet() {
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

  /**
   * 選択が適正かどうか判定するメソッドです。
   * 
   * @param choice
   *          選択肢
   * @return 選択が適正の場合trueを返す
   */
  public boolean isValidChoice(String choice) {
    return (choice.equals("y") || choice.equals("n"));
  }
}
