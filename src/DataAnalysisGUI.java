import javafx.geometry.Pos;

import javafx.scene.control.Button;
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
	vBox0.setPrefHeight(1000);
	
	vBox0.setSpacing(20.0);
	vBox0.setMaxHeight(1700);
	vBox0.setPrefWidth(1000);
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
	text2.setText("Data Analysis");

	// Adding child to parent
	vBox1.getChildren().add(text2);

	// Adding child to parent
	vBox0.getChildren().add(vBox1);
	HBox hBox3 = new HBox();
	hBox3.setPrefHeight(49.0);
	hBox3.setSpacing(30.0);
	hBox3.setPrefWidth(600.0);
	hBox3.setAlignment(Pos.CENTER);
	Text text4 = new Text();
	text4.setStrokeWidth(0.0);
	text4.setStrokeType(StrokeType.OUTSIDE);
	text4.setText("Enter Dates to get occupancy rates for all Hotels and Room");

	// Adding child to parent
	hBox3.getChildren().add(text4);

	// Adding child to parent
	vBox0.getChildren().add(hBox3);
	HBox hBox5 = new HBox();
	hBox5.setPrefHeight(49.0);
	hBox5.setSpacing(30.0);
	hBox5.setPrefWidth(600.0);
	hBox5.setAlignment(Pos.CENTER);
	Text text6 = new Text();
	text6.setStrokeWidth(0.0);
	text6.setStrokeType(StrokeType.OUTSIDE);
	text6.setText("Start date:");

	// Adding child to parent
	hBox5.getChildren().add(text6);
	DatePicker datePicker7 = new DatePicker();

	// Adding child to parent
	hBox5.getChildren().add(datePicker7);
	Text text8 = new Text();
	text8.setStrokeWidth(0.0);
	text8.setStrokeType(StrokeType.OUTSIDE);
	text8.setLayoutX(147.0);
	text8.setLayoutY(39.0);

	// Adding child to parent
	hBox5.getChildren().add(text8);

	// Adding child to parent
	vBox0.getChildren().add(hBox5);
	HBox hBox9 = new HBox();
	hBox9.setPrefHeight(49.0);
	hBox9.setSpacing(30.0);
	hBox9.setPrefWidth(600.0);
	hBox9.setAlignment(Pos.CENTER);
	Text text10 = new Text();
	text10.setStrokeWidth(0.0);
	text10.setStrokeType(StrokeType.OUTSIDE);
	text10.setText("End Date:");

	// Adding child to parent
	hBox9.getChildren().add(text10);
	DatePicker datePicker11 = new DatePicker();

	// Adding child to parent
	hBox9.getChildren().add(datePicker11);
	Text text12 = new Text();
	text12.setStrokeWidth(0.0);
	text12.setStrokeType(StrokeType.OUTSIDE);
	text12.setLayoutX(143.0);
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

	// Adding child to parent
	vBox0.getChildren().add(hBox13);
	ScrollPane scrollPane14 = new ScrollPane();
	scrollPane14.setPrefHeight(450.0);
	scrollPane14.setPrefWidth(774.0);
	scrollPane14.setFitToHeight(true);
	scrollPane14.setFitToWidth(true);

	// Adding child to parent
	vBox0.getChildren().add(scrollPane14);
	Scene scene = new Scene(vBox0, 800, 500);
	primaryStage.setScene(scene);
	primaryStage.show();
	}
	    
	    public static void main(String[] args) {
	    	Application.launch(args);
	    }
		
		}
