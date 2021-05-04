package scyoung.hw3;

import edu.princeton.cs.algs4.StdArrayIO;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * This is the template code for question 1.
 *
 * Be sure to Explain whether the empirical results support the proposition.
 *
 */
public class Question1 {
	public static void main(String[] args) {
		// for N in 16 .. 512

		// for each N, do T=100 trials you want to keep track of
		// what you believe to be the MOST number of exch invocations
		// and most number of less invocations

		// compute a random array of N uniform doubles

		// Make sure you output for each N the maximum values you saw
		// in a table like...
		//
		// N MaxComp MaxExch
		// 16 22 8
		// .....

		System.out.println("N          MaxComp          MaxExch");
		
		for (int n = 16; n <= 512; n *= 2) {
			int maxLess = 0;
			int maxExch = 0;
			
			for (int t = 0; t < 100; t++) {
				Heap.lessCount = 0;
				Heap.exchCount = 0;
				Double a[] = new Double[n];
				int lessCount = 0;
				int exchCount = 0;
				
				for (int i = 0; i < n; i++) {
					a[i]= StdRandom.uniform();
				}
				Heap.constructHeap(a);
				lessCount = Heap.lessCount;
				exchCount = Heap.exchCount;
				
				if (lessCount > maxLess) {
					maxLess = lessCount;
				}
				if (exchCount > maxExch) {
					maxExch = exchCount;
				}
			}
			System.out.println(String.format("%d\t %8d %16d", n, maxLess, maxExch));
		}
	}
}
