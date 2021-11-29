import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;


import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This is the main driver of the GUI version
 * of the reservation system.
 *
 * @author Edison Cai 20241135
 */
public class HotelGUI extends Application {
    private static final Stage mainStage = new Stage();
    private static boolean isAdmin = false;
    private static InputScanner userInput;
    private static InputValidator validator;
    private static Writer writer;

    public static void main(String[] args) {
        userInput = new InputScanner();
        writer = new Writer();
        validator = new InputValidator();

        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Reader.readReservations(ConstantReferences.RESERVATIONS);
        Reader.readCancellations(ConstantReferences.CANCELLATIONS);
        HotelInitialiser.initialise(HotelInitialiser.getFileCells(ConstantReferences.HOTELS));
        mainStage.setTitle("Hotel Login");
        mainStage.setScene(createLogin());
        mainStage.show();
    }

    /*
     *
     * @author Jack O'Brien
     */
    private static Scene createLogin() {
        VBox vBox0 = new VBox();
        vBox0.setMinHeight(700);
        vBox0.setPrefHeight(400.0);
        //vBox0.setXmlns("http://javafx.com/javafx/17");
        //          vBox0.setFx("http://javafx.com/fxml/1");
        vBox0.setMaxHeight(1700);
        vBox0.setPrefWidth(600.0);
        vBox0.setMinWidth(700);
        vBox0.setMaxWidth(700);
        VBox vBox1 = new VBox();
        vBox1.setPrefHeight(53.0);
        vBox1.setPrefWidth(500.0);
        vBox1.setAlignment(Pos.CENTER);
        Text text2 = new Text();
        text2.setStrokeWidth(0.0);
        text2.setStrokeType(StrokeType.OUTSIDE);
        text2.setText("Are you accessing the Reservation System as an User or an Admin?");

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
        button4.setOnAction(event -> {
            isAdmin = false;
            mainStage.setScene(createUserChoicesGUI());
        });
        button4.setText("User");
        button4.setAlignment(Pos.CENTER);
        button4.setMnemonicParsing(false);

        // Adding child to parent
        vBox3.getChildren().add(button4);
        Button button5 = new Button();
        button5.setContentDisplay(ContentDisplay.CENTER);
        button5.setText("Admin");
        button5.setAlignment(Pos.CENTER);
        button5.setOnAction(event -> {
            isAdmin = true;
            mainStage.setScene(createAdminChoicesGUI());
        });
        button5.setMnemonicParsing(false);
        vBox3.getChildren().add(button5);


        // Adding child to parent
        vBox0.getChildren().add(vBox3);

        //return new Scene(vBox0, 200, 100);
        return new Scene(vBox0, 800, 500);

    }

    //Done
    private static Scene createAdminChoicesGUI() {
        mainStage.setTitle("Admin Interface");
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
        button4.setOnAction(event -> {
            mainStage.setScene(createMakeReservationsGUI());
        });
        button4.setMnemonicParsing(false);
        // Adding child to parent
        vBox3.getChildren().add(button4);

        Button button5 = new Button();
        button5.setLayoutX(10.0);
        button5.setMinWidth(150.0);
        button5.setLayoutY(35.0);
        button5.setText("Cancel Reservation");
        button5.setOnAction(event -> {
            mainStage.setScene(createCancellationsGUI());
        });
        button5.setMnemonicParsing(false);
        // Adding child to parent
        vBox3.getChildren().add(button5);

        Button button7 = new Button();
        button7.setLayoutX(10.0);
        button7.setMinWidth(150.0);
        button7.setLayoutY(60.0);
        button7.setText("Show all Reservations");
        button7.setOnAction(event -> {
            mainStage.setScene(createShowReservationsGUI());
        });
        button7.setMnemonicParsing(false);
        // Adding child to parent
        vBox3.getChildren().add(button7);

        Button button8 = new Button();
        button8.setLayoutX(10.0);
        button8.setMinWidth(150.0);
        button8.setLayoutY(60.0);
        button8.setText("Show all Cancellations");
        button8.setOnAction(event -> {
            mainStage.setScene(createShowCancellationsGUI());
        });
        button8.setMnemonicParsing(false);
        // Adding child to parent
        vBox3.getChildren().add(button8);

        Button buttonDataAnalysis = new Button();
        buttonDataAnalysis.setLayoutX(10.0);
        buttonDataAnalysis.setMinWidth(150.0);
        buttonDataAnalysis.setLayoutY(60.0);
        buttonDataAnalysis.setText("Show Data Analysis");
        buttonDataAnalysis.setOnAction(event -> {
            mainStage.setScene(createDataAnalysisGUI());
        });
        buttonDataAnalysis.setMnemonicParsing(false);
        // Adding child to parent
        vBox3.getChildren().add(buttonDataAnalysis);

        Button button6 = new Button();
        button6.setLayoutX(10.0);
        button6.setMinWidth(150.0);
        button6.setLayoutY(60.0);
        button6.setText("Return to Login");
        button6.setOnAction(event -> {
            mainStage.setScene(createLogin());
        });
        button6.setMnemonicParsing(false);
        // Adding child to parent
        vBox3.getChildren().add(button6);

        // Adding child to parent
        vBox0.getChildren().add(vBox3);

        return new Scene(vBox0, 800, 500);
    }

    //Done
    private static Scene createUserChoicesGUI() {
        mainStage.setTitle("User Interface");
        VBox vBox0 = new VBox();
        vBox0.setMinHeight(700);
        vBox0.setPrefHeight(1000);
        vBox0.setMaxHeight(1700);
        vBox0.setPrefWidth(1000);
        vBox0.setMinWidth(700);
        vBox0.setAlignment(Pos.CENTER);
        vBox0.setMaxWidth(1700);
        VBox vBox1 = new VBox();
        vBox1.setPrefHeight(92.0);
        vBox1.setPrefWidth(600.0);
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
        vBox3.setPrefHeight(130.0);
        vBox3.setSpacing(10.0);
        vBox3.setPrefWidth(600.0);
        vBox3.setAlignment(Pos.CENTER);
        Button button4 = new Button();
        button4.setLayoutX(10.0);
        button4.setMinWidth(200.0);
        button4.setLayoutY(10.0);
        button4.setText("Make a Reservation");
        button4.setOnAction(event -> {
            mainStage.setScene(createMakeReservationsGUI());
        });

        button4.setMnemonicParsing(false);

        // Adding child to parent
        vBox3.getChildren().add(button4);
        Button button5 = new Button();
        button5.setLayoutX(10.0);
        button5.setMinWidth(200.0);
        button5.setLayoutY(35.0);
        button5.setText("Cancel Reservation");
        button5.setOnAction(event -> {
            mainStage.setScene(createCancellationsGUI());
        });
        button5.setMnemonicParsing(false);

        // Adding child to parent
        vBox3.getChildren().add(button5);
        Button button6 = new Button();
        button6.setLayoutX(10.0);
        button6.setMinWidth(200.0);
        button6.setLayoutY(60.0);
        button6.setText("Return to Login");
        button6.setOnAction(event -> {
            mainStage.setScene(createLogin());
        });
        button6.setMnemonicParsing(false);

        // Adding child to parent
        vBox3.getChildren().add(button6);

        // Adding child to parent
        vBox0.getChildren().add(vBox3);
        return new Scene(vBox0, 800, 500);

    }

    //Done
    private static Scene createCancellationsGUI() {
        mainStage.setTitle("Cancel reservation");
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
        text4.setText("Reservation Number of the Reservation you Want to Cancel:");

        // Adding child to parent
        hBox3.getChildren().add(text4);
        TextField textField5 = new TextField();
        textField5.setPromptText("120584");
        Text invalidNum = new Text("Not a valid reservation number.");
        invalidNum.setVisible(false);
        Text success = new Text("Successfully cancelled!");
        success.setVisible(false);

        // Adding child to parent
        hBox3.getChildren().add(textField5);
        hBox3.getChildren().add(invalidNum);

        // Adding child to parent
        vBox0.getChildren().add(hBox3);
        vBox0.getChildren().add(success);

        Button button21 = new Button();
        button21.setText("Cancel Reservation");
        button21.setMnemonicParsing(false);


        // Adding child to parent
        vBox0.getChildren().add(button21);
        Button button22 = new Button();
        button22.setLayoutX(338.0);
        button22.setLayoutY(582.0);
        button22.setText("Return to Choices");
        button22.setOnAction(event -> {
            if (isAdmin) {
                mainStage.setScene(createAdminChoicesGUI());
            } else {
                mainStage.setScene(createUserChoicesGUI());
            }
        });
        button22.setMnemonicParsing(false);

        // Adding child to parent
        vBox0.getChildren().add(button22);

        button21.setOnAction(event -> {
            int resNumber = 0;


            try {
                resNumber = Integer.parseInt(textField5.getText());
            } catch (NumberFormatException e) {
                invalidNum.setVisible(true);
            }


            Reservation chosenReservation = ReservationCancellationManager.getReservation(resNumber);
            Cancellation cancellation = new Cancellation(chosenReservation);


            if (chosenReservation != null) {
                ReservationCancellationManager.addCancellation(cancellation);
                int resNum = cancellation.getReservation().getResNumber();
                writer.writeCancellation(ConstantReferences.CANCELLATIONS, cancellation);
                ArrayList<Reservation> reservations = ReservationCancellationManager.getAllReservations();
                reservations.remove(ReservationCancellationManager.getReservation(resNum));
                ReservationCancellationManager.setAllReservations(reservations);
                writer.writeReservations(ConstantReferences.RESERVATIONS, reservations);
                success.setVisible(true);
            } else {
                success.setVisible(false);
                invalidNum.setVisible(true);
            }
        });

        return new Scene(vBox0, 800, 500);
    }

    private static Scene createDataAnalysisGUI() {
        mainStage.setTitle("Data Analysis");

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
        Button button4 = new Button();
        button4.setText("Return to Choices");
        button4.setOnAction(event -> {
            mainStage.setScene(createAdminChoicesGUI());
        });
        button4.setMnemonicParsing(false);

        // Adding child to parent
        vBox0.getChildren().add(button4);

        // Adding child to parent
        vBox0.getChildren().add(scrollPane14);
        return new Scene(vBox0, 800, 500);

    }

    private static Scene createMakeReservationsGUI() {
        mainStage.setTitle("Making reservation");

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
        text2.setText("Book a Reservation");

        // Adding child to parent
        vBox1.getChildren().add(text2);

        // Adding child to parent
        vBox0.getChildren().add(vBox1);

        ////////////////////
        HBox hBoxNum = new HBox();
        hBoxNum.setPrefHeight(49.0);
        hBoxNum.setSpacing(70.0);
        hBoxNum.setPrefWidth(600.0);
        hBoxNum.setAlignment(Pos.CENTER);

        Text textNum = new Text();
        textNum.setStrokeWidth(0.0);
        textNum.setStrokeType(StrokeType.OUTSIDE);
        textNum.setText("Reservation Number:");

        // Adding child to parent
        hBoxNum.getChildren().add(textNum);
        TextField textFieldNum = new TextField();
        textFieldNum.setPromptText("e.g. 421562");


        // Adding child to parent
        hBoxNum.getChildren().add(textFieldNum);

        Text invalidNum = new Text("Not a valid reservation number.");
        invalidNum.setVisible(false);
        hBoxNum.getChildren().add(invalidNum);

        vBox0.getChildren().add(hBoxNum);
        /////////////

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
        TextField textFieldName = new TextField();
        textFieldName.setPromptText("e.g. John Smith");

        // Adding child to parent
        hBox3.getChildren().add(textFieldName);

        Text invalidName = new Text("Not a valid name (No numbers allowed).");
        invalidName.setVisible(false);
        hBox3.getChildren().add(invalidName);

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
        ChoiceBox<String> choiceBox8 = new ChoiceBox<>();
        choiceBox8.getItems().add("S");
        choiceBox8.getItems().add("AP");
        choiceBox8.setPrefWidth(150.0);

        // Adding child to parent
        hBox6.getChildren().add(choiceBox8);

        Text invalidType = new Text("Please choose reservation type.");
        invalidType.setVisible(false);
        hBox6.getChildren().add(invalidType);

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
        DatePicker datePickerStart = new DatePicker();

        // Adding child to parent
        hBox9.getChildren().add(datePickerStart);

        Text invalidStart = new Text("Invalid start date.");
        invalidStart.setVisible(false);
        hBox9.getChildren().add(invalidStart);

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
        DatePicker datePickerEnd = new DatePicker();

        // Adding child to parent
        hBox13.getChildren().add(datePickerEnd);

        Text invalidEnd = new Text("Invalid end date.");
        invalidEnd.setVisible(false);
        hBox13.getChildren().add(invalidEnd);

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
        TextField textFieldRoomNum = new TextField();
        textFieldRoomNum.setPromptText("e.g. 2");


        // Adding child to parent
        hBox17.getChildren().add(textFieldRoomNum);

        Text invalidRoomNum = new Text("Invalid number of rooms.");
        invalidRoomNum.setVisible(false);
        hBox17.getChildren().add(invalidRoomNum);

        // Adding child to parent
        vBox0.getChildren().add(hBox17);
        ScrollPane scrollPane20 = new ScrollPane();
        scrollPane20.setPrefHeight(800.0);
        scrollPane20.setPrefWidth(774.0);
        scrollPane20.setFitToHeight(true);
        scrollPane20.setFitToWidth(true);

        // Adding child to parent
        vBox0.getChildren().add(scrollPane20);

        // Adding a listener to the content of the text field.
        textFieldRoomNum.textProperty().addListener((v, oldValue, newValue) -> {
            try {
                int numberOfRooms = Integer.parseInt(textFieldRoomNum.getText());

                if (validator.inputIsInteger(Integer.toString(numberOfRooms))) {
                    scrollPane20.setContent(createRoomsPicker(numberOfRooms));
                } else {
                    scrollPane20.setContent(null);
                }
            } catch (NumberFormatException ignored) {
            }
        });

        // Show this text when the reservation has been successful.
        Text successText = new Text("Successfully made reservation!");
        successText.setVisible(false);
        vBox0.getChildren().add(successText);

        Button button21 = new Button();
        button21.setText("Book Reservation");
        button21.setMnemonicParsing(false);

        button21.setOnAction(event -> {
            createReservation(textFieldRoomNum, invalidRoomNum,
                    textFieldName, invalidName, choiceBox8, invalidType, datePickerStart,
                    invalidStart, datePickerEnd, invalidEnd, scrollPane20, (HBox) scrollPane20.getContent(), successText);
        });

        // Adding child to parent
        vBox0.getChildren().add(button21);
        Button button22 = new Button();
        button22.setLayoutX(338.0);
        button22.setLayoutY(582.0);
        button22.setText("Return to Choices");
        button22.setOnAction(event -> {
            if (isAdmin) {
                mainStage.setScene(createAdminChoicesGUI());
            } else {
                mainStage.setScene(createUserChoicesGUI());
            }
        });
        button22.setMnemonicParsing(false);

        // Adding child to parent
        vBox0.getChildren().add(button22);
        return new Scene(vBox0, 1600, 1000);


    }

    //Done
    private static Scene createShowReservationsGUI() {
        mainStage.setTitle("Show Reservations");
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

        VBox allReservations = new VBox();

        ArrayList<Reservation> reservations = ReservationCancellationManager.getAllReservations();
        for (Reservation reservation : reservations) {
            Text reservationText = new Text(reservation.toString());
            allReservations.getChildren().add(reservationText);
        }

        scrollPane3.setContent(allReservations);

        // Adding child to parent
        vBox0.getChildren().add(scrollPane3);
        Button button4 = new Button();
        button4.setText("Return to Choices");
        button4.setOnAction(event -> {
            mainStage.setScene(createAdminChoicesGUI());
        });
        button4.setMnemonicParsing(false);

        // Adding child to parent
        vBox0.getChildren().add(button4);
        return new Scene(vBox0, 800, 500);
    }

    //Done
    private static Scene createShowCancellationsGUI() {
        mainStage.setTitle("Show Cancellations");
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
        text2.setText("Showing all cancellations:");

        // Adding child to parent
        vBox1.getChildren().add(text2);

        // Adding child to parent
        vBox0.getChildren().add(vBox1);
        ScrollPane scrollPane3 = new ScrollPane();
        scrollPane3.setPrefHeight(300.0);
        scrollPane3.setPrefWidth(200.0);

        VBox allReservations = new VBox();
        allReservations.setSpacing(30);

        ArrayList<Cancellation> cancellations = ReservationCancellationManager.getAllCancellations();
        for (Cancellation cancellation : cancellations) {
            Text cancellationText = new Text(cancellation.toString());
            allReservations.getChildren().add(cancellationText);
        }

        scrollPane3.setContent(allReservations);

        // Adding child to parent
        vBox0.getChildren().add(scrollPane3);
        Button button4 = new Button();
        button4.setText("Return to Choices");
        button4.setOnAction(event -> {
            mainStage.setScene(createAdminChoicesGUI());
        });
        button4.setMnemonicParsing(false);

        // Adding child to parent
        vBox0.getChildren().add(button4);

        mainStage.setResizable(false);
        return new Scene(vBox0, 800, 500);

    }

    /*
    Creates a reservation by taking in the values of every field.
    Checks if an attempt to create a reservation is invalid, and updates
    the invalid texts if so.
    @author Edison Cai 20241135
     */
    private static void createReservation(TextField textFieldRoomNum, Text invalidRoomNum, TextField textFieldName, Text invalidName, ChoiceBox<String> choiceBox8, Text invalidType, DatePicker datePickerStart,
                                          Text invalidStart, DatePicker datePickerEnd, Text invalidEnd, ScrollPane roomPickers, HBox allRoomPickers, Text successText) {
        Reservation reservationToBeAdded = null;
        String resName = null, resType = null;
        LocalDate checkInDate = null, checkOutDate = null;
        int numberOfRooms = 0;
        ArrayList<Room> rooms = new ArrayList<>();
        boolean validReservation = true;

        try {
            numberOfRooms = Integer.parseInt(textFieldRoomNum.getText());
        } catch (NumberFormatException e) {
            validReservation = false;
            invalidRoomNum.setVisible(true);
        }
        if (validator.inputIsName(textFieldName.getText())) {
            resName = textFieldName.getText();
            invalidName.setVisible(false);
        } else {
            validReservation = false;
            invalidName.setVisible(true);
        }

        if (!choiceBox8.getSelectionModel().isEmpty()) {
            if (choiceBox8.getSelectionModel().getSelectedItem().equals("S")) {
                resType = "S";
                invalidType.setVisible(false);
            } else if (choiceBox8.getSelectionModel().getSelectedItem().equals("AP")) {
                resType = "AP";
                invalidType.setVisible(false);
            } else {
                validReservation = false;
                invalidType.setVisible(true);
            }
        } else {
            validReservation = false;
            invalidType.setVisible(true);
        }

        if (datePickerStart.getValue() != null) {
            if (!datePickerStart.getValue().isBefore(LocalDate.now())) {
                checkInDate = datePickerStart.getValue();
                invalidStart.setVisible(false);
            } else {
                validReservation = false;
                invalidStart.setVisible(true);
            }
        } else {
            validReservation = false;
            invalidStart.setVisible(true);
        }


        if (datePickerEnd.getValue() != null) {
            if (datePickerEnd.getValue().compareTo(datePickerStart.getValue()) == 0 || datePickerEnd.getValue().compareTo(datePickerStart.getValue()) < 0) {
                validReservation = false;
                invalidEnd.setVisible(true);
            } else {
                checkOutDate = datePickerEnd.getValue();
                invalidEnd.setVisible(false);
            }
        } else {
            validReservation = false;
            invalidEnd.setVisible(true);
        }


        if (!validator.inputIsInteger(Integer.toString(numberOfRooms))) {
            validReservation = false;
            invalidRoomNum.setVisible(true);
        } else {
            invalidRoomNum.setVisible(false);
        }

        for(Node node : allRoomPickers.getChildren()){
            VBox vbox = (VBox) node;
            ChoiceBox<String> chooseHotel = (ChoiceBox<String>) vbox.getChildren().get(1);
            ChoiceBox<String> chooseRoom = (ChoiceBox<String>) vbox.getChildren().get(2);
            ChoiceBox<Integer> chooseOccupancy = (ChoiceBox<Integer>) vbox.getChildren().get(3);

            if(chooseHotel.getValue() != null && chooseRoom != null && chooseOccupancy.getValue() != null){
                rooms.add(new Room(chooseRoom.getValue(), chooseOccupancy.getValue()));
            }
            else{
                validReservation = false;
                rooms.clear();
                break;
            }
        }

        if (validReservation) {
            reservationToBeAdded = new Reservation(userInput.getNumberNeeded(), resName, resType, checkInDate, checkOutDate, numberOfRooms);
            reservationToBeAdded.setTotalCost(reservationToBeAdded.getTotalCost());
            reservationToBeAdded.setRooms(rooms);
            reservationToBeAdded.setTotalCost(reservationToBeAdded.getTotalCost());
            ReservationCancellationManager.addReservation(reservationToBeAdded);
            writer.writeReservation(ConstantReferences.RESERVATIONS, reservationToBeAdded);
            successText.setVisible(true);
        } else {
            successText.setVisible(false);
        }
    }

    /*
    Creates and returns a HBox containing all the choices for rooms.
     */
    private static HBox createRoomsPicker(int rooms) {
        HBox allRoomChoices = new HBox();

        if (rooms > 100) {
            return null;
        }

        // For every number of rooms, create a room picker.
        for (int i = 1; i <= rooms; i++) {
            VBox roomPicker = new VBox();

            Text roomText = new Text("Room " + i);


            ChoiceBox<String> chooseHotel = new ChoiceBox<String>();
            ChoiceBox<String> chooseRoom = new ChoiceBox<String>();
            ChoiceBox<Integer> chooseOccupancy = new ChoiceBox<Integer>();

            for (Hotel h : HotelInitialiser.getAllHotels()) {
                chooseHotel.getItems().add(h.getHotelType());
            }

            chooseHotel.getSelectionModel().selectedItemProperty()
                    .addListener((v, oldValue, newValue) -> {
                        chooseRoom.getItems().clear();
                        chooseOccupancy.getItems().clear();
                        for (TypeOfRoom room : HotelInitialiser.getHotel(newValue).getTypeOfRooms()) {
                            chooseRoom.getItems().add(room.getRoomType());
                        }
                    });

            chooseRoom.getSelectionModel().selectedItemProperty()
                    .addListener((v, oldValue, newValue) -> {
                        if(newValue != null){
                            TypeOfRoom selectedRoom = HotelInitialiser.getHotel(chooseHotel.getValue()).findTypeOfRoom(newValue);
                            chooseOccupancy.getItems().clear();
                            for(int j = selectedRoom.getOccuMin(); j <= selectedRoom.getOccuMax(); j++){
                                chooseOccupancy.getItems().add(j);
                            }
                        }
                    });

            roomPicker.getChildren().add(roomText);
            roomPicker.getChildren().add(chooseHotel);
            roomPicker.getChildren().add(chooseRoom);
            roomPicker.getChildren().add(chooseOccupancy);

            allRoomChoices.getChildren().add(roomPicker);
        }
        allRoomChoices.setSpacing(10.0);
        return allRoomChoices;
    }
}
