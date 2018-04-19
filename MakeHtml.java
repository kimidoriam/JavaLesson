import java.io.*;

public class MakeHtml {
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("<!DOCTYPE html>");
		System.out.println("<html>");
		System.out.println("<head>");
		System.out.println("<title>My Page</title>");
		System.out.println("</head>");
		System.out.println("<body>");
		
		try {
			String line = reader.readLine();
			while(line != null) {
				char c = line.charAt(0);
				if (c == '■'){
					String s = line.substring(1);
					System.out.println("<h1>" + s + "</h1>");
				} else if (c == '●') {
					String s = line.substring(1);
					System.out.println("<h2>" + s + "</h2>");
				} else {
					System.out.println("<p>" + line + "</p>");
				}
				line = reader.readLine();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		
		System.out.println("</body>");
		System.out.println("</html>");
	}
}
