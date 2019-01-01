//Sorts Reflection

//Alex Bruckhaus, Period 6, 1/11/2018
//This program took me 30 minutes
//This lab was very interesting, yet quite simple. Initially, I was confused as to what exactly the task was, but as I began to re read the notes 
//and instructions, it became more clear to me. The end result was really interesting to see, as it actually displays the amount of steps it takes
//to complete each algorithm using a surprisingly easy algorithm. Overall, this assignment was very informative and fun.


//Sorts Reflection with MergeSort

//Alex Bruckhaus, Period 6, 1/18/2018
//This program took me 2 hours.
//Out of all the sorting algorithms I programmed, merge sort was probably the most difficult to understand and to code.
//It took me a while to understand the process of mergesort, but the merge algorithm helped me work my way up to that level.
//I spent a lot of time testing my code by printing out the output every so often. After counting the steps, it seems like mergeSort
//is by far the fastest out of all the algorithms we programmed. Overall, this lab was quite challenging and interesting.

package P6_Bruckhaus_Alexander_SortInvestigation;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 *  Description of the Class
 *
 * @author     Your Name Here
 * @created    Month Day, Year
 */
public class P6_Bruckhaus_Alexander_Sorts{
  private long steps;

  /**
   *  Description of Constructor
   *
   * @param  list  Description of Parameter
   */
  public P6_Bruckhaus_Alexander_Sorts(){
    steps = 0;
  }
  

  public void gnomeSort(ArrayList<Comparable> list){
	  int i = 1;
	  steps++;
	  Comparable temp = 0;
	  steps++;
	  while(i < list.size()){
		  steps++;
		  steps++;
		  if(i == 0 || list.get(i).compareTo(list.get(i - 1)) >= 0){
			  steps++;
			  steps++;
			  steps++;
			  steps++;
			  i++;
			  steps++;
		  }else{
			  temp = list.get(i);
			  steps++;
			  steps++;
			  list.set(i, list.get(i - 1));
			  steps++;
			  steps++;
			  steps++;
			  list.set(i - 1, temp);
			  steps++;
			  steps++;
			  i--;
			  steps++;
		  }
	  }
  }


  /**
   *  Description of the Method
   *
   * @param  list  reference to an array of integers to be sorted
   */
  public void bubbleSort(ArrayList <Comparable> list) { //ASCENDIN
	  Comparable temp = 0;
	  steps++; // initialize temp
		for(int outer = list.size() - 1; outer > 0; outer--) {
			steps++; //initializing outer
			steps++; //subtracting one from outer
			steps++; //checking condition
			steps++; //decrementing outer
			for(int inner = 0; inner <  outer; inner++) {
				steps++; //initializing inner
				steps++; //checking condition
				steps++; //incrementing inner
				if(list.get(inner).compareTo(list.get(inner + 1)) > 0) {
					steps++; //getting inner
					steps++; //using compareTo() method
					steps++; //getting inner
					steps++; //adding one to inner
					steps++; //checking condition
					temp = list.get(inner + 1);
					steps++; //initializing temp
					steps++; //getting inner
					steps++; //adding one to inner
					list.set(inner + 1, list.get(inner));
					steps++; //setting the list
					steps++; //adding one to inner
					steps++; //getting inner
					list.set(inner, temp);
					steps++; //setting the list
				}
			}	
			//System.out.println(list);
			//DOESN'T COUNT B/C PRINTING IS NOT NECESSARY
		}
  }

  /**
   *  Description of the Method
   *
   * @param  list  reference to an array of integers to be sorted
   */
  public void selectionSort(ArrayList <Comparable> list){ //DESCENDING
	  int maxIndex = 0;
	  steps++; //initializing maxIndex
		for(int outer = 0; outer < list.size() - 1; outer++) {
			steps++; //initializing outer
			steps++; //checking condition
			steps++; //subtracting one from list size
			steps++; //incrementing outer
			maxIndex = 0;
			steps++; //initializing maxIndex
			for(int inner = 0; inner < list.size() - outer; inner++) {
				steps++; //initializing inner
				steps++; //checking condition
				steps++; //subtracting outer
				steps++; //incrementing inner
				if(list.get(inner).compareTo(list.get(maxIndex)) > 0) {
					steps++; //getting inner
					steps++; //using compareTo() method
					steps++; //getting maxIndex
					steps++; //checking condition
					maxIndex = inner;
					steps++; //setting maxIndex
				}
			}
			Comparable temp = list.get(list.size() - 1- outer);
			steps++; //initializing temp
			steps++; //getting list
			steps++; //subtracting one
			steps++; //subtracting outer
			list.set(list.size() - 1 - outer,  list.get(maxIndex));
			steps++; //setting list
			steps++; //subtracting one from list size
			steps++; //subtracting outer from list size
			steps++; //getting maxIndex
			list.set(maxIndex, temp);
			steps++; //setting list
			//System.out.println(list);
			//DOESN'T COUNT B/C PRINTING IS NOT NECCESSARY
			
		}
  }

  /**
   *  Description of the Method
   *
   * @param  list  reference to an array of integers to be sorted
   */
  public void insertionSort(ArrayList <Comparable> list){
	  for (int outer = 1; outer < list.size(); outer++){
		  	steps++; //initializing outer
		  	steps++; //checking condition
		  	steps++; //incrementing outer
		    int position = outer;
		    steps++; //initializing position
		    Comparable key = list.get(position);
		    steps++; //initializing key
		    steps++; //getting position
		    while (position > 0 && list.get(position - 1).compareTo(key) > 0){
		    	steps++; //checking condition
		    	steps++; //getting list
		    	steps++; //subtracting 1 from position
		    	steps++; //using compareTo() method
		    	steps++; //checking condition
		      list.set(position, list.get(position - 1));
		      steps++; //setting position
		      steps++; //getting position
		      steps++; //subtracting 1 from position
		      position--;
		      steps++; //decrementing position
		    }
		    list.set(position, key);
		    steps++; //setting position
		  }
		
  }


 /**
   *  Takes in entire vector, but will merge the following sections
   *  together:  Left sublist from a[first]..a[mid], right sublist from
   *  a[mid+1]..a[last].  Precondition:  each sublist is already in
   *  ascending order
   *
   * @param  a      reference to an array of integers to be sorted
   * @param  first  starting index of range of values to be sorted
   * @param  mid    midpoint index of range of values to be sorted
   * @param  last   last index of range of values to be sorted
   */
  public void merge(ArrayList <Comparable> a, int first, int mid, int last){
		int i = first;
		steps++; // initializing i
		int j = mid + 1;
		steps++; // initializing j
		steps++; // adding 1 to mid
		while(true) {
			steps++; //checking condition
			//System.out.printf("a: %s, first: %d, mid: %d, last: %d\n", a, first, mid, last);
			if(i > mid && j > last) {
				steps++; // checks condition
				steps++; // checks condition
				//System.out.printf("both done: %s\n", a);
				steps++; // break
				break;
			}else if(i > mid) {
				steps++; // checks condition
				//System.out.printf("left is done: %s\n", a);
				steps++; // break
				break;
			}else if(j > last) {
				steps++; // checks condition
				//System.out.printf("right is done: %s\n", a);
				steps++; // break
				break;
			}else if(a.get(i).compareTo(a.get(j)) > 0 ) {
				steps++; // access i
				steps++; // compares
				steps++; // access j
				steps++; // check condition
				//System.out.printf("a(%d): %d > a(%d): %d.\n", i, a.get(i), j, a.get(j));
				a.add(i, a.get(j));
				steps++; // add
				steps++; // access j
				a.remove(j + 1);
				steps++; // remove
				steps++; // add one
				steps++; // stores
				mid++;
				steps++; // increment mid
				j++;
				steps++; // increment j
			}else {
				//System.out.printf("a(%d): %d <= a(%d): %d.\n", i, a.get(i), j, a.get(j));
				i++;
				steps++; //increment i
			}
		}
  }
		//System.out.println("a: " + a);


  /**
   *  Recursive mergesort of an array of integers
   *
   * @param  a      reference to an array of integers to be sorted
   * @param  first  starting index of range of values to be sorted
   * @param  last   ending index of range of values to be sorted
   */
  public void mergeSort(ArrayList <Comparable> a, int first, int last){
	  if (last == first){
		  steps++; // checks condition
		  //System.out.printf("list %s only has one element, first %d, last %d\n", a, first, last);
		  steps++; // return
		  return; 
	  } else if (first + 1 == last){
		  steps++; // add one to first
		  steps++; // checks condition
		  //System.out.printf("list %s only has two elements, first %d, last %d\n", a, first, last);
		  if(a.get(first).compareTo(a.get(last)) > 0) {
			  steps++; // access first
			  steps++; // compares
			  steps++; // access last
			  steps++; // checks condition
			  //System.out.printf("swapping two elements\n");
			  Comparable temp = a.get(first);
			  steps++; // initialize temp;
			  steps++; // access first
			  a.set(first, a.get(last));
			  steps++; // set
			  steps++; // access last
			  a.set(last, temp);
			  steps++; // set
			  steps++; // returns
			  return;
		  }
	  }else{ // recursion, divide list into two halves
		  //System.out.printf("sorting more than two values: %s, first %d, last %d\n", a, first, last);
		  //Find midpoint of current sublist: 
		  int mid = (first + last) / 2;
		  steps++; // initialize mid
		  steps++; // add first and last
		  steps++; // divide by 2
		  //Call mergeSort and process left sublist:
		  mergeSort(a, first, mid);
		  //Call mergeSort and process right sublist:
		  mergeSort(a, mid + 1, last);
		  //merge left and right sublists:
		  merge(a, first, mid, last);
		  //System.out.printf("sorted more than two values %s, first %d, last %d\n", a, first, last);
	  } 
  }
 
  /**
   *  Accessor method to return the current value of steps
   *
   */
  public long getStepCount(){
    return steps;
  }

  /**
   *  Modifier method to set or reset the step count. Usually called
   *  prior to invocation of a sort method.
   *
   * @param  stepCount   value assigned to steps
   */
  public void setStepCount(long stepCount){
    steps = stepCount;
  }
  
   /**
   *  Interchanges two elements in an ArrayList
   *
   * @param  list  reference to an array of integers
   * @param  a     index of integer to be swapped
   * @param  b     index of integer to be swapped
   */
  public void swap(ArrayList <Comparable> list, int a, int b){
	//replace these lines with your code
	System.out.println();
	System.out.println("Swap");
	System.out.println();
  }
  
  //public static void main(String[] args) {
//	  ArrayList<Comparable> a = new ArrayList<Comparable>();
//	  ArrayList<Comparable> b = new ArrayList<Comparable>();
//	  Random random = new Random();
//	  for(int i = 0; i < 5; i++) {
//		  a.add(random.nextInt(100));
//		  b.add(random.nextInt(100));
//	  }
//	  P6_Bruckhaus_Alexander_Sorts p = new P6_Bruckhaus_Alexander_Sorts();
//	  p.bubbleSort(a);
//	  p.bubbleSort(b);
//	  a.addAll(b);
//	  p.merge(a, 0, 4, 9);
	  
//	  ArrayList<Comparable> a = new ArrayList<Comparable>();
//	  Random random = new Random();
//	  for(int i = 0; i < 10; i++) {
//		  a.add(random.nextInt(100));
//	  }
//	  P6_Bruckhaus_Alexander_Sorts p = new P6_Bruckhaus_Alexander_Sorts();
//	  p.mergeSort(a, 0, 9);
  //}
}



