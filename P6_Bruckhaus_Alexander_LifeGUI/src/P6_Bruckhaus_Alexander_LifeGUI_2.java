//GridViewer Reflection

//Alex Bruckhaus, Period 6, 3/15/2018
//This program took me 2 hours and 15 minutes.
//Overall, this program was significantly more difficult than the other JavaFX assignments, but it was manageable.
//I faced difficulty trying to load the text files into the grid. I managed to code it correctly for some of the grids, but not all of them.
//The clear button was relatively easy as well as the slider. Playing life itself was also considerably more difficult than the other tasks.
//After reviewing my old code of life and thinking of a way to implement the code into this context, I eventually figured out a way
//to do so. This assignment was challenging, yet informative.

//LifeGUI_1 reflection

//Alex Bruckhaus, Period 6, 3/18/2018
//This program took me 3 hours.
//This assignment has been one of the most difficult assignments of the semester so far. Although I am learning and understanding a lot
//from this assignment, it continues to be a challenge for me. I am having trouble loading the files and converting it to a GUI. 
//I am also having trouble advancing the generations. However, I did make a menu bar, which was very interesting and useful to me.
//I will try to get help from peers, teachers, or others to continue my learning.


//LifeGUI_2 Reflection

//Alex Bruckhaus, Period 6, 3/20/2018
//This program took me 3 hours.
//Again, this assignment was quite challenging, yet informative. From this lab, I learned a lot about JavaFX. During this assignment,
//I had some trouble changing the label of the generation, but after a lot of research, I managed to figure out a way to do so.
//It was also a challenge to save the file, but after studying the code for a while, I realized what I needed to do and added
//added a save feature. The animation aspect of this game was also very interesting to work on, which made my program a lot more intriguing.
//From all of these improvements, I am happy to say that I learned a lot from this assignment while also attaining
//satisfaction from the completion of this game. During this assignment, I received some help from my classmates, teachers, and father,
//which ended up helping a lot.



import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.io.File;
import java.util.Scanner;

public class P6_Bruckhaus_Alexander_LifeGUI_2 extends Application implements GenerationListener {

	private P6_Bruckhaus_Alexander_LifeModel model;
	private BooleanGridPane view;
	private BorderPane root;
	private Slider slider;
	private Slider sliderTime;
	private StringProperty generation = new SimpleStringProperty();
	private long animationDelay = (long) 300.0e6;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
	

		// set up model:
		Boolean[][] rawData = {{true, true, true, true},
				{false, true, true, false},
				{false, true, true, false}};
		model = new P6_Bruckhaus_Alexander_LifeModel(rawData);
		model.addGenerationListener(this);

		// set up root:
		root = new BorderPane();

		// set up view:
		view = new BooleanGridPane();
		view.setModel(model);
		view.setTileSize(50);
		root.setCenter(view);		

		// set up stage:
		stage.setTitle("GUI_5");
		stage.setResizable(true);
		stage.sizeToScene();
		Scene scene = new Scene(root, 900, 600);
		stage.setScene(scene);
		stage.show();

		// set up menu:
		MenuBar menubar = new MenuBar();
		Menu menu = new Menu("File");
		MenuItem open = new MenuItem("Open");
		MenuItem save = new MenuItem("Save");
		menu.getItems().add(open);
		menu.getItems().add(save);
		menubar.getMenus().add(menu);
		root.setTop(menubar);

		// set up controls:
		//Button load = new Button("Load");
		Button next = new Button("Next Generation");
		Label generationLabel = new Label();
		generationLabel.textProperty().bind(generation);
		generation.set("Generation " + model.getGeneration());
		ToggleButton play = new ToggleButton("Play");
		Button clear = new Button("Clear");
		Label cellSize = new Label("Cell Size");
		slider = new Slider(0, 100, 50);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(25);
		
		Label time = new Label("Animation Speed");
		sliderTime = new Slider(0, 100, 50);
		sliderTime.setShowTickLabels(true);
		sliderTime.setShowTickMarks(true);
		sliderTime.setMajorTickUnit(25);


		// set up hbox:
		HBox hBox = new HBox();
		BackgroundFill fill = new BackgroundFill(Color.GREY, new CornerRadii(3), new Insets(0));
		hBox.setBackground(new Background(fill));
		hBox.setPadding(new Insets(15));
		hBox.getChildren().addAll(clear, generationLabel, next, play, cellSize, slider, time, sliderTime);
		hBox.setSpacing(15);
		root.setBottom(hBox);

		// set up handlers:
		DragHandler dragHandler = new DragHandler();
		view.setOnMouseDragged(dragHandler);
		ClearHandler clearHandler = new ClearHandler();
		clear.setOnMouseClicked(clearHandler);
		OpenHandler openHandler = new OpenHandler();
		open.setOnAction(openHandler);
		SaveHandler saveHandler = new SaveHandler();
		save.setOnAction(saveHandler);
		SliderHandler sliderHandler = new SliderHandler();
		slider.setOnMouseDragged(sliderHandler);
		TimeSlider timeSlider = new TimeSlider();
		sliderTime.setOnMouseDragged(timeSlider);
		PlayHandler playHandler = new PlayHandler();
		play.setOnMouseClicked(playHandler);
		//ANONYMOUS DOUBLECLICK
		view.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				int row = view.rowForYPos(event.getY());
				int col = view.colForXPos(event.getX());
				if(event.getClickCount() == 2) {
					model.erase(row, col);
				}else if(event.getClickCount() == 1) {
					model.setValueAt(row, col, !model.getValueAt(row, col));
				}
			}
		});
		//ANONYMOUS NEXT HANDLER
		next.setOnMouseClicked(new EventHandler<MouseEvent>()
			{
				@Override
				public void handle(MouseEvent event) {
					System.out.println("Next Pressed");
					model.nextGeneration();
					updateGenerationLabel();
				}
			}
		);
	}
	
	private void updateGenerationLabel() {
		generation.set("Generation " + model.getGeneration());
	}
	
	@Override
	public void generationChanged(int oldVal, int newVal) {
		generation.set(String.valueOf(model.getGeneration()));
	}
	
	private class TimeSlider implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent event) {
			animationDelay = (long) (1.0e9 * (1 - (sliderTime.getValue() / 100.0)));
		}
	}
	
	private class PlayHandler implements EventHandler<MouseEvent>{
		MyAnimationTimer animation = new MyAnimationTimer();
		Boolean running = false;
		@Override
		public void handle(MouseEvent event) {
			if (running) {
				animation.stop();
				running = false;
			} else {
				animation.start();
				running = true;
			}
		}
	}
	
	private class MyAnimationTimer extends AnimationTimer{
		private long previousTime = 0;
		@Override
		public void handle(long now) {
			if (previousTime == 0) {
				previousTime = now;
				return;
			}
			if(now - previousTime >= animationDelay){
				model.nextGeneration();
				updateGenerationLabel();
				previousTime = now;
			}
		}
	}

	private class OpenHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			File selectedFile = chooseFile();
			if (selectedFile != null) {
				model.setGridFromFile(selectedFile);
			}
		}
		private File chooseFile() {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Grid File");
			fileChooser.getExtensionFilters().addAll(
					new ExtensionFilter("Text Files", "*.txt"),
					new ExtensionFilter("All Files", "*.*"));
			File selectedFile = fileChooser.showOpenDialog(null);
			return selectedFile;
		}
	}

	private class SaveHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			File selectedFile = chooseFile();
			if (selectedFile != null) {
				model.saveGridToFile(selectedFile);
			}
		}
		private File chooseFile() {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Grid File");
			fileChooser.getExtensionFilters().addAll(
					new ExtensionFilter("Text Files", "*.txt"),
					new ExtensionFilter("All Files", "*.*"));
			File selectedFile = fileChooser.showSaveDialog(null);
			return selectedFile;
		}
	}


	private class DragHandler implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent event) {
			int row = view.rowForYPos(event.getY());
			int col = view.colForXPos(event.getX());
			//model.clickEvent(row, col, true, false);
			if(event.getEventType().equals(MouseEvent.MOUSE_DRAGGED) && event.getButton() == MouseButton.PRIMARY){
				if(event.getEventType().equals(MouseEvent.MOUSE_RELEASED)){
					return;
				}
				model.setValueAt(row, col, true);
			}else if(event.getEventType().equals(MouseEvent.MOUSE_DRAGGED) && event.getButton() == MouseButton.SECONDARY){
				if(event.getEventType().equals(MouseEvent.MOUSE_RELEASED)){
					return;
				}
				model.setValueAt(row, col, false);
			}	
		}
	}

	private class ClearHandler implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent event) {
			System.out.println("Pressed clear");
			model.setAll(false);
		}
	}

	private class SliderHandler implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent event) {
			double size = slider.getValue();
			view.setTileSize(size);			
		}
	}
}
