import java.util.*;

@SuppressWarnings({"rawtypes", "unchecked"})
public class Lab17_0 {

	public static void main(String[] args) {

		Lab17_0 lab = new Lab17_0();

		ArrayList <Comparable> list = lab.initializeList();
		ArrayList <Comparable> copy = lab.duplicate(list);

		System.out.println("Before Bubble Sort:");
		System.out.println(list);

		lab.bubbleSort(list);	// runs your Bubble Sort code
		Collections.sort(copy);	// runs built-in sorting code
		Collections.reverse(copy);

		System.out.println("After Bubble Sort:");
		System.out.println(list);
		System.out.println(copy.toString().equals(list.toString()) ? "CORRECT" : "NOT SORTED PROPERLY");		
		
		list = lab.initializeList();
		copy = lab.duplicate(list);
		System.out.println("\nBefore Selection Sort:");
		System.out.println(list);

		lab.selectionSort(list);	// runs your Selection Sort code
		Collections.sort(copy);		// runs built-in sorting code

		System.out.println("After Selection Sort:");
		System.out.println(list);
		System.out.println(copy.toString().equals(list.toString()) ? "CORRECT" : "NOT SORTED PROPERLY");		

		list = lab.initializeList();
		copy = lab.duplicate(list);
		System.out.println("\nBefore Insertion Sort:");
		System.out.println(list);

		lab.insertionSort(list);	// runs your Insertion Sort code
		Collections.sort(copy);		// runs built-in sorting code
		Collections.reverse(copy);

		System.out.println("After Insertion Sort:");
		System.out.println(list);
		System.out.println(copy.toString().equals(list.toString()) ? "CORRECT" : "NOT SORTED PROPERLY");		
	}

	/* Write code for a Bubble Sort algorithm that starts at the right side of
	 * of ArrayList of Comparable objects and "bubbles" the largest item to the
	 * left of the list.  The result should be an ArrayList arranged in descending
	 * order.
	*/
	void bubbleSort(ArrayList <Comparable> list) {
		Comparable temp = 0;
		for(int outer = 0; outer < list.size(); outer++) {
			for(int inner = list.size() - 1; inner > 0; inner--) {
				if(list.get(inner).compareTo(list.get(inner - 1)) > 0){
					temp = list.get(inner);
					list.set(inner, list.get(inner - 1));
					list.set(inner - 1, temp);
				}
			}
		}
	}

	/* Write code for a Selection Sort algorithm that starts at the left side
	 * of an ArrayList of Comparable objects and searches through the list for
	 * the largest item and then swaps it with the last item in the list.  The
	 * "last item" then becomes the item to its left. The result should be
	 * an ArrayList arranged in ascending order.
	*/
	void selectionSort(ArrayList <Comparable> list) {
		int maxIndex = 0;
		Comparable temp = 0;
		
		for(int outer = 0; outer < list.size() - 1; outer++) {
			maxIndex = 0;
			for(int inner = 0; inner < list.size() - outer; inner++) {
				if(list.get(inner).compareTo(list.get(maxIndex)) > 0) {
					maxIndex = inner;
				}
			}
			temp = list.get(list.size() - 1 - outer);
			list.set(list.size() - 1 - outer, list.get(maxIndex));
			list.set(maxIndex, temp);
			
//			temp = list.get(maxIndex);
//			list.set(maxIndex, list.get(list.size() - 2));
//			list.set(list.size() - 2, temp);
		}
	}

	/* Write code for an Insertion Sort algorithm that starts at the left side
	 * of an ArrayList of Comparable objects and inserts the first item (in
	 * position 1) into it's correct place within the first two items...then
	 * inserts the third item into its correct place on the left, then the fourth
	 * item into its correct place on the left, etc, until the last item is
	 * inserted into the list.  Insert items so the result is an ArrayList arranged
	 * in descending order.
	*/
	void insertionSort(ArrayList <Comparable> list) {

	}

	ArrayList <Comparable> initializeList() {

		String[] words = {"apple", "orange", "banana", "pear", "peach", "plum",
						  "grape", "cherry", "apricot", "strawberry"};

		ArrayList <Comparable> temp = new ArrayList<Comparable>();
		ArrayList <Comparable> list = new ArrayList<Comparable>();

		for (int i = 0; i < words.length; i++)
			temp.add(words[i]);

		list.clear(); // clear the list before adding to it

		while (temp.size() > 0) {
			list.add(temp.remove((int)(Math.random()*temp.size())));
		}

		return list;
	}
	
	ArrayList <Comparable> duplicate(ArrayList<Comparable> list) {

		ArrayList<Comparable> listCopy = new ArrayList<Comparable>();
		
		Iterator<Comparable> iter = list.iterator();
		
		while(iter.hasNext()){
			listCopy.add(iter.next());
		}
		
		return listCopy;
	}


}