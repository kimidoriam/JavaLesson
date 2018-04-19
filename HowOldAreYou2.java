import java.io.*;

public class HowOldAreYou2 {
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String name1, name2;
			int age1, age2;
			// 1人目
			System.out.println("1人目の名前を入力してください");
			String line = reader.readLine();
			name1 = line;
			System.out.println(name1 + "さんの年齢を入力してください");
			line = reader.readLine();
			age1 = Integer.parseInt(line);
			// 2人目
			System.out.println("2人目の名前を入力してください");
			line = reader.readLine();
			name2 = line;
			System.out.println(name2 + "さんの年齢を入力してください");
			line = reader.readLine();
			age2 = Integer.parseInt(line);
			// 平均
			double ave = (age1 + age2) / 2.0; 
			System.out.println("二人の平均年齢は" + ave + "歳です。");
		} catch (IOException e) {
			System.out.println(e);
		} catch (NumberFormatException e) {
			System.out.println("年齢が正しくありません。");
		}
	}
}
