//JavaFX Birthday Card Reflection

//Alex Bruckhaus, Period 6, 3/11/2018
//This program took me 1 hour.
//Overall, this lab was one of the most fun and informative assignments this year. It was very captivating to see the code come alive.
//During this assignment, I did not run into many issues other than importing the wrong stuff. Initially, a lot of the methods
//I used did not work and after further investigation, I realized it was because I was importing the wrong stuff. To fix this,
//I checked the API for the classes and imported them correctly. Other than that, this assignment was very smooth. This lab helped me gain 
//a better understanding of JavaFX and was overall very enjoyable.


import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Group;
//import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
//import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.media.AudioClip;
import javafx.animation.FadeTransition;
import javafx.scene.effect.Lighting;

public class P6_Bruckhaus_Alexander_BirthdayCard extends Application {
	// measurements
	private final int BALLOON_RADIUS = 30;
	
	private final Color BALLOON_COLOR = Color.RED;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		stage.setTitle("BirthdayCard");
		stage.setResizable(false);
		stage.sizeToScene();
		
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500);
		stage.setScene(scene);
				
		String fileName = getClass().getResource("The_Danimals_-_Super_Psyched_for_Your_Birthday.mp3").toURI().toString();
		AudioClip clip = new AudioClip(fileName);
		clip.play();

		
		DropShadow dropShadow = new DropShadow(5, 5, 5, Color.YELLOW);
		Bloom bloom = new Bloom(20);
		Glow glow = new Glow(5);
		Lighting light = new Lighting();
		
		
		Rectangle rect = new Rectangle(scene.getWidth() / 2, scene.getHeight() / 2, 50, 25);
		rect.setFill(Color.CYAN);
		rect.setEffect(glow);
		root.getChildren().add(rect);
		
		Rectangle rect2 = new Rectangle(scene.getWidth() / 4.4, scene.getHeight() / 2, 50, 25);
		rect2.setFill(Color.CYAN);
		rect2.setEffect(glow);
		root.getChildren().add(rect2);

		//CubicCurve path = new CubicCurve(60, 200, 260, 80, 400, 200, 35, 170);
		
		RotateTransition rt = new RotateTransition();
		rt.setDuration(Duration.millis(1500));
		rt.setCycleCount(50);
		rt.setFromAngle(0);
		rt.setToAngle(360);
		rt.setNode(rect);
		rt.play();
		
		RotateTransition rt2 = new RotateTransition();
		rt2.setDuration(Duration.millis(1500));
		rt2.setCycleCount(50);
		rt2.setFromAngle(0);
		rt2.setToAngle(360);
		rt2.setNode(rect2);
		rt2.play();
		
		Arc mouth = new Arc(scene.getWidth() / 2 - 15, (scene.getHeight() / 2) - 150, 50, 50, 200, 150);
		mouth.setFill(null);
		mouth.setStroke(Color.BLUE);
		mouth.setStrokeWidth(5);
		bloom.setInput(light);
		mouth.setEffect(bloom);
		root.getChildren().add(mouth);
		
		Circle eye1 = new Circle(mouth.getCenterX() - 10, mouth.getCenterY() - 5, 5);
		eye1.setFill(Color.BLUE);
		eye1.setEffect(bloom);
		root.getChildren().add(eye1);
		
		
		Circle eye2 = new Circle(mouth.getCenterX() + 10, mouth.getCenterY() - 5, 5);
		eye2.setFill(Color.BLUE);
		eye2.setEffect(bloom);
		root.getChildren().add(eye2);

		Text text = new Text(scene.getWidth() / 3, scene.getHeight() / 2, "HAPPY BIRTHDAY!");
		text.setFill(Color.ORANGE);
		bloom.setInput(dropShadow);
		text.setEffect(bloom);
		root.getChildren().add(text);
		
		
		ScaleTransition st = new ScaleTransition(Duration.seconds(1), text);
		st.setByX(1.5);
		st.setByY(1.5);
		st.setCycleCount(50);
		st.setAutoReverse(true);
		st.play();


		Circle balloon = new Circle(BALLOON_RADIUS);
		balloon.setCenterX(scene.getWidth() / 4);
		balloon.setCenterY(scene.getHeight() / 3);
		balloon.setFill(BALLOON_COLOR);
		balloon.setStroke(Color.BLACK);
		root.getChildren().add(balloon);
		
		
		Circle balloon2 = new Circle(BALLOON_RADIUS);
		balloon2.setCenterX(scene.getWidth() / 1.5);
		balloon2.setCenterY(scene.getHeight() / 3);
		balloon2.setFill(BALLOON_COLOR);
		balloon2.setStroke(Color.BLACK);
		root.getChildren().add(balloon2);
		
		FadeTransition ft = new FadeTransition();
		ft.setDuration(Duration.seconds(3));
		ft.setFromValue(1.0);
		ft.setToValue(0.1);
		ft.setCycleCount(50);
		ft.setNode(balloon);
		ft.play();
		
		FadeTransition ft2 = new FadeTransition();
		ft2.setDuration(Duration.seconds(3));
		ft2.setFromValue(1.0);
		ft2.setToValue(0.1);
		ft2.setCycleCount(50);
		ft2.setNode(balloon2);
		ft2.play();
		
		
		PathTransition pt = new PathTransition();
		pt.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
	    pt.setDuration(Duration.seconds(2));
		pt.setCycleCount(50);
		pt.setAutoReverse(true);
		pt.setPath(balloon);
		pt.setNode(rect);
		pt.play();
		
		
		PathTransition pt2 = new PathTransition();
		pt2.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
	    pt2.setDuration(Duration.seconds(2));
		pt2.setCycleCount(50);
		pt2.setAutoReverse(true);
		pt2.setPath(balloon2);
		pt2.setNode(rect2);
		pt2.play();
		
		Line line = new Line();
		line.setStartX(balloon.getCenterX());
		line.setStartY(balloon.getCenterY() + BALLOON_RADIUS);
		line.setEndX(line.getStartX());
		line.setEndY(line.getStartY() + BALLOON_RADIUS);
		root.getChildren().add(line);
		
		Line line2 = new Line();
		line2.setStartX(balloon2.getCenterX());
		line2.setStartY(balloon2.getCenterY() + BALLOON_RADIUS);
		line2.setEndX(line2.getStartX());
		line2.setEndY(line2.getStartY() + BALLOON_RADIUS);
		root.getChildren().add(line2);
		
		stage.show();
	}
}
