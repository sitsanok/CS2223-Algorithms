package scyoung.hw2;

import java.security.AllPermission;

import edu.princeton.cs.algs4.Count;
import scyoung.hw2.WordList.Node;

/**
 * For this question, you are to process the book "The Tale of Two Cities" as
 * included in the repository.
 * 
 * There are 45 chapters in the book, which I have extracted into separate
 * files. I will admit that the transcription is quite awkward. For example,
 * everything has been converted to lower case, and all punctuation marks have
 * been removed. Some words are subdivided improperly, but this is what we have
 * to work with!
 * 
 * The questions you are to answer are:
 * 
 * 1. Which chapter contains the most # of words in total, as returned by the
 * TaleOfTwoCitiesExtractor 2. Which chapter (of the 45) contains the most # of
 * unique words? and how many unique words occur in that chapter. 3. Which two
 * distinct chapters share the most words in common? And how many words is that?
 * 
 * The definition of a word is given to you by the TaleOfTwoCitiesExtractor
 * class, which provides an Iterable interface to a given chapter. This object
 * will return the words in a chapter, one at a time, in the order they appear
 * in the chapter.
 * 
 * Your first responsibility is to ensure that TaleOfTwoCitiesExtractor works in
 * your location.
 */
public class Q1 {

	/** Complete this implementation. */
	static void largestChapter() throws java.io.IOException {
		int chapter = -1; // the largest chapter seen so far
		int max = -1; // largest sum ever seen

		// for all chapters
		for (int i = 1; i <= 45; i++) {
			int count = 0; // reset count

			// count the words in the chapter
			for (String s : new TaleOfTwoCitiesExtractor(i)) {
				count++;
			}
			// if greater than ever seen, then keep track of it
			if (count > max) {
				max = count; // count becomes max
				chapter = i; // record chapter #
			}
		}
		System.out.println(
				String.format("The chapter with the most number of words is %d with a total of %s", chapter, max));
	}

	/** Complete this implementation. */
	static void fewestUniqueWords() throws java.io.IOException {
		int chapter = -1;
		int minUnique = Integer.MAX_VALUE;

		// for all chapters
		for (int i = 1; i <= 45; i++) {
			int count = 0;
			WordList list = new WordList();

			// count ONLY unique words in the chapter
			for (String s : new TaleOfTwoCitiesExtractor(i)) {
				if (list.add(s) == true) {
					list.add(s);
					count++;
				}
			}

			// if less than ever seen, then keep track of it
			if (count < minUnique) {
				minUnique = count;
				chapter = i;
			}
		}
		System.out.println(String.format("The chapter with the fewest number of unique words is %d with a total of %s",
				chapter, minUnique));
	}

	/** Complete this implementation. */
	static void totalUniqueWords() throws java.io.IOException {
		int totalUnique = 0;
		WordList list = new WordList();

		// for all chapters
		for (int i = 1; i <= 45; i++) {

			// count ONLY unique words in all chapters
			for (String s : new TaleOfTwoCitiesExtractor(i)) {
				if (list.add(s) == true) {
					list.add(s);
					totalUnique++;
				}
			}
		}
		System.out.println(String.format("There are a total of %d unique words in the book.", totalUnique));
	}

	/** Complete this implementation. */
	static void twoChaptersShareMostInCommon() throws java.io.IOException {
		int chapter1 = -1;
		int chapter2 = -1;
		int maxShared = -1;

		// for all chapters
		for (int i = 1; i <= 45; i++) {
			WordList list = new WordList();
			int matches;

			for (String s : new TaleOfTwoCitiesExtractor(i)) {
				if (list.add(s) == true) {
					list.add(s);
				}
			}

			for (int j = 1; j <= 45; j++) {
				matches = 0;
				WordList otherList = new WordList();

				if (i != j) {
					for (String string : new TaleOfTwoCitiesExtractor(j)) {
						if (otherList.add(string) == true) {
							otherList.add(string);
							if (list.contains(string)) {
								matches++;
							}
						}
					}
				}
				if (matches > maxShared) {
					chapter1 = i;
					chapter2 = j;
					maxShared = matches;
				}
			}
		}
		System.out.println(String.format(
				"The two chapters that share the most words in common are chapters %d and %d with a total of %s words",
				chapter1, chapter2, maxShared));
	}

	/** Leave this method alone. */
	public static void main(String[] args) throws java.io.IOException {
		largestChapter();
		fewestUniqueWords();
		totalUniqueWords();
		twoChaptersShareMostInCommon();
	}
}
