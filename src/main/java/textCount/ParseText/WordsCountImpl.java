package textCount.ParseText;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WordsCountImpl implements WordsCount {
	private Path filePath;
	private int maxlengthOfWord = 1;
	private Map<String, Integer> wordsCountMap = new HashMap<String, Integer>();

	public WordsCountImpl(Path filePath) {
		this.filePath = filePath;
	}

	public void parse() {
		try {
			Document doc = Jsoup.parse(filePath.toFile(), "UTF-8");
			Element element = doc.body();
//			По условию задачи разделителями слов считать следующие символы: 
//			' ', ',', '.', '!', '?','"', ';', ':', '[', ']', '(', ')', '\n', '\r', '\t'
			String[] text = element.text().split("(\\d+)" 
												+ "|(\\s+)" 
												+ "|([,.!?;:()]+)" 
												+ "|\\[+|\\]+" 
												+ "|\"+");
			
			for (String word : text) {
				word = word.trim().toLowerCase();
				if (maxlengthOfWord < word.length()) maxlengthOfWord = word.length(); 
				if (!word.isEmpty() && word.length() > 1) {
					if (wordsCountMap.containsKey(word)) {
						Integer count = wordsCountMap.get(word) + 1;
						wordsCountMap.put(word, count);
					} else {
						wordsCountMap.put(word, 1);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Исключение: " + e);
		}
	}

	public Path getFile() {
		return filePath;
	}

	public void setFile(Path filePath) {
		this.filePath = filePath;
	}

	@Override
	public Map<String, Integer> wordsCountMap() {
		return wordsCountMap;
	}

	@Override
	public void print() {
		System.out.println(" * Words on this URL * " );
		System.out.println(" = = = = = = = = = = = = = = = = = = =");
		String format = "      %-" + (maxlengthOfWord + 5) + "s %d";
		for (Map.Entry<String, Integer> map : wordsCountMap.entrySet()) {			
			String wordAndCount = String.format(format, map.getKey(), map.getValue());			
			System.out.println(wordAndCount);
		}
		System.out.println(" = = = = = = = = = = = = = = = = = = =");
	}

}
