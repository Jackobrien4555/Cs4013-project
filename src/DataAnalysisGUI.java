import javafx.geometry.Pos;
import javax.swing.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;


import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;
public class DataAnalysisGUI extends Application{
	@Override    
    public void start(Stage primaryStage) throws Exception  {
		primaryStage.setTitle("Data Analysis");
	
		VBox vBox0 = new VBox();
		vBox0.setMinHeight(700);
		vBox0.setPrefHeight(650.0);
		
		vBox0.setSpacing(20.0);
		vBox0.setMaxHeight(1700);
		vBox0.setPrefWidth(774.0);
		vBox0.setMinWidth(1000);
		vBox0.setAlignment(Pos.CENTER);
		vBox0.setMaxWidth(1700);
		VBox vBox1 = new VBox();
		vBox1.setPrefHeight(79.0);
		vBox1.setPrefWidth(211.0);
		vBox1.setAlignment(Pos.CENTER);
		Text text2 = new Text();
		text2.setStrokeWidth(0.0);
		text2.setStrokeType(StrokeType.OUTSIDE);
		text2.setText("Data Analysis");

		// Adding child to parent
		vBox1.getChildren().add(text2);
		String[] optionsToChoose = {"Hotel Occupancy Analytics", "Financial Analytics"};
		// Adding child to parent
		vBox0.getChildren().add(vBox1);
		 ChoiceBox choiceBox = new ChoiceBox();
		 choiceBox.getItems().add("Hotel Occupancy Analytics");
		 choiceBox.getItems().add("Financial Analytics");
		 vBox0.getChildren().add(choiceBox);
		 
		

		// Adding child to parent
		//vBox0.getChildren().add(jComboBox);
		Text text4 = new Text();
		text4.setStrokeWidth(0.0);
		text4.setStrokeType(StrokeType.OUTSIDE);
		text4.setText("Do you want to show rooms that aren't booked?");

		// Adding child to parent
		vBox0.getChildren().add(text4);
		HBox hBox5 = new HBox();
		hBox5.setPrefHeight(49.0);
		hBox5.setSpacing(30.0);
		hBox5.setPrefWidth(600.0);
		hBox5.setAlignment(Pos.CENTER);

		// Adding child to parent
		vBox0.getChildren().add(hBox5);
		CheckBox checkBox6 = new CheckBox();
		
		checkBox6.setText("Yes");
		checkBox6.setMnemonicParsing(false);

		// Adding child to parent
		vBox0.getChildren().add(checkBox6);
		CheckBox checkBox7 = new CheckBox();
		
		checkBox7.setText("No");
		checkBox7.setMnemonicParsing(false);

		// Adding child to parent
		vBox0.getChildren().add(checkBox7);
		HBox hBox8 = new HBox();
		hBox8.setPrefHeight(49.0);
		hBox8.setSpacing(30.0);
		hBox8.setPrefWidth(600.0);
		hBox8.setAlignment(Pos.CENTER);
		Text text9 = new Text();
		text9.setStrokeWidth(0.0);
		text9.setStrokeType(StrokeType.OUTSIDE);
		text9.setLayoutX(143.0);
		text9.setLayoutY(39.0);

		// Adding child to parent
		hBox8.getChildren().add(text9);

		// Adding child to parent
		vBox0.getChildren().add(hBox8);
		HBox hBox10 = new HBox();
		hBox10.setPrefHeight(49.0);
		hBox10.setSpacing(30.0);
		hBox10.setPrefWidth(600.0);
		hBox10.setAlignment(Pos.CENTER);

		// Adding child to parent
		vBox0.getChildren().add(hBox10);
		ScrollPane scrollPane11 = new ScrollPane();
		scrollPane11.setPrefHeight(450.0);
		scrollPane11.setPrefWidth(774.0);
		scrollPane11.setFitToHeight(true);
		scrollPane11.setFitToWidth(true);

		// Adding child to parent
		vBox0.getChildren().add(scrollPane11);
	Scene scene = new Scene(vBox0, 800, 500);
	
	primaryStage.setScene(scene);
	primaryStage.show();
	}
	    
	    public static void main(String[] args) {
	    	Application.launch(args);
	    }
		
		}
