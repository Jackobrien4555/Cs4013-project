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

    // Initialises the InputScanner Object.
    public InputScanner() {
        sc = new Scanner(System.in);
        userValidator = new InputValidator();
    }

    // Lets the user enter a choice on startup screen.
    public int getStartUpChoice() {
        return getUserMenuChoice(5);
    }

    // Lets the user enter a choice on analytics screen.
    public int getAnalyticsChoice() {
        return getUserMenuChoice(4);
    }

    // Gets all the information necessary for a reservation.
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

    /**
     * User can select a valid choice when selecting an option.
     * @param  exitValue The highest value the user can input for a certain menu.
     * @return  The user's choice.
     */
    public int getUserMenuChoice(int exitValue){
        return getInputInRange(1, exitValue);
    }

    // Returns the integer if the input is a valid number.
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

    // Returns the integer if it is in range between the selected min and max.
    private int getInputInRange(int minValueOfRange, int maxValueOfRange) {
        String choice;

        choice = sc.nextLine();
        while(!userValidator.inputIsInRange(choice, minValueOfRange, maxValueOfRange)) {
            System.out.print("The input is invalid. Enter a new value from the range " + minValueOfRange + " to " + maxValueOfRange + ": ");
            choice = sc.nextLine();
        }
        return Integer.parseInt(choice);
    }

    // Return the reservation type if it's valid between the two choices.
    private String getReservationType() {
        String input;
        input = sc.nextLine();
        while(!userValidator.isValidReservationType(input)) {
            System.out.print("The input type is invalid. Enter a new value S (Standard) or AP (Advanced Purchase): ");
            input = sc.nextLine();
        }
        return input;
    }

    // Returns the occupancy if the input is in valid format.
    private String getOccupancy() {
        String input;
        input = sc.nextLine();
        while(!userValidator.isValidOccupancy(input)) {
            System.out.print("The input is invalid. Enter a new value in the format number+number. Try again: ");
            input = sc.nextLine();
        }
        return input;
    }

    // Returns the date if the input is in valid format and it can be parsed.
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

    private void readRoom(ArrayList<Room> rooms) {
        int roomNum;
        String typeOfRoom, occupancy;

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
