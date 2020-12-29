package textCount;

import java.net.MalformedURLException;
import java.net.URL;
import textCount.Download.DownloadHtmlPageToHtmlFile;
import textCount.Download.DownloadWebPage;
import textCount.ParseText.WordsCount;
import textCount.ParseText.WordsCountImpl;
import textCount.textResourse.Adress;

public class Main {

	public static void main(String[] args) {

		String adress = "https://www.simbirsoft.com/";

		try {
			URL url = new URL(adress);
			boolean normAdress = Adress.urlAdrees(url);
			if (normAdress) {
				DownloadWebPage dwp = new DownloadHtmlPageToHtmlFile(url);
				dwp.download();

				if (dwp.isDownloadSuccessfully()) {
					WordsCount wordsCount = new WordsCountImpl(dwp.getDownloadFile());
					wordsCount.parse();
					wordsCount.print();
				}
			}

		} catch (MalformedURLException e) {
			System.out.println("Исключение: " + e);
		}

	}

}
