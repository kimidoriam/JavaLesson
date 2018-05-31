package updowngame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Continuation {
  private boolean shouldContinueGame; // ゲーム継続判定
  private boolean shouldContinueBet; // 正解したときのベット継続判定

  public Continuation() {
    this.shouldContinueGame = true;
    this.shouldContinueBet = true;
  }

  public boolean getShouldContinueGame() {
    return shouldContinueGame;
  }

  public boolean getShouldContinueBet() {
    return shouldContinueBet;
  }

  public void setShouldContinueGame(boolean shouldContinueGame) {
    this.shouldContinueGame = shouldContinueGame;
  }

  public void setShouldContinueBet(boolean shouldContinueBet) {
    this.shouldContinueBet = shouldContinueBet;
  }

  public int finishGame(Bet bet, int wallet) {
    System.out.println("ベット額と所持金の合計が" + (bet.getBet() + wallet) + "Gになりました。");
    wallet += bet.getBet();
    setShouldContinueBet(false);
    setShouldContinueGame(false);
    return wallet;
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
    if (!(choice.equals("y") || choice.equals("n"))) {
      return false;
    } else {
      return true;
    }
  }
}
