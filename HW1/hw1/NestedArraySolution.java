package scyoung.hw1;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import algs.hw1.arraysearch.NestedArraySearch;

/**
 * Copy this class into your package, which must have USERID has its root.
 */
public class NestedArraySolution extends NestedArraySearch {

	/** Construct problem solution for given array. Do not modify this method. */
	public NestedArraySolution(int[][] a) {
		super(a);
	}
	
	/** 
	 * For this homework assignment, you need to complete the implementation of this
	 * method.
	 */
	@Override
	public int[] locate(int target) {
		int n = this.length();
		
		// Find the triangle in which the target could exist
		
		// Counting triangles
		int triMin= 1;
		int triMax = 1 + ((n-1)/3);
		boolean backStep = false;
		
		// Position of smallest value in a triangle
		int r = 0;
		int c = 0;
		int val = 0;

		while (backStep != true) {
			
			int rc = inspect(r, c) - target;
			
			if (rc < 0) {
				r = r+2;
				c = c+1;
				triMin++;
				val = rc + target;
				if (r > n-1 || c > n-1) {
					return null;
				}
			} else if (rc > 0 ) {
				r = r-2;
				c = c-1;
				triMin--;
				backStep = true;
				if (r < 0 || c < 0) {
					return null;
				}
			} else {
			return new int[] { r, c }; // return r,c where I found target 
			}
		}
		
		//If number hasn't been found, it could be smaller
		if ((c == -1)) {
			return null;
		}
		
		// If number hasn't been found, we know which triangle it's in
		
		// Smallest value in the triangle
		int rowLo = r;
		int colLo = c;
		int valLo = val;
		
		// Bottom corner value in the triangle
		int rowBot = n-triMin;
		int colBot = c;
		int valBot = inspect(rowBot, colBot);
		
		// Middle value in the triangle
		int rowMid = n-triMin;
		int colMid = n-((triMin*2)-1);
		int valMid = ((valLo  + valBot)/2);
		
		// Largest value in the triangle
		int rowHi = r+1;
		int colHi = c;
		
		// Case 1: Smallest < Target < Middle
		if (target <= valMid) {
			++rowLo;
			++colLo;
			
			while (rowLo <= rowMid && colLo <= colMid) {
				int rMid = (rowLo+rowMid)/2;
				int cMid = (colLo+colMid)/2;
				
				int rc = inspect(rMid, cMid) - target;
						
				if (rc < 0) {
					rowLo = rMid+1;
					colLo = cMid+1;
					if (rowLo > n-1 || colLo > n-1) {
						return null;
					}
				} else if (rc > 0 ) {
					rowMid = rMid-1;
					colMid = cMid-1;
					if (rowMid < 0 || colMid < 0) {
						return null;
					}
				} else {
				return new int[] { rMid, cMid }; // return r,c where I found target 
				}
			}
		}
		
		// Case 2: Middle < Target < Bottom
		else if (target <= valBot) {
			colMid--;
			
			while (colBot <= colMid) {
				int newMid = (colBot+colMid)/2;
				
				int rc = inspect(rowBot, newMid) - target;
				
				if (rc < 0) {
					colMid = newMid-1;
				} else if (rc > 0 ) {
					colBot = newMid+1;
				} else {
					return new int[] { rowBot, newMid }; // return r,c where I found target 
				}
			}
		}
		
		// Case 3: Bottom < Target < Largest
		else {
			rowBot--;
			
			while (rowHi <= rowBot) {
				int mid = (rowHi+rowBot)/2;
				
				int rc = inspect(mid, colHi) - target;
				
				if (rc < 0) {
					rowBot = mid-1;
				} else if (rc > 0 ) {
					rowHi = mid+1;
				} else {
					return new int[] { mid, colHi }; // Return r,c where I found target 
				}
			}
		}
		return null;
	}
	
	/** Be sure that you call your class constructor. Do not modify this method. */ 
	public static void main (String args[]) {
		int[][] ar = NestedArraySearch.create(13);
		new NestedArraySolution(ar).trial();
	}
}
