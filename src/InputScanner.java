/**
 * This class uses InputValidator and allows users to actually input their choices
 * using the Scanner utility. Also allows them to re-enter until they reach a choice that is acceptable.
 *
 * @author 20238029 Sergiu Mereacre
 * @since 15/11/2021
 */

import java.time.LocalDate;
import java.util.*;
import java.util.Scanner;

public class InputScanner {
    private Scanner sc;
    private InputValidator userValidator;
    private ConstantReferences references;

    /**
     * Initialises the input scanner we are going to use to complete all the different requirements from our user.
     *
     * @return Objects initialised.
     */
    public InputScanner() {
        sc = new Scanner(System.in);
        userValidator = new InputValidator();
    }

    /**
     * User can select a valid choice when selecting an option in the StartUp screen.
     *
     * @return The user's choice.
     */
    public int getStartUpChoice() {
        return getUserMenuChoice(references.EXIT_STARTUP);
    }

    /**
     * User can select a valid choice when selecting an option in the Analytics screen.
     *
     * @return The user's choice.
     */
    public int getAnalyticsChoice() {
        return getUserMenuChoice(references.EXIT_ANALYTICAL);
    }

    /**
     * Takes the user through a bunch of reservation questions so a reservation instance can be completed.
     *
     * @return Completed reservation.
     */
    public Reservation readReservation() {
        Reservation reservation;
        int resNumber;
        String resName, resType;
        LocalDate checkInDate, checkOutDate;
        int numberOfRooms;
        ArrayList<Room> rooms;
        double totalCost;

        System.out.println("\n--------- RESERVATION INFORMATION ---------");
        System.out.print("Enter the reservation number: ");
        resNumber = getNumber();
        System.out.print("Enter the reservation name: ");
        resName = sc.nextLine();
        System.out.print("Enter the reservation type (S or AP): ");
        resType = getReservationType();
        System.out.print("Enter a check in date: ");
        checkInDate = getDate();
        while (checkInDate.isBefore(LocalDate.now())) {
            System.out.print("You cannot reserve rooms for past dates, try again: ");
            checkInDate = getDate();
        }
        System.out.print("Enter a check out date: ");
        checkOutDate = getDate();
        while (checkOutDate.compareTo(checkInDate) == 0 || checkOutDate.compareTo(checkInDate) < 0) {
            System.out.print("You cannot set the check out date before or same day as the check in date, try again: ");
            checkOutDate = getDate();
        }
        System.out.print("Enter the number of rooms: ");
        numberOfRooms = getNumber();
        while (numberOfRooms == 0 || numberOfRooms < 0) {
            System.out.print("You cannot set the number of rooms to 0 or less than 0, try again: ");
            numberOfRooms = getNumber();
        }
        System.out.print("--------------------------------------------");
        rooms = new ArrayList<Room>();
        readRoom(rooms, numberOfRooms);

        reservation = new Reservation(resNumber, resName, resType, checkInDate, checkOutDate, numberOfRooms);
        reservation.setRooms(rooms);
        return reservation;
    }

    public Cancellation readValidCancellation(Reader rReader) {
        Cancellation userInputCancellation;
        Reservation chosenReservation;
        System.out.println("\n------ REQUESTING CANCELLATION INFORMATION ------");
        System.out.println("Enter the reservation number for the reservation you are cancelling.");
        System.out.println("-------------------------------------------------");
        System.out.print("Reservation number: ");
        chosenReservation = getReservationFromUserReservationNumber();
        userInputCancellation = new Cancellation(chosenReservation);
        return userInputCancellation;
    }

    /**
     * User can select a valid choice when selecting an option.
     *
     * @param exitValue The highest value the user can input for a certain menu.
     * @return The user's choice.
     */
    public int getUserMenuChoice(int exitValue) {
        return getInputInRange(1, exitValue);
    }

    /**
     * Checks the input value from the user so that it is a valid Number.
     *
     * @return A valid number as Integer.
     */
    private int getNumber() {
        String input;
        int validNum;
        input = sc.nextLine();
        while (!userValidator.inputIsInteger(input)) {
            System.out.print("The input is not a valid number. Try again: ");
            input = sc.nextLine();
        }
        validNum = Integer.parseInt(input);
        return validNum;
    }

    /**
     * Checks the input value from the user so that it is a valid number in the range given.
     *
     * @param minValueOfRange Minimum value of the range.
     * @param maxValueOfRange Maximum value of the range.
     * @return The choice the user has made inside the range we instructed it to.
     */
    private int getInputInRange(int minValueOfRange, int maxValueOfRange) {
        String choice;

        choice = sc.nextLine();
        while (!userValidator.inputIsInRange(choice, minValueOfRange, maxValueOfRange)) {
            System.out.print("The input is invalid. Enter a new value from the range " + minValueOfRange + " to " + maxValueOfRange + ": ");
            choice = sc.nextLine();
        }
        return Integer.parseInt(choice);
    }

    /**
     * Checks the input value from the user so that it is a valid Reservation Type.
     *
     * @return A valid reservation type as a String.
     */
    private String getReservationType() {
        String input;
        input = sc.nextLine();
        while (!userValidator.isValidReservationType(input)) {
            System.out.print("The input type is invalid. Enter a new value S (Standard) or AP (Advanced Purchase): ");
            input = sc.nextLine();
        }
        input = input.toUpperCase();
        return input;
    }

    /**
     * Asks for user input and returns the corresponding Reservation.
     *
     * @return
     */
    private Reservation getReservationFromUserReservationNumber() {
        Reservation chosenReservation;

        int resNumber = getReservationNumber();
        chosenReservation = Reader.getReservation(resNumber);

        return chosenReservation;
    }

    /**
     * Checks to see if the reservation number exists
     *
     * @return
     */
    private int getReservationNumber() {
        int resNumber = getNumber();
        while (Reader.getReservation(resNumber) == null) {
            System.out.print("Reservation you inputted does not exist. Try again: ");
            resNumber = getNumber();
        }
        return resNumber;
    }

    /**
     * Checks the input value from the user so that it is a valid Date.
     *
     * @return A valid date as a LocalDate.
     */
    public LocalDate getDate() {
        String input;
        input = sc.nextLine();
        LocalDate date;
        while (!userValidator.inputIsDate(input)) {
            System.out.print("The date input is invalid. Enter a new value in the format YYYY-MM-DD. Try again: ");
            input = sc.nextLine();
        }
        date = LocalDate.parse(input);
        return date;
    }

    public boolean getYesOrNo() {
        String input;
        input = sc.nextLine();
        boolean result = false;
        while (!userValidator.inputIsYesOrNo(input)) {
            System.out.print("The input is invalid. Please enter 'Y' or 'N': ");
            input = sc.nextLine();
        }
        if (input.equalsIgnoreCase("Y")) {
            result = true;
        }

        return result;
    }

    /**
     * Instructs the user on completing different rooms and rooms details.
     *
     * @param rooms The rooms arraylist given to us.
     * @return The completed Rooms arraylist with all the details given by the user.
     */
    private void readRoom(ArrayList<Room> rooms, int numberOfRooms) {
        String typeOfRoom;
        int occupancy;

        System.out.println("\n----------- REQUESTING ROOM INFO -----------");
        for (int i = 0; i < numberOfRooms; i++) {
            System.out.print("Enter room type: ");
            typeOfRoom = sc.nextLine();
            while (!userValidator.isValidRoomType(typeOfRoom)) {
                System.out.print("This is not a valid input, these are some options: Deluxe Double, Deluxe Twin, Deluxe Single, Deluxe Family,\n");
                System.out.print("Executive Double, Executive Twin, Executive Single, Classic Double, Classic Twin, Classic Single.");
                System.out.print("\nPlease enter your choice: ");
                typeOfRoom = sc.nextLine();
            }
            System.out.print("Enter occupancy total: ");
            occupancy = getNumber();

            rooms.add(new Room(typeOfRoom, occupancy));
        }
        System.out.print("----------------------------------------------");
    }
}
