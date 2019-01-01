//Store Reflection

//Alex Bruckhaus, Period 6, 1/21/2018
//This program took me 1 hour and 30 minutes.
//Overall this program was a good refresher on many previous topics including getters and setters and loading files.
//Loading the file took me a while and I used the notes and the internet to help guide me through the load method. The merge part
//of the assignment was really interesting to see how you can apply this to real life situations. Overall, this assignment showed
//me the importance and the value of sorting algorithms and how they can be applied in real life.


//Search Reflection

//Alex Bruckhaus, Period 6, 1/22/2018
//This program took me 30 minutes.
//This program was really interesting because, again, it can be used for practical real life situations. I find this really fascinating.
//Through out the program, I did not have much difficulty understanding the process of binary search. I thought the iterative version
//and recursive version were similar in terms of difficulty, with the recursive one being a little easier to understand. Overall,
//I learned a lot about searching and the efficiency of using binary search as opposed to sequential search.

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class P6_Bruckhaus_Alexander_SearchDriver {
	
	public static void main(String[] args) {
		Store s = new Store("file50.txt");
		s.sort();
		s.displayStore();
		s.testSearch();
	}
}

		
class Item implements Comparable<Item> {

	private int myId;
	private int myInv;

	/**
	*  Constructor for the Item object
	*
	* @param  id   id value
	* @param  inv  inventory value
	*/	
	public Item(int id, int inv){
		myId = id;
		myInv = inv;
	}
	
	/**
	*  Gets the id attribute of the Item object
	*
	* @return    The id value
	*/	
	public int getId(){
		return myId;
	}
	
	/**
	*  Gets the inv attribute of the Item object
	*
	* @return    The inv value
	*/	
	public int getInv(){
		return myInv;
	}
	
	/**
	*  Compares this item to another item based on id number. Returns the
	*  difference between this item's id and the other item's id. A
	*  difference of zero means the items' ids are equal in value.
	*
	* @param  other  Item object to compare to
	* @return        positive int if myId > other.myId
	*                0 if myId == other.myId
	*                negative int if myId < other.myId
	*/	
	public int compareTo(Item other){
		return myId - other.myId;
	}
	
	/**
	*  Compares the Item to the specified object
	*
	* @param  otherObject  Item object to compare to
	* @return              true if equal, false otherwise
	*/	
	public boolean equals(Item other){
		return other.myId == myId && other.myInv == myInv;
	}

	/**
	*  Overrides the default toString() of Object.
	*  Returns a String representation of this object. It's up to you
	*  exactly what this looks like.
	*/
	public String toString(){
		return "ID # : " + myId + ", " +
			   "Inv # : " + myInv;
	}
}	


class Store {

	private ArrayList <Item> myStore = new ArrayList <Item>();

	/**
	*  Creates a Store object from data stored in the given file name
	*
	*  @param  fName  name of the file containing id/inv pairs of data
	*/
	public Store(String fName){
		loadFile(fName);
	}
	
	/**
	*  Reads a file containing id/inv data pairs one pair per line. 
	*
	*  @param  inFileName  name of file containing id/inv pairs of data
	*/
	private void loadFile(String inFileName){
		try {
			BufferedReader in = new BufferedReader(new FileReader(inFileName));
			String str;
			while((str = in.readLine()) != null) {
				String[] fields = str.split("\\s+");
				//System.out.println("Str: " + str);
				//System.out.println("Fields: " + Arrays.toString(fields));
				myStore.add(new Item(Integer.parseInt(fields[1]), Integer.parseInt(fields[2])));
			}
		}catch (IOException e){
			e.printStackTrace();
		}
		//System.out.println("My Store: " + myStore);
	}
	
	/**
	*  Prints the store contents in the format shown below
	*  Line #   	Id	     	Inv
	*  1	       	184	    	14
	*  2	       	196	    	60
	*/
	public void displayStore(){
		System.out.printf("\t%7s\t%8s\r\n", "Id", "Inv");
		for(int row = 0; row < myStore.size(); row++) {
			Item item = myStore.get(row);
			System.out.printf("%d\t%8d\t%8d\r\n", row + 1, item.getId(), item.getInv());
			if(row % 10 == 9) {
				System.out.println();
			}
		}
	}

	/**
	*  Sorts the store ArrayList using recursive mergesort
	*/
	public void sort(){
		// Make a single call to mergeSort to get sorting going
		// (If your mergeSort is broken, then use a quadratic sort)
		mergeSort(myStore, 0, myStore.size() - 1);
	}
	
	private void merge(ArrayList <Item> a, int first, int mid, int last){
		int i = first;
		int j = mid + 1;
		while(true) {
			//System.out.printf("a: %s, first: %d, mid: %d, last: %d\n", a, first, mid, last);
			if(i > mid && j > last) {
				//System.out.printf("both done: %s\n", a);
				break;
			}else if(i > mid) {
				//System.out.printf("left is done: %s\n", a);
				break;
			}else if(j > last) {
				//System.out.printf("right is done: %s\n", a);
				break;
			}else if(a.get(i).compareTo(a.get(j)) > 0 ) {
				//System.out.printf("a(%d): %d > a(%d): %d.\n", i, a.get(i), j, a.get(j));
				a.add(i, a.get(j));
				a.remove(j + 1);
				mid++;
				j++;
			}else {
				//System.out.printf("a(%d): %d <= a(%d): %d.\n", i, a.get(i), j, a.get(j));
				i++;
			}
		}
	}
	
	/**
	*  Recursive mergesort of an ArrayList of Items
	*
	* @param  a      reference to an ArrayList of Items to be sorted
	* @param  first  starting index of range of values to be sorted
	* @param  last   ending index of range of values to be sorted
	*/
	public void mergeSort(ArrayList <Item> a, int first, int last){
		 if (last == first){
			  //System.out.printf("list %s only has one element, first %d, last %d\n", a, first, last);
			  return; 
		  } else if (first + 1 == last){
			  //System.out.printf("list %s only has two elements, first %d, last %d\n", a, first, last);
			  if(a.get(first).compareTo(a.get(last)) > 0) {
				  //System.out.printf("swapping two elements\n");
				  Item temp = a.get(first);
				  a.set(first, a.get(last));
				  a.set(last, temp);
				  return;
			  }
		  }else{ // recursion, divide list into two halves
			  //System.out.printf("sorting more than two values: %s, first %d, last %d\n", a, first, last);
			  //Find midpoint of current sublist: 
			  int mid = (first + last) / 2;
			  //Call mergeSort and process left sublist:
			  mergeSort(a, first, mid);
			  //Call mergeSort and process right sublist:
			  mergeSort(a, mid + 1, last);
			  //merge left and right sublists:
			  merge(a, first, mid, last);
			  //System.out.printf("sorted more than two values %s, first %d, last %d\n", a, first, last);
		  } 
	}
	
	public void testSearch(){
		   int idToFind;
		   int invReturn;
		   int index;
		   Scanner in = new Scanner(System.in);

		   System.out.println("Testing search algorithm\n");
		   do{
		      System.out.println();
		      System.out.print("Enter Id value to search for (-1 to quit) ---> ");
		      idToFind = in.nextInt();
		      index = bsearch(new Item(idToFind, 0));
		      //recursive version call
		      //index = bsearch(new Item(idToFind, 0), 0, myStore.size()-1);
		      System.out.print("Id # " + idToFind);
		      if (index == -1){
		         System.out.println(" No such part in stock");
		      }else{
		         System.out.println(" Inventory = " + myStore.get(index).getInv());
		      }
		   } while (idToFind >= 0);
		}

		/**
		   * Searches the myStore ArrayList of Item Objects for the specified
		   * item object using a iterative binary search algorithm
		   *
		   * @param idToSearch Item object containing id value being searched for
		   * @return index of Item if found, -1 if not found
		*/
		private int bsearch(Item idToSearch){
		   int min = 0;
		   int max = myStore.size() - 1;
		   while(min <= max) {
			   int mid = (min + max) / 2;
			   //System.out.println("Mid: " + mid);
			   if(myStore.get(mid).getId() < idToSearch.getId()) {
				   min = mid + 1;
			   }else if(myStore.get(mid).getId() > idToSearch.getId()) {
				   max = mid - 1;
			   }else {
				   return mid;
			   }
		   }
		   return -1;
		}

		/**
		* Searches the specified ArrayList of Item Objects for the specified
		   * id using a recursive binary search algorithm
		   *
		   * @param idToSearch Id value being search for
		   * @param first Starting index of search range
		   * @param last Ending index of search range
		   * @return index of Item if found, -1 if not found
		*/
		private int bsearch(Item idToSearch, int first, int last){
		   int mid = (first + last) /2;
		   if(last < first) {
			   return -1;
		   }
		   if(myStore.get(mid).getId() > idToSearch.getId()) {
			   return bsearch(idToSearch, first, mid - 1);
			   
		   }
		   if(myStore.get(mid).getId() < idToSearch.getId()) {
			  return bsearch(idToSearch, mid + 1, last);
		   }
		   if(idToSearch.getId() == myStore.get(mid).getId()) {
			   return mid;
		   }
		   return -1;
		}
}		