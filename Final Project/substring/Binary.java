package scyoung.substring;

import java.util.Random;
import java.util.Scanner;

import edu.princeton.cs.algs4.StdOut;

public class Binary {
	
	public Binary() {}
	
	public String makeSeq(int n) {
		String alphabet = "01";
		String result= "";
		for (int i = 0; i < n ; i++) {
			int index = (int)(2*Math.random());
			result=result+(alphabet.charAt(index));
		}
		return result;
	}
	
	/**
	 * 
	 * @param len length of randomized binary string
	 * @return randomized binary string of length len
	 */
	public static String binNumber(int len) {
	    String binNum = "";
	    for(int i = 0; i < len; i++) {
	    	if(Math.random() < 0.5) { binNum += "0"; }
		    else { binNum += "1"; }
	    }
	    return binNum;
	}
	
	

	public static void main(String[] args) {

		int[] averageKMP = new int [11];
		int[] averageBRUTE = new int [11];
		
		Binary bin = new Binary();

		for(int exp = 14; exp <= 19; exp++) {
			StdOut.println("text length: 2^" + exp);
			StdOut.println("pat length 	    KMP                Brute");

			for(int trials = 1; trials <= 100; trials++) {
				
				String text = bin.makeSeq((int)(Math.pow(2,exp)));
	
				KMP.patCount = 0;
				KMP.txtCount = 0;
				Brute.count = 0;
				
				for(int i = 1; i <= 10; i++) {
					
					String pattern = binNumber(i);
					KMP kmp = new KMP(pattern, 2);
					Brute brute = new Brute();

					Brute.search(pattern, text);
					kmp.search(text);

					int kmpCalc = kmp.patCount + kmp.txtCount;
					int bruteCalc = brute.count;

					averageKMP[i] += kmpCalc;
					averageBRUTE[i] += bruteCalc;


					System.out.println(i  +"\t"+ kmpCalc +"\t"+ bruteCalc);

				}
				StdOut.println();
			}

			StdOut.println("Averages for Text Length 2^" + exp);
			
			for(int i = 1; i < 11; i++) {
				StdOut.println(i +"\t"+ ((float)averageKMP[i]/100) +"\t"+ ((float)averageBRUTE[i]/100));
			}
			
			StdOut.println();
		}
	}
}