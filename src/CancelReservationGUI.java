import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;


import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class CancelReservationGUI extends Application{
	@Override    
    public void start(Stage primaryStage) throws Exception  {
		primaryStage.setTitle("Cancel reservation");
	
	VBox vBox0 = new VBox();
	vBox0.setMinHeight(700);
	vBox0.setPrefHeight(1000);
	
	vBox0.setSpacing(20.0);
	vBox0.setMaxHeight(1700);
	vBox0.setPrefWidth(774.0);
	vBox0.setMinWidth(700);
	vBox0.setAlignment(Pos.CENTER);
	vBox0.setMaxWidth(1700);
	VBox vBox1 = new VBox();
	vBox1.setPrefHeight(79.0);
	vBox1.setPrefWidth(211.0);
	vBox1.setAlignment(Pos.CENTER);
	Text text2 = new Text();
	text2.setStrokeWidth(0.0);
	text2.setStrokeType(StrokeType.OUTSIDE);
	text2.setText("Cancel a Reservation");

	// Adding child to parent
	vBox1.getChildren().add(text2);

	// Adding child to parent
	vBox0.getChildren().add(vBox1);
	HBox hBox3 = new HBox();
	hBox3.setPrefHeight(49.0);
	hBox3.setSpacing(70.0);
	hBox3.setPrefWidth(600.0);
	hBox3.setAlignment(Pos.CENTER);
	Text text4 = new Text();
	text4.setStrokeWidth(0.0);
	text4.setStrokeType(StrokeType.OUTSIDE);
	text4.setText("Name:");

	// Adding child to parent
	hBox3.getChildren().add(text4);
	TextField textField5 = new TextField();
	textField5.setPromptText("e.g. John Smith");

	// Adding child to parent
	hBox3.getChildren().add(textField5);

	// Adding child to parent
	vBox0.getChildren().add(hBox3);
	HBox hBox6 = new HBox();
	hBox6.setPrefHeight(49.0);
	hBox6.setSpacing(30.0);
	hBox6.setPrefWidth(600.0);
	hBox6.setAlignment(Pos.CENTER);
	Text text7 = new Text();
	text7.setStrokeWidth(0.0);
	text7.setStrokeType(StrokeType.OUTSIDE);
	text7.setText("Reservation Type:");

	// Adding child to parent
	hBox6.getChildren().add(text7);
	ChoiceBox choiceBox8 = new ChoiceBox();
	choiceBox8.setPrefWidth(150.0);

	// Adding child to parent
	hBox6.getChildren().add(choiceBox8);

	// Adding child to parent
	vBox0.getChildren().add(hBox6);
	HBox hBox9 = new HBox();
	hBox9.setPrefHeight(49.0);
	hBox9.setSpacing(30.0);
	hBox9.setPrefWidth(600.0);
	hBox9.setAlignment(Pos.CENTER);
	Text text10 = new Text();
	text10.setStrokeWidth(0.0);
	text10.setStrokeType(StrokeType.OUTSIDE);
	text10.setText("Check-in Date:");

	// Adding child to parent
	hBox9.getChildren().add(text10);
	DatePicker datePicker11 = new DatePicker();

	// Adding child to parent
	hBox9.getChildren().add(datePicker11);
	Text text12 = new Text();
	text12.setStrokeWidth(0.0);
	text12.setStrokeType(StrokeType.OUTSIDE);
	text12.setLayoutX(147.0);
	text12.setLayoutY(39.0);

	// Adding child to parent
	hBox9.getChildren().add(text12);

	// Adding child to parent
	vBox0.getChildren().add(hBox9);
	HBox hBox13 = new HBox();
	hBox13.setPrefHeight(49.0);
	hBox13.setSpacing(30.0);
	hBox13.setPrefWidth(600.0);
	hBox13.setAlignment(Pos.CENTER);
	Text text14 = new Text();
	text14.setStrokeWidth(0.0);
	text14.setStrokeType(StrokeType.OUTSIDE);
	text14.setText("Check-out Date:");

	// Adding child to parent
	hBox13.getChildren().add(text14);
	DatePicker datePicker15 = new DatePicker();

	// Adding child to parent
	hBox13.getChildren().add(datePicker15);
	Text text16 = new Text();
	text16.setStrokeWidth(0.0);
	text16.setStrokeType(StrokeType.OUTSIDE);
	text16.setLayoutX(143.0);
	text16.setLayoutY(39.0);

	// Adding child to parent
	hBox13.getChildren().add(text16);

	// Adding child to parent
	vBox0.getChildren().add(hBox13);
	HBox hBox17 = new HBox();
	hBox17.setPrefHeight(49.0);
	hBox17.setSpacing(30.0);
	hBox17.setPrefWidth(600.0);
	hBox17.setAlignment(Pos.CENTER);
	Text text18 = new Text();
	text18.setStrokeWidth(0.0);
	text18.setStrokeType(StrokeType.OUTSIDE);
	text18.setText("Number of rooms:");

	// Adding child to parent
	hBox17.getChildren().add(text18);
	TextField textField19 = new TextField();
	textField19.setPromptText("e.g. 2");

	// Adding child to parent
	hBox17.getChildren().add(textField19);

	// Adding child to parent
	vBox0.getChildren().add(hBox17);
	ScrollPane scrollPane20 = new ScrollPane();
	scrollPane20.setPrefHeight(450.0);
	scrollPane20.setPrefWidth(774.0);
	scrollPane20.setFitToHeight(true);
	scrollPane20.setFitToWidth(true);

	// Adding child to parent
	vBox0.getChildren().add(scrollPane20);
	Button button21 = new Button();
	button21.setText("Book Reservation");
	button21.setMnemonicParsing(false);

	// Adding child to parent
	vBox0.getChildren().add(button21);
	Button button22 = new Button();
	button22.setLayoutX(338.0);
	button22.setLayoutY(582.0);
	button22.setText("Return to Choices");
	button22.setMnemonicParsing(false);

	// Adding child to parent
	vBox0.getChildren().add(button22);

	Scene scene = new Scene(vBox0, 800, 500);
	primaryStage.setScene(scene);
	primaryStage.show();
	}
	    
	    public static void main(String[] args) {
	    	Application.launch(args);
	    }
		
		}

