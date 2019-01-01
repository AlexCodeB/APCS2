//Minesweeper HW #2 Reflection

//Alex Bruckhaus, Period 6, 3/25/2018
//This program took me 1 hour and 30 minutes.
//Overall, the controller aspect of minesweeper was not too difficult. A lot of the challenging parts and logical thinking
//took place in the model, rather than the controller. However, upon creating and testing my controller, I realized that I had quite a few
//bugs in my model. The controller helped me find bugs, which were relatively easy to fix, such as revealing the whole board
//prematurely. This assignment further expanded my knowledge of problem solving and finding bugs with efficiency.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class P6_Bruckhaus_Alexander_MinesweeperModel implements P6_Bruckhaus_Alexander_MSModelInterface{

	// covered
	public char COVER = '_';
	public char QUESTION_MARK = '?';
	public char FLAG = 'f';
	public char UNCOVERED = ' ';
	private char[][] coverLayer;

	// uncovered
	public char BLANK = ' ';
	public char MINE = 'm';
	public char EXPLODED = '*';
	public char CROSSED = 'X';
	private char[][] uncoveredLayer;

	private int rows;
	private int cols;
	public int numMines;
	private int flags = 0;
	private int moves;
	private int coveredCells;
	private Boolean isGameOver = false;
	private ArrayList<GridListener> listeners;


	public P6_Bruckhaus_Alexander_MinesweeperModel(int rows, int cols, int mines) {
		this.rows = rows;
		this.cols = cols;
		this.numMines = mines;
		this.coveredCells = rows * cols;
		uncoveredLayer = new char[getNumRows()][getNumCols()];
		coverLayer = new char[getNumRows()][getNumCols()];
		listeners = new ArrayList<>();
		setBoard();
	}

	private void setBoard() {
		initializeEmptyBoard();
		setMines();
		setMineCounts();
	}
	
	public void reset() {
		initializeEmptyBoard();
		setMines();
		setMineCounts();
	}

	private void initializeEmptyBoard() {
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				uncoveredLayer[row][col] = BLANK;
				coverLayer[row][col] = COVER;
			}
		}
	}

	public void setMines() {
		Random random = new Random();
		int minesSet = 0;
		while (minesSet < numMines) {
			int row = random.nextInt(getNumRows());
			int col = random.nextInt(getNumCols());
			if (uncoveredLayer[row][col] == BLANK) {
				uncoveredLayer[row][col] = MINE;
				minesSet++;
			}
		}
	}

	private void setMineCounts() {
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < cols; col++) {
				if (uncoveredLayer[row][col] == BLANK) {
					int count = getNumSurroundingMines(row, col);
					if (count > 0) {
						uncoveredLayer[row][col] = (char) ('0' + count);
					}
				}
			}
		}
	}

	@Override
	public void reveal(int row, int col) {
		if (row < 0 || row >= getNumRows() || col < 0 || col >= getNumCols()) {
			// OOB:
			return;
		} else if (coverLayer[row][col] == UNCOVERED) {
			// already uncovered:
			return;
		} else if (coverLayer[row][col] == FLAG) {
			// is flagged: remove flag and reveal
			coverLayer[row][col] = COVER;
			//coveredCells++;
			flags--;
			System.out.println("Flags" + flags);
			reveal(row, col);
		}
		else if (uncoveredLayer[row][col] == MINE) {
			// game over, show all unmarked mines:
			uncoveredLayer[row][col] = EXPLODED;				
			coverLayer[row][col] = UNCOVERED;
			for (int r = 0; r < getNumRows(); r++) {
				for (int c = 0; c < getNumCols(); c++) {
					if (uncoveredLayer[r][c] == MINE) {
						if (coverLayer[r][c] == COVER) {
							coverLayer[r][c] = UNCOVERED;
						} else if (coverLayer[r][c] == FLAG) {
							// keep flag - do nothing
						} else if (coverLayer[r][c] == QUESTION_MARK) {
							coverLayer[r][c] = UNCOVERED;
						} else if (coverLayer[r][c] == EXPLODED) {
							// keep exploded - do nothing
						}
					}
				}
			}			
			isGameOver = true;
		} else if (isNumber(row, col)) {
			// uncover number:
			coverLayer[row][col] = UNCOVERED;
			coveredCells--;
		} else {
			// tile is blank: uncover and call recursive:
			coverLayer[row][col] = UNCOVERED;
			coveredCells--;
			for (int r = row - 1; r <= row + 1; r++) {
				for (int c = col - 1; c <= col + 1; c++) {
					reveal(r, c);
				}
			}
		}
		moves++;
	}

	public boolean isWin() {
		if(coveredCells + flags == numMines) {
			return true;
		}
		return false;
	}

	private boolean isNumber(int row, int col) {
		return uncoveredLayer[row][col] >= '0' + 1 &&
				uncoveredLayer[row][col] <= '0' + 8;
	}

	@Override
	public void setFlag(int row, int col) {
		if(coverLayer[row][col] == COVER) {
			if(coverLayer[row][col] != FLAG) {
				coverLayer[row][col] = FLAG;
				flags++;
				return;
			}
		}
		if(coverLayer[row][col] == FLAG) {
			coverLayer[row][col] = COVER;
			flags--;
		}

	}

	@Override
	public void setQuestionMark(int row, int col) {
		coverLayer[row][col] = QUESTION_MARK;	
	}

	@Override
	public int getNumMoves() {
		return moves;
	}

	@Override
	public int numMinesLeft() {
		return numMines - flags;
	}

	@Override
	public int getNumFlags() {
		return flags;
	}

	@Override
	public char getCellContent(int row, int col, boolean uncover) {
		if (uncover || coverLayer[row][col] == UNCOVERED) {
			return uncoveredLayer[row][col];
		} else {
			return coverLayer[row][col];
		}
	}

	@Override
	public boolean isMine(int row, int col) {
		return uncoveredLayer[row][col] == MINE;
	}

	@Override
	public boolean isGameOver() {
		return isGameOver;
	}

	private int getNumSurroundingMines(int row, int col) {
		int count = 0;
		for (int r = row - 1; r <= row + 1; r++){
			for(int c = col - 1; c <= col + 1; c++){
				if (r >= 0 && r < getNumRows() && c >= 0 && c < getNumCols() && isMine(r, c)) {
					count++;
				}
			}
		}
		return count;		
	}

	public int getNumRows() {
		return rows;
	}

	public int getNumCols() {
		return cols;
	}

	public String getBoardString(boolean uncover) {
		String boardStr = " ";
		for(int i = 0; i < getNumCols(); i++) {
			boardStr += " " + i;
		}
		boardStr += "\n";
		for(int row = 0; row < getNumRows(); row++) {
			boardStr += row + " ";
			for(int col = 0; col < getNumCols(); col++) {
				boardStr += getCellContent(row, col, uncover) + " ";
			}
			boardStr += "\n";
		}
		return boardStr;
	}

	public void addListener(MSGridPane gridPane) {
		if (!listeners.contains(gridPane)) {
			listeners.add(gridPane);
		}		
	}
}
