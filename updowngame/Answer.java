package updowngame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 答えを決めるクラスです。
 */
public class Answer {
  private String answer = ""; // 回答

  /**
   * 答えを入力するコンストラクタです。
   */
  public Answer() {
    while (!isValidAnswer()) {

      try {
        System.out.println("UP [1] or DOWN [2] or SAME [3]");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        answer = line;

        if (!isValidAnswer()) {
          System.out.println("1, 2, 3のいずれかを入力してください。");
        }
      } catch (IOException e) {
        System.out.println(e);
      }

    }
  }

  /**
   * 答えが適正かどうか判定するメソッドです。
   * 
   * @return 答えが適正の場合trueを返す
   */
  public boolean isValidAnswer() {
    return (answer.equals("1") || answer.equals("2") || answer.equals("3"));
  }

  public String getAnswer() {
    return answer;
  }
}
