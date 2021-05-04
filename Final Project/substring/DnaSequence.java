package scyoung.substring;

import edu.princeton.cs.algs4.StdOut;

public class DnaSequence {
	public DnaSequence() {}
	
	public String makeSeq(int n) {
		String alphabet = "acgt";
		String result= "";
		for (int i = 0; i < n ; i++) {
			int index = (int)(4*Math.random());
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
	public static void main(String[] args) {
		DnaSequence dna = new DnaSequence();
		int patLength =10;
		double[] totalKMP = new double[patLength];
		double[] totalBrute = new double[patLength];
		StdOut.println("pat length   KMP      brute");
		for (double exp = 14; exp<=17; exp+=1) {					//2^14-2^17
			StdOut.println("text length: "+(String.valueOf(exp)));
			
			for (int j = 0 ; j < 100 ; j ++) { 						//100 trials 
				String txt1 = dna.makeSeq((int)(Math.pow(2, exp)));
				for (int i = 1; i < patLength+1; i += 1) {
					String pattern1 = dna.lastPat(txt1, i);

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
			for (int i = 0; i < patLength; i+=1) {
				double avgKMP = totalKMP[i]/100;
				double avgBrute = totalBrute[i]/100;
				System.out.println( i+1 +"\t"+ avgKMP+"\t"+ avgBrute);
				totalKMP[i] = 0;
				totalBrute[i] = 0;
			}
			
		}
		
	
	}
}
