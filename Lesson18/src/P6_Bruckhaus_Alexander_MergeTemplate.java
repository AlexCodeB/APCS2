//Merge Reflection

//Alex Bruckhaus, Period 6, 1/15/2018
//This program took me 1 hour and 30 minutes.
//In the beginning, I had some difficulty understanding what merge actually was, but as I continued to watch videos and read notes,
//I began to understand it more thoroughly. While working on this assignment, I ran into a couple of issues. First, I had to realize that
//the special cases were to be handled first because I was using a while loop, incrementing i and j every iteration, which means
//that if i or j is greater than the list size, the list has been transferred already. Another issue I ran into involved my selection sort.
//Because I used a selection sort that sorted integers in descending order, this went against the task of merging the integers in non descending
//order. To fix this, I resolved the issue in my selection sort to sort the integers in ascending order. I then printed my code 
//periodically to test and debug my program. Overall, this assignment was very interesting and informative.

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class P6_Bruckhaus_Alexander_MergeTemplate {
 
	/**
	*  Sorts any ArrayList of Comparable objects using Selection Sort.
	*
	* @param  list  reference to an array of integers to be sorted
	*/
	public void selectionSort(ArrayList <Comparable> list) {
		int max;
		Comparable temp;

		  for (int outer = 0; outer < list.size() - 1; outer++){
		    max = outer;
		    for (int inner = outer + 1; inner < list.size(); inner++){
		      if (list.get(inner).compareTo(list.get(max)) < 0) {
		        max = inner; 
		      }
		    }
		   
		    temp = list.get(outer);
		    list.set(outer, list.get(max));
		    list.set(max, temp);
		  }
		
	}
 
	/**
	 *  Write a merge method to merge two sorted lists.
	 *
	 *  Preconditions: Lists A and B are sorted in nondecreasing order.
	 *  Action:        Lists A and B are merged into one list, C.
	 *  Postcondition: List C contains all the values from
	 *                 Lists A and B, in nondecreasing order.
	 */
	public void merge (ArrayList <Comparable> a, ArrayList <Comparable> b, ArrayList <Comparable> c) {
		//System.out.println("c : " + c);
		int i = 0;
		int j = 0;
		while(true) {
			if(i >= a.size() && j >= b.size()) {
				//System.out.println("both done : " + c);
				break;
			}else if(i >= a.size()) {
				//System.out.println("a is done" + c);
				c.add(b.get(j));
				j++;
			}else if(j >= b.size()) {
				//System.out.println("b is done : " + c);
				c.add(a.get(i));
				i++;
			}else if(a.get(i).compareTo(b.get(j)) > 0 ) {
				//System.out.printf("a: %d > b: %d. c: %s\n", a.get(i), b.get(j), c);
				c.add(b.get(j));
				j++;
			}else {
				//System.out.printf("a: %d <= b: %d. c: %s\n", a.get(i), b.get(j), c);
				c.add(a.get(i));
				i++;
			}
		}
		//System.out.println("c : " + c);
	}

	/**
	*  Write a method to
	*    - Ask the user how many numbers to generate
	*    - Ask the user to enter the largest integer to generate
	*    - Initialize an ArrayList of random Integers from 1 to largestInt
	*	- Return the ArrayList
	*
	* @return  an ArrayList of size specified by the user filled
	*          with random numbers
	*/
	public ArrayList <Comparable> fillArray() {
		Scanner s = new Scanner(System.in);
		System.out.println("How many numbers do you wish to generate?");
		int count = s.nextInt();
		
		System.out.println("Enter the largest integer you wish to generate.");
		int largestInt = s.nextInt();
		
		Random random = new Random();
		ArrayList<Comparable> list = new ArrayList<Comparable>();
		for(int i = 0; i < count; i++){
			int r = random.nextInt(largestInt) + 1;
			list.add(r);
		}
		return list;
	}

	/**
	*  Write a method to print out the contents of the ArrayList
	*  in tabular form, 20 columns.  You can use the \t escape character
	*  or use printf to format using fields.
	*/
	public void screenOutput(ArrayList <Comparable> temp) {
		int i = 0;
		while(i < temp.size()) {
			System.out.printf("%5d", temp.get(i));
			if((i + 1) % 20 == 0) {
				System.out.println();
			}
			i++;
		}
	}
}

