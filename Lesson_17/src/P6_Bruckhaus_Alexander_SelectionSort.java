//SelectionSort Reflection

//Alex Bruckhaus, Period 6, 1/4/2018
//This program took me 2 hours and 30 minutes.
//The majority of my time was spent sorting the strings and the deck of cards. Using selection sort for integers was not as difficult for me
//and I quickly understood the process of it. However, sorting the strings took me a little more time to understand, but after
//slowly tracing my code, I began to understand my errors and from there, I resolved them. Sorting the deck of cards took me a long time because
//I had to access my SpiderSolitaire lab and apply selection sort to a different context. However, after working on it for a while,
//I began to understand it more and more. This program helped me understand selection sort a lot more.


import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;


public class P6_Bruckhaus_Alexander_SelectionSort {
	
	public static void selectionSort1(int[] list) {
		
		int maxIndex = 0;
		for(int outer = 0; outer < list.length - 1; outer++) {
			maxIndex = 0;
			for(int inner = 0; inner < list.length - outer; inner++) {
				if(list[inner] > list[maxIndex]) {
					maxIndex = inner;
				}
			}
			int temp = list[list.length - outer - 1];
			list[list.length - outer - 1] = list[maxIndex];
			list[maxIndex] = temp;
			System.out.println(Arrays.toString(list));
		}
	}
	
	public static void selectionSort2(String[] list2) {
		int maxIndex = 0;
		for(int outer = 0; outer < list2.length - 1; outer++) {
			maxIndex = outer;
			for(int inner = outer + 1; inner < list2.length; inner++) {
				if(list2[inner].compareTo(list2[maxIndex]) > 0) {
					maxIndex = inner;
				}
			}
			String temp = list2[outer];
			list2[outer] = list2[maxIndex];
			list2[maxIndex] = temp;
			System.out.println(Arrays.toString(list2));
		}
	}
	
	public static void selectionSort(ArrayList<Card> arr) {
		int count = arr.size();
		Card temp = new Card(null, 0);
		int maxIndex = 0;
		for(int outer = 0; outer < arr.size() - 1; outer++) {
			maxIndex = 0;
			for(int inner = 0; inner < arr.size() - outer; inner++) {
				if(arr.get(inner).getValue() > arr.get(maxIndex).getValue()) {
					maxIndex = inner;
				}
			}
			temp = arr.get(count - outer - 1);
			arr.set(count - outer - 1, arr.get(maxIndex));
			arr.set(maxIndex, temp);
		}
	}
	
	public static void main(String[] args) {
		int[] list = new int[] {9, 5, 1, 3, 7, 4};
		System.out.println("Starting array: " + Arrays.toString(list));
		selectionSort1(list);
		System.out.println("Ending array: " + Arrays.toString(list));
		
		String[] list2 = new String[] {"chicken", "apple", "zebra", "bee"};
		System.out.println("Starting array : " + Arrays.toString(list2));
		selectionSort2(list2);
		System.out.println("Ending array : " + Arrays.toString(list2));
		
		System.out.println("Testing deck sorting:");
	 	ArrayList<Card> deck = new ArrayList<Card>();
	 	Random random = new Random();
        for(int i = 0; i < 13; i++){
        		int r = random.nextInt(13) + 1;
        		Card card = new Card("" + i, r);
        		card.setFaceUp(true);
            deck.add(card);
            
        }
        System.out.println("Created Deck: " + deck);
        selectionSort(deck);
        System.out.println("Sorted Deck : " + deck);
        
		
	}
}
