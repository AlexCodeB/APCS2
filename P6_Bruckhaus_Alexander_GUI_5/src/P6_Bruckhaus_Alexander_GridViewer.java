//GridViewer Reflection

//Alex Bruckhaus, Period 6, 3/15/2018
//This program took me 2 hours and 15 minutes.
//Overall, this program was significantly more difficult than the other JavaFX assignments, but it was manageable.
//I faced difficulty trying to load the text files into the grid. I managed to code it correctly for some of the grids, but not all of them.
//The clear button was relatively easy as well as the slider. Playing life itself was also considerably more difficult than the other tasks.
//After reviewing my old code of life and thinking of a way to implement the code into this context, I eventually figured out a way
//to do so. This assignment was challenging, yet informative.


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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

import java.io.File;
import java.util.Scanner;

public class P6_Bruckhaus_Alexander_GridViewer extends Application {
	private GridModel<Boolean> model;
	private BooleanGridPane view;
	private Button clear;
	private Stage myStage;
	private BorderPane root;
	private Slider slider;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		myStage = stage;
		view = new BooleanGridPane();
		
		Boolean[][] rawData = { {true, true, true, true},
							    {false, true, true, false},
								{false, true, true, false}};
								
		model = new GridModel<Boolean>(rawData);
		view.setModel(model);
		view.setTileSize(50);
		
		MyMouseHandler handle = new MyMouseHandler();
		view.setOnMouseClicked(handle);
		
		myStage.setTitle("GUI_5");
		myStage.setResizable(true);
		myStage.sizeToScene();
		
		root = new BorderPane();
		Scene scene = new Scene(root, 500, 500);
		myStage.setScene(scene);
		
		root.setCenter(view);		
		
		HBox hBox2 = new HBox();
		Button clear = new Button("Clear");
		Button load = new Button("Load");
		Label cellSize = new Label("Cell Size");
		slider = new Slider(0, 100, 50);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(25);
		//slider.setMinorTickCount(5);
		BackgroundFill fill = new BackgroundFill(Color.GREY, new CornerRadii(3), new Insets(0));
		hBox2.setBackground(new Background(fill));
		hBox2.setPadding(new Insets(15));
		hBox2.getChildren().addAll(clear, load, cellSize, slider);
		hBox2.setSpacing(15);
		root.setBottom(hBox2);
		
		ClearHandler clearHandler = new ClearHandler();
		clear.setOnMouseClicked(clearHandler);
		
		LoadHandler loadHandler = new LoadHandler();
		load.setOnMouseClicked(loadHandler);
		
		SliderHandler sliderHandler = new SliderHandler();
		slider.setOnMouseReleased(sliderHandler);
		
		myStage.show();
	}
	
	private class MyMouseHandler implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent event) {
			//System.out.println("Hello");
			int row = view.rowForYPos(event.getY());
			int col = view.colForXPos(event.getX());
			//model.setValueAt(row, col, !model.getValueAt(row, col));
			model.clickEvent(row, col, true, false);
		}
	}
	
	private class ClearHandler implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent event) {
			System.out.println("Pressed clear");
			model.setAll(false);
		}
	}
	
	private class LoadHandler implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent event) {
			System.out.println("Load Pressed");
			FileChooser fileChooser = new FileChooser();
			 fileChooser.setTitle("Open Grid File");
			 fileChooser.getExtensionFilters().addAll(
			         new ExtensionFilter("Text Files", "*.txt"),
			         new ExtensionFilter("All Files", "*.*"));
			 File selectedFile = fileChooser.showOpenDialog(null);
			 if (selectedFile != null) {
			    model = new GridModel<Boolean>(selectedFile, true, false);
				view.setModel(model);
				root.setCenter(view);
				myStage.show();
			 }
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
