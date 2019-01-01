import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class P6_Bruckhaus_Alexander_LifeModel extends GridModel<Boolean> {
	private int generation;
	ArrayList<GenerationListener> genListeners;

	public P6_Bruckhaus_Alexander_LifeModel(Boolean[][] grid) {
		super(grid);
		genListeners = new ArrayList<GenerationListener>();
	}
	
	void erase(int row, int col) { // also pass array if it is not accessible in this method
		if (row >= 0 && row < getNumRows() && col >= 0 && col < getNumCols()) {
			if(!getValueAt(row, col)) {
				return;
			}else {
				setValueAt(row, col, false);
				erase(row - 1, col);
				erase(row, col + 1);
				erase(row + 1, col);
				erase(row, col - 1);
			}
		}
	}

	public Boolean[][] getGridFromFile(File file) {
		Boolean[][] grid = null;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String text = null;
			int row = 0;
			int numRows = 0;
			int numCols = 0;
			Boolean firstLine = true;
			while ((text = reader.readLine()) != null) {
				if(firstLine) {
					String[] tokens = text.split(" ");
					numRows = Integer.parseInt(tokens[0]);
					numCols = Integer.parseInt(tokens[1]);
					grid = new Boolean[numRows][numCols];
					firstLine = false;
				} else {
					String[] tokens = text.split(" ");
					for(int col = 0; col < numCols; col++) {
						String valStr = tokens[col];
						if(valStr.equals("X")) {
							grid[row][col] = true;
						} else {
							grid[row][col] = false;
						}
					}
					row++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
			}
		}
		return grid;
	}

	void setGridFromFile(File file) {
		Boolean[][] grid = getGridFromFile(file);
		setGrid(grid);
	}

	void saveGridToFile(File file) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			String firstLine = "" + getNumRows() + " " + getNumCols() + "\n";
			writer.write(firstLine);
			for (int row = 0; row < getNumRows(); row++) {
				Boolean firstCol = true;
				for (int col = 0; col < getNumCols(); col++) {
					if (!firstCol) {
						writer.write(" ");
					}
					firstCol = false;
					if (getValueAt(row, col)) {
						writer.write("X");
					} else {
						writer.write("O");
					}
				}
				writer.write("\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
			}
		}
	}

	void addGenerationListener(GenerationListener l) {
		genListeners.add(l);
	}

	void removeGenerationListener(GenerationListener l) {
		genListeners.remove(l);
	}

	void setGeneration(int gen) {
		int oldGen = this.generation;
		this.generation = gen;
		for (GenerationListener listener : genListeners) {
			listener.generationChanged(oldGen, gen);
		}
	}

	int getGeneration() {
		return generation;
	}

	// Advances Life forward one generation
	public void nextGeneration() {
		Boolean[][] grid = new Boolean[getNumRows()][getNumCols()];
		for(int row = 0; row < getNumRows(); row++) {
			for(int col = 0; col < getNumCols(); col++) {
				grid[row][col] = populateCell(row, col);
			}
		}
		//System.out.println("grid:\n");
		//for(int i = 0; i < grid.length; i++) {
			//System.out.println(Arrays.toString(grid[i]));
		//}
		setGrid(grid);
		generation++;
	}

	private Boolean populateCell(int row, int col) {
		int neighbors = countNeighbors(row, col);
		boolean living = getValueAt(row, col);
		if(living && (neighbors == 0 || neighbors == 1 || neighbors >= 4)) {
			return false;
		}else if(living && (neighbors == 2 || neighbors == 3)) {
			return true;
		}else if(!living && neighbors == 3) {
			return true;
		}else {
			return false;
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
		if(i >= 0 && i < getNumRows() && j >= 0 && j < getNumCols() && !(i == row && j == col) && getValueAt(i, j)) {
			return 1;
		}else {
			return 0;
		}
	}
}
