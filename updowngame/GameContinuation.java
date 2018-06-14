package updowngame;

/**
 * ゲームを終了したり、継続を判定するクラスです。
 */
public class GameContinuation {
  /**
   * ゲーム中に、ベット額と所持金の合計がゲームクリアの金額になった場合のメソッドです。
   * 
   * @param bet
   *          ベット額
   * @param wallet
   *          プレイヤーの所持金
   * @param betContinuation
   *          ベットを継続するかどうか
   * @return プレイヤーの所持金を返す
   */
  public static int finishGame(Bet bet, int wallet, BetContinuation betContinuation) {
    System.out.println("ベット額と所持金の合計が" + (bet.getBet() + wallet) + "Gになりました。");
    wallet += bet.getBet();
    return wallet;
  }

  /**
   * ベットを継続するかどうか判定するメソッドです。
   * 
   * @param wallet
   *          プレイヤーの所持金
   * @param GAME_OVER_GOLD
   *          ゲーム終了の金額
   * @param GAME_CLEAR_GOLD
   *          ゲームクリアの金額
   * @return ゲームを継続する場合trueを返す
   */
  public static boolean shouldContinueGame(int wallet, int GAME_OVER_GOLD, int GAME_CLEAR_GOLD) {
    return !(wallet <= GAME_OVER_GOLD || wallet >= GAME_CLEAR_GOLD);
  }
}
