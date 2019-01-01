//BubbleSort Reflection

//Alex Bruckhaus, Period 6, 1/2/2018
//This program took me 40 minutes.
//Overall, this program was very informative and fun to learn. I learned a lot from the in class demo, which helped me understand sorting algorithms.
//The problem solving aspect and swapping took some time to figure out, but after that, everything made sense to me. To ensure accuracy, I ran multiple test cases,
//which helped me find and resolve issues in my code.

import java.util.ArrayList;
import java.util.Arrays;

public class P6_Bruckhaus_Alexander_BubbleSort {
	
	public static void bubbleSort1(int[] list) {
		int temp = 0;
		for(int outer = list.length - 1; outer > 0; outer--) {
			for(int inner = 0; inner <  outer; inner++) {
				if(list[inner] < list[inner + 1]) {
					temp = list[inner + 1];
					list[inner + 1] = list[inner];
					list[inner] = temp;
				}
			}	
			System.out.println(Arrays.toString(list));
		}
	}
	
	public static void bubbleSort2(String[] list2) {
		String temp = null;
		for(int outer = list2.length - 1; outer > 0; outer--) {
			for(int inner = 1; inner < outer + 1; inner++) {
				if(list2[inner - 1].compareTo(list2[inner]) > 0) {
					temp = list2[inner - 1];
					list2[inner - 1] = list2[inner];
					list2[inner] = temp;
				}
			}
			System.out.println(Arrays.toString(list2));
		}
 	}
	
	
	

	public static void main(String[] args) {		
		int[] li = new int[] {5, 7, 2, 4, 3, 9};
	    System.out.println("Starting array: " + Arrays.toString(li));
		bubbleSort1(li);
		System.out.println("Ending array: " + Arrays.toString(li));
		
//		String[] arr = new String[] {"disappeared", "crocodile", "cadabra", "abra"};
//		System.out.println("Starting array: " + Arrays.toString(arr));
//		bubbleSort2(arr);
//		System.out.println("Ending array: " + Arrays.toString(arr));
		
		
	}

}
