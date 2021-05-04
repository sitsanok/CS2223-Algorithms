package scyoung.hw3;

import scyoung.hw2.TaleOfTwoCitiesExtractor;

/**
 * Copy this class into your USERID.hw3 package and complete
 */
public class Question3 {

	public static void main(String[] args) throws java.io.IOException {

		// First Construct the Binary Search Tree from these Strings where
		// the associated value is the total number of times the key appeared
		// in "The Tale Of Two Cities".
		BST bt = new BST();

		// for all chapters
		for (int i = 1; i <= 45; i++) {

			// count the words in the chapter
			for (String s : new TaleOfTwoCitiesExtractor(i)) {

				// if key exists, get its value, increment the value
				// if key doesn't exist, put the pair in the table
				if (bt.get(s) != null) {
					int count = bt.get(s);
					count++;
					bt.put(s, count);
				} else {
					bt.put(s, 1);
				}
			}
		}

		System.out.println("Top ten most frequent words");

		// 1. the (7983)
		String one = bt.mostFrequent();
		System.out.println(one + " " + bt.get(one));

		// 2. and (4935)
		bt.delete(one);
		one = bt.mostFrequent();
		System.out.println(one + " " + bt.get(one));

		// 3. of (3999)
		bt.delete(one);
		one = bt.mostFrequent();
		System.out.println(one + " " + bt.get(one));

		// 4. to (3460)
		bt.delete(one);
		one = bt.mostFrequent();
		System.out.println(one + " " + bt.get(one));

		// 5. a (2908)
		bt.delete(one);
		one = bt.mostFrequent();
		System.out.println(one + " " + bt.get(one));

		// 6. in (2579)
		bt.delete(one);
		one = bt.mostFrequent();
		System.out.println(one + " " + bt.get(one));

		// 7. his (2005)
		bt.delete(one);
		one = bt.mostFrequent();
		System.out.println(one + " " + bt.get(one));

		// 8. it (2003)
		bt.delete(one);
		one = bt.mostFrequent();
		System.out.println(one + " " + bt.get(one));

		// 9. i (1913)
		bt.delete(one);
		one = bt.mostFrequent();
		System.out.println(one + " " + bt.get(one));

		// 10. that (1889)
		bt.delete(one);
		one = bt.mostFrequent();
		System.out.println(one + " " + bt.get(one));

		System.out.println();

		int n = bt.printUnique();
		System.out.println(n + " unique words.");

	}

}
