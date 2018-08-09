import java.util.Iterator;
import java.util.NoSuchElementException;

public class BSTBag<E extends Comparable<E>> implements Bag<E> {

	/******
	 * 
	 * StudentID: 2294163p
	 * Name: Yangjiangdai Pan
	 *
	******/
	
	// Each BST object is a binary-search-tree header.
	private Node<E> root;
	private int size;

	public BSTBag() {

		// Construct an empty BST.
		root = null;
		size = 0;
	}

	// //////// Inner class //////////
	private static class Node<E extends Comparable<E>> {

		protected CountedElement<E> element;
		protected Node<E> left, right;

		protected Node(E elem) {
			element = new CountedElement<E>(elem);
			left = null;
			right = null;
		}

	}

	// ///// end of inner class ///////////

	// ////////// Accessors ////////////
	public boolean isEmpty() {

		// Return true if and only if this bag is empty.
		return (root == null);
	}

	public int size() {

		// Return the size of this set.
		return this.size;
	}

	public boolean contains(E element) {

		// Return true if and only if element is a member of this bag.
		int direction = 0;
		Node<E> curr = root;
		for (;;) {
			if (curr == null) {
				return false;
			} else {
			}
			direction = curr.element.getElement().compareTo(element);
			if (direction == 0 && curr.element.getCount() > 0) {
				return true;
			} else if (direction > 0) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}
	}

	public boolean equals(Bag<E> that) {

		// Return true if and only if this bag is equal to that.
		Iterator<E> iter1 = this.iterator();
		Iterator<E> iter2 = that.iterator();
		if (size != that.size()) {
			return false;
		}
		for (int i = 0; i < size; i++) {
			while (iter1.hasNext() && iter2.hasNext()) {
				if (!iter2.next().equals(iter1.next())) {
					return false;
				}
			}
		}
		return true;
	}

	// //////////Transformers ////////////

	public void clear() {

		// Make this bag empty.
		root = null;
	}

	public void add(E element) {

		// Add element to bag.
		int direction = 0;
		Node<E> parent = null, curr = root;
		for (;;) {
			if (curr == null) {
				Node<E> ins = new Node<E>(element);
				if (root == null) {
					root = ins;
					root.element.setCount(1);
				} else if (direction > 0) {
					parent.left = ins;
					parent.left.element.setCount(1);

				} else {
					parent.right = ins;
					parent.right.element.setCount(1);

				}
				this.size++;
				return;
			}

			direction = curr.element.getElement().compareTo(element);
			if (direction == 0) {

				// increment the number of element items in the bag
				curr.element.setCount(curr.element.getCount() + 1);
				this.size++;
				return;
			}
			parent = curr;
			if (direction > 0) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}
	}

	public void remove(E element) {

		// Remove it from this set.
		int direction = 0;
		Node<E> parent = null, curr = root;
		while (curr != null) {
			direction = curr.element.getElement().compareTo(element);
			if (direction == 0) {
				if (curr.element.getCount() < 1) {

					// Do nothing if no item in bag pertaining to element
				} else {

					// otherwise decrement number of element items (lazy deletion)
					curr.element.setCount(curr.element.getCount() - 1);
					this.size--;
				}
				return;
			}
			parent = curr;
			if (direction > 0) {
				curr = parent.left;
			} else {
				curr = parent.right;
			}

		}

	}

	// ////////// Iterator ////////////

	public Iterator<E> iterator() {

		// Return an iterator that will visit all members of this
		// bag, in no particular order

		LinkedStack<Node<E>> track = new LinkedStack<Node<E>>();

		for (Node<E> curr = root; curr != null; curr = curr.left) {
			track.push(curr);
		}

		Iterator i = new Iterator() {
			public boolean hasNext() {
				return (!track.empty());
			}

			public E next() {
				if (track.empty()) {
					throw new NoSuchElementException();
				}
				Node<E> place = track.pop();
				for (Node<E> curr = place.right; curr != null; curr = curr.left) {
					track.push(curr);
				}

				String p = "";
				for (int i = 0; i < place.element.getCount(); i++) {
					p = p + place.element.getElement().toString() + ", ";
				}
				return (E) p;
			}
		};
		return i;
	}

}
