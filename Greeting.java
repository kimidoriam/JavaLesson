import java.io.*;

public class Greeting {
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			int time;
			System.out.println("現在の時刻を入力してください");
			String line = reader.readLine();
			time = Integer.parseInt(line);
			if (time < 0 || time > 23) {
				System.out.println("時刻の範囲を越えています");
			} else if (time < 12) {
				System.out.println("おはようございます");
			} else if (time == 12) {
				System.out.println("お昼です");
			} else if (time > 12 && time < 19) {
				System.out.println("こんにちは");
			} else {
				System.out.println("こんばんは");
			}
		} catch (IOException e) {
			System.out.println(e);
		} catch (NumberFormatException e) {
			System.out.println("時刻が正しくありません。");
		}
	}
}
