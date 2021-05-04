package scyoung.hw3;

import java.util.NoSuchElementException;

import javax.swing.text.html.HTML;
import javax.xml.bind.ValidationEvent;

import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;

/**
 * Minimum implementation of Binary Search Tree (BST) as a Symbol Table<String, Integer>
 *
 * You need to copy this class into your USERID.hw3 and add methods to the end of this class.
 */
public class BST {

	Node root;               // root of the tree
	
	class Node {
		String    key;          
		Integer   count;         
		Node      left, right;  // left and right subtrees
		int       N;            // number of nodes in subtree

		public Node(String key, int ct, int N) {
			this.key = key;
			this.count = ct;
			this.N = N;
		}
		
		public String toString() { return "[" + key + "]"; }
	}

	public boolean isEmpty() { return size() == 0; }
	
	/** Return number of key-value pairs in ST. */
	public int size()                { return size(root); }

	// Helper method that deals with "empty nodes"
	private int size(Node node) {
        if (node == null) return 0;
        
        return node.N;
    }
	
	/** Search for key in BST. */
	public Integer get(String key)      { return get(root, key); }
	
	/** Helper method to search for key in BST rooted at parent. */
	private Integer get(Node parent, String key) {
		if (parent == null) return null;
		
		int cmp = key.compareTo(parent.key);
		
		if      (cmp < 0) return get(parent.left, key);
		else if (cmp > 0) return get(parent.right, key);
		else              return parent.count;
	}

	/** Invoke put on parent, should it exist. */
	public void put(String key, Integer val) {
		root = put(root, key, val);
	}
	
	/** Helper method to put (key, ct) pair into BST rooted at parent. */
	private Node put(Node parent, String key, Integer ct) {
		if (parent == null) return new Node(key, ct, 1);
		
		int cmp = key.compareTo(parent.key);
		if      (cmp < 0) parent.left  = put(parent.left,  key, ct);
		else if (cmp > 0) parent.right = put(parent.right, key, ct);
		else              parent.count = ct;
		
		parent.N = 1 + size(parent.left) + size(parent.right);
		return parent;
	}
	
	// traversal ideas
    // invoke an inorder traversal of the tree
    public void inorder() { inorder(root); }
    private void inorder(Node n) {
    	if (n == null) { return; }
    	
		inorder (n.left);
		StdOut.println (n.key);
		inorder (n.right);
    }
   
    /**
     * Removes the specified key and its associated value from this symbol table     
     * (if the key is in this symbol table).    
     *
     * @param  key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(String key) {
        if (key == null) throw new IllegalArgumentException("calls delete() with a null key");
        root = delete(root, key);
    }

    /** Taken from Sedgewick algo. */
    private Node delete(Node x, String key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = delete(x.left,  key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else { 
            if (x.right == null) return x.left;
            if (x.left  == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        } 
        return x;
    } 
    
    private Node min(Node x) { 
        if (x.left == null) return x; 
        else                return min(x.left); 
    } 

    /**
     * Removes the smallest key and associated value from the symbol table.
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }
    
	// ------------------------------------------------------------------------------------------------
	// YOU WILL ADD METHODS BELOW. THERE IS NO NEED TO MODIFY CODE ABOVE.
	// ------------------------------------------------------------------------------------------------

    /** 
     * Returns the count of nodes at a given depth. Key is depth, Value is count.
     * 
     * For the following tree
     * 
     *          G           -- depth is 0     { (0, 1) } 
     *         / \
     *        D   H         -- depth is 1
     *         \
     *          F           -- depth is 2
     *  
     * The returned hash table should be { (0, 1), (1, 2), (2, 1) } where each (key, value) is (depth, count).
     * 
     * Note: you will need a helper method, much like you have seen in other recursive methods.
     * 
     * Returns a edu.princeton.cs.algs4.SeparateChainingHashST object representing the symbol table.
     */
    public SeparateChainingHashST<Integer,Integer> collect() {
    	SeparateChainingHashST<Integer, Integer> ht = new SeparateChainingHashST<Integer, Integer>();
    	ht = collect(root, ht, 0);
    	return ht;
    }
    
    SeparateChainingHashST<Integer, Integer> collect(Node parent, SeparateChainingHashST<Integer, Integer> ht, int myDepth){
    	if (parent == null) { return ht; }
    	
    	collect(parent.left,ht, myDepth+1);
    	
    	// if key exists, get its value, increment the value
    	// if key doesn't exist, put the pair in the table
    	if (ht.contains(myDepth)) {
    		int count = ht.get(myDepth);
    		count++;
    		ht.put(myDepth, count);
    	} else {
    		ht.put(myDepth, 1);
    	}
    	
    	collect(parent.right,ht, myDepth+1);
    	
    	return ht;
    }
    
    /**
     * Returns the height of this binary tree.
     */
	public int height() {
		return height(root);
	}
	
	/** 
     * Returns the height of a given node.
     * 
     * For the following tree
     * 
     *          G           -- height of G is 2
     *         / \
     *        D   H         -- height of D is 1, height of H is 0
     *         \
     *          F           -- height of F is 0
     */
	int height(Node parent) {
		// height of null = -1
		// height of 1 node = 0
		if (parent == null) { return -1; }
		
		int leftMax = 0;
		int rightMax = 0;
		
		// +1 counts for the root
		if (parent.left != null) {
			leftMax = 1 + height(parent.left);
		}
		if (parent.right != null) {
			rightMax = 1 + height(parent.right);
		}

		return (Math.max(leftMax, rightMax));	
	}
	
    int max = 0;
    String winner = "";
    /**
     * Return the key whose count is the greatest (that is, has the most occurrences in the BST).
     * 
     */
    public String mostFrequent() {
        max = 0;
        winner = "";
    	return mostFrequent(root);
    }
    
    String mostFrequent(Node parent) {
    	if (parent == null) { return winner;}
    	
    	mostFrequent(parent.left);
    	
    	if (parent.count > max) {
    		max = parent.count;
    		winner = parent.key;
    	}
    	
    	mostFrequent(parent.right);
    	
    	return winner;
    }
    
    int total = 0;
    /** Print in ascending order the keys whose count is 1 (that is, only occur once) and return total. */
    public int printUnique() {
    	total = 0;
    	return printUnique(root);
    }

    int printUnique(Node parent){
    	if (parent == null) { return -1;}
    	
    	printUnique(parent.left);
    	
    	if (parent.count == 1) {
    		total++;
    		System.out.println(parent.key);
    	}
    	
    	printUnique(parent.right);
    	
    	return total;
    }
}
