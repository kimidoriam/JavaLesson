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
    int firstNumber = 0;
    int secondNumber = 0; // 問題の数字1, 2

    while (GameContinuation.shouldContinueGame(wallet, GAME_OVER_GOLD, GAME_CLEAR_GOLD)) {
      System.out.println("現在の所持金：" + wallet + "G");

      try {
        Bet bet = new Bet(MAX_BET_GOLD, wallet);

        playGame(firstNumber, secondNumber, bet);
      } catch (IOException e) {
        System.out.println(e);
      }
    }

    endGame(wallet);
  }

  private void playGame(int firstNumber, int secondNumber, Bet bet) {
    Random random = new Random(); // 1つめの数字生成
    firstNumber = random.nextInt(13) + 1;
    System.out.println("NUMBER : " + firstNumber);

    Answer answer = new Answer();

    secondNumber = random.nextInt(13) + 1; // 2つめの数字生成
    System.out.println("ANSWER : " + secondNumber);

    BetContinuation betContinuation = null;
    switch (answer.getAnswer()) {
      case "1":
        if (firstNumber < secondNumber) {
          betContinuation = hasCorrectAnswer(bet, UP_DOWN_ODDS, answer);
        } else {
          betContinuation = hasIncorrectAnswer(bet, answer);
        }
        break;

      case "2":
        if (firstNumber > secondNumber) {
          betContinuation = hasCorrectAnswer(bet, UP_DOWN_ODDS, answer);
        } else {
          betContinuation = hasIncorrectAnswer(bet, answer);
        }
        break;

      case "3":
        if (firstNumber == secondNumber) {
          betContinuation = hasCorrectAnswer(bet, SAME_ODDS, answer);
        } else {
          betContinuation = hasIncorrectAnswer(bet, answer);
        }
        break;
    }

    if (betContinuation.shouldContinueBet()) {
      playGame(firstNumber, secondNumber, bet);
    }
  }

  private BetContinuation hasCorrectAnswer(Bet bet, int odds, Answer answer) {
    BetContinuation betContinuation = new BetContinuation();
    bet.multiplyBet(odds);

    if (bet.getBet() + wallet >= GAME_CLEAR_GOLD) {
      this.wallet = GameContinuation.finishGame(bet, wallet, betContinuation);
    } else {
      betContinuation.askContinueBet();

      if (!betContinuation.shouldContinueBet()) {
        this.wallet += bet.getBet();
      }
    }
    return betContinuation;
  }

  private BetContinuation hasIncorrectAnswer(Bet bet, Answer answer) {
    BetContinuation betContinuation = new BetContinuation();
    this.wallet = bet.subtractBet(wallet, bet.getBet());
    return betContinuation;
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
