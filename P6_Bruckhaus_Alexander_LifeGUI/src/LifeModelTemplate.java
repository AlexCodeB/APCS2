
public class LifeModelTemplate extends GridModel<Boolean> {

	public LifeModelTemplate(Boolean[][] grid) {
		super(grid);
		// TODO Auto-generated constructor stub
	}

	int[][] lifeArray;
	int generation;

	public int numRows() {
		return lifeArray.length;
	}

	public int numCols() {
		return lifeArray[0].length;
	}

	// This is a temporary method that reverses all cells so you can get
	// the basics working during class.
	void nextGeneration() {

		// copy lifeArray into tempArray
		for (int i = 0; i < numRows(); i++)
			for (int j = 0; j < numCols(); j++)
				lifeArray[i][j] = (lifeArray[i][j] == 0) ? 1 : 0;

		generation++;
	}
}
