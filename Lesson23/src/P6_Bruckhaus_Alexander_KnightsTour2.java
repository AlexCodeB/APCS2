//KnightsTour1 Reflection

//Alex Bruckhaus, Period 6, 2/5/2018
//This program took me 1 hour and 30 minutes.
//This program was one of the most enjoyable assignments so far this year. However, coding it was somewhat of a challenge. I had some difficulty
//choosing a move that was valid. However, after coding many helper methods such as isValidMove() and validMoves(), it became easier for me.
//To choose a valid move, I stored possible moves into an arraylist and chose a random move to do. Running this program was very interesting
//to see how much of the square I could cover before there were no valid moves left. Overall, this assignment helped me a lot with understanding 2D arrays.

/*
  1 46 51  0  0 44 53 56
  50 19 48 45 52 55  6 43
  47  2 21  4  7 38 57 54
  20 49 18 23 28  5 42 39
  25 22  3  8 37 40 29 58
  14 17 24 27 34 31 10 41
   0 26 15 12  9 36 33 30
  16 13  0 35 32 11  0  0

  58 squares were visited
 */


//KnightsTour2 Reflection

//Alex Bruckhaus, Period 6, 2/8/2018
//This program took me 2 hours.
//Initially, I was very puzzled with this assignment. However, after reading into the accessibility strategy, I started to understand
//the program more. It took me a while to read the file and convert it into a 2D array. After that, the assignment was not too bad
//as I updated the isValidMove() method and made a getBestMoves() method. The next challenge was to update the accessibility so 
//I made a method that subtracted one from the accessible elements. Overall, I learned a lot from this assignment
//and it also helped me with my problem solving skills.

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class P6_Bruckhaus_Alexander_KnightsTour2 {
	private static int[][] board = new int[9][9];
	private static int count = 0;
	private static int[] horizontal = {+1, +2, +2, +1, -1, -2, -2, -1};
	private static int[] vertical =   {-2, -1, +1, +2, +2, +1, -1, -2};
	private static int kX;
	private static int kY;
	private static final int MAX = 8;
	private static int[][] accessibility;	
	
	public static void initGame() {
		for(int i = 1; i  < board[1].length; i++) {
			for(int j = 1; j < board.length; j++) {
				board[i][j] = 0;
			}
		}
		kX = 1;
		kY = 1;
		count++;
		board[1][1] = count;
		accessibility = getAccessibility();
	}
	
	public static void showBoard() {
		//System.out.println("\nBoard:");
		for(int i = 1; i  < board[1].length; i++) {
			for(int j = 1; j < board.length; j++) {
				System.out.printf("%3d", board[i][j]);
			}
			System.out.println();
			
		}
		//System.out.println("\nAccessibility:");
		for(int i = 0; i  < accessibility[0].length; i++) {
			for(int j = 0; j < accessibility.length; j++) {
				//System.out.printf("%3d", accessibility[i][j]);
			}
			//System.out.println();
		}
		System.out.println();
	}
	
	public static void move(int move) {
		//System.out.println("Making move: " + move + " = " + horizontal[move] + ", " + vertical[move]);
		kY = kY + vertical[move];
		kX = kX + horizontal[move];
		//System.out.println("New position: "+ kX + ", " + kY);
		count++;
		board[kY][kX] = count;
		updateAccessibility();
		showBoard();
	}  
	
	private static void updateAccessibility() {
		for(int move = 0; move < horizontal.length; move++) {
			int row = kY + vertical[move];
			int col = kX + horizontal[move];
			if(row >= 1 && col >= 1 && row <= MAX && col <= MAX && board[row][col] == 0 && accessibility[row - 1][col - 1] > 0) {
				accessibility[row - 1][col - 1]--;
			}
		}
	}

	private static boolean isValidMove(int move) {
		int row = kY + vertical[move];
		int col = kX + horizontal[move];
		if(row >= 1 && col >= 1 && row <= MAX && col <= MAX && board[row][col] == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	private static ArrayList<Integer> getBestMoves() {
		ArrayList<Integer> moves = new ArrayList<Integer>();
		int acc = 0;
		int minAccessibility = Integer.MAX_VALUE;
		for(int move = 0; move < horizontal.length; move++) {
			if(isValidMove(move)) {
				acc = getAccessibility(move);
				if(acc < minAccessibility) {
					moves.clear();
					moves.add(move);
					minAccessibility = acc;
				}else if(acc == minAccessibility) {
					moves.add(move);
				}
			}
		}
		//System.out.println("Best moves: " + moves);
		return moves;
	}
	
	private static int getAccessibility(int move) {
		int row = kY + vertical[move];
		int col = kX + horizontal[move];
		return accessibility[row -1][col - 1];
	}

	private static int[][] getAccessibility() {
		int[][] accessibility = new int[8][8];
		String line;
		String fileName = "access.txt";
		int row = 0;
		try {
			FileReader fileReader =  new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                accessibility[row] = parseLine(line);
        			//System.out.println("Accessibility ints row " + row + ": " +  Arrays.toString(accessibility[row]));
                row++;
            }   
            bufferedReader.close();         
		}catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");                
            }
            catch(IOException e) {
                System.out.println("Error reading file '" + fileName + "'");                  
                e.printStackTrace();
            }
		return accessibility;
	}
	
	private static int[] parseLine(String line) {
		String[] strArr = line.split(" ");
		int[] intArr = new int[strArr.length];
		for(int i = 0; i < strArr.length; i++) {
			intArr[i] = Integer.parseInt(strArr[i]);
		}
		return intArr;
	}

	private static void tour() {
		Random random = new Random();
		ArrayList<Integer> validMoveList = new ArrayList<Integer>();
		while(true) {
			validMoveList = getBestMoves();
			if(validMoveList.size() == 0) {
				return;
			}else {
				int moveIndex = random.nextInt(validMoveList.size());
				move(validMoveList.get(moveIndex));
			}
		}
	}

	public static void main(String[] args) {
		initGame();
		showBoard();
		tour();
		showBoard();
		System.out.println(count + " squares were visited");
	}
}

/*
  1 38  3 18 35 40 13 16
  4 19 36 39 14 17 34 41
 37  2 49 58 43 64 15 12
 20  5 60 63 50 57 42 33
 53 48 51 44 59 62 11 26
  6 21 54 61 56 27 32 29
 47 52 23  8 45 30 25 10
 22  7 46 55 24  9 28 31

64 squares were visited
*/
