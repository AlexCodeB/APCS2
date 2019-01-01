//MagicSquare Reflection

//Alex Bruckhaus, Period 6, 2/4/2018
//This program took me 1 hour.
//In the beginning, I was perplexed by 2D arrays, but after carefully listening to the lecture and reading over the notes, it became easier for me.
//This lab was not too difficult, but did take a little time to understand before completing the assignment. The most difficult part of the lab was to
//determine if the square was unique. After asking for help from my father, I realized that I could put the contents of the square into an array list
//and then sort it to check if the contents of the square is unique. Overall, I learned a lot about 2D arrays from this lab.



import java.util.ArrayList;

public class P6_Bruckhaus_Alexander_MagicSquare {

	/**
	 * Uses the other methods in this class to determine whether or not the
	 * given array is a Magic Square
	 */
	boolean isMagicSquare(int[][] matrix) {
		int sum = sumULDiagonal(matrix);
		if((sumURDiagonal(matrix) != sum) || !(unique(matrix))) {
			return false;
		}
		for(int i = 0; i < matrix[0].length; i++) {
			if((sumRow(matrix, i) != sum) || !(unique(matrix))) {
				return false;
			}
		}
		for(int i = 0; i < matrix[0].length; i++) {
			if((sumCol(matrix, i) != sum) || !(unique(matrix))) {
				return false;
			}
		}
		return true;
	}

	/** Returns the sum of values in the given row */
	int sumRow(int[][] matrix, int row) {
		int sum = 0;
		for(int i = 0; i < matrix[row].length; i++) {
			sum += matrix[row][i];
		}
		return sum;
	}

	/** Returns the sum of values in the given column */
	int sumCol(int[][] matrix, int col) {
		int sum = 0;
		for(int i = 0; i < matrix[col].length; i++) {
			sum += matrix[i][col];
		}
		return sum;
	}

	/** Returns the sum of the upper left diagonal */
	int sumULDiagonal(int[][] matrix) {
		int sum = 0;
		for(int i = 0; i < matrix[0].length; i++) {
			sum += matrix[i][i];
		}
		return sum;
	}

	/** Returns the sum of the upper right diagonal */
	int sumURDiagonal(int[][] matrix) {
		int sum = 0;
		for(int i = 0; i < matrix[0].length; i++) {
			sum += matrix[i][matrix.length - 1 - i];
		}
		return sum;
	}
	
	void selectionSort(ArrayList <Integer> list){
		  int min, temp;

		  for (int outer = 0; outer < list.size() - 1; outer++){
		    min = outer;
		    for (int inner = outer + 1; inner < list.size(); inner++){
		      if (list.get(inner) < list.get(min)) {
		        min = inner; // a new smallest item is found
		      }
		    }
		    //swap list[outer] & list[min]
		    temp = list.get(outer);
		    list.set(outer, list.get(min));
		    list.set(min, temp);
		  }
		}

	/**
	 * Returns whether or not the given matrix is unique, meaning that it
	 * contains each integer from 1 to MAX*MAX. Precondition: matrix is square
	 * and initialized with integers
	 */
	boolean unique(int[][] matrix) {
		int limit = matrix.length * matrix.length;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for(int row = 0; row < matrix[0].length; row++) {
			for(int col = 0; col < matrix.length; col++) {
				int val = matrix[row][col];
				numbers.add(val);
				if(val < min) {
					min = val;
				}
				if(val > max) {
					max = val;
				}
			}
		}
		if(max != limit || min != 1) {
			return false;
		}
		selectionSort(numbers);
		for(int i = 0; i < numbers.size(); i++) {
			if(numbers.get(i) != i + 1) {
				return false;
			}
		}
		return true;
	}



	public static void main(String[] args) {

		/**** Sample arrays used during testing ****/

		// A 4x4 magic square
		int[][] array1 = { {16,  3,  2, 13},
						   { 5, 10,	11,  8},
						   { 9,  6,  7, 12},
						   { 4, 15, 14,  1} };
		
		// A 5x5 magic square
		int[][] array2 = { { 1,  2, 19, 20, 23},
				   		   {18, 16,	 9, 14,  8},
				   		   {21, 11, 13, 15,  5},
				   		   {22, 12, 17, 10,  4},
						   { 3, 24,  7,  6, 25} };

		// A 4x4 semi-magic square (diagonals don't match row/col sums)
		int[][] array3 = { {110,  72,  63, 80 },
						   { 64, 105,  66, 90 },
						   { 81,  88, 100, 56 },
						   { 70,  60,  96, 99 } };

		// a 5x5 non-magical square (bottom row, last col don't add)
		int[][] array4 = { { 11, 15, 24,  3,  8 },
						   {  5, 14, 18, 22,  1 },
						   {  4,  8, 12, 16, 20 },
						   { 23,  2,  6, 10, 19 },
						   { 17, 21,  0,  9, 13 } };

		// Unique only
		int[][] array5 = { {16,  3,  1, 13},
						   { 5, 10,	11,  8},
						   { 9,  6,  7, 12},
						   { 4, 15, 14,  2} };

		// Sums all same but not unique
		int[][] array6 = { { 2, 2, 2, 2},
				   		   { 2, 2, 2, 2},
				   		   { 2, 2, 2, 2},
				   		   { 2, 2, 2, 2} };

		// Random
		int[][] array7 = { { 5, 8, 2, 5 },
						   { 1, 4, 6, 3 },
						   { 9, 4, 2, 1 },
						   { 7, 3, 8, 2 } };
		
		// Not unique b/c not 1 to n^2
		int[][] array8 = { { 1, 12,  8,  4 },
				   		   { 7,  2, 14, 15 },
				   		   { 5, 13,  9,  0 },
				   		   { 3,  6, 11, 10 } };
		
		/**** Test Cases ****/

		P6_Bruckhaus_Alexander_MagicSquare m = new P6_Bruckhaus_Alexander_MagicSquare();
		
		System.out.println("Test 1 (row sum): " + (m.sumRow(array3, 0) == 325 && m.sumRow(array7, 3) == 20 && m.sumRow(array5, 1) == 34 ? "PASSED" : "**** FAILED *****"));
		System.out.println("Test 2 (col sum): " + (m.sumCol(array3, 0) == 325 && m.sumCol(array7, 2) == 18 && m.sumCol(array7, 3) == 11? "PASSED" : "**** FAILED *****"));
		System.out.println("Test 3 (UL diag): " + (m.sumULDiagonal(array1) == 34 && m.sumULDiagonal(array7) == 13  && m.sumULDiagonal(array3) == 414 ? "PASSED" : "**** FAILED *****"));
		System.out.println("Test 4 (UR diag): " + (m.sumURDiagonal(array1) == 34 && m.sumURDiagonal(array7) == 22  && m.sumURDiagonal(array3) == 304 ? "PASSED" : "**** FAILED *****"));
		System.out.println("Test 5 (unique) : " + (m.unique(array1) && !m.unique(array4) && m.unique(array5) && !m.unique(array6) ? "PASSED" : "**** FAILED *****"));
		System.out.println("Test 6 (isMagic): " + 
				(m.isMagicSquare(array1) 
				&& m.isMagicSquare(array2) 
				&& !m.isMagicSquare(array3) 
				&& !m.isMagicSquare(array4) 
				&& !m.isMagicSquare(array5) 
				&& !m.isMagicSquare(array6)
				&& !m.isMagicSquare(array8)? "PASSED" : "**** FAILED *****"));		

	
}
}

