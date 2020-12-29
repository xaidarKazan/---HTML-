package textCount.textResourse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Adress {

	public static boolean urlAdrees(URL url) {
		// определение на существование URL адреса
		try {
			System.out.println(url.toString());
			
			int response = ((HttpURLConnection) url.openConnection()).getResponseCode();
			
			if (response == 200) {
				System.out.println(" * URL is valid * ");
				return true;
			}
			
		} catch (IOException e) {
			System.out.println("Страницы с таким адресом не существует");
			System.out.println("Исключение : " + e);
			return false;
		}

		System.out.println("Страницы с таким адресом не существует");
		return false;
	}

}
