//EraseObject Reflection

//Alex Bruckhaus, Period 6, 2/13/2018
//This program took me 1 hour.
//This assignment was not too difficult after reading through the notes about the maze. In the beginning, I was a little confused
//on how to approach the problem, but after refreshing my memory about recursion, it became easier. I did not run into much trouble
//during the lab, except for reading the file and converting it to a 2D array which took me a while. Other than that, this assignment
//was very informative and helped me understand 2D arrays and recursion more. It was also very interesting to program in general.

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.Arrays;
import java.util.Scanner;

public class P6_Bruckhaus_Alexander_EraseObject {
	private static final String FILE_NAME = "digital.txt";
	private static final int DIMENSION = 20;
	private static char[][] grid = new char[DIMENSION + 1][DIMENSION + 1];
	
	public void readGrid() {
		String line;
		try {
			FileReader fileReader =  new FileReader(FILE_NAME);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			line = bufferedReader.readLine();
			int pairCount = getPairCount(line);
			initializeGrid();
			for(int i = 0; i < pairCount; i++) {
				line = bufferedReader.readLine();
				populateGrid(line);
			}
			bufferedReader.close();         
		}catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + FILE_NAME + "'");                
		}
		catch(IOException e) {
			System.out.println("Error reading file '" + FILE_NAME + "'");                  
			e.printStackTrace();
		}
	}

	private void populateGrid(String line) {
		String[] arr = line.split(" ");
		//System.out.println(Arrays.toString(arr));
		int y = Integer.parseInt(arr[0]);
		int x = Integer.parseInt(arr[1]);
		grid[y][x] = '@';
	}

	private void initializeGrid() {
		for(int row = 0; row < grid.length; row++) {
			for(int col = 0; col < grid.length; col++) {
				grid[row][col] = '-';
			}
		}
	}

	private int getPairCount(String line) {
		int count = 0;
		count = Integer.parseInt(line);
		return count;
	}
	
	private void printGrid() {
		System.out.print("\n   ");
		for(int i = 1; i < DIMENSION + 1; i++) {
			System.out.printf("%s", i % 10);
		}
		System.out.println();
		for(int row = 1; row < DIMENSION + 1; row++) {
			System.out.printf("%2s ", row);
			for(int col = 1; col < DIMENSION + 1; col++) {
				System.out.printf("%c", grid[row][col]);
			}
			System.out.println();
		}
		
	}
	
	private void ask() {
		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.println("Enter y-coordinate (row) of point to erase: ");
			int y = in.nextInt();
			System.out.println("Enter x-coordinate (col) of point to erase: ");
			int x = in.nextInt();
			erase(y, x);
			printGrid();
			System.out.print("Do you want to erase again? (y/n): ");
			String q = in.nextLine();
			q = in.nextLine();
			if(q.equals("n")){
				System.out.println("Done.");
				in.close();
				return;
			}
		}
	}
	
	private void erase(int y, int x) {
		if(1 <= y && y <= DIMENSION && 1 <= x && x <= DIMENSION) {
			if(grid[y][x] == '-') {
				return;
			}else {
				grid[y][x] = '-';
				erase(y + 1, x);
				erase(y, x + 1);
				erase(y - 1, x);
				erase(y, x - 1);
			}
		}
	}
	
	public static void main(String[] args) {
		P6_Bruckhaus_Alexander_EraseObject e = new P6_Bruckhaus_Alexander_EraseObject();
		//e.printGrid();
		e.readGrid();
		e.printGrid();
		e.ask();
	}
}
