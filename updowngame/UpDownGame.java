package updowngame;

import java.io.IOException;
import java.util.Random;

public class UpDownGame {
  private static final int START_GOLD = 100_000; // ゲーム開始時の所持金
  private static final int MAX_BET_GOLD = 30_000; // ベット上限
  private static final int GAME_OVER_GOLD = 0; // ゲーム終了の金額
  private static final int GAME_CLEAR_GOLD = 500_000; // ゲームクリアの金額
  private static final int UP_DOWN_ODDS = 2; // UPとDOWNのオッズ
  private static final int SAME_ODDS = 5; // SAMEのオッズ
  private int wallet = START_GOLD; // プレイヤーの所持金

  public static void main(String[] args) {
    UpDownGame udgame = new UpDownGame();
    udgame.startGame();
  }

  public void startGame() {
    int firstNumber, secondNumber; // 問題の数字1, 2

    Continuation continuation = new Continuation();
    while (continuation.getShouldContinueGame()) {
      System.out.println("現在の所持金：" + wallet + "G");

      try {
        Bet bet = new Bet(MAX_BET_GOLD, wallet);

        do {

          Random random = new Random(); // 1つめの数字生成
          firstNumber = random.nextInt(13) + 1;
          System.out.println("NUMBER : " + firstNumber);

          Answer answer = new Answer();

          secondNumber = random.nextInt(13) + 1; // 2つめの数字生成
          System.out.println("ANSWER : " + secondNumber);

          switch (answer.getAnswer()) {

          case "1":
            if (firstNumber < secondNumber) {
              hasCorrectAnswer(bet, UP_DOWN_ODDS, continuation, answer);
            } else {
              hasIncorrectAnswer(bet, answer, continuation);
            }
            break;

          case "2":
            if (firstNumber > secondNumber) {
              hasCorrectAnswer(bet, UP_DOWN_ODDS, continuation, answer);
            } else {
              hasIncorrectAnswer(bet, answer, continuation);
            }
            break;

          case "3":
            if (firstNumber == secondNumber) {
              hasCorrectAnswer(bet, SAME_ODDS, continuation, answer);
            } else {
              hasIncorrectAnswer(bet, answer, continuation);
            }
            break;

          }
        } while (continuation.getShouldContinueBet());

      } catch (IOException e) {
        System.out.println(e);
      }
    }

    endGame(wallet);
  }

  private void hasCorrectAnswer(Bet bet, int odds, Continuation continuation, Answer answer) {
    bet.multiplyBet(odds);

    if (bet.getBet() + wallet >= GAME_CLEAR_GOLD) {
      this.wallet = continuation.finishGame(bet, wallet);
    } else {
      continuation.shouldContinueBet();

      if (!continuation.getShouldContinueBet()) {
        this.wallet += bet.getBet();
      }
    }
  }

  private void hasIncorrectAnswer(Bet bet, Answer answer, Continuation continuation) {
    this.wallet = bet.subtractBet(wallet, bet.getBet());
    continuation.setShouldContinueBet(false);

    if (wallet <= GAME_OVER_GOLD) {
      continuation.setShouldContinueGame(false);
    }
  }

  private void endGame(int wallet) {
    if (wallet >= GAME_CLEAR_GOLD) {
      System.out.println("所持金が50万G以上になりました。");
    } else {
      System.out.println("所持金が0になりました。");
    }
    System.out.println("ゲームを終了します。");
  }
}
