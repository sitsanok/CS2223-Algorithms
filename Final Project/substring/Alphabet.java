package scyoung.substring;

import edu.princeton.cs.algs4.StdOut;

public class Alphabet {
	public Alphabet() {}
	
	public String makeSeq(int n) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String result= "";
		for (int i = 0; i < n ; i++) {
			int index = (int)(26*Math.random());
			result=result+(alphabet.charAt(index));
		}
		return result;
	}
	public String lastPat(String text,int n) {
		String result = "";
		for (int i = 0; i<n; i++) {
			char add= text.charAt(text.length()-n+i);
			result += add;
		}
		return result;
	}
	
	public void test(int exp, int increment) {
		
		int patLength =(int)Math.pow(2, exp);
		double[] totalKMP = new double[patLength];
		double[] totalBrute = new double[patLength];
		StdOut.println("pat length   KMP      brute");

		StdOut.println("text length: "+(String.valueOf(exp)));

		for (int j = 0 ; j < 100 ; j ++) { 						//100 trials 
			String txt1 = this.makeSeq((int)(Math.pow(2, exp)));
			for (int i = 1; i < patLength+1; i += increment) {
				String pattern1 = this.lastPat(txt1, i);

				KMP kmp = new KMP(pattern1, 256);
				Brute brute = new Brute();

				Brute.search(pattern1, txt1);
				kmp.search(txt1);

				int kmpCalc = KMP.patCount+KMP.txtCount;
				int bruteCalc = Brute.count;
				totalKMP[i-1]= totalKMP[i-1]+kmpCalc;
				totalBrute[i-1]=totalBrute[i-1]+bruteCalc;

				System.out.println( pattern1.length()+"\t"+ kmpCalc+"\t"+ bruteCalc);

				KMP.patCount = 0;
				KMP.txtCount = 0;
				Brute.count = 0;
			}
			StdOut.println();
		}
		StdOut.println("pat length   KMP      brute");
		for (int i = 0; i < patLength; i+=increment) {
			double avgKMP = totalKMP[i]/100;
			double avgBrute = totalBrute[i]/100;
			System.out.println( i+1 +"\t"+ avgKMP+"\t"+ avgBrute);
			totalKMP[i] = 0;
			totalBrute[i] = 0;
		}
	}
	
	
	public static void main(String[] args) {
		Alphabet abc = new Alphabet();
		abc.test(14, 1000);
		abc.test(15, 5000);
		abc.test(16, 10000);
	}
}
