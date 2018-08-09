import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
//import implementing set (eg. TreeSet)

public class WordProcessor {
	
	/******
	 * 
	 * StudentID: 2294163p
	 * Name: Yangjiangdai Pan
	 *
	******/
	
	private static <E> String displaySet(Set<E> inputSet) {
		String input = "";
		for (E e : inputSet) {
			input = input + e.toString() + ", ";
		}
		// implement this static method to create a
		// String representation of set - 5 comma separated elements per line
		// assume that type E has a toString method
		return input;
	}

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		// create a set of type String called wordSet
		Set<String> wordSet = new TreeSet<String>();

		// create a set of type CountedElement<String> called countedWordSet
		Set<CountedElement<String>> countedWordSet = new TreeSet<CountedElement<String>>();

		// for each input file (assume 3 arguments, each the name of a file)
		for (int i = 0; i < 3; i++) {

			try {
				FileReader file = new FileReader(args[i]);
				Scanner scanner = new Scanner(file);

				// for each word w
				while (scanner.hasNext()) {
					String w = scanner.next();
					CountedElement c = null;
					c = new CountedElement(w, 1);
					
					// if wordset doesnt contain w:
					if (!wordSet.contains(w)) {
						
						// add w to wordset
						wordSet.add(w);
						
						// add new element to countedWordSet
						countedWordSet.add(c);
						
						// else
					} else {
						
						// increment numeric part of element in countedWordSet containing w
						for(CountedElement sC1 : countedWordSet) {
							if(c.compareTo(sC1) == 0) {
								int count = sC1.getCount() + 1;
								sC1.setCount(count);
							}
						}
					}
				}
				scanner.close();
				file.close();
			} catch (IOException e) {

			}
		}
		System.out.println(displaySet(countedWordSet));
	}
	
}
