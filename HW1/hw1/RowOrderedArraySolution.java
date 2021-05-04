package scyoung.hw1;

import algs.hw1.arraysearch.RowOrderedArraySearch;

/**
 * Copy this class into your package, which must have USERID has its root.
 */
public class RowOrderedArraySolution extends RowOrderedArraySearch {

	/** Construct problem solution for given array. Do not modify this method. */
	public RowOrderedArraySolution(int[][] a) {
		super(a);
	}
	
	/** 
	 * For this homework assignment, you need to complete the implementation of this
	 * method.
	 */
	@Override
	public int[] locate(int target) {
		int n = this.length();
		
		// Find the row in the 0 column in which the target could exist
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
		
		// If number hasn't been found, we know which row it's in
		// hi points to the row that the value must be in
		
		// newLo starts at 1 since target can't be in the 0 position
		int newLo = 1;
		int newHi = hi;
		
		while (newLo <= newHi) {
			int newMid = (newLo+newHi)/2;
			
			int rc = inspect(hi, newMid) - target;
			
			if (rc < 0) {
				newLo = newMid+1;
			} else if (rc > 0 ) {
				newHi = newMid-1;
			} else {
				return new int[] { hi, newMid }; // return r,c where I found target 
			}
		}
		return null; // not found
	}
	
	/** Be sure that you call your class constructor. Do not modify this method. */ 
	public static void main (String args[]) {
		int[][] ar = RowOrderedArraySearch.create(13);
		new RowOrderedArraySolution(ar).trial();
	}
}
