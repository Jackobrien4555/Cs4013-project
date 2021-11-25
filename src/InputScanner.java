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

    /**
     * Initialises the input scanner we are going to use to complete all the different requirements from our user.
     * @return Objects initialised.
     */
    public InputScanner() {
        sc = new Scanner(System.in);
        userValidator = new InputValidator();
    }

    /**
     * User can select a valid choice when selecting an option in the StartUp screen.
     * @return  The user's choice.
     */
    public int getStartUpChoice() {
        return getUserMenuChoice(3);
    }

    /**
     * User can select a valid choice when selecting an option in the Analytics screen.
     * @return  The user's choice.
     */
    public int getAnalyticsChoice() {
        return getUserMenuChoice(4);
    }

    /**
     * Takes the user through a bunch of reservation questions so a reservation instance can be completed.
     * @return  Completed reservation.
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
        while(!checkInDate.isEqual(LocalDate.now()) && !checkInDate.isAfter(LocalDate.now())) {
            System.out.print("You cannot reserve rooms for past dates, try again: ");
            checkInDate = getDate();
        }
        System.out.print("Enter a check out date: ");
        checkOutDate = getDate();
        while(checkOutDate.isEqual(checkInDate) && !checkOutDate.isBefore(checkInDate)) {
            System.out.print("You cannot set the check out date before or same day as the check in date, try again: ");
            checkOutDate = getDate();
        }
        System.out.print("Enter the number of rooms: ");
        numberOfRooms = getNumber();
        System.out.println("--------------------------------------------");
        rooms = new ArrayList<Room>();
        readRoom(rooms);

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
        return null;
    }

    /**
     * User can select a valid choice when selecting an option.
     * @param  exitValue The highest value the user can input for a certain menu.
     * @return  The user's choice.
     */
    public int getUserMenuChoice(int exitValue){
        return getInputInRange(1, exitValue);
    }

    /**
     * Checks the input value from the user so that it is a valid Number.
     * @return A valid number as Integer.
     */
    private int getNumber() {
        String input;
        int validNum;
        input = sc.nextLine();
        while(!userValidator.inputIsInteger(input)) {
            System.out.print("The input is not a number. Try again: ");
            input = sc.nextLine();
        }
        validNum = Integer.parseInt(input);
        return validNum;
    }

    /**
     * Checks the input value from the user so that it is a valid number in the range given.
     * @param minValueOfRange Minimum value of the range.
     * @param maxValueOfRange Maximum value of the range.
     * @return The choice the user has made inside the range we instructed it to.
     */
    private int getInputInRange(int minValueOfRange, int maxValueOfRange) {
        String choice;

        choice = sc.nextLine();
        while(!userValidator.inputIsInRange(choice, minValueOfRange, maxValueOfRange)) {
            System.out.print("The input is invalid. Enter a new value from the range " + minValueOfRange + " to " + maxValueOfRange + ": ");
            choice = sc.nextLine();
        }
        return Integer.parseInt(choice);
    }

    /**
     * Checks the input value from the user so that it is a valid Reservation Type.
     * @return A valid reservation type as a String.
     */
    private String getReservationType() {
        String input;
        input = sc.nextLine();
        while(!userValidator.isValidReservationType(input)) {
            System.out.print("The input type is invalid. Enter a new value S (Standard) or AP (Advanced Purchase): ");
            input = sc.nextLine();
        }
        return input;
    }

    /**
     * Checks the input value from the user so that it is a valid Occupancy.
     * @return A valid occupancy in the format required.
     */
    private int getOccupancy() {
        String input;
        input = sc.nextLine();
        while(!userValidator.isValidOccupancy(input)) {
            System.out.print("The input is invalid. Enter a new value in the format number+number. Try again: ");
            input = sc.nextLine();
        }
        return Integer.parseInt(input);
    }

    private Reservation getReservationFromUserReservationNumber(Reader rReader) {
        Reservation chosenReservation;

        //int
        return null;
    }

    /**
     * Checks the input value from the user so that it is a valid Date.
     * @return A valid date as a LocalDate.
     */
    private LocalDate getDate() {
        String input;
        input = sc.nextLine();
        LocalDate date;
        while(!userValidator.inputIsDate(input)) {
            System.out.print("The date input is invalid. Enter a new value in the format YYYY-MM-DD. Try again: ");
            input = sc.nextLine();
        }
        date = LocalDate.parse(input);
        return date;
    }

    /**
     * Instructs the user on completing different rooms and reooms details.
     * @param rooms The rooms arraylist given to us.
     * @return The completed Rooms arraylist with all the details given by the user.
     */
    private void readRoom(ArrayList<Room> rooms) {
        int roomNum;
        String typeOfRoom;
        int occupancy;

        System.out.println("\n------- REQUESTING ROOM INFO -------");
        for(int i = 0; i < rooms.size(); i++) {
            System.out.print("Enter room number: ");
            roomNum = getNumber();
            System.out.print("Enter room type: ");
            typeOfRoom = sc.nextLine();
            System.out.print("Enter occupancy total (adults + children): ");
            occupancy = getOccupancy();

            rooms.add(new Room(typeOfRoom, occupancy, roomNum));
        }
        System.out.print("--------------------------------------");
    }
}
