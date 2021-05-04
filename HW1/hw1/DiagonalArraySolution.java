package scyoung.hw1;

import java.util.LinkedList;

import algs.hw1.arraysearch.DiagonalArraySearch;

/**
 * Copy this class into your package, which must have USERID has its root.
 */
public class DiagonalArraySolution extends DiagonalArraySearch {

	/** Construct problem solution for given array. Do not modify this method. */
	public DiagonalArraySolution(int[][] a) {
		super(a);
	}
	
	/** 
	 * For this homework assignment, you need to complete the implementation of this
	 * method.
	 */
	@Override
	public int[] locate(int target) {
		int n = this.length();
		
		// Find the diagonal in the 0 column in which the target could exist
		int lo = 0;
		int hi = n-1;
		
		while (lo <= hi) {
			int mid = (lo+hi)/2;

			int rc = inspect(mid, 0) - target;
			
			if (rc < 0) {
				lo = mid+1;
			} else if (rc > 0 ) {
				hi = mid-1;
			} else {
				return new int[] { mid, 0 }; // Return r,c where I found target 
			}
		}
		
		//If number hasn't been found, it could be smaller
		if ((hi == -1)) {
			return null;
		}
		
		// If number hasn't been found, we know which diagonal it's in
		// hi points to the diagonal that the value must be in
				
		// colLo starts at 1 since target can't be in the 0 column
		int rowLo = hi+1;
		int rowHi = n-1;
		int colLo = 1;
		int colHi = n-hi-1;
		
		while (rowLo <= rowHi) {
			
			int rowMid = (rowLo+rowHi)/2;
			int colMid = (colLo+colHi)/2;

			int rc = inspect(rowMid, colMid) - target;
					
			if (rc < 0) {
				rowLo = rowMid+1;
				colLo = colMid+1;
				if (rowLo > n-1 || colLo > n-1) {
					return null;
				}
			} else if (rc > 0 ) {
				rowHi = rowMid-1;
				colHi = colMid-1;
				if (rowHi < 0 || colHi < 0) {
					return null;
				}
			} else {
			return new int[] { rowMid, colMid }; // return r,c where I found target 
			}
		}		
		return null;
	}
	
	/** Be sure that you call your class constructor. Do not modify this method. */ 
	public static void main (String args[]) {
		int[][] ar = DiagonalArraySearch.create(13);
		new DiagonalArraySolution(ar).trial();
	}
}
