import java.util.Scanner;

public class P6_Bruckhaus_Alexander_MinesweeperController{
	P6_Bruckhaus_Alexander_MinesweeperModel model;

	public P6_Bruckhaus_Alexander_MinesweeperController(int rows, int cols, int mines) {
		model = new P6_Bruckhaus_Alexander_MinesweeperModel(rows, cols, mines);
	}
	
	public static void main(String[] args) {
		P6_Bruckhaus_Alexander_MinesweeperController controller = new P6_Bruckhaus_Alexander_MinesweeperController(8, 8, 3);
		printBoard(controller);
		// While it is not game over and you did not win
		while(!controller.model.isGameOver() && !controller.model.isWin()) {
			gameMove(controller);
			printBoard(controller);
			// If it's game over and you did not win
			if(controller.model.isGameOver() && !controller.model.isWin()) {
				System.out.println("You lose");
			}
		}
	}

	private static void printBoard(P6_Bruckhaus_Alexander_MinesweeperController controller) {
		System.out.println(controller.model.getBoardString(false));
		System.out.println();
		System.out.println(controller.model.getBoardString(true));
	}

	private static void gameMove(P6_Bruckhaus_Alexander_MinesweeperController controller) {
		Scanner in = new Scanner(System.in);
		System.out.println("There are " + controller.model.numMinesLeft() + " mines left.");
		System.out.println("Would you like to flag a cell or reveal a cell?");
		System.out.print("Enter 'f' or 'r' > ");
		String action = in.nextLine();

		System.out.print("Enter row: ");
		int row = in.nextInt();
		System.out.print("Enter column: ");
		int col = in.nextInt();
		
		if(action.equals("r")) {
			controller.model.reveal(row, col);
		}else if(action.equals("f")) {
			controller.model.setFlag(row, col);
		}
		
		if(controller.model.isWin()) {
			System.out.println("You win!");
		}
	}
	
}
