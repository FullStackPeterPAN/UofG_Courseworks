public class CountedElement<E extends Comparable<E>> implements Comparable<CountedElement<E>> {
	
	/******
	 * 
	 * StudentID: 2294163p
	 * Name: Yangjiangdai Pan
	 *
	******/
	
	private E element;
	private int count;

	public CountedElement(E e, int count) {

		// constructor - to complete
		this.element = e;
		this.count = count;
	}

	public CountedElement(E e) {

		// constructor - to complete
		this.element = e;
	}

	// add getters and setters
	public E getElement() {
		return element;
	}

	public int getCount() {
		return count;
	}

	public void setElement(E e) {
		this.element = e;
	}

	public void setCount(int count) {
		this.count = count;
	}

	// add toString() method
	public String toString() {
		return "(" + element + "," + count + ")";
	}

	public int compareTo(CountedElement<E> sC1) {
		return element.compareTo(sC1.getElement());
	}

}
