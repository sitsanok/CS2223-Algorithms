package scyoung.substring;

import edu.princeton.cs.algs4.StdArrayIO;
import edu.princeton.cs.algs4.StdOut;

public class Brute {
	static int count = 0;

	public static int search(String pat, String txt) {
		int M = pat.length();
		int N = txt.length();
		for (int i = 0; i <= N - M; i++) {
			int j;
			for (j = 0; j < M; j++) {
				count++;
				if (txt.charAt(i + j) != pat.charAt(j)) {
					break;
				}
			}
			if (j == M)
				return i; // found
		}
		return N; // not found
	}
}
