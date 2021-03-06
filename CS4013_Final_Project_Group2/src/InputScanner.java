

import java.time.LocalDate;
import java.util.*;

/**
 * This class uses InputValidator and allows users to actually input their choices
 * using the Scanner utility. Also allows them to re-enter until they reach a choice that is valid.
 *
 * @author 20238029 Sergiu Mereacre
 * @since 15/11/2021
 */
public class InputScanner {
    private final Scanner sc;
    private final InputValidator userValidator;

    /**
     * Initialises the input scanner we are going to use to complete all the different requirements from our user.
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
        return getInputInRangeMenu(1, ConstantReferences.EXIT_STARTUP);
    }

    /**
     * User can select a valid choice when selecting an option in the Analytics screen.
     *
     * @return The user's choice.
     */
    public int getAnalyticsChoice() {
        return getUserMenuChoice(ConstantReferences.EXIT_ANALYTICAL);
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

        System.out.println("\n ------------------ RESERVATION INFORMATION ------------------");
        resNumber = getNumberNeeded();
        System.out.println("Your reservation number is " + resNumber + ".");

        System.out.print("Enter the reservation name (e.g. Jeff Simmons) (-1 to quit): ");
        resName = getName();
        if (resName == null) {
            return null;
        }

        System.out.print("Enter the reservation type (S or AP) (-1 to quit): ");
        resType = getReservationType();
        if (resType.equals("-1")) {
            return null;
        }

        System.out.print("Enter a check in date (e.g. YYYY-MM-DD) (-1 to quit): ");
        checkInDate = getDate();
        if (checkInDate == null) {
            return null;
        }
        while (checkInDate.isBefore(LocalDate.now())) {
            System.out.print("You cannot reserve rooms for past dates, try again (-1 to quit): ");
            checkInDate = getDate();

            if (checkInDate == null) {
                return null;
            }
        }

        System.out.print("Enter a check out date (e.g. YYYY-MM-DD) (-1 to quit): ");
        checkOutDate = getDate();
        if (checkOutDate == null) {
            return null;
        }

        while (checkOutDate.compareTo(checkInDate) == 0 || checkOutDate.compareTo(checkInDate) < 0) {
            System.out.print("You cannot set the check out date before or same day as the check in date, try again (-1 to quit): ");
            checkOutDate = getDate();

            if (checkOutDate == null) {
                return null;
            }
        }

        System.out.print("Enter the number of rooms (-1 to quit): ");
        numberOfRooms = getNumber();
        if (numberOfRooms == -1) {
            return null;
        }

        rooms = readRoom(numberOfRooms, checkInDate, checkOutDate);

        if (rooms == null) {
            return null;
        }

        reservation = new Reservation(resNumber, resName, resType, checkInDate, checkOutDate, numberOfRooms);
        reservation.setTotalCost(reservation.getTotalCost());
        reservation.setRooms(rooms);
        reservation.setTotalCost(reservation.getTotalCost());
        System.out.println("Thank you! Your reservation will cost: \u20AC" + reservation.getTotalCost());
        System.out.print("--------------------------------------------------------------");
        ReservationCancellationManager.addReservation(reservation);
        return reservation;
    }

    /**
     * Goes through all the reservations and cancellations and checks to see what is the next number available
     * for a reservation number.
     *
     * @return A valid reservation number.
     */
    public int getNumberNeeded() {
        int max = 0;
        for (int i = 0; i < ReservationCancellationManager.getAllReservations().size(); i++) {
            if (max < ReservationCancellationManager.getAllReservations().get(i).getResNumber()) {
                max = ReservationCancellationManager.getAllReservations().get(i).getResNumber();
            }
        }
        for (int j = 0; j < ReservationCancellationManager.getAllCancellations().size(); j++) {
            if (max < ReservationCancellationManager.getAllCancellations().get(j).getReservation().getResNumber()) {
                max = ReservationCancellationManager.getAllCancellations().get(j).getReservation().getResNumber();
            }
        }
        return max + 1;
    }

    /**
     * This method checks to see if a reservation number is correct or not. If it is validated it can be cancelled.
     *
     * @return The user's chosen cancellation reference based on the reservation number.
     */
    public Cancellation readValidCancellation() {
        Cancellation userInputCancellation;
        Reservation chosenReservation;
        System.out.println("\n------ REQUESTING CANCELLATION INFORMATION ------");
        System.out.println("Enter the reservation number for the reservation you are cancelling.");
        System.out.print("Reservation number (-1 to quit): ");
        chosenReservation = getReservationFromUserReservationNumber();

        if (chosenReservation == null) {
            return null;
        }
        System.out.println();
        System.out.println(chosenReservation.reservationFormat());
        System.out.println();
        System.out.println("Are you sure you want to cancel " + chosenReservation.getResNumber() + "?");
        System.out.print("Type your answer (Y/N): ");
        int confirmation = getYesOrNo();
        if (confirmation == 0 || confirmation == -1) {
            return null;
        }




        userInputCancellation = new Cancellation(chosenReservation);

        System.out.println("You have successfully cancelled the reservation with reservation number " + chosenReservation.getResNumber() + ".");
        System.out.println("Your refund value is: \u20AC" + (chosenReservation.getTotalCost() - userInputCancellation.getIncome()));
        System.out.println("-------------------------------------------------");

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

        // If the user wants to quit.
        if (input.equals("-1")) {
            return -1;
        }

        while (!userValidator.inputIsInteger(input)) {
            System.out.print("The input is not a valid positive number, make sure it is greater than 0. Try again (-1 to quit): ");
            input = sc.nextLine();

            if (input.equals("-1")) {
                return -1;
            }
        }


        validNum = Integer.parseInt(input);
        return validNum;
    }

    /**
     * Checks the input value from the user so that it is a valid occupancy depending on the typeOfRoom.
     *
     * @return A valid occupancy for the type of room.
     */
    private int getValidOccupancy(String typeOfRoom) {
        int occuMin = 0;
        int occuMax = 0;

        int validOccupancy = getNumber();

        if (validOccupancy == -1) {
            return -1;
        }

        for (int i = 0; i < HotelInitialiser.getAllHotels().size(); i++) {
            for (int j = 0; j < HotelInitialiser.getAllHotels().get(i).getTypeOfRooms().size(); j++) {
                String roomType = HotelInitialiser.getAllHotels().get(i).getRoomType(j).getRoomType();
                if (roomType.equalsIgnoreCase(typeOfRoom)) {
                    occuMin = HotelInitialiser.getAllHotels().get(i).getRoomType(j).getOccuMin();
                    occuMax = HotelInitialiser.getAllHotels().get(i).getRoomType(j).getOccuMax();
                    break;
                }
            }
        }

        while (validOccupancy < occuMin || validOccupancy > occuMax) {
            System.out.print("The input is not a valid occupancy for the room type, try again between " + occuMin + " and " + occuMax + " (-1 to quit): ");
            validOccupancy = getNumber();

            if (validOccupancy == -1) {
                return -1;
            }
        }
        return validOccupancy;
    }

    /**
     * Checks the input value from the user so that it is a valid Name.
     *
     * @return A valid name as a String.
     */
    private String getName() {
        String input;
        input = sc.nextLine();

        if (input.equals("-1")) {
            return null;
        }
        while (!userValidator.inputIsName(input)) {
            System.out.print("The input is not a valid name. Try again (e.g. Jeff Simmons) (-1 to quit): ");
            input = sc.nextLine();

            if (input.equals("-1")) {
                return null;
            }
        }

        return input;
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
            System.out.print("The input is invalid. Enter a new value from the range " + minValueOfRange + " to " + maxValueOfRange + " (-1 to quit): ");
            choice = sc.nextLine();
            if (choice.equals("-1")) {
                return -1;
            }
        }
        return Integer.parseInt(choice);
    }

    /**
     * Checks the input value from the user so that it is a valid number in the range given.
     *
     * @param minValueOfRange Minimum value of the range.
     * @param maxValueOfRange Maximum value of the range.
     * @return The choice the user has made inside the range we instructed it to.
     */
    private int getInputInRangeMenu(int minValueOfRange, int maxValueOfRange) {
        String choice;

        choice = sc.nextLine();
        while (!userValidator.inputIsInRange(choice, minValueOfRange, maxValueOfRange)) {
            System.out.print("The input is invalid. Enter a new value from the range " + minValueOfRange + " to " + maxValueOfRange + ": ");
            choice = sc.nextLine();
            if (choice.equals("-1")) {
                return -1;
            }
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

        if (input.equals("-1")) {
            return "-1";
        }

        while (!userValidator.isValidReservationType(input)) {
            System.out.print("The input type is invalid. Enter a new value S (Standard) or AP (Advanced Purchase) (-1 to quit): ");
            input = sc.nextLine();

            if (input.equals("-1")) {
                return "-1";
            }
        }
        input = input.toUpperCase();
        return input;
    }

    /**
     * Asks for user input reservation number and returns the corresponding reservation.
     *
     * @return Reservation based on the user reservation number.
     */
    private Reservation getReservationFromUserReservationNumber() {
        Reservation chosenReservation;

        int resNumber = getReservationNumber();

        if (resNumber == -1) {
            return null;
        }
        chosenReservation = ReservationCancellationManager.getReservation(resNumber);

        return chosenReservation;
    }

    /**
     * Asks the user for input and makes sure that the reservation number is valid.
     *
     * @return Valid reservation number.
     */
    private int getReservationNumber() {
        int resNumber = getNumber();

        if (resNumber == -1) {
            return -1;
        }
        while (ReservationCancellationManager.getReservation(resNumber) == null) {
            System.out.print("Reservation you inputted does not exist. Try again (-1 to quit): ");
            resNumber = getNumber();

            if (resNumber == -1) {
                return -1;
            }
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

        if (input.equals("-1")) {
            return null;
        }

        LocalDate date;
        while (!userValidator.inputIsDate(input)) {
            System.out.print("The date input is invalid. Enter a new value in the format YYYY-MM-DD. Try again (-1 to quit): ");
            input = sc.nextLine();

            if (input.equals("-1")) {
                return null;
            }
        }
        date = LocalDate.parse(input);
        return date;
    }

    /**
     * Checks the input value from the user so that it is a valid Username from the Admins csv file.
     *
     * @return A valid username as a String.
     */
    public String getUsername() {
        String input;
        input = sc.nextLine();

        if (input.equals("-1")) {
            return null;
        }

        while (!userValidator.inputIsUsername(input)) {
            System.out.print("The username given is not a valid username, please try again (-1 to quit): ");
            input = sc.nextLine();

            if (input.equals("-1")) {
                return null;
            }
        }

        return input;
    }

    /**
     * Checks the input value from the user so that it is a valid Password for a specific username.
     * @param username The inputted username.
     * @return A valid password as a String.
     */
    public String getPassword(String username) {
        User user = getUser(username);
        String input;
        input = sc.nextLine();

        if (input.equals("-1")) {
            return null;
        }

        while (!input.equalsIgnoreCase(user.getPassword())) {
            System.out.print("The password for this username is incorrect, please try again (-1 to quit): ");
            input = sc.nextLine();

            if (input.equals("-1")) {
                return null;
            }
        }

        return input;
    }


    /**
     * Based on the username it returns a specific user.
     * @param username The inputted username.
     * @return A valid user based on the username.
     */
    public User getUser(String username) {
        for (User user : Reader.readUsers(ConstantReferences.ADMINS)) {
            if (username.equalsIgnoreCase(user.getUsername())) {
                return user;
            }
        }
        return null;
    }

    /**
     * Co-Author: Edison Cai
     * Asks the user for a Y or N input and returns a boolean expression.
     *
     * @return Boolean value depending on the users choice of Y or N.
     */
    public int getYesOrNo() {
        String input;
        input = sc.nextLine();

        if (input.equals("-1")) {
            return -1;
        }
        int result = 0;
        while (!userValidator.inputIsYesOrNo(input)) {
            System.out.print("The input is invalid. Please enter 'Y' or 'N' (-1 to quit): ");
            input = sc.nextLine();

            if (input.equals("-1")) {
                return -1;
            }
        }
        if (input.equalsIgnoreCase("Y")) {
            result = 1;
        }

        return result;
    }

    /**
     * Co-Creator: Edison Cai
     * Instructs the user on completing different rooms and rooms details.
     *
     * @param numberOfRooms Number of rooms the user needs.
     * @param checkIn       Date the user wants to check in.
     * @param checkOut      Date the user wants to check out.
     * @return The completed Rooms arraylist with all the details given by the user.
     */
    private ArrayList<Room> readRoom(int numberOfRooms, LocalDate checkIn, LocalDate checkOut) {
        String typeOfRoom;
        int occupancy;
        ArrayList<Room> rooms = new ArrayList<>();

        System.out.println("\n+++++++++++++++++ REQUESTING ROOM INFORMATION ++++++++++++++++");
        for (int i = 0; i < numberOfRooms; i++) {
            System.out.println("-------------------- AVAILABLE ROOM TYPES --------------------");
            System.out.println("-- Deluxe Double, Deluxe Twin, Deluxe Single, Deluxe Family --");
            System.out.println("----- Executive Double, Executive Twin, Executive Single -----");
            System.out.println("-------- Classic Double, Classic Twin, Classic Single --------");
            System.out.println("--------------------------------------------------------------");
            System.out.print("Enter room type (-1 to quit): ");
            typeOfRoom = sc.nextLine();
            if (typeOfRoom.equals("-1")) {
                return null;
            }
            while (!userValidator.isValidRoomType(typeOfRoom)) {
                System.out.println("This is not a valid input, these are some options:");
                System.out.println("-------------------- AVAILABLE ROOM TYPES --------------------");
                System.out.println("-- Deluxe Double, Deluxe Twin, Deluxe Single, Deluxe Family --");
                System.out.println("----- Executive Double, Executive Twin, Executive Single -----");
                System.out.println("-------- Classic Double, Classic Twin, Classic Single --------");
                System.out.println("--------------------------------------------------------------");
                System.out.print("Please enter your choice (-1 to quit): ");
                typeOfRoom = sc.nextLine();
                if (typeOfRoom.equals("-1")) {
                    return null;
                }
            }

            // Checking to see if there are any rooms that are already completely booked
            // between the check-in and check-out dates.
            while (!Room.roomIsAvailable(new Room(typeOfRoom, 1), checkIn, checkOut)) {
                System.out.println("This is not a valid input, these are some options:");
                System.out.println("-------------------- AVAILABLE ROOM TYPES --------------------");
                System.out.println("-- Deluxe Double, Deluxe Twin, Deluxe Single, Deluxe Family --");
                System.out.println("----- Executive Double, Executive Twin, Executive Single -----");
                System.out.println("-------- Classic Double, Classic Twin, Classic Single --------");
                System.out.println("--------------------------------------------------------------");
                System.out.print("Please enter your choice (-1 to quit): ");
                typeOfRoom = sc.nextLine();
                if (typeOfRoom.equals("-1")) {
                    return null;
                }
            }

            // Getting the capitalised room name.
            for (TypeOfRoom room : HotelInitialiser.getAllRooms()) {
                if (typeOfRoom.equalsIgnoreCase(room.getRoomType())) {
                    typeOfRoom = room.getRoomType();
                    break;
                }
            }

            System.out.print("Enter occupancy total (-1 to quit): ");
            occupancy = getValidOccupancy(typeOfRoom);
            if (occupancy == -1) {
                return null;
            }

            rooms.add(new Room(typeOfRoom, occupancy));
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        return rooms;
    }
}
