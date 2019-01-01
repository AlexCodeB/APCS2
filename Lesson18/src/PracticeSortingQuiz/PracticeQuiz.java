package PracticeSortingQuiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PracticeQuiz {

	public static void main(String[] args) {

		String[] words = { "abe", "bat", "car", "cat", "dab", "elf", "fig", "get" };
		ArrayList<String> wordList = new ArrayList<String>();

		for (String word : words) {
			wordList.add(word);
		}

		System.out.println("***** Selection Sort *****");
		Collections.shuffle(wordList);
		System.out.println("Before sorting: " + wordList);
		selectionSort(wordList);
		System.out.println("After sorting:  " + wordList);

		int[] intArray = generateRandomIntArray(8);
		
		System.out.println("\n***** Midsertion Sort *****");
		System.out.println("Before sorting: " + Arrays.toString(intArray));
		midsertionSort(intArray);
		System.out.println("After sorting:  " + Arrays.toString(intArray));

		intArray = generateRandomIntArray(8);
		
		System.out.println("\n***** V Sort *****");
		System.out.println("Before sorting: " + Arrays.toString(intArray));
		vSort(intArray);
		System.out.println("After sorting:  " + Arrays.toString(intArray));
	}

	private static int[] generateRandomIntArray(int length) {
		int[] arr = new int[length];
		for (int i = 0; i < length; i++) {
        	arr[i] = (int)(Math.random() * length);
        }
		return arr;
	}
	
	private static void selectionSort(ArrayList<String> arr) {
		for(int i = arr.size() - 1; i > 0; i--) {
			int min = i;
			for(int j = 0; j < i; j++) {
				if(arr.get(j).compareTo(arr.get(min)) < 0) {
					min = j;
				}
			}
			// swap values at indexes: max, i
			String temp = arr.get(min);
			arr.set(min, arr.get(i));
			arr.set(i, temp);
			System.out.println(arr);
		}	
	}

	public static void midsertionSort(int[] arr) {
		int i = 0;
		int j = arr.length - 1;
		System.out.println(Arrays.toString(arr));
		while(i < j) {
			if(arr[i] < arr[j]) {
				int temp = arr[j];
				arr[j] = arr[i];
				arr[i] = temp;
			}
			i++;
			j--;
		}
		System.out.println(Arrays.toString(arr));
		int k = (arr.length - 1) / 2;
		int l = (arr.length) / 2;
		while(k > 0 && l <= arr.length - 1) {
			int m = k;
			while(arr[m - 1] < arr[m]) {
				int temp = arr[m];
				arr[m] = arr[m - 1];
				arr[m - 1] = temp;
				m++;
			}
			m = l;
			while(arr[m + 1] > arr[m]) {
				int temp = arr[m];
				arr[m] = arr[m + 1];
				arr[m + 1] = temp;
				m--;
			} 
			System.out.println(Arrays.toString(arr));
			k--;
			l++;
		}
	}

	public static void vSort(int[] arr) {
		for(int i = 1; i < arr.length / 2; i++) {
			int pos = i;
			int key = arr[pos];
			while(pos > 0 && arr[pos - 1] < key) {
				arr[pos] = arr[pos - 1];
				pos--;
			}
			arr[pos] = key;
		}	
		for(int i = arr.length - 1; i >= arr.length / 2; i--) {
			int max = i;
			for(int j = arr.length / 2; j < i; j++) {
				if(arr[j] > arr[max]) {
					max = j;
				}
			}
			int temp = arr[i];
			arr[i] = arr[max];
			arr[max] = temp;
		}
	}
}