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
    boolean isValidChoice = false; // 選択肢有効判定
    String choice = ""; // 選択肢

    while (!isValidChoice) {

      try {
        System.out.println("現在のベットでゲームを続けますか？(y/n)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        choice = line;

        if (!(choice.equals("y") || choice.equals("n"))) {
          System.out.println("yかnを入力してください。");
        } else {
          isValidChoice = true;
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
}
