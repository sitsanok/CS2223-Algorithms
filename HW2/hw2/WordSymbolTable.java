package scyoung.hw2;

import org.omg.CORBA.Current;

import algs.days.day02.CompareOperation;
import scyoung.hw2.WordList.Node;

/**
 * Once you copy this file into your USERID.hw2 package, you must complete this
 * implementation.
 * 
 * This class will be used by Question Q2 on Homework2.
 */
public class WordSymbolTable {
	Node first;
	Node last;
	int n;

	/**
	 * Leave this Node class as is. While you don't need to make changes to this
	 * class, it is acceptable if you would like to add methods to this class.
	 */
	class Node {
		String word;
		int count;
		Node next;

		Node(String w, int count) {
			this.word = w;
			this.count = count;
		}
	}

	/**
	 * Increase the count for given word.
	 * 
	 * Note: this might need to add the word in the first place.
	 * 
	 * Returns TRUE if the word was newly added, otherwise FALSE
	 * 
	 * @param elt element whose count has increased by 1.
	 */
	public boolean increment(String elt) {
		Node previous = null;
		Node current = this.first;

		if (this.size() == 0) { // if empty, add to the table, increment n, and return true
			first = new Node(elt, 1);
			n++;
			return true;
		} else {
			while (current != null) {
				if (current.word.equals(elt)) {
					current.count = current.count + 1;
					return false;
				} else {
					previous = current;
					current = current.next;
				}
			}
			// not in the table, so need to add
			last = new Node(elt, 1); // replacing last with the new word
			previous.next = last; // connecting the old last with new last
			n++; // increment because we added to the set
			return true; // return true because we successfully added
		}
	}

	/**
	 * Decrease the count for given word.
	 * 
	 * Note: this might need to remove the word once the count reaches zero. Returns
	 * TRUE if the word was removed, otherwise FALSE
	 * 
	 * @param elt element whose count is to decrease by 1.
	 */
	public boolean decrement(String elt) {
		Node previous = null;
		Node current = this.first;

		if (this.size() == 0) { // if empty, return false
			return false;
		} else if (first.word.equals(elt)) {
			first.count--; // decrement count

			if (first.count == 0) { // if count reaches 0, we remove it, decrement n, return true
				first = null;
				n--;
				return true;
			} else { // if count does not reach 0, no need to remove, so we leave it
				return false;
			}
		} else {
			while (current != null) {
				if (current.word.contentEquals(elt)) {
					current.count = current.count - 1;

					if (current.count == 0) { // if current count reaches 0, we need to remove it
						if (current.next == null) { // if current is last, set previous as new last
							last = previous;
							last.next = null;
							current = null;
							n--;
							return true;
						} else { // if current isn't last, connect previous with next
							previous.next = current.next;
							current = null;
							n--;
							return true;
						}
					} else { // if current count does not reach 0, no need to remove
						return false;
					}
				} else { // if word and elt don't equal, keep going
					previous = current;
					current = current.next;
				}
			}
			return false; // while loop breaks, we've checked all elements in list
		}
	}

	/** Return number of words in the symbol table. */
	public int size() {
		return n;
	}

	/** Return the accumulated counts of all words in the word table. */
	public int totalCounts() {
		Node current = this.first;
		int count = 0;
		
		if (this.size() == 0) {
			return count;
		} else {
			while (current!= null) {
				count = count + current.count;
				current = current.next;
			}
			return count;
		}
	}

	/** Remove entire word from the word table. */
	public boolean remove(String elt) {
		Node previous = null;
		Node current = this.first;
		Node next;

		if (this.size() == 0) { // if empty, cannot remove anything, return false
			return false;
		} else if (first.word.equals(elt)) { // if first = elt, set first to its next value, decrement, return true
			if (this.size() == 1) {
				first = null;
				n--;
				return true;
			} else {
				first = first.next;
				n--;
				return true;
			}
		} else { // if not empty, check if we can remove something
			next = first.next;
			while (current != null) {
				if (current.word.equals(elt)) { // if equal, set node = null, connect previous with next, return true
					current = null;
					if (next == null) {
						last = previous;
						last.next = null;
					} else {
						previous.next = next;
					}
					n--;
					return true;
				} else if (next == null) { // if next is null, we've checked all elements in list, return false
					return false;
				} else { // else, keep going through the list
					previous = current;
					current = current.next;
					next = current.next;
				}
			}
			return false; // while loop breaks, we've checked all elements in list, return false
		}
	}

	/**
	 * Returns true if word exists in the WordSymbolTable; false otherwise.
	 * 
	 * @param elt target element to seek.
	 */
	public boolean contains(String elt) {
		Node current = this.first;

		if (this.size() == 0) { // if empty, element is not in the list
			return false;
		} else {
			while (current != null) {
				if (current.word.equals(elt)) { // if they match, return true
					return true;
				} else { // else go through the list
					current = current.next;
				}
			}
			return false; // have gone through list and did not find element
		}
	}

	/**
	 * Returns the count for the word (or 0 if the word doesn't exist in the symbol
	 * table).
	 * 
	 * @param elt target element to seek.
	 */
	public int count(String elt) {
		Node current = this.first;

		if (this.size() == 0) { // if empty, element is not in the list
			return 0;
		} else {
			while (current != null) {
				if (current.word.equals(elt)) { // if they match, return count
					return current.count;
				} else { // else go through the list
					current = current.next;
				}
			}
			return 0; // have gone through list and did not find element
		}
	}

	/**
	 * Return a word whose repetition count is equal to the maximum in the Symbol
	 * table.
	 * 
	 * Note that there may be multiple words that have the maximum count, so this
	 * method only needs to return one of them.
	 */
	public String mostFrequent() {
		Node previous = null;
		Node current = this.first;
		String winner = "";
		int max = 0;
		int candidate = 0;
		
		if (this.size() == 0) {
			return winner;
		} else {
			while (current != null) {
				candidate = current.count;
				previous = current;
				if (candidate > max) {
					max = candidate;
					winner = current.word;
				}
				current = current.next;
			}
			return winner;
		}
	}

	/** For debugging, return semicolon-separated string of (word,count) pairs. */
	public String elements() {
		Node front = this.first;
		Node next;
		String list = "";
		
		if (this.size() == 0) {
			return list;
		} else if (this.size() == 1) {
			list = "(" + front.word + "," + front.count + ")";
			return list;
		} else {
			list = "(" + front.word + "," + front.count + ")";
			next = front.next;
			while (next != null) {
				list = list + "(" + next.word + "," + next.count + ")";
				next = next.next;
			}
			return list;
		}
	}

	// you should not have to modify anything below. These are testing routines for
	// you to check your work.
	// ----------------------------------------------------------------------------------------------------
	static void validate(Object o1, Object o2) {
		if (o1.equals(o2)) {
			return;
		}
		throw new RuntimeException(o1 + " doesn't equal " + o2);
	}

	// Once you have completed the implementation, you should be able to run this
	// method and have
	// it complete without any runtime exceptions. While not an exhaustive test,
	// this should be
	// sufficient to help you uncover many of the boundary cases.
	public static void main(String[] args) {

		WordSymbolTable wl = new WordSymbolTable();
		validate(0, wl.size());
		validate("", wl.elements()); // empty word list must return ""
		validate(0, wl.count("nothing"));
		validate(false, wl.contains("this"));
		validate(true, wl.increment("test"));
		validate("(test,1)", wl.elements()); // no trailing or pre comma.
		validate(1, wl.count("test"));
		validate(false, wl.contains("this"));
		validate(true, wl.contains("test"));

		validate(1, wl.count("test"));
		validate(false, wl.increment("test")); // when add TWICE, false is returned
		validate(2, wl.count("test"));

		validate(true, wl.remove("test")); // can remove first element
		validate(false, wl.remove("test")); // can't remove first empty
		validate(true, wl.increment("test"));
		validate(true, wl.increment("that"));
		validate(1, wl.count("test"));
		validate(false, wl.increment("that"));
		validate(2, wl.count("that"));
		validate(3, wl.totalCounts());
		validate(2, wl.size());
		validate(false, wl.remove("not"));
		validate(true, wl.remove("test"));
		validate("(that,2)", wl.elements()); // no trailing or pre comma.
		validate(true, wl.remove("that"));	
	}
}
