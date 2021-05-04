package scyoung.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Create 10 AVL trees, one for each size.
 
processing 1
processing 2
processing 3
processing 4
processing 5
processing 6
processing 7
Total Time:266.861
Enter word to start from (or press return to stop program.):
relapse
relapse
serape
spear
spar
spa
pa
a
 
 */
public class WordPyramid {
	static int MAX = 8;
	
	static Digraph graph;
	
	@SuppressWarnings("unchecked")
	static AVL<String>[] wordTrees = (AVL<String>[]) new AVL[11]; // 1 .. 10 and burn 0
	
	/**
	 * Represent the mapping of (uniqueID,word)
	 */
	static SeparateChainingHashST<String,Integer> table = new SeparateChainingHashST<String,Integer>();
	static SeparateChainingHashST<Integer,String> reverse = new SeparateChainingHashST<Integer,String>();
	
	/**
	 * Determine if the two same-sized words are off by just a single character.
	 */
	public static boolean offByOne(String w1, String w2) {
		int sameCount = 0;
		
		for (int i = 0; i < w1.length(); i++) {
			if (w1.charAt(i) == (w2.charAt(i))) {
				sameCount++;
			}
		}

		if (sameCount == (w1.length()-1)) {
			return true;
		} else {
			return false;
		}
	}
	
	/** 
	 * For bonus question.
	 * 
	 * Given a string of four-letters, determine if the five letter words contains
	 * each of these four letters distinctly in any arrangement.
	 * 
	 * Thus anagramPlusOne ("this", "shirt") is true, and anagramPlusOne("seas", "tasse") is true, but 
	 * anagramPlusOne ("meet", "tempt") is not because "tempt" has only one "e" while "meet" had two 
	 */
	static boolean anagramPlusOne(String word, String wordPlus) {
		int sameCount = 0;
		boolean j0 = false;
		boolean j1 = false;
		boolean j2 = false;
		boolean j3 = false;
		boolean j4 = false;
		
		for (int i = 0; i < word.length(); i++) {
			boolean found = false;
			for (int j = 0; j < wordPlus.length(); j++) {
				boolean compare = word.charAt(i) == (wordPlus.charAt(j));
				if (found == false) {
					if (compare) {
						if ((j == 0) && (j0 == false)) {
							sameCount++;
							found = true;
							j0 = true;
						} else if ((j == 1) && (j1 == false)) {
							sameCount++;
							found = true;
							j1 = true;
						} else if ((j == 2) && (j2 == false)) {
							sameCount++;
							found = true;
							j2 = true;
						} else if ((j == 3) && (j3 == false)) {
							sameCount++;
							found = true;
							j3 = true;
						} else if ((j == 4) && (j4 == false)) {
							sameCount++;
							found = true;
							j4 = true;
						}
					}	
				}
			}
		}

		if (sameCount == (word.length())) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		// Create AVL trees
		for (int i = 1; i <= MAX; i++) {
			wordTrees[i] = new AVL<String>();
		}
		
		// create a graph where each node represents a four-letter word.
		// Also construct avl tree of all four-letter words.
		Stopwatch sw = new Stopwatch();
		Scanner sc = new Scanner(new File ("words.english.txt"));
		int total = 0;
		while (sc.hasNext()) {
			String s = sc.next();
			int length = s.length();
			if (length <= 7) {
				wordTrees[length-1].insert(s);
				table.put(s, total);
				reverse.put(total, s);
				total++;
			}
		}
		sc.close();
		
		// now construct graph, where each node represents a word, and an edge exists between 
		// two nodes if their respective words are off by a single letter.
		total = total + 1; // reserve space for terminal one.
		graph = new Digraph(total);
		
		// TO DO LOTS HERE....
		
		// all back to special one. 
		for (String w1: wordTrees[1].keys()) {
			graph.addEdge( table.get(w1), 0);
		}
		table.put("", 0);
		reverse.put(0, "");
		
		System.out.printf("Total Time:%.3f\n", sw.elapsedTime());
		
		while (true) {
			StdOut.println("Enter word to start from (or press return to stop program):");
			String start = StdIn.readLine().toLowerCase();
			
			if (start.length() == 0) {
				break;
			}
			
			int len = start.length();
			if (!wordTrees[len].contains(start)) {
				System.out.println (start + "is an invalid word to start from.");
				continue;
			}
			
			// TO DO HERE....

		}
	}
}
