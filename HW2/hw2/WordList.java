package scyoung.hw2;

import javax.jws.WebParam.Mode;

/**
 * Once you copy this file into your USERID.hw2 package, you must complete this
 * implementation.
 * 
 * This class will be used by Question Q1 on Homework2.
 */
public class WordList {
	Node first;
	Node last;
	int n;

	/**
	 * Leave this Node class as is. While you don't need to make changes to this
	 * class, it is acceptable if you would like to add methods to this class.
	 */
	class Node {
		String word;
		Node next;

		Node(String w) {
			this.word = w;
		}
	}

	/**
	 * If the given element doesn't exist in the set then update the set and return
	 * true; otherwise return false. This means that adding a duplicate element to a
	 * set must return false.
	 * 
	 * @param elt element to be added.
	 */
	public boolean add(String elt) {
		Node current = this.first;
		boolean nextIsNull = false;

		if (this.size() == 0) { // if empty, add the element as the first
			first = new Node(elt);
			n++;
			return true;
		} else { // if not empty, compare and see if Node first matches input element
			while (nextIsNull == false) { // while the node has an element
				if (current.word.equals(elt)) { // if elements equal, return false; don't add input to set
					return false;
				} else {
					if (current.next != null) {
						current = current.next; // if element does not equal, check other elements in set
					} else {
						nextIsNull = true;
					}
				}
			}
			// reached the end and found no matches
			// current = last
			// need to add 5 to make that last
			last = new Node(elt); // replacing last with the new word
			current.next = last; // connecting the old last with new last
			n++; // increment because we added to the set
			return true; // return true because we successfully added
		}
	}

	/** Returns the number of elements in the set. */
	public int size() {
		return n;
	}

	/**
	 * Returns true if the given element was in the set (and was removed) or false
	 * if the given element did not belong to the set.
	 * 
	 * @param elt element to be removed.
	 */
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
	 * Returns true if the element exists within the collection.
	 * 
	 * @param elt target element to be searched.
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

	/** For debugging, return comma-separated string of elements. */
	public String elements() {
		Node front = this.first;
		Node next;
		String list = "";

		if (this.size() == 0) {
			return list;
		} else if (this.size() == 1) {
			return front.word;
		} else {
			list = front.word;
			next = front.next;
			while (next != null) {
				list = list + "," + next.word;
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

		WordList wl = new WordList();
		validate(0, wl.size());
		validate("", wl.elements()); // empty word list must return ""
		validate(false, wl.contains("this"));
		validate(true, wl.add("test"));
		validate("test", wl.elements()); // no trailing or pre comma.
		validate(false, wl.contains("this"));
		validate(true, wl.contains("test"));

		validate(false, wl.add("test")); // can't add twice
		validate(true, wl.remove("test")); // can remove first element
		validate(false, wl.remove("test")); // can't remove first empty
		validate(true, wl.add("test"));
		validate(true, wl.add("that"));
		validate(false, wl.remove("not"));
		validate(true, wl.remove("test"));
		validate("that", wl.elements()); // no trailing or pre comma.
		validate(true, wl.remove("that"));
	}
}
