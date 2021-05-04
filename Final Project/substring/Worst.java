package scyoung.substring;

public class Worst {

	public static String makeSeq(int n) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String result = "";
		for (int i = 0; i < n; i++) {
			int index = (int) (26 * Math.random());
			result = result + (alphabet.charAt(index));
		}
		return result;
	}

	public static void run(int R, String pattern, String text) {
		// reset KMP counts since they're static
		KMP.patCount = 0;
		KMP.txtCount = 0;

		// create kmp and search
		KMP piKmp = new KMP(pattern, R);
		int search = piKmp.search(text);

		int txtLength = text.length();
		int patLength = pattern.length();
		int expected = txtLength + patLength;
		int kmpC = (KMP.txtCount + KMP.patCount);

		// worst case for KMP is N + M
		// N = text length
		// M = pattern length
		System.out.println("Comparisons   Expected");
		System.out.println("\t" + kmpC + "\t" + expected);

		if (expected == kmpC) {
			System.out.println("Yay! You found a worst case pair!");
		}
	}

	public static void main(String[] args) {
		String pattern, text;

		int R = 256;
		pattern = "aaaaa";
		text = "aaaabaaaataaaasaaaa";

		run(R, pattern, text);

		// just for readability once printed
		System.out.println();

		pattern = "abc";
		text = "abgabtabc";

		run(R, pattern, text);

		// just for readability once printed
		System.out.println();

		pattern = "qwertyuiop";
		text = "qwertyuiol";

		run(R, pattern, text);

		// just for readability once printed
		System.out.println();

		pattern = "2223";
		text = "222122242223";

		run(R, pattern, text);

		// just for readability once printed
		System.out.println();

		pattern = "sec";
		text = "sec";

		run(R, pattern, text);
	}
}
