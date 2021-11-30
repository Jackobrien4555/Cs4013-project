import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
 * @author Jack O'Brien
 * @author Sergiu Mereacre
 */
public class HotelGUI extends Application {
    private static final Stage mainStage = new Stage();
    private static boolean isAdmin = false;
    private static InputScanner userInput;
    private static InputValidator validator;
    private static Writer writer;

    public static void main(String[] args) {
        SystemMain.checkReservationsFlag(args);
        SystemMain.checkCancellationsFlag(args);
        SystemMain.checkHotelsFlag(args);
        SystemMain.checkAdminsFlag(args);

        userInput = new InputScanner();
        writer = new Writer();
        validator = new InputValidator();

        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Reader.readReservations(ConstantReferences.RESERVATIONS);
        Reader.readCancellations(ConstantReferences.CANCELLATIONS);
        HotelInitialiser.initialise(HotelInitialiser.getFileCells(ConstantReferences.HOTELS));
        Image icon = new Image("file:resources/icon.png");
        mainStage.getIcons().add(icon);
        mainStage.setTitle("Hotel Login");
        mainStage.setScene(createLogin());
        mainStage.setResizable(false);
        mainStage.show();
    }

    /*
     * Creates a screen that asks if user or admin login
     */
    private static Scene createLogin() {
        mainStage.setTitle("Hotel Login");
        VBox vBox0 = new VBox();
        VBox vBox1 = new VBox();
        vBox1.setAlignment(Pos.CENTER);
        VBox.setMargin(vBox1, new Insets(80, 0, 0, 0));
        Text text2 = new Text();
        text2.setStrokeWidth(0.0);
        text2.setText("Are you accessing the Reservation System as a User or an Admin?");
        text2.setStyle("-fx-font: 16 arial;");

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

        // Adding child to parent
        vBox3.getChildren().add(button4);
        Button button5 = new Button();
        button5.setContentDisplay(ContentDisplay.CENTER);
        button5.setText("Admin");
        button5.setAlignment(Pos.CENTER);
        button5.setOnAction(event -> {
            isAdmin = true;
            mainStage.setScene(createUsernamePasswordGUI());
        });
        vBox3.getChildren().add(button5);


        // Adding child to parent
        vBox0.getChildren().add(vBox3);

        //return new Scene(vBox0, 200, 100);
        return new Scene(vBox0, 600, 300);

    }

    /*
     * Creates admin screen with multiple options
     */
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
        text2.setStyle("-fx-font: 24 arial;");

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
        button4.setOnAction(event -> mainStage.setScene(createMakeReservationsGUI()));
        // Adding child to parent
        vBox3.getChildren().add(button4);

        Button button5 = new Button();
        button5.setLayoutX(10.0);
        button5.setMinWidth(150.0);
        button5.setLayoutY(35.0);
        button5.setText("Cancel Reservation");
        button5.setOnAction(event -> mainStage.setScene(createCancellationsGUI()));
        // Adding child to parent
        vBox3.getChildren().add(button5);

        Button button7 = new Button();
        button7.setLayoutX(10.0);
        button7.setMinWidth(150.0);
        button7.setLayoutY(60.0);
        button7.setText("Show all Reservations");
        button7.setOnAction(event -> mainStage.setScene(createShowReservationsGUI()));
        // Adding child to parent
        vBox3.getChildren().add(button7);

        Button button8 = new Button();
        button8.setLayoutX(10.0);
        button8.setMinWidth(150.0);
        button8.setLayoutY(60.0);
        button8.setText("Show all Cancellations");
        button8.setOnAction(event -> mainStage.setScene(createShowCancellationsGUI()));
        // Adding child to parent
        vBox3.getChildren().add(button8);

        Button buttonDataAnalysis = new Button();
        buttonDataAnalysis.setLayoutX(10.0);
        buttonDataAnalysis.setMinWidth(150.0);
        buttonDataAnalysis.setLayoutY(60.0);
        buttonDataAnalysis.setText("Show Data Analysis");
        buttonDataAnalysis.setOnAction(event -> mainStage.setScene(createDataAnalysisGUI()));
        // Adding child to parent
        vBox3.getChildren().add(buttonDataAnalysis);

        Button button6 = new Button();
        button6.setLayoutX(10.0);
        button6.setMinWidth(150.0);
        button6.setLayoutY(60.0);
        button6.setText("Return to Login");
        button6.setOnAction(event -> mainStage.setScene(createLogin()));
        // Adding child to parent
        vBox3.getChildren().add(button6);

        // Adding child to parent
        vBox0.getChildren().add(vBox3);

        return new Scene(vBox0, 690, 500);
    }

    /*
     * Admin log in screen where username and password is required
     */
    private static Scene createUsernamePasswordGUI() {
        StackPane root = new StackPane();
        VBox vBox = new VBox();

        vBox.setSpacing(8);
        vBox.setPadding(new Insets(10, 10, 10, 10));

        // Making text fields.
        TextField textFieldUsername = new TextField();
        PasswordField textFieldPassword = new PasswordField();

        Text invalidLogin = new Text("Invalid login.");
        invalidLogin.setVisible(false);

        Button buttonLogin = new Button();
        buttonLogin.setContentDisplay(ContentDisplay.CENTER);
        buttonLogin.setText("Login");
        buttonLogin.setAlignment(Pos.CENTER);
        buttonLogin.setOnAction(event -> {


            if (validator.inputIsUsername(textFieldUsername.getText())) {
                User user = userInput.getUser(textFieldUsername.getText());

                if (textFieldPassword.getText().equalsIgnoreCase(user.getPassword())) {
                    mainStage.setScene(createAdminChoicesGUI());
                } else {
                    invalidLogin.setVisible(true);
                }

            } else {
                invalidLogin.setVisible(true);
            }
        });

        Button buttonReturn = new Button();
        buttonReturn.setContentDisplay(ContentDisplay.CENTER);
        buttonReturn.setText("Return");
        buttonReturn.setAlignment(Pos.CENTER);
        buttonReturn.setOnAction(event -> mainStage.setScene(createLogin()));

        vBox.getChildren().addAll(
                new Label("Username"),
                textFieldUsername,
                new Label("Password"),
                textFieldPassword,
                invalidLogin,
                buttonLogin,
                buttonReturn);
        root.getChildren().addAll(vBox);


        Scene scene = new Scene(root, 400, 250);
        mainStage.setTitle("Login");

        return scene;
    }

    /*
     * Once logged in as User displays user screen with multiple options
     */
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
        VBox.setMargin(text2, new Insets(20, 0, 0, 0));
        text2.setStyle("-fx-font: 18 arial;");

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
        button4.setOnAction(event -> mainStage.setScene(createMakeReservationsGUI()));

        // Adding child to parent
        vBox3.getChildren().add(button4);
        Button button5 = new Button();
        button5.setLayoutX(10.0);
        button5.setMinWidth(200.0);
        button5.setLayoutY(35.0);
        button5.setText("Cancel Reservation");
        button5.setOnAction(event -> mainStage.setScene(createCancellationsGUI()));

        // Adding child to parent
        vBox3.getChildren().add(button5);
        Button button6 = new Button();
        button6.setLayoutX(10.0);
        button6.setMinWidth(200.0);
        button6.setLayoutY(60.0);
        button6.setText("Return to Login");
        button6.setOnAction(event -> mainStage.setScene(createLogin()));

        // Adding child to parent
        vBox3.getChildren().add(button6);

        // Adding child to parent
        vBox0.getChildren().add(vBox3);
        return new Scene(vBox0, 300, 300);
    }

    /*
     * Screen where you make cancellations
     */
    private static Scene createCancellationsGUI() {
        mainStage.setTitle("Cancel Reservations");
        VBox vBox0 = new VBox();

        vBox0.setSpacing(0);
        vBox0.setAlignment(Pos.CENTER);
        VBox vBox1 = new VBox();
        vBox1.setAlignment(Pos.CENTER);
        Text text2 = new Text();
        text2.setText("Cancel a Reservation");
        text2.setStyle("-fx-font: 20 arial;");
        VBox vBox2 = new VBox();
        vBox2.setAlignment(Pos.CENTER);
        VBox vBox3 = new VBox();
        vBox3.setAlignment(Pos.CENTER);

        // Adding child to parent
        vBox1.getChildren().add(text2);

        // Adding child to parent
        vBox0.getChildren().add(vBox1);

        HBox hBox3 = new HBox();
        hBox3.setPrefHeight(49.0);
        hBox3.setSpacing(30);
        hBox3.setPrefWidth(600.0);
        hBox3.setAlignment(Pos.CENTER);
        Text text4 = new Text();
        text4.setStrokeWidth(0.0);
        text4.setStrokeType(StrokeType.OUTSIDE);
        text4.setText("Reservation Number:");

        // Adding child to parent
        hBox3.getChildren().add(text4);
        TextField textField5 = new TextField();
        textField5.setPromptText("e.g. 120584");
        Text invalidNum = new Text("Not a valid reservation number.");
        invalidNum.setVisible(false);
        Text success = new Text("Successfully cancelled!");
        success.setVisible(false);

        // Adding child to parent
        hBox3.getChildren().add(textField5);
        vBox3.getChildren().add(invalidNum);

        // Adding child to parent
        vBox0.getChildren().add(hBox3);
        vBox0.getChildren().add(vBox3);
        vBox0.getChildren().add(vBox2);
        vBox3.getChildren().add(success);

        Button button21 = new Button();
        button21.setText("Cancel Reservation");

        // Adding child to parent
        vBox2.getChildren().add(button21);
        Button button22 = new Button();
        button22.setText("Return to Choices");
        button22.setOnAction(event -> {
            if (isAdmin) {
                mainStage.setScene(createAdminChoicesGUI());
            } else {
                mainStage.setScene(createUserChoicesGUI());
            }
        });

        // Adding child to parent
        vBox2.getChildren().add(button22);

        button21.setOnAction(event -> {
            int resNumber = 0;


            try {
                resNumber = Integer.parseInt(textField5.getText());
            } catch (NumberFormatException e) {
                invalidNum.setVisible(true);
            }


            Reservation chosenReservation = ReservationCancellationManager.getReservation(resNumber);


            if (chosenReservation != null) {
                Cancellation cancellation = new Cancellation(chosenReservation);
                ReservationCancellationManager.addCancellation(cancellation);
                int resNum = cancellation.getReservation().getResNumber();
                writer.writeCancellation(ConstantReferences.CANCELLATIONS, cancellation);
                ArrayList<Reservation> reservations = ReservationCancellationManager.getAllReservations();
                reservations.remove(ReservationCancellationManager.getReservation(resNum));
                ReservationCancellationManager.setAllReservations(reservations);
                writer.writeReservations(ConstantReferences.RESERVATIONS, reservations);
                invalidNum.setVisible(false);
                success.setVisible(true);
            } else {
                success.setVisible(false);
                invalidNum.setVisible(true);
            }
        });

        vBox2.setSpacing(10);
        VBox.setMargin(vBox1, new Insets(0, 0, 25, 0));
        VBox.setMargin(vBox2, new Insets(25, 0, 0, 0));
        VBox.setMargin(vBox3, new Insets(10, 0, 10, 0));

        return new Scene(vBox0, 600, 300);
    }

    /*
    *Screen where Data analysis is displayed
    */
    private static Scene createDataAnalysisGUI() {
        mainStage.setTitle("Data Analysis");

        VBox vBox0 = new VBox();
        vBox0.setPadding(new Insets(20, 50, 20, 50));

        vBox0.setSpacing(10.0);
        vBox0.setAlignment(Pos.CENTER);
        VBox vBox1 = new VBox();
        vBox1.setAlignment(Pos.CENTER);
        Text text2 = new Text();
        text2.setText("Data Analysis");
        text2.setStyle("-fx-font: 24 arial;");

        // Adding child to parent
        vBox1.getChildren().add(text2);
        // Adding child to parent
        vBox0.getChildren().add(vBox1);
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().add("Hotel Occupancy Analytics");
        choiceBox.getItems().add("Financial Analytics");
        choiceBox.setValue("Hotel Occupancy Analytics");
        vBox0.getChildren().add(choiceBox);


        // Adding child to parent
        //vBox0.getChildren().add(jComboBox);
        Text text4 = new Text();
        text4.setText("Do you want to show rooms that aren't booked?");
        text4.setStyle("-fx-font: 20 arial;");

        // Adding child to parent
        vBox0.getChildren().add(text4);
        HBox hBox5 = new HBox();
        hBox5.setSpacing(5.0);
        hBox5.setAlignment(Pos.CENTER);

        // Adding child to parent
        vBox0.getChildren().add(hBox5);
        ChoiceBox<String> yesOrNo = new ChoiceBox<>();
        yesOrNo.setValue("Yes");
        yesOrNo.getItems().add("Yes");
        yesOrNo.getItems().add("No");

        vBox0.getChildren().add(yesOrNo);

        Text textStart = new Text();
        textStart.setText("Start Date:");

        // Adding child to parent
        vBox0.getChildren().add(textStart);
        DatePicker datePickerStart = new DatePicker();

        // Adding child to parent
        vBox0.getChildren().add(datePickerStart);

        Text invalidStart = new Text("Invalid start date.");
        invalidStart.setVisible(false);
        vBox0.getChildren().add(invalidStart);

        Text textEnd = new Text();
        textEnd.setText("End Date:");

        // Adding child to parent
        vBox0.getChildren().add(textEnd);
        DatePicker datePickerEnd = new DatePicker();

        // Adding child to parent
        vBox0.getChildren().add(datePickerEnd);

        Text invalidEnd = new Text("Invalid end date.");
        invalidEnd.setVisible(false);
        vBox0.getChildren().add(invalidEnd);

        HBox hBox8 = new HBox();
        hBox8.setSpacing(30.0);
        hBox8.setAlignment(Pos.CENTER);
        Text text9 = new Text();

        // Adding child to parent
        hBox8.getChildren().add(text9);

        // Adding child to parent
        vBox0.getChildren().add(hBox8);
        HBox hBox10 = new HBox();
        hBox10.setSpacing(30.0);
        hBox10.setAlignment(Pos.CENTER);

        // Adding child to parent
        vBox0.getChildren().add(hBox10);
        ScrollPane scrollPane11 = new ScrollPane();

        // Adding child to parent
        vBox0.getChildren().add(scrollPane11);

        Button buttonStartAnalysis = new Button();
        buttonStartAnalysis.setText("Start Analysis");

        buttonStartAnalysis.setOnAction(event -> showValidAnalysis(choiceBox, yesOrNo, datePickerStart,
                invalidStart, datePickerEnd, invalidEnd, scrollPane11));

        // Adding child to parent
        vBox0.getChildren().add(buttonStartAnalysis);

        Button buttonReturn = new Button();
        buttonReturn.setText("Return");

        buttonReturn.setOnAction(event -> mainStage.setScene(createAdminChoicesGUI()));

        // Adding child to parent
        vBox0.getChildren().add(buttonReturn);

        return new Scene(vBox0, 1200, 650);

    }

    /*
     *Screen for making a reservation
     */
    private static Scene createMakeReservationsGUI() {
        mainStage.setTitle("Making a reservation.");

        VBox vBox0 = new VBox();

        vBox0.setSpacing(20.0);
        vBox0.setAlignment(Pos.CENTER);
        VBox vBox1 = new VBox();
        vBox1.setAlignment(Pos.CENTER);
        Text text2 = new Text();
        text2.setText("Book a Reservation");
        text2.setStyle("-fx-font: 24 arial;");

        // Adding child to parent
        vBox1.getChildren().add(text2);

        // Adding child to parent
        vBox0.getChildren().add(vBox1);

        ////////////////////
        HBox hBoxNum = new HBox();
        hBoxNum.setSpacing(20.0);
        hBoxNum.setAlignment(Pos.CENTER_LEFT);
        VBox.setMargin(hBoxNum, new Insets(0, 0, 0, 400));

        Text textNum = new Text();
        textNum.setText("Reservation Number:");

        // Adding child to parent
        hBoxNum.getChildren().add(textNum);
        int numberNeeded = userInput.getNumberNeeded();
        TextField textFieldNum = new TextField(numberNeeded + "");
        textFieldNum.setEditable(false);


        // Adding child to parent
        hBoxNum.getChildren().add(textFieldNum);

        vBox0.getChildren().add(hBoxNum);
        /////////////

        HBox hBox3 = new HBox();
        hBox3.setSpacing(20.0);
        hBox3.setAlignment(Pos.CENTER_LEFT);
        VBox.setMargin(hBox3, new Insets(0, 0, 0, 400));


        Text text4 = new Text();
        text4.setText("Reservation Name:");

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
        hBox6.setSpacing(20.0);
        hBox6.setAlignment(Pos.CENTER_LEFT);
        VBox.setMargin(hBox6, new Insets(0, 0, 0, 400));
        Text text7 = new Text();
        text7.setText("Reservation Type:");

        // Adding child to parent
        hBox6.getChildren().add(text7);
        ChoiceBox<String> choiceBox8 = new ChoiceBox<>();
        choiceBox8.getItems().add("Standard");
        choiceBox8.getItems().add("Advanced Purchase");
        choiceBox8.setPrefWidth(150.0);

        // Adding child to parent
        hBox6.getChildren().add(choiceBox8);

        Text invalidType = new Text("Please choose reservation type.");
        invalidType.setVisible(false);
        hBox6.getChildren().add(invalidType);

        // Adding child to parent
        vBox0.getChildren().add(hBox6);
        HBox hBox9 = new HBox();
        hBox9.setSpacing(20.0);
        hBox9.setAlignment(Pos.CENTER_LEFT);
        VBox.setMargin(hBox9, new Insets(0, 0, 0, 400));
        Text text10 = new Text();
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

        // Adding child to parent
        hBox9.getChildren().add(text12);

        // Adding child to parent
        vBox0.getChildren().add(hBox9);
        HBox hBox13 = new HBox();
        hBox13.setSpacing(20.0);
        hBox13.setAlignment(Pos.CENTER_LEFT);
        VBox.setMargin(hBox13, new Insets(0, 0, 0, 400));
        Text text14 = new Text();
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

        // Adding child to parent
        hBox13.getChildren().add(text16);

        // Adding child to parent
        vBox0.getChildren().add(hBox13);
        HBox hBox17 = new HBox();
        hBox17.setSpacing(20.0);
        hBox17.setAlignment(Pos.CENTER_LEFT);
        VBox.setMargin(hBox17, new Insets(0, 0, 0, 400));
        Text text18 = new Text();
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
        scrollPane20.setPrefHeight(175);
        scrollPane20.setPrefWidth(700);
//        scrollPane20.setFitToHeight(true);
//        scrollPane20.setFitToWidth(true);

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

        VBox vBoxButtons = new VBox();
        vBoxButtons.setAlignment(Pos.CENTER);
        vBoxButtons.setSpacing(10.0);

        Button button21 = new Button();
        button21.setText("Book Reservation");

        button21.setOnAction(event -> createReservation(textFieldNum, textFieldRoomNum, invalidRoomNum,
                textFieldName, invalidName, choiceBox8, invalidType, datePickerStart,
                invalidStart, datePickerEnd, invalidEnd, (HBox) scrollPane20.getContent(), successText));

        // Adding child to parent
        vBoxButtons.getChildren().add(button21);
        Button button22 = new Button();
        button22.setText("Return to Choices");
        button22.setOnAction(event -> {
            if (isAdmin) {
                mainStage.setScene(createAdminChoicesGUI());
            } else {
                mainStage.setScene(createUserChoicesGUI());
            }
        });

        // Adding child to parent
        vBoxButtons.getChildren().add(button22);

        vBox0.getChildren().add(vBoxButtons);

        return new Scene(vBox0, 1200, 650);
    }

    /*
     *Screen for showing previous reservations
     */
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
        text2.setStyle("-fx-font: 20 arial;");

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
            Text reservationText = new Text(reservation.reservationFormat() + "\n");
            allReservations.getChildren().add(reservationText);
        }

        scrollPane3.setContent(allReservations);

        // Adding child to parent
        vBox0.getChildren().add(scrollPane3);
        VBox.setMargin(scrollPane3, new Insets(0, 30, 40, 30));
        Button button4 = new Button();
        button4.setText("Return to Choices");
        button4.setOnAction(event -> mainStage.setScene(createAdminChoicesGUI()));

        // Adding child to parent
        vBox0.getChildren().add(button4);
//        mainStage.setResizable(false);
        return new Scene(vBox0, 800, 500);
    }

    /*
     *Screen for showing previous cancellations
     */
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
        text2.setStyle("-fx-font: 20 arial;");

        // Adding child to parent
        vBox1.getChildren().add(text2);

        // Adding child to parent
        vBox0.getChildren().add(vBox1);
        ScrollPane scrollPane3 = new ScrollPane();
        scrollPane3.setPrefHeight(300.0);
        scrollPane3.setPrefWidth(200.0);
        VBox.setMargin(scrollPane3, new Insets(0, 30, 40, 30));

        VBox allReservations = new VBox();
        allReservations.setSpacing(30);

        ArrayList<Cancellation> cancellations = ReservationCancellationManager.getAllCancellations();
        for (Cancellation cancellation : cancellations) {
            Text cancellationText = new Text(cancellation.cancellationFormat());
            allReservations.getChildren().add(cancellationText);
        }

        scrollPane3.setContent(allReservations);

        // Adding child to parent
        vBox0.getChildren().add(scrollPane3);
        Button button4 = new Button();
        button4.setText("Return to Choices");
        button4.setOnAction(event -> mainStage.setScene(createAdminChoicesGUI()));

        // Adding child to parent
        vBox0.getChildren().add(button4);

//        mainStage.setResizable(false);
        return new Scene(vBox0, 800, 500);

    }

    /*
    Creates a reservation by taking in the values of every field.
    Checks if an attempt to create a reservation is invalid, and updates
    the invalid texts if so.
     */
    private static void createReservation(TextField textFieldNum, TextField textFieldRoomNum, Text invalidRoomNum, TextField textFieldName, Text invalidName, ChoiceBox<String> choiceBox8, Text invalidType, DatePicker datePickerStart,
                                          Text invalidStart, DatePicker datePickerEnd, Text invalidEnd, HBox allRoomPickers, Text successText) {
        Reservation reservationToBeAdded;
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
            if (choiceBox8.getSelectionModel().getSelectedItem().equals("Standard")) {
                resType = "S";
                invalidType.setVisible(false);
            } else if (choiceBox8.getSelectionModel().getSelectedItem().equals("Advanced Purchase")) {
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

        for (Node node : allRoomPickers.getChildren()) {
            VBox vbox = (VBox) node;
            ChoiceBox<String> chooseHotel = (ChoiceBox<String>) vbox.getChildren().get(1);
            ChoiceBox<String> chooseRoom = (ChoiceBox<String>) vbox.getChildren().get(2);
            ChoiceBox<Integer> chooseOccupancy = (ChoiceBox<Integer>) vbox.getChildren().get(3);

            if (chooseHotel.getValue() != null && chooseRoom != null && chooseOccupancy.getValue() != null) {
                rooms.add(new Room(chooseRoom.getValue(), chooseOccupancy.getValue()));
            } else {
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
            textFieldNum.setText(Integer.toString(userInput.getNumberNeeded()));
        } else {
            successText.setVisible(false);
        }
    }

    /*
    Creates and returns a HBox containing all the choices for rooms.
     */
    private static HBox createRoomsPicker(int rooms) {
        HBox allRoomChoices = new HBox();
        allRoomChoices.setPadding(new Insets(20, 50, 0, 50));

        if (rooms > 100) {
            return null;
        }

        // For every number of rooms, create a room picker.
        for (int i = 1; i <= rooms; i++) {
            VBox roomPicker = new VBox();
            roomPicker.setSpacing(10);

            Text roomText = new Text("Room " + i);


            ChoiceBox<String> chooseHotel = new ChoiceBox<>();
            ChoiceBox<String> chooseRoom = new ChoiceBox<>();
            ChoiceBox<Integer> chooseOccupancy = new ChoiceBox<>();

            chooseHotel.setPrefWidth(130);
            chooseRoom.setPrefWidth(130);
            chooseOccupancy.setPrefWidth(130);

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
                        if (newValue != null) {
                            TypeOfRoom selectedRoom = HotelInitialiser.getHotel(chooseHotel.getValue()).findTypeOfRoom(newValue);
                            chooseOccupancy.getItems().clear();
                            for (int j = selectedRoom.getOccuMin(); j <= selectedRoom.getOccuMax(); j++) {
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

    /*
    Check if the values are valid for data analysis.
     */
    private static void showValidAnalysis(ChoiceBox<String> choiceBox, ChoiceBox<String> yesOrNo, DatePicker datePickerStart, Text invalidStart, DatePicker datePickerEnd, Text invalidEnd, ScrollPane scrollPane11) {
        LocalDate startDate = null, endDate = null;
        boolean showUnoccupiedRooms = false;
        boolean validAnalysis = true;
        VBox analysisResults = new VBox();

        if (yesOrNo.getSelectionModel().getSelectedItem().equals("Yes")) {
            showUnoccupiedRooms = true;
        }

        if (datePickerStart.getValue() != null) {
            startDate = datePickerStart.getValue();
            invalidStart.setVisible(false);
        } else {
            validAnalysis = false;
            invalidStart.setVisible(true);
        }

        if (datePickerEnd.getValue() != null && startDate != null) {
            if (startDate.compareTo(datePickerEnd.getValue()) > 0) {
                validAnalysis = false;
                invalidEnd.setVisible(true);
            } else {
                endDate = datePickerEnd.getValue();
                invalidEnd.setVisible(false);
            }
        } else {
            validAnalysis = false;
            invalidEnd.setVisible(true);
        }

        if (validAnalysis) {
            if (choiceBox.getSelectionModel().getSelectedItem().equals("Hotel Occupancy Analytics")) {
                ArrayList<String> results = DataAnalysis.getOccupancyRatesAll(ReservationCancellationManager.getAllReservations(), startDate, endDate,
                        showUnoccupiedRooms);
                for (String s : results) {
                    Text text = new Text(s);
                    analysisResults.getChildren().add(text);
                }
                scrollPane11.setContent(analysisResults);
            } else {
                ArrayList<String> results = DataAnalysis.calculateIncomeAll(ReservationCancellationManager.getAllReservations(),
                        ReservationCancellationManager.getAllCancellations(), startDate, endDate,
                        showUnoccupiedRooms);
                for (String s : results) {
                    Text text = new Text(s);
                    analysisResults.getChildren().add(text);
                }
                scrollPane11.setContent(analysisResults);
            }
        } else {
            scrollPane11.setContent(null);
        }


    }
}
