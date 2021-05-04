package scyoung.hw4;

import java.io.File;
import java.util.Scanner;

import edu.princeton.cs.algs4.DijkstraUndirectedSP;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;
import algs.hw4.AVL;

public class LongestWordLadder {

	/**
	 * Represent the mapping of (uniqueID, 4-letter word)
	 */
	static SeparateChainingHashST<String,Integer> table = new SeparateChainingHashST<String,Integer>();
	static SeparateChainingHashST<Integer,String> reverse = new SeparateChainingHashST<Integer,String>();

	public static void main(String[] args) throws java.io.IOException {

		// Use this to contain all four-letter words that you find in dictionary
		AVL<String> avl = new AVL<String>();

		// create a graph where each node represents a four-letter word.
		// Also construct avl tree of all four-letter words.
		// Note: you will have to copy this file into your project to access it, unless you
		// are already writing your code within the SedgewickAlgorithms4ed project.
		Scanner sc = new Scanner(new File ("words.english.txt"));
		
		int i = 0; // number of vertices
		while (sc.hasNext()) {
			String s = sc.next();
			if (s.length() == 4) {
				avl.insert(s);
				table.put(s, i);
				reverse.put(i, s);
				i++;
			}
		}
		sc.close();

		// now construct graph, where each node represents a word, and an edge exists between 
		// two nodes if their respective words are off by a single letter. Use EdgeWeightedGraph
		// Because you can run DijkstraUndirectedSP on it. Hint: use the
		// keys() method provided by the AVL tree to iterate over all keys in the graph
		
		EdgeWeightedGraph graph = new EdgeWeightedGraph(table.size());

		String min = avl.min();
		for (String w1: avl.keys()) {
			for (String w2: (avl.keys(min, w1))) {
				if (WordLadder.offByOne(w1, w2)) {
					Edge e = new Edge(table.get(w1), table.get(w2), 1);
					graph.addEdge(e);
				}
			}
		}
		
		double max = 0;
		int maxu = 0;
		int maxv = 0;
		
		for (int n = 0; n < graph.V(); n++) {
			DijkstraUndirectedSP duSp = new DijkstraUndirectedSP(graph, n);
			for (int m = n+1; m < graph.V(); m++) {
				if (duSp.hasPathTo(m)) {
					double potential = (duSp.distTo(m));
						if (potential > max) {
							max = potential;
							maxu = n;
							maxv = m;
						}
				}
			}
		}
		
		// Find largest non-infinite distance between any two vertices.
		
		System.out.println("longest WordLadder from " + reverse.get(maxu) + " to " + reverse.get(maxv) + " in " + max + " steps.");
		System.out.println("No go back and use WordLadder to recover this.");
	}
}
