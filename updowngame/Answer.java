package updowngame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Answer {
  private boolean isValidAnswer = false; // 回答有効判定
  private String answer = ""; // 回答

  public Answer() {
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
  }

  public String getAnswer() {
    return answer;
  }

  public void setIsValidAnswer(boolean isValidAnswer) {
    this.isValidAnswer = isValidAnswer;
  }
}
