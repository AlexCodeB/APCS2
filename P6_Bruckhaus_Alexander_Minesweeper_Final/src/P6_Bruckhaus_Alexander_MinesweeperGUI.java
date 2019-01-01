//Mine sweeper HW #3

//Alex Bruckhaus, Period 6, 3/26/2018
//This program took me 2 hours.
//Overall, this program was easy to begin with, but I couldn't seem to figure out how to lay out the grid in the view.
//Setting up the menu bar was a simple task, but I spent a long time trying to figure out how to display the minesweeper grid.
//I approached it many different ways including a gridpane like I used in Life, as well as Hboxes and Vboxes. However, I did not come
//to a solution tonight. I will reach out for help and continue to try to solve my problems.

//Minesweeper HW #4

//Alex Bruckhaus, Period 6, 3/27/2018
//This program took me 2 hours.
//From this assignment, I learned how to add the view of the grid. After analyzing my code for hours, and re visiting other 
//strategies, I realized that my images were not in the location that they were supposed to be. It was frustrating, yet relieving
//to realize the weight of such a small error and all the trouble it caused. I also spent some time trying to display 
//the model, but could not successfully program it. I spent some time working on other parts of the code such as the mouse handler.
//I will seek help and try to find a solution to the problems I am facing.
//UPDATED 3/29 : I realized that I turned in my program early and fixed many bugs. After getting some help in class and at home, 
//I managed to make a lot more progress and successfully connected the model to the view. I used some of my LifeGUI code to help
//me out with the model and view connection. Overall, I learned a lot from this assignment as it was quite challenging, yet
//informative.


//Minesweeper HW #5

//Alex Bruckhaus, Period 6, 4/1/2018
//This program took me 3 hours
//Overall, this was a very challenging project. However, I am happy to say I learned a lot from this long, difficult assignment.
//Creating my own mine sweeper gave me better problem solving skills, helped me understand java syntax to a higher degree, and helped
//me become a better, more efficient coder. I ran into many obstacles during this assignment, but I tackled them with help from 
//my peers, family, and teacher. With a lot of the bugs I had, I printed out what was happening under the hood to give me 
//a better idea of how to fix it. This helped me in many cases. I decided to add extra options to my game to make it 
//better and more enjoyable, such as intermediate mode and expert mode. This was a very fascinating and informative assignment.

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class P6_Bruckhaus_Alexander_MinesweeperGUI extends Application{

	private P6_Bruckhaus_Alexander_MinesweeperModel model;
	private BorderPane root;
	private MSGridPane gridPane;

	private StringProperty minesRemaining = new SimpleStringProperty();
	private StringProperty time = new SimpleStringProperty();
	private StringProperty gameStatus = new SimpleStringProperty();
	private long startTime;
	private long animationDelay = 1000;
	private ClockTimer timer = new ClockTimer();
	private String answer;


	@Override
	public void start(Stage stage) throws Exception {
		// set up root:
		root = new BorderPane();
		model = new P6_Bruckhaus_Alexander_MinesweeperModel(10, 10, 8);
		gridPane = new MSGridPane();

		// set up stage:
		stage.setTitle("Minesweeper");
		stage.setResizable(false);
		stage.sizeToScene();
		Scene scene = new Scene(root, 900, 600);
		stage.setScene(scene);

		// set up menu at top:
		MenuBar menubar = new MenuBar();
		// set up game menu
		Menu menu = new Menu("Game");
		MenuItem newGameB = new MenuItem("New Beginner Game");
		MenuItem newGameI = new MenuItem("New Intermediate Game");
		MenuItem newGameE = new MenuItem("New Expert Game");
		MenuItem exit = new MenuItem("Exit");
		// set up options menu
		Menu menu2 = new Menu("Options");
		MenuItem setMines = new MenuItem("Set Number of Mines");
		// set up help menu
		Menu menu3 = new Menu("Help");
		MenuItem howToPlay = new MenuItem("How To Play");
		MenuItem about = new MenuItem("About");
		menu.getItems().add(newGameB);
		menu.getItems().add(newGameI);
		menu.getItems().add(newGameE);
		menu.getItems().add(exit);
		menu2.getItems().add(setMines);
		menu3.getItems().add(howToPlay);
		menu3.getItems().add(about);
		menubar.getMenus().addAll(menu, menu2, menu3);
		root.setTop(menubar);

		gridPane.setModel(model);

		root.setCenter(gridPane);
		stage.show();

		// set up handlers
		MouseHandler mouseHandler = new MouseHandler();
		gridPane.setOnMouseClicked(mouseHandler);

		// beginner game
		NewGameHandlerB newGameHandlerB = new NewGameHandlerB();
		newGameB.setOnAction(newGameHandlerB);
		// intermediate game
		NewGameHandlerI newGameHandlerI = new NewGameHandlerI();
		newGameI.setOnAction(newGameHandlerI);
		// expert game
		NewGameHandlerE newGameHandlerE = new NewGameHandlerE();
		newGameE.setOnAction(newGameHandlerE);

		SetMinesHandler setMinesHandler = new SetMinesHandler();
		setMines.setOnAction(setMinesHandler);
		AboutHandler aboutHandler = new AboutHandler();
		about.setOnAction(aboutHandler);
		HowToPlayHandler howToPlayHandler = new HowToPlayHandler();
		howToPlay.setOnAction(howToPlayHandler);

		// set up bottom HBox
		HBox bottomHBox = new HBox();
		Label minesLeft = new Label();
		minesLeft.setPadding(new Insets(0, 20, 20, 20));
		minesLeft.textProperty().bind(minesRemaining);

		// set up time elapsed label
		Label timeElapsed = new Label();
		timeElapsed.setPadding(new Insets(0, 20, 0, 0));
		timeElapsed.textProperty().bind(time);

		// set up gameStatus label
		Label statusLabel = new Label();
		statusLabel.textProperty().bind(gameStatus);

		// add labels to bottom HBox
		bottomHBox.getChildren().addAll(minesLeft, timeElapsed, statusLabel);
		root.setBottom(bottomHBox);

		// start timer when game begins
		startTime = System.currentTimeMillis();
		updateGameStatus();
		timer.start();
	}

	private void updateGameStatus() {
		minesRemaining.set("Mines Remaining " + model.numMinesLeft());
		if(model.isWin()) {
			gameStatus.set("You win!");
			timer.stop();
		}else if(model.isGameOver()) {
			gameStatus.set("You lose!");
			timer.stop();
		}
	}

	private void restartTimer() {
		timer.stop();
		gameStatus.set("");
		startTime = System.currentTimeMillis();
		timer.start();
	}

	private class ClockTimer extends AnimationTimer{
		private long previousTime = 0;
		@Override
		public void handle(long now) {
			if (previousTime == 0) {
				previousTime = now;
				return;
			}
			if(now - previousTime >= animationDelay){
				updateClock();
				previousTime = now;
			}
		}
	}

	private void updateClock() {
		long currentTime = System.currentTimeMillis();
		int elapsedSeconds = (int)(currentTime - startTime) / 1000;
		time.set("Elapsed time: " + elapsedSeconds);
	}

	private class AboutHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			Alert a = new Alert(AlertType.INFORMATION, "Welcome to Minesweeper.\n"
					+ "Version 1.0 - April 2018\n"
					+ "Alexander Bruckhaus", ButtonType.OK);
			a.showAndWait();
		}
	}

	private class HowToPlayHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			Alert a = new Alert(AlertType.INFORMATION, "Welcome to Minewseeper!\n"
					+ "To play, simply press on a blank tile. If you land on a mine, Game Over!\n"
					+ "The number represents the amount of mines adjacent to the selected tile.\n"
					+ "If you reveal all mineless tiles, you win! Good Luck!"
					+ "", ButtonType.OK);
			a.showAndWait();
		}
	}

	private class SetMinesHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			TextInputDialog input = new TextInputDialog();
			input.setHeaderText("How many mines would you like?");
			input.showAndWait();
			answer = input.getEditor().getText();
			int ans = Integer.parseInt(answer);
			int numTiles = model.getNumRows() * model.getNumCols();
			if(ans < 1 || ans >= numTiles) {
				Alert a = new Alert(AlertType.ERROR, "Enter a number from 1 - " + numTiles + ".", ButtonType.OK);
				a.showAndWait();
				return;
			}
			model = new P6_Bruckhaus_Alexander_MinesweeperModel(model.getNumRows(), model.getNumCols() , ans);
			restartTimer();
			updateGameStatus();
			gridPane.setModel(model);
		}
	}

	private class NewGameHandlerB implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			model.reset();
			model = new P6_Bruckhaus_Alexander_MinesweeperModel(8, 8, 10);
			gridPane.setTileSize(40);
			gridPane.setModel(model);
			updateGameStatus();
			restartTimer();
		}
	}

	private class NewGameHandlerI implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			model.reset();
			model = new P6_Bruckhaus_Alexander_MinesweeperModel(16, 16, 40);
			gridPane.setTileSize(28);
			gridPane.setModel(model);
			updateGameStatus();
			restartTimer();
		}
	}

	private class NewGameHandlerE implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			model.reset();
			model = new P6_Bruckhaus_Alexander_MinesweeperModel(16, 31, 99);
			gridPane.setTileSize(25);
			gridPane.setModel(model);
			updateGameStatus();
			restartTimer();
		}
	}

	private class MouseHandler implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent event) {
			if((model.isWin() || model.isGameOver())) {
				return;
			}
			if (event.getButton() == MouseButton.PRIMARY) {
				int row = gridPane.rowForYPos(event.getY());
				int col = gridPane.colForXPos(event.getX());
				model.reveal(row, col);
				gridPane.resetCells();
			}else if (event.getButton() == MouseButton.SECONDARY) {
				int row = gridPane.rowForYPos(event.getY());
				int col = gridPane.colForXPos(event.getX());
				model.setFlag(row, col);
				gridPane.resetCells();
			}
			updateGameStatus();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
