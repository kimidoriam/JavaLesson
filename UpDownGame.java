import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class UpDownGame {
  private static final int START_GOLD = 100_000; // ゲーム開始時の所持金
  private static final int MAX_BET_GOLD = 30_000; // ベット上限
  private static final int GAME_OVER_GOLD = 0; // ゲーム終了の金額
  private static final int GAME_CLEAR_GOLD = 500_000; // ゲームクリアの金額
  private static final int UP_DOWN_ODDS = 2; // UPとDOWNのオッズ
  private static final int SAME_ODDS = 5; // SAMEのオッズ

  public static void main(String[] args) {
    boolean shouldContinueGame = true; // ゲーム継続判定
    boolean isValidBet = false; // ベット金額有効判定
    boolean isValidAnswer = false; // 回答有効判定
    boolean shouldContinueBet = true; // 正解したときのベット継続判定
    int wallet = START_GOLD; // プレイヤーの所持金
    int bet = 0; // ベット額
    int firstNumber, secondNumber; // 問題の数字1, 2
    String answer = ""; // 回答

    while (shouldContinueGame) {
      System.out.println("現在の所持金：" + wallet + "G");

      while (!isValidBet) {

        try {
          System.out.println("ベットする金額を入力してください。（3万Gまで）");
          BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
          String line = reader.readLine();

          try {
            bet = Integer.parseInt(line);

            if (bet > MAX_BET_GOLD) {
              System.out.println("1度にベットできる金額は3万Gまでです。");
            } else if (bet > wallet) {
              System.out.println("所持金を越える金額はベットできません。");
            } else if (bet <= 0) {
              System.out.println("1以上の整数で入力してください。");
            } else {
              isValidBet = true;
            }
          } catch (NumberFormatException e) {
            System.out.println("整数で入力してください。");
          }
        } catch (IOException e) {
          System.out.println(e);
        }

      }

      do {

        Random random = new Random(); // 1つめの数字生成
        firstNumber = random.nextInt(13) + 1;
        System.out.println("NUMBER : " + firstNumber);

        while (!isValidAnswer) {

          try {
            System.out.println("UP [1] or DOWN [2] or SAME [3]");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = reader.readLine();
            answer = line;

            if (!(answer.equals("1") || answer.equals("2") || answer.equals("3"))) {
              System.out.println("1, 2, 3のいずれかを入力してください。");
            } else {
              isValidAnswer = true;
            }
          } catch (IOException e) {
            System.out.println(e);
          }

        }

        secondNumber = random.nextInt(13) + 1; // 2つめの数字生成
        System.out.println("ANSWER : " + secondNumber);

        switch (answer) {

        case "1":
          if (firstNumber < secondNumber) {
            bet = multiplyBet(bet, UP_DOWN_ODDS);

            if (bet + wallet >= GAME_CLEAR_GOLD) {
              System.out.println("ベット額と所持金の合計が" + (bet + wallet) + "Gになりました。");
              wallet += bet;
              shouldContinueBet = false;
              shouldContinueGame = false;
            } else {
              shouldContinueBet = shouldContinueBet();
              isValidAnswer = false;

              if (!shouldContinueBet) {
                wallet += bet;
                isValidBet = false;
              }
            }

          } else {
            wallet = subtractBet(wallet, bet);
            isValidBet = false;
            isValidAnswer = false;
            shouldContinueBet = false;

            if (wallet <= GAME_OVER_GOLD) {
              shouldContinueGame = false;
            }
          }
          break;

        case "2":
          if (firstNumber > secondNumber) {
            bet = multiplyBet(bet, UP_DOWN_ODDS);

            if (bet + wallet >= GAME_CLEAR_GOLD) {
              System.out.println("ベット額と所持金の合計が" + (bet + wallet) + "Gになりました。");
              wallet += bet;
              shouldContinueBet = false;
              shouldContinueGame = false;
            } else {
              shouldContinueBet = shouldContinueBet();
              isValidAnswer = false;

              if (!shouldContinueBet) {
                wallet += bet;
                isValidBet = false;
              }
            }

          } else {
            wallet = subtractBet(wallet, bet);
            isValidBet = false;
            isValidAnswer = false;
            shouldContinueBet = false;

            if (wallet <= GAME_OVER_GOLD) {
              shouldContinueGame = false;
            }
          }
          break;

        case "3":
          if (firstNumber == secondNumber) {
            bet = multiplyBet(bet, SAME_ODDS);

            if (bet + wallet >= GAME_CLEAR_GOLD) {
              System.out.println("ベット額と所持金の合計が" + (bet + wallet) + "Gになりました。");
              wallet += bet;
              shouldContinueBet = false;
              shouldContinueGame = false;
            } else {
              shouldContinueBet = shouldContinueBet();
              isValidAnswer = false;

              if (!shouldContinueBet) {
                wallet += bet;
                isValidBet = false;
              }
            }

          } else {
            wallet = subtractBet(wallet, bet);
            isValidBet = false;
            isValidAnswer = false;
            shouldContinueBet = false;

            if (wallet <= GAME_OVER_GOLD) {
              shouldContinueGame = false;
            }
          }
          break;

        }
      } while (shouldContinueBet);
    }

    if (wallet >= GAME_CLEAR_GOLD) {
      System.out.println("所持金が50万G以上になりました。");
    } else {
      System.out.println("所持金が0になりました。");
    }
    System.out.println("ゲームを終了します。");
  }

  private static int multiplyBet(int bet, int x) {
    System.out.println("正解！ベット額が" + x + "倍になります。");
    bet *= x;
    System.out.println("現在のベット額：" + bet + "G");
    return bet;
  }

  private static int subtractBet(int wallet, int bet) {
    System.out.println("不正解！ベット額を没収します。");
    wallet -= bet;
    return wallet;
  }

  private static boolean shouldContinueBet() {
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
      return true;
    } else {
      return false;
    }
  }

}
