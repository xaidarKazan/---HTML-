package textCount.ParseText;

import java.util.Map;

public interface WordsCount {
	Map<String, Integer> wordsCountMap();
	void parse();
	void print();
}
