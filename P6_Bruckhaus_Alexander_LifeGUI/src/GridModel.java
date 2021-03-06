import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GridModel<T> {

	private T[][] grid;
	private ArrayList<GridListener<T>> listeners;

	public GridModel(T[][] grid) {
		this.grid = grid;
		this.listeners = new ArrayList<>();
	}

	public void addListener(GridListener<T> l) {
		if (!listeners.contains(l)) {
			listeners.add(l);
		}
	}

	public void removeListener(GridListener<T> l) {
		listeners.remove(l);
	}

	public int getNumRows() {
		return this.grid.length;
	}

	public int getNumCols() {
		return this.grid.length > 0 ? this.grid[0].length : 0;
	}

	public T getValueAt(int row, int col) {
		return this.grid[row][col];
	}

	public void setValueAt(int row, int col, T val) {
		T oldVal = this.grid[row][col];
		this.grid[row][col] = val;
		if (oldVal != null && !oldVal.equals(val)) {
			for (GridListener<T> l : listeners) {
				l.cellChanged(row, col, oldVal, val);
			}
		}
	}

	public void setGrid(T[][] grid) {
		if (grid == null) throw new IllegalArgumentException("grid cannot be null.");
		this.grid = grid;
		for (GridListener<T> l : listeners) {
			//System.out.println("calling listener with grid replaced");
			l.gridReplaced();
		}
	}

	public void setAll(T val) {
		for(int row = 0; row < getNumRows(); row++) {
			for(int col = 0; col < getNumCols(); col++) {
				setValueAt(row, col, val);
			}
		}
	}

	public void clickEvent(int row, int col, T val1, T val2) {
		for(int r = row - 1; r <= row + 1; r++) {
			for(int c = col - 1; c <= col + 1; c++) {
				if (r >= 0 && r < getNumRows() && 
						c >= 0 && c < getNumCols() &&
						(r != row || c != col)) {
					T oldVal = this.grid[r][c];
					if (oldVal == val1) {
						setValueAt(r, c, val2);
					} else {
						setValueAt(r, c, val1);
					}
				}
			}
		}
	}

	@Override
	public String toString() {
		return "GridModel [grid=" + Arrays.toString(grid) + ", listeners=" + listeners + "]";
	}	
}
