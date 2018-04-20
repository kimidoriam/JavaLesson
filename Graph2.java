public class Graph2 {
	public static void main(String[] args) {
		for (int i = 8; i > 0; i--) {
			printGraph(i*i);
		}
		for (int i = 0; i < 9; i++) {
			printGraph(i*i);
		}
	}
	
	public static void printGraph(int x) {
		for (int i = 0; i < x; i++) {
			System.out.print("*");
		}
		System.out.println("");
	}
}
