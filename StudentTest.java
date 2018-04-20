public class StudentTest {
	public static void main(String[] args) {
		Student[] data = {
				new Student("結城浩", 65, 90, 100),
				new Student("阿部和真", 82, 73, 64),
				new Student("伊藤光一", 74, 31, 42),
				new Student("佐藤太郎", 100, 95, 99),
		};
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i] + "\t-> " + data[i].total());
		}
	}
}
