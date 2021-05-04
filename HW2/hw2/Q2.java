package scyoung.hw2;

/**
 * Building from Question 1, there are different questions to answer.
 * 
 * 1. What is the most frequently used word in the entire book? 2. What are the
 * top-ten most frequently used words in the entire book? 3. How many words
 * occur exactly once in the book?
 */
public class Q2 {

	static void mostFrequent() throws java.io.IOException {
		String word = "";
		int max = 0;
		int totalWords = 0;
		WordSymbolTable table = new WordSymbolTable();

		// for all chapters
		for (int i = 1; i <= 45; i++) {

			// count each word, increment the word
			for (String s : new TaleOfTwoCitiesExtractor(i)) {
				totalWords++;
				table.increment(s);
			}
		}
		//
		word = table.mostFrequent();
		max = table.count(word);
		double ratio = (double) ((double)(max*100) / totalWords);

		System.out
				.println(String.format("\"%s\" is the most frequent word, used %d times out of %d total words (%.2f%%)",
						word, max, totalWords, ratio));

		int topTenWords = 0;

		System.out.println("The Top Ten words by frequency are:");
		System.out.println(String.format("%2d. %s (%d)", 1, word, max));

		// remove #1 to get the new #1
		topTenWords = max;
		table.remove(word);
		word = table.mostFrequent();
		max = table.count(word);
		System.out.println(String.format("%2d. %s (%d)", 2, word, max));

		// repeat
		topTenWords = topTenWords + max;
		table.remove(word);
		word = table.mostFrequent();
		max = table.count(word);
		System.out.println(String.format("%2d. %s (%d)", 3, word, max));

		// repeat
		topTenWords = topTenWords + max;
		table.remove(word);
		word = table.mostFrequent();
		max = table.count(word);
		System.out.println(String.format("%2d. %s (%d)", 4, word, max));

		// repeat
		topTenWords = topTenWords + max;
		table.remove(word);
		word = table.mostFrequent();
		max = table.count(word);
		System.out.println(String.format("%2d. %s (%d)", 5, word, max));

		// repeat
		topTenWords = topTenWords + max;
		table.remove(word);
		word = table.mostFrequent();
		max = table.count(word);
		System.out.println(String.format("%2d. %s (%d)", 6, word, max));

		// repeat
		topTenWords = topTenWords + max;
		table.remove(word);
		word = table.mostFrequent();
		max = table.count(word);
		System.out.println(String.format("%2d. %s (%d)", 7, word, max));

		// repeat
		topTenWords = topTenWords + max;
		table.remove(word);
		word = table.mostFrequent();
		max = table.count(word);
		System.out.println(String.format("%2d. %s (%d)", 8, word, max));

		// repeat
		topTenWords = topTenWords + max;
		table.remove(word);
		word = table.mostFrequent();
		max = table.count(word);
		System.out.println(String.format("%2d. %s (%d)", 9, word, max));

		// repeat
		topTenWords = topTenWords + max;
		table.remove(word);
		word = table.mostFrequent();
		max = table.count(word);
		System.out.println(String.format("%2d. %s (%d)", 10, word, max));

		topTenWords = topTenWords + max;
		double percent = (double) (((double) topTenWords*100) / totalWords);
		System.out.println(String.format("These ten words represent %.2f%% of the total words in the book", percent));

	}

	static void wordsUsedOnce() throws java.io.IOException {
		int numSingle = 0;
		String longest = ""; // to print the longest word that is found only once
		WordSymbolTable table = new WordSymbolTable();

		// for all chapters
		for (int i = 1; i <= 45; i++) {

			// count each word, increment the word
			for (String s : new TaleOfTwoCitiesExtractor(i)) {
				table.increment(s);
			}
		}
		// table contains all unique words now
		// need to find words that only appear once
		
		int n = table.size();
		while (n > 0) {
			if (table.last.count == 1) {
				numSingle++;
				if (table.last.word.length() > longest.length()) {
					longest = table.last.word;
				}
				table.remove(table.last.word);
			} else {
				table.remove(table.last.word);
			}
			n--;
		}
		System.out.println(String.format("%d words are used exactly once (longest is \"%s\")", numSingle, longest));
	}

	public static void main(String[] args) throws java.io.IOException {
		mostFrequent();
		wordsUsedOnce();
	}
}
