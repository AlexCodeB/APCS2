//JavaFX day 3 Reflection

//Alex Bruckhaus, Period 6, 3/12/2018
//This program took me 40 minutes.
//Again, this assignment was very fun to code. I enjoyed seeing the code come to life on the screen. It's very interesting to see
//everything you can do with java. During this lab, I did not run into any issues except deciding which layouts and controls to add.
//Overall, this lab was very informative and helped me gain a better understanding of JavaFX and its usefulness.



//JavaFX day 4 Reflection

//Alex Bruckhaus, Period 6, 3/13/2018
//This program took me 1 hour.
//This lab was even more enjoyable than the last one because it allowed me to modify my assignment to do impressive things. I feel like
//I am gaining more useful knowledge that will help me in the future thanks to these assignments. In this assignment, I did not run into many
//issues besides some confusion in the beginning. It took me a while to understand how to use event handlers, but once I got the hang
//of it, it became a lot easier to understand. Overall, this lab further expanded my knowledge of JavaFX while also being enjoyable.

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class P6_Bruckhaus_Alexander_GUI_4 extends Application {
	
	// measurements
	private final double HEAD_RADIUS = 100;
	
	private final double EAR_RADIUS = HEAD_RADIUS / 3;
	private final double	 EAR_OFF_X = HEAD_RADIUS / 1.1;
	private final double EAR_OFF_Y = HEAD_RADIUS / 1.5;
	
	private final double MOUTH_OFF_Y = HEAD_RADIUS / 1.5;
	private final double MOUTH_RADIUS = HEAD_RADIUS / 6.5;
	
	private final double EYE_HEIGHT = HEAD_RADIUS / 2.5;
	private final double EYE_WIDTH = HEAD_RADIUS / 15;
	private final double EYE_OFF_Y = HEAD_RADIUS / 2;
	private final double EYE_OFF_X = HEAD_RADIUS / 4;
	
	private final Color HEAD_COLOR = Color.GRAY;
	private final Color EAR_COLOR = Color.WHEAT;
	private final Color MOUTH_COLOR = Color.SALMON;
	private final Color EYE_COLOR = Color.BLACK;
	
	Group root2 = new Group();

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("CoolStuff");
		stage.setResizable(true);
		stage.sizeToScene();
		
		VBox root = new VBox();
		Scene scene = new Scene(root, 500, 500);
		stage.setScene(scene);
		
		HBox userBox = new HBox();
		Label user = new Label("Username: ");
		TextField userField = new TextField();
		userField.setPromptText("Username");
		root.getChildren().add(userBox);
		userBox.setAlignment(Pos.CENTER);
		userBox.setPadding(new Insets(15));
		userBox.setPrefWidth(50);
		userBox.setMinWidth(20);
		userBox.setMinHeight(20);
		userBox.getChildren().addAll(user, userField);
		
		HBox pwdBox = new HBox();
		Label pwd = new Label("Password: ");
		PasswordField pwdField = new PasswordField();
		pwdField.setPromptText("Password");
		root.getChildren().add(pwdBox);		
		pwdBox.setAlignment(Pos.CENTER);
		pwdBox.setPrefWidth(50);
		pwdBox.setMinWidth(20);
		pwdBox.setMinHeight(20);
		pwdBox.getChildren().addAll(pwd, pwdField);
		
//		BackgroundFill fill = new BackgroundFill(
//								Color.AQUA,
//								new CornerRadii(2),
//		new Insets(0));
//		pwdBox.setBackground(new Background(fill));
		
		HBox buttonBox = new HBox();
		Button ok = new Button("Ok");
		Button cancel = new Button("Cancel");
		root.getChildren().add(buttonBox);
		buttonBox.getChildren().add(ok);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setPadding(new Insets(15));
		buttonBox.getChildren().add(cancel);
		
		VBox colors = new VBox();
		colors.setAlignment(Pos.CENTER);
		ColorPicker colorPicker = new ColorPicker();
		colorPicker.setValue(Color.GREEN);
		root.getChildren().add(colors);
		colors.getChildren().add(colorPicker);
		
		////////////////////////////////////// My Picture

		Group root2 = new Group();
		
		Circle head = new Circle(HEAD_RADIUS);
		head.setCenterX(scene.getWidth() / 2);
		head.setCenterY(scene.getHeight() / 2);
		head.setFill(HEAD_COLOR);
		head.setStroke(Color.YELLOW);
		root2.getChildren().add(head);
			
		Circle leftEar = new Circle(EAR_RADIUS);
		leftEar.setCenterX(head.getCenterX() - EAR_OFF_X);
		leftEar.setCenterY(head.getCenterY() - EAR_OFF_Y);
		leftEar.setFill(EAR_COLOR);
		root2.getChildren().add(leftEar);
				
		Circle rightEar = new Circle(EAR_RADIUS);
		rightEar.setCenterX(head.getCenterX() + EAR_OFF_X);
		rightEar.setCenterY(head.getCenterY() - EAR_OFF_Y);
		rightEar.setFill(EAR_COLOR);
		root2.getChildren().add(rightEar);
				
		Circle mouth = new Circle(MOUTH_RADIUS);
		mouth.setCenterX(head.getCenterX());
		mouth.setCenterY(head.getCenterY() + MOUTH_OFF_Y);
		mouth.setFill(MOUTH_COLOR);
		root2.getChildren()
		.add(mouth);
		
		Rectangle leftEye = new Rectangle(head.getCenterX() - EYE_OFF_X, head.getCenterY() -EYE_OFF_Y,
											EYE_WIDTH, EYE_HEIGHT);
		leftEye.setFill(EYE_COLOR);
		root2.getChildren().add(leftEye);
				
		Rectangle rightEye = new Rectangle (head.getCenterX() + EYE_OFF_X, head.getCenterY() - EYE_OFF_Y,
											EYE_WIDTH, EYE_HEIGHT);
		rightEye.setFill(EYE_COLOR);
		root2.getChildren().add(rightEye);
				
		Ellipse nose = new Ellipse(head.getCenterX() + HEAD_RADIUS / 10, 
									head.getCenterY() + HEAD_RADIUS / 5, HEAD_RADIUS / 5, HEAD_RADIUS / 10);
		nose.setFill(Color.PINK);
		root2.getChildren().add(nose);
		
		
		VBox picture = new VBox();
		picture.setAlignment(Pos.CENTER);
		picture.setPrefHeight(100);
		picture.setPrefWidth(100);
		root.getChildren().add(picture);
		picture.getChildren().add(root2);
		
	
		
		class MyMouseHandler implements EventHandler<MouseEvent>{

			@Override
			public void handle(MouseEvent event) {
				if(event.getSource() == root2) {
					if(event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
						System.out.println("Your mouse is tickling the mouse!");
					}else if(event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
						System.out.println("Your mouse stopped tickling the mouse");
					}else if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
						RotateTransition rot = new RotateTransition(Duration.millis(750), root2);
						rot.setByAngle(-360);
						rot.play();
					}
				}
			}
		}
		
		class MyColorHandler implements EventHandler<Event>{

			@Override
			public void handle(Event event) {
				if(event.getSource() == head) {
					if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
						head.setFill(colorPicker.getValue());
						System.out.println("You changed the color of the mouse's head to: " + colorPicker.getValue());
					}
				}
			}
		}
		
		MyMouseHandler handle = new MyMouseHandler();
		root2.setOnMouseEntered(handle);
		root2.setOnMouseExited(handle);
		root2.setOnMouseClicked(handle);
		
		MyColorHandler cHandle = new MyColorHandler();
		head.setOnMouseClicked(cHandle);
		
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
			
}
