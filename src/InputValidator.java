import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * This class is necessary for other classes as it has various methods using regex
 * to check if an input that the user gives us is acceptable or not.
 *
 * @author 20238029 Sergiu Mereacre
 * @since 15/11/2021
 */

public class InputValidator {

    /**
     * Validates the input if it is in the range given.
     *
     * @param input           String that is being checked.
     * @param minValueOfRange Minimum value of the range.
     * @param maxValueOfRange Maximum value of the range.
     * @return True or false if the input is in the range.
     */
    public boolean inputIsInRange(String input, int minValueOfRange, int maxValueOfRange) {
        if (inputIsInteger(input)) {
            int inputAsInt = Integer.parseInt(input);
            return inputAsInt >= minValueOfRange && inputAsInt <= maxValueOfRange;
        }
        return false;
    }

    /**
     * Validates if the String input is an integer or not.
     *
     * @param input String that is being checked.
     * @return True or false for whether it's an integer or not.
     */
    public boolean inputIsInteger(String input) {
        // Making sure that the integer isn't too large to cause a NumberFormatException.
        if (input.length() > 9) {
            return false;
        }

        // Also true if the input is -1.
        try{
            if(Integer.parseInt(input) == -1){
                return true;
            }
        } catch (NumberFormatException e){
            return false;
        }


        if (input.matches("\\d+") || input.matches("[-\\d+]")) {
            return Integer.parseInt(input) > 0;
        }
        return false;
    }

    /**
     * Validates if the String input is a reservation type.
     *
     * @param input String that is being checked.
     * @return True or false for whether it's a valid reservation type.
     */
    public boolean isValidReservationType(String input) {
        return (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("ap"));
    }

//    Check's if the input is a valid double number.
//    public boolean inputIsDouble(String input) {
//        return (input.matches("\\d+") || input.matches("\\d+.\\d+"));
//    }

    /**
     * Checks the input value from the user so that it is a valid reservation number and not already
     * included in our reservations file.
     *
     * @param input The reseravation number.
     * @return A valid reservation number.
     */
    public boolean inputIsValidResNum(int input) {
        ArrayList<Reservation> reservations = ReservationCancellationManager.getAllReservations();
        for (Reservation reservation : reservations) {
            if (reservation.getResNumber() == input) {
                return false;
            }
        }
        return true;
    }

    /**
     * Validates if String correctly represents a room type.
     *
     * @param input String that is being checked.
     * @return True or False for whether it's a valid room type.
     */
    public boolean isValidRoomType(String input) {
        for (int i = 0; i < HotelInitialiser.getAllHotels().size(); i++) {
            for (int j = 0; j < HotelInitialiser.getAllHotels().get(i).getTypeOfRooms().size(); j++) {
                String roomType = HotelInitialiser.getAllHotels().get(i).getRoomType(j).getRoomType();
                if (roomType.equals(input)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Validates if String correctly represents a date.
     *
     * @param input String that is being checked.
     * @return True or False for whether it's a valid date.
     */
    public boolean inputIsDate(String input) {

        // Checking if the input can be parsed.
        try {
            LocalDate.parse(input);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Validates if String correctly represents a name
     *
     * @param input String that is being checked.
     * @return True or False for whether it's a valid name.
     */
    public boolean inputIsName(String input) {
        if(input == null || input.equals("")){
            return false;
        }
        // Make sure that there aren't any spaces at the start of end of the input.
        if (input.charAt(0) == ' ' || input.charAt(input.length() - 1) == ' ') {
            return false;
        }

        return input.matches("[a-zA-Z ]+");

    }

    public boolean inputIsUsername(String input) {
        for (User user : Reader.readUsers(ConstantReferences.ADMINS)) {
            if (input.equalsIgnoreCase(user.getUsername())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validates if String correctly represents a yes or no answer from user.
     *
     * @param input String that is being checked.
     * @return True or False for whether it's a valid yes or no answer.
     */
    public boolean inputIsYesOrNo(String input) {
        return input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N");
    }
}


