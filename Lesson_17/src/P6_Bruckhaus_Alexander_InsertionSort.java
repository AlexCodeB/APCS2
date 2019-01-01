//InsertionSort Reflection

//Alex Bruckhaus, Period 6, 1/7/2018
//This program took me 1 hour.
//This sorting algorithm was the most difficult to understand so far, but I used multiple videos to help me grasp the logic of insertion sort. After understanding,
//this program became smoother and easier to write. It was also interesting to write the YelpReview part of the lab, considering it sorts the ratings from 
//highest to lowest. It took me a while to fully understand, but once I did, everything became easier.
//Overall, I learned a lot about insertion sort from this lab and how it can be used in every day life.

import java.util.ArrayList;
import java.util.Arrays;

public class P6_Bruckhaus_Alexander_InsertionSort {


	
	public static void insertionSort1(int[] arr) {
		
//		for(int i = 1; i < arr.length; i++) {
//			int key = arr[i];
//			int j = i - 1;
//			while(j >= 0 && arr[j] > key) {
//				arr[j + 1] = arr[j];
//				j--;
//			}
//			arr[j +1] = key; 
//		}
		
		//ASCENDING ^^^
		
		for(int i = arr.length - 2; i > 0; i--) {
			int key = arr[i];
			int j = i + 1;
			while(j <= arr.length - 1 && arr[j] > key) {
				arr[j - 1] = arr[j];
				j++;
			}
			arr[j - 1] = key;
		}
		
		
		
		
		
		
//		for(int outer = 1; outer < arr.length; outer++) {
//			int key = arr[outer];
//			int inner = outer - 1;
//			while(inner >= 0 && arr[inner] > key) {
//				arr[inner + 1] = arr[inner];
//				inner--;
//			}
//			arr[inner + 1] = key;
//			System.out.println(Arrays.toString(arr));
//		}
	}
	
	public static void insertionSort2(String[] arr2) {
		for(int outer = arr2.length - 2; outer >= 0; outer--) {
			String key = arr2[outer];
			int inner = outer + 1;
			while(inner < arr2.length && arr2[inner].compareTo(key) > 0) {
				arr2[inner - 1] = arr2[inner];
				inner++;
			}
			arr2[inner - 1] = key;
			System.out.println(Arrays.toString(arr2));
		}
	}
	
	public static void insertionSort3(ArrayList<P6_Bruckhaus_Alexander_YelpRating> list) {
		for(int outer = 1; outer < list.size(); outer++) {
			P6_Bruckhaus_Alexander_YelpRating key = list.get(outer);
			int inner = outer - 1;
			while(inner >= 0 && list.get(inner).compareTo(key) > 0) {
				list.set(inner + 1, list.get(inner));
				inner--;
			}
			list.set(inner + 1, key);
			//System.out.println(list);
		}
	}
	public static void main(String[] args) {
		int[] ar = new int[] {9, 5, 1, 3, 7, 4};
		System.out.println("Starting array: " + Arrays.toString(ar));
		insertionSort1(ar);
		System.out.println("Ending array: " + Arrays.toString(ar));
		
		String[] ar2 = new String[] {"cat", "dog", "zebra", "hamster"};
		System.out.println("Starting array: " + Arrays.toString(ar2));
		insertionSort2(ar2);
		System.out.println("Ending array: " + Arrays.toString(ar2));
		
		
		ArrayList<P6_Bruckhaus_Alexander_YelpRating> list = new ArrayList<P6_Bruckhaus_Alexander_YelpRating>();
		
		list.add(new P6_Bruckhaus_Alexander_YelpRating("target", "good", 4.5, "jimmy"));
		list.add(new P6_Bruckhaus_Alexander_YelpRating("target", "good", 4.0, "jimmy"));
		list.add(new P6_Bruckhaus_Alexander_YelpRating("target", "good", 3.5, "jimmy"));
		list.add(new P6_Bruckhaus_Alexander_YelpRating("target", "good", 5.0, "jimmy"));
		System.out.println(list);
		insertionSort3(list);
		System.out.println(list);
		
	}

}
