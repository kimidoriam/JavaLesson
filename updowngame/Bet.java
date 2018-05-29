package updowngame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bet {
  private boolean isValidBet = false; // ベット金額有効判定
  private int bet = 0; // ベット額

  public Bet(int maxBetGold, int wallet) {
    while (!isValidBet) {

      try {
        System.out.println("ベットする金額を入力してください。（3万Gまで）");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        try {
          bet = Integer.parseInt(line);

          if (bet > maxBetGold) {
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
  }

  public boolean getIsValidBet() {
    return isValidBet;
  }

  public int getBet() {
    return bet;
  }

  public void setIsValidBet(boolean isValidBet) {
    this.isValidBet = isValidBet;
  }

  public void multiplyBet(int x) {
    System.out.println("正解！ベット額が" + x + "倍になります。");
    this.bet *= x;
    System.out.println("現在のベット額：" + bet + "G");
  }

  public int subtractBet(int wallet, int bet) {
    System.out.println("不正解！ベット額を没収します。");
    wallet -= bet;
    return wallet;
  }
}
