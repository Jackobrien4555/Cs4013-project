import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;


import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;
public class ShowReservationGUI extends Application {
	@Override    
    public void start(Stage primaryStage) throws Exception  {
	 primaryStage.setTitle("Show Reservations");
	VBox vBox0 = new VBox();
	vBox0.setMinHeight(700);
	vBox0.setPrefHeight(1000);
	
	vBox0.setMaxHeight(1700);
	vBox0.setPrefWidth(1000);
	vBox0.setMinWidth(700);
	vBox0.setAlignment(Pos.CENTER);
	vBox0.setMaxWidth(1700);
	VBox vBox1 = new VBox();
	vBox1.setPrefHeight(50.0);
	vBox1.setSpacing(30.0);
	vBox1.setPrefWidth(600.0);
	vBox1.setAlignment(Pos.CENTER);
	Text text2 = new Text();
	text2.setStrokeWidth(0.0);
	text2.setStrokeType(StrokeType.OUTSIDE);
	text2.setText("Showing all reservations:");

	// Adding child to parent
	vBox1.getChildren().add(text2);

	// Adding child to parent
	vBox0.getChildren().add(vBox1);
	ScrollPane scrollPane3 = new ScrollPane();
	scrollPane3.setPrefHeight(300.0);
	scrollPane3.setPrefWidth(200.0);

	// Adding child to parent
	vBox0.getChildren().add(scrollPane3);
	Button button4 = new Button();
	button4.setText("Return to Choices");
	button4.setMnemonicParsing(false);

	// Adding child to parent
	vBox0.getChildren().add(button4);
	Scene scene = new Scene(vBox0, 800, 500);
	primaryStage.setScene(scene);
	primaryStage.show();
	}
	    
	    public static void main(String[] args) {
	    	Application.launch(args);
	    }
		
		}
