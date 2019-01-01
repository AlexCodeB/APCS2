
public interface P6_Bruckhaus_Alexander_MSModelInterface {
	public void reveal(int row, int col);
	public void setFlag(int row, int col);
	public void setQuestionMark(int row, int col);
	public int getNumMoves();
	public int numMinesLeft();
	public int getNumFlags();
	public char getCellContent(int row, int col, boolean uncover);
	public boolean isMine(int row, int col);
	public boolean isGameOver();
}
