package scyoung.hw1;

import algs.days.day04.FixedCapacityStack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Code from p. 129 of Sedgewick (4ed), slightly modified to use FixedCapacityStack
 * 
 * To run in Eclipse, you will need to enter your input into the Console window directly. 
 * After you press return, nothing appears to happen. This is because you need to "close" the
 * StdIn. 
 * 
 * This is done on a PC by pressing Control-z.
 * 
 * On a Macintosh (I am not making this up), to terminate the input, click the mouse anywhere else in Eclipse
 * (typically just back in the source code or in the package explorer), then click BACK in the console window
 * and press control-d (not Command-d).
 * 
 * Copy this class into your package, which must have USERID has its root.
 */
public class Evaluate {
	public static void main(String[] args) {
		FixedCapacityStack<String> ops = new FixedCapacityStack<String>(100);
		FixedCapacityStack<Double> vals = new FixedCapacityStack<Double>(100);

		boolean hasOp = false;   // true if previous token was an operator
		boolean hasVal = false; // true if previous token was a value
		int firstToken = 1;
		int parenCount = 0;     // counts the number of left parentheses created
		
		while (!StdIn.isEmpty()) {
			// Read token. push if operator.
			String s = StdIn.readString();
			if (s.equals ("(")) { 
				/* do nothing */ 
				parenCount++;
				if (firstToken == 1) { firstToken = 0; }
			}
			else if (s.equals ("+")) { 
				ops.push(s);
				if (firstToken == 1) { throw new RuntimeException("Invalid infix expression: + operator at the beginning of expression"); }
				if (hasOp == true) { throw new RuntimeException("Invalid infix expression due to consecutive operators."); }
				hasOp = true;
				hasVal = false;
				}
			else if (s.equals ("-")) { 
				ops.push(s);
				if (firstToken == 1) { throw new RuntimeException("Invalid infix expression: - operator at the beginning of expression"); }
				if (hasOp == true) { throw new RuntimeException("Invalid infix expression due to consecutive operators."); }
				hasOp = true;
				hasVal = false;
				}
			else if (s.equals ("*")) { 
				ops.push(s);
				if (firstToken == 1) { throw new RuntimeException("Invalid infix expression: * operator at the beginning of expression"); }
				if (hasOp == true) { throw new RuntimeException("Invalid infix expression due to consecutive operators."); }
				hasOp = true;
				hasVal = false;	
			}
			else if (s.equals ("/")) { 
				ops.push(s);
				if (firstToken == 1) { throw new RuntimeException("Invalid infix expression: / operator at the beginning of expression"); }
				if (hasOp == true) { throw new RuntimeException("Invalid infix expression due to consecutive operators."); }
				hasOp = true;
				hasVal = false;
				}
			else if (s.equals ("sqrt")) {
				ops.push(s); 
				if (firstToken == 1) { firstToken = 0; }
				if (hasOp == true) { throw new RuntimeException("Invalid infix expression due to consecutive operators."); }
				hasOp = true;
				hasVal = false;
			}
			else if (s.equals ("=")) { 
				ops.push(s); 
				if (firstToken == 1) { throw new RuntimeException("Invalid infix expression: = operator at the beginning of expression"); }
				if (hasOp == true) { throw new RuntimeException("Invalid infix expression due to consecutive operators."); }
				hasOp = true;
				hasVal = false;
				}
			else if (s.equals ("round")) { 
				ops.push(s); 
				if (firstToken == 1) { firstToken = 0; }
				if (hasOp == true) { throw new RuntimeException("Invalid infix expression due to consecutive operators."); }
				hasOp = true;
				hasVal = false;
				}
			else if (s.equals (")")) {
				// pop, evaluate, and push result if token is ")".
				parenCount--;
				String op = ops.pop();
				double v = vals.pop();
				if (op.equals("+")) { v = vals.pop() + v; }
				else if (op.equals("-")) { v = vals.pop() - v; }
				else if (op.equals("*")) { v = vals.pop() * v; }
				else if (op.equals("/")) { v = vals.pop() / v; }
				else if (op.equals("sqrt")) { v = Math.sqrt(v); }
				else if (op.equals("=")) {
					if (vals.pop() == v) {
						v = 1.0;
					} else {
						v = 0.0;
					}
				}
				else if (op.equals("round")) { v = Math.round(v); }
				vals.push(v);
			} else {
				// Token no operator or paren; must be double value to push
				vals.push(Double.parseDouble(s));
				if (firstToken == 1) { firstToken = 0; }
				if (hasVal == true) { throw new RuntimeException("Invalid infix expression due to consecutive values."); }
				hasOp = false;
				hasVal = true;
			}
		}
		
		if (parenCount == 0) {
			StdOut.print(vals.pop());
		} else {
			throw new RuntimeException("Invalid infix expression due to uneven parentheses.");
		}
		
		
		// set a breakpoint on this line and you can observe the state
		// of the ops stack and vals stack. You will need this for your 
		// answer.
		StdOut.println();
	}
}
