import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.VBox;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;


import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class AdminChoicesGUI extends Application {
	@Override    
    public void start(Stage primaryStage) throws Exception  {
		primaryStage.setTitle("Admin Interface");
	VBox vBox0 = new VBox();
	vBox0.setMinHeight(700);
	vBox0.setPrefHeight(1000);
	
	vBox0.setMaxHeight(1700);
	vBox0.setPrefWidth(1000);
	vBox0.setMinWidth(700);
	vBox0.setMaxWidth(1700);
	VBox vBox1 = new VBox();
	vBox1.setPrefHeight(200.0);
	vBox1.setPrefWidth(100.0);
	vBox1.setAlignment(Pos.CENTER);
	Text text2 = new Text();
	text2.setStrokeWidth(0.0);
	text2.setStrokeType(StrokeType.OUTSIDE);
	text2.setText("Please choose an option:");

	// Adding child to parent
	vBox1.getChildren().add(text2);

	// Adding child to parent
	vBox0.getChildren().add(vBox1);
	VBox vBox3 = new VBox();
	vBox3.setPrefHeight(200.0);
	vBox3.setSpacing(10.0);
	vBox3.setPrefWidth(100.0);
	vBox3.setAlignment(Pos.CENTER);
	Button button4 = new Button();
	button4.setLayoutX(10.0);
	button4.setMinWidth(150.0);
	button4.setLayoutY(10.0);
	button4.setText("Make a Reservation");
	button4.setMnemonicParsing(false);

	// Adding child to parent
	vBox3.getChildren().add(button4);
	Button button5 = new Button();
	button5.setLayoutX(10.0);
	button5.setMinWidth(150.0);
	button5.setLayoutY(35.0);
	button5.setText("Cancel Reservation");
	button5.setMnemonicParsing(false);

	// Adding child to parent
	vBox3.getChildren().add(button5);
	Button button6 = new Button();
	button6.setLayoutX(10.0);
	button6.setMinWidth(150.0);
	button6.setLayoutY(60.0);
	button6.setText("Return to Login");
	button6.setMnemonicParsing(false);

	// Adding child to parent
	vBox3.getChildren().add(button6);

	// Adding child to parent
	vBox0.getChildren().add(vBox3);

	Scene scene = new Scene(vBox0, 800, 500);
	primaryStage.setScene(scene);
	primaryStage.show();
	}
	    
	    public static void main(String[] args) {
	    	Application.launch(args);
	    }
		
		}

