
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.VBox;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;


import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;


public class LoginGUI extends Application  {
 
	
	   
 //  private final VBox vBox0;
 //   private final Button button5;
  //  private final Button button4;
@Override    
    public void start(Stage primaryStage) throws Exception  {
	 primaryStage.setTitle("VBox Experiment 1");
    	VBox vBox0 = new VBox();
vBox0.setMinHeight(700);
vBox0.setPrefHeight(1000.0);
//vBox0.setXmlns("http://javafx.com/javafx/17");
  //          vBox0.setFx("http://javafx.com/fxml/1");
            vBox0.setMaxHeight(1700);
vBox0.setPrefWidth(1000.0);
vBox0.setMinWidth(700);
vBox0.setMaxWidth(1700);
    VBox vBox1 = new VBox();
vBox1.setPrefHeight(53.0);
vBox1.setPrefWidth(1000.0);
vBox1.setAlignment(Pos.CENTER);
    Text text2 = new Text();
text2.setStrokeWidth(0.0);
text2.setStrokeType(StrokeType.OUTSIDE);
text2.setText("Are you accessing the Reservation System as an User or an Admin?");
text2.setStyle("-fx-font-size: 2em; ");
// Adding child to parent
vBox1.getChildren().add(text2);

// Adding child to parent
vBox0.getChildren().add(vBox1);
    VBox vBox3 = new VBox();
vBox3.setPrefHeight(200.0);
vBox3.setSpacing(25.0);
vBox3.setPrefWidth(100.0);
vBox3.setAlignment(Pos.CENTER);
     Button button4 = new Button();
button4.setText("User");
//button4.setPrefHeight(50);
//button4.setPrefWidth(100);
button4.setStyle("-fx-text-fill: #0000ff");
button4.setStyle("-fx-font-size: 2em; ");
button4.setAlignment(Pos.CENTER);
button4.setMnemonicParsing(false);

// Adding child to parent
vBox3.getChildren().add(button4);
   Button button5 = new Button();
button5.setContentDisplay(ContentDisplay.CENTER);
button5.setText("Admin");
button5.setStyle("-fx-text-fill: #0000ff");
button5.setStyle("-fx-font-size: 2em; ");
button5.setAlignment(Pos.CENTER);
button5.setMnemonicParsing(false);
vBox3.getChildren().add(button5);




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



	