//Life Reflection

//Alex Bruckhaus, Period 6, 2/11/2018
//This program took me 2 hours.
//This program was pretty difficult to approach. However, upon reading the program requirements, the process began to make more sense
//to me. In this program, I found that making helper methods were vital to its success. Again, reading the file and converting
//the file to a 2D array took me a while but I managed to do so eventually. Another challenging aspect of this assignment was
//to figure out which cells were neighbors of the current position. To do so, I made a method called countNeighbors() and determined 
//neighbors with a double for loop with boundaries i - 1, to i + 1 and j - 1, to j + 1. Overall, this assignment was quite challenging,
//but working with helper methods helped a lot during the process.

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class P6_Bruckhaus_Alexander_Life {
	private static char[][] grid;
	private static int rows;
	private static int cols;
	
	// Constructor that initializes a game of Life from the given data file
	public P6_Bruckhaus_Alexander_Life(String fileName) {		
		String line;
		try {
			FileReader fileReader =  new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            line = bufferedReader.readLine();
            setDimensions(line);
            grid = new char[rows][cols];
            initializeGrid();
            while((line = bufferedReader.readLine()) != null) {
                populateGrid(line);
            }   
            bufferedReader.close();         
		}catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");                
            }
            catch(IOException e) {
                System.out.println("Error reading file '" + fileName + "'");                  
                e.printStackTrace();
            }
	}

	private void initializeGrid() {
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				grid[row][col] = ' ';
			}
		}
	}

	private void setDimensions(String line) {
		String[] arr = line.split("\\s+");
		rows = Integer.parseInt(arr[0]);
		cols = Integer.parseInt(arr[1]);
	}

	private void populateGrid(String line) {
		//System.out.println("line: " + line);
		String[] arr = line.split("\\s+");
		//System.out.println("arr: " + Arrays.toString(arr));
		int row = Integer.parseInt(arr[1]);
		int col = Integer.parseInt(arr[2]);
		grid[row][col] = '*';
	}

	// Method that runs the Life simulation through the given generation
	// Generation 0 is the initial position, Generation 1 is the position after one round of Life, etc...
	public void runLife(int numGenerations) {
		for(int i = 0; i < numGenerations; i++) {
			nextGeneration();
			printBoard();
		}
	}
	
	// Method that returns the number of living cells in the given row
	// or returns -1 if row is out of bounds.  The first row is row 0.
	public int rowCount(int row) {
		int rowCount = 0;
		for(int col = 0; col < cols; col++) {
			if(grid[row][col] == '*') {
				rowCount++;
			}
		}
		return rowCount;
	}
	
	// Method that returns the number of living cells in the given column
	// or returns -1 if column is out of bounds.  The first column is column 0.
	public int colCount(int col) {
		int colCount = 0;
		for(int row = 0; row < rows; row++) {
			if(grid[row][col] == '*') {
				colCount++;
			}
		}
		return colCount;
	}
	
	// Method that returns the total number of living cells on the board
	public int totalCount() {
		int count = 0;
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				if(grid[row][col] == '*') {
					count++;
				}
			}
		}
		return count;
	}
	
	// Prints out the Life array with row and column headers as shown in the example below.
	public void printBoard() {
		printColumnHeaders();
		for(int row = 0; row < rows; row++) {
			printRowHeader(row);
			for(int col = 0; col < cols; col++) {
				System.out.printf("%c", grid[row][col]);
			}
			System.out.println();
		}
	}
	
	private void printRowHeader(int row) {
		System.out.printf("%2d", row);
	}

	private void printColumnHeaders() {
		System.out.print("   ");
		for(int i = 0; i < cols; i++) {
			System.out.printf("%d", i % 10);
		}
		System.out.println();
	}

	// Advances Life forward one generation
	public void nextGeneration() {
		char[][] nextGrid = new char[rows][cols];
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				nextGrid[row][col] = populateCell(row, col);
			}
		}
		grid = nextGrid;
	}
	
	public void printStats() {
		System.out.println("Number of living cells in row 9 --> " + rowCount(9));
		System.out.println("Number of living cells in col 9 --> " + colCount(9));
		System.out.println("Number of living cells total --> " + totalCount());
	}
	
	private char populateCell(int row, int col) {
		int neighbors = countNeighbors(row, col);
		boolean living = grid[row][col] == '*';
		if(living && (neighbors == 0 || neighbors == 1 || neighbors >= 4)) {
			return ' ';
		}else if(living && (neighbors == 2 || neighbors == 3)) {
			return '*';
		}else if(!living && neighbors == 3) {
			return '*';
		}else {
			return ' ';
		}
	}

	private int countNeighbors(int row, int col) {
		int count = 0;
		for(int i = row - 1; i <= row + 1; i++) {
			for(int j = col - 1; j <= col + 1; j++) {
				count += countNeighbor(row, col, i, j);
			}
		}
		return count;
	}

	private int countNeighbor(int row, int col, int i, int j) {
		if(i >= 0 && i < rows && j >= 0 && j < cols && !(i == row && j == col) && grid[i][j] == '*') {
			return 1;
		}else {
			return 0;
		}
	}

	public static void main(String[] args) {
		P6_Bruckhaus_Alexander_Life f = new P6_Bruckhaus_Alexander_Life("life100.txt");
		f.printBoard();
		f.runLife(5);
		f.printStats();
	}
}
