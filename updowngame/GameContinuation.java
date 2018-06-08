package updowngame;

public class GameContinuation {

  public static int finishGame(Bet bet, int wallet, BetContinuation betContinuation) {
    System.out.println("ベット額と所持金の合計が" + (bet.getBet() + wallet) + "Gになりました。");
    wallet += bet.getBet();
    return wallet;
  }

  public static boolean shouldContinueGame(int wallet, int GAME_OVER_GOLD, int GAME_CLEAR_GOLD) {
    return !(wallet <= GAME_OVER_GOLD || wallet >= GAME_CLEAR_GOLD);
  }
}