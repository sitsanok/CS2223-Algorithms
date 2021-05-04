package scyoung.substring;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Pi {

	public static String makeSeq(int n) {
		String alphabet = "0123456789";
		String result = "";
		for (int i = 0; i < n; i++) {
			int index = (int) (10 * Math.random());
			result = result + (alphabet.charAt(index));
		}
		return result;
	}

	/**
	 * performs 100 trials for each pattern length against text
	 * @param pattern desired string to check against text
	 * @param text desired string to check pattern against
	 * @param R radix
	 * @param maxPatLength maximum pattern length desired
	 * @param increment number desired to be incremented after every 100 trials of a pattern length
	 */
	public static void run(String pattern, String text, int R, int maxPatLength, int increment) {
		
		int[] kmpAve = new int[maxPatLength+1];
		int[] bAve = new int[maxPatLength+1];
		int patLength = 0;
		
		for (int j = 1; j <= maxPatLength; j = j + increment) {

			for (int i = 1; i <= 100; i++) {
				pattern = makeSeq(j);

				// reset KMP counts since they're static
				KMP.patCount = 0;
				KMP.txtCount = 0;

				// create kmp and search
				KMP piKmp = new KMP(pattern, R);
				int search = piKmp.search(text);

				// reset brute count since it's static
				Brute.count = 0;

				// search via brute
				Brute.search(pattern, text);
				
				int kmpC = (KMP.txtCount + KMP.patCount);
				int bruteC = Brute.count;

				patLength = pattern.length();
				
				kmpAve[j] += kmpC;
				bAve[j] += bruteC;

				System.out.println(patLength + "\t" + kmpC + "\t" + bruteC);
			}
			
		}
		for (int i = 1; i <= maxPatLength; i++) {
			if (!((kmpAve[i]/100) == 0)) {
				System.out.println(i + "\t" +  ((double) kmpAve[i]/100.0) + "\t" + ((double)bAve[i]/100.0));
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {

		String pattern = "", text = "";
		int R = 10; // radix is 10

		// 4 text sizes of pi
		File fourteen = new File("2^14pi.txt");
		Scanner scFour = new Scanner(fourteen);
		text = scFour.nextLine();
		
		Pi.run(pattern, text, R, 1401, 100);
		
		File fifteen = new File("2^15pi.txt");
		Scanner scFive = new Scanner(fifteen);
		text = scFive.nextLine();
		
		Pi.run(pattern, text, R, 2401, 100);
		
		File sixteen = new File("2^16pi.txt");
		Scanner scSix = new Scanner(sixteen);
		text = scSix.nextLine();
		
		Pi.run(pattern, text, R, 5401, 100);
	}
}