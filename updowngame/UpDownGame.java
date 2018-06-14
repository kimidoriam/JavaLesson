package updowngame;

import java.io.IOException;
import java.util.Random;

/**
 * アップダウンゲームを実行するクラスです。
 */
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

  /**
   * 所持金を表示し、ベット額を決めてゲームを始めるメソッドです。
   */
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

  /**
   * 数字を生成し、答えを判定するメソッドです。
   * 
   * @param firstNumber
   *          1つめの数字
   * @param secondNumber
   *          2つめの数字
   * @param bet
   *          ベットしている金額
   */
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
          betContinuation = hasCorrectAnswer(bet, UP_DOWN_ODDS);
        } else {
          betContinuation = hasIncorrectAnswer(bet);
        }
        break;

      case "2":
        if (firstNumber > secondNumber) {
          betContinuation = hasCorrectAnswer(bet, UP_DOWN_ODDS);
        } else {
          betContinuation = hasIncorrectAnswer(bet);
        }
        break;

      case "3":
        if (firstNumber == secondNumber) {
          betContinuation = hasCorrectAnswer(bet, SAME_ODDS);
        } else {
          betContinuation = hasIncorrectAnswer(bet);
        }
        break;
    }

    if (betContinuation.shouldContinueBet()) {
      playGame(firstNumber, secondNumber, bet);
    }
  }

  /**
   * 正解の場合に呼び出すメソッドです。
   * 
   * @param bet
   *          ベットしている金額
   * @param odds
   *          オッズ
   * @return ベットを継続する場合trueを返す
   */
  private BetContinuation hasCorrectAnswer(Bet bet, int odds) {
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

  /**
   * 不正解の場合に呼び出すメソッドです。
   * 
   * @param bet
   *          ベットしている金額
   * @return ベットを継続する場合trueを返す
   */
  private BetContinuation hasIncorrectAnswer(Bet bet) {
    BetContinuation betContinuation = new BetContinuation();
    this.wallet = bet.subtractBet(wallet, bet.getBet());
    return betContinuation;
  }

  /**
   * ゲームを終了するメソッドです。
   * 
   * @param wallet
   *          プレイヤーの所持金
   */
  private void endGame(int wallet) {
    if (wallet >= GAME_CLEAR_GOLD) {
      System.out.println("所持金が50万G以上になりました。");
    } else {
      System.out.println("所持金が0になりました。");
    }
    System.out.println("ゲームを終了します。");
  }
}
