package Task1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MyWordCount {
	// public static final String fileName = "data/hamlet.txt";
	public static final String fileName = "data/fit.txt";

	private List<String> words = new ArrayList<>();

	public MyWordCount() {
		try {
			this.words.addAll(Utils.loadWords(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<WordCount> getWordCounts() {
		List<WordCount> result = new ArrayList<>();
		for (String w : words) {
			WordCount wc = new WordCount(w, 1);
			if (!result.contains(wc)) {
				result.add(wc);
			} else {
				wc = result.get(result.indexOf(wc));
				wc.setCount(wc.getCount() + 1);
			}
		}
		return result;
	}

	private int count(String w) {
		int count = 0;
		for (int i = 0; i < words.size(); i++) {
			if (words.get(i).equals(w)) {
				count++;
			}
		}
		return count;

	}

	public Set<String> getUniqueWords() {
		Set<String> result = new HashSet<>();
		for (WordCount wc : getWordCounts()) {
			if (wc.getCount() == 1) {
				result.add(wc.getWord());
			}

		}
		return result;
	}

	public Set<String> getDistinctWords() {
		Set<String> result = new HashSet<>();
		for (String s : words) {

			result.add(s);

		}
		return result;
	}

	public Set<WordCount> printWordCounts() {
		Set<WordCount> result = new TreeSet<>(new Comparator<WordCount>() {
			public int compare(WordCount wc1, WordCount wc2) {
				return wc1.getWord().compareTo(wc2.getWord());

			}
		});
		result.addAll(this.getWordCounts());

		return result;
	}

	public Set<WordCount> exportWordCountsByOccurence() {
		// TODO
		return null;
	}

	public Set<String> filterWords(String pattern) {
		Set<String> result = new HashSet<>();
		for (String s : words) {
			if (!s.startsWith(pattern)) {
				result.add(s);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		MyWordCount result = new MyWordCount();

		System.out.println(result.getWordCounts());
	}

}
