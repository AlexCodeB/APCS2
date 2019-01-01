
public class Position {
	
	public Position(int r, int c) {
		
	}
	
	public static Position findPosition(int r, int[][] intArr) {
		for (int row = 0; row < intArr.length; row++) {
			for (int col = 0; col < intArr[0].length; col++) {
				if (intArr[row][col] == r) {
					return new Position(row, col);
				}
			}
		}		
		return null;
	}
		
	public static Position[][] getSuccessorArray(int[][] intArr){
		Position[][] succ = new Position[intArr.length][intArr[0].length];
		for (int row = 0; row < intArr.length; row++) {
			for (int col = 0; col < intArr[0].length; col++) {
				int value = intArr[row][col];
				succ[row][col] = findPosition(value + 1, intArr);
			}
		}		
		return succ;
	}
}
