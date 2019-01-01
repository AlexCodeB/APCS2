package PracticeSortingQuiz;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class CardTester {

	public static void main(String[] args) {

		char[] symbols = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
		int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
		String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
		
		Card[] cards = new Card[symbols.length * suits.length];
		int counter = 0;
		
		for (int j = 0; j < suits.length; j++) {
			for (int i = 0; i < symbols.length; i++) {
				cards[counter] = new Card(symbols[i], values[i], suits[j]);
				counter++;
			}
		}

		System.out.println("Testing your implementation of the Comparable interface");
		for (int i = 0; i < 10; i++) {
			Card c1 = cards[(int)(Math.random()*cards.length)];
			Card c2 = cards[(int)(Math.random()*cards.length)];			
			System.out.println(c1 + ".compareTo(" + c2 + ") returned " + c1.compareTo(c2));
		}
		
		System.out.println("Before shuffling (this is what the deck looks like in ascending order):");
		System.out.println(Arrays.toString(cards));

		shuffle(cards);
		System.out.println("\nAfter shuffling:\n" + Arrays.toString(cards));

		Arrays.sort(cards);
		System.out.println("\nAfter sorting with Java's built-in sort:\n" + Arrays.toString(cards));

		shuffle(cards);
		bubbleSort(cards);
		System.out.println("\nAfter sorting with your Bubble Sort:\n" + Arrays.toString(cards));
	}
	
	public static void shuffle(Card[] cards) {
        for (int i = 0; i < cards.length; i++) {
        	int randIndex = (int)(Math.random()*cards.length);
        	Card temp = cards[i];
        	cards[i] = cards[randIndex];
        	cards[randIndex] = temp;
        }		
	}

	public static void bubbleSort(Card[] cards) {
		for(int i = 0; i < cards.length; i++) {
			for(int j = cards.length - 2; j >= i; j--) {
				if(cards[j + 1].compareTo(cards[j]) < 0) {
					Card temp = cards[j];
					cards[j] = cards[j + 1];
					cards[j + 1] = temp;
				}
			}
//			System.out.println(Arrays.toString(cards));
//			try {
//				TimeUnit.MILLISECONDS.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
		}
	}
}
