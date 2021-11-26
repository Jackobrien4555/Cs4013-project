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
            if (inputAsInt >= minValueOfRange && inputAsInt <= maxValueOfRange) {
                return true;
            }
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
        return input.matches("\\d+") || input.matches("[-\\d+]");
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

    /**
     * Validates if String input is a double.
     *
     * @param input String that is being checked.
     * @return True or false for whether it's a valid double.
     */
    public boolean inputIsDouble(String input) {
        return (input.matches("\\d+") || input.matches("\\d+.\\d+"));
    }

    /**
     * Validates if String correctly represents an occupancy.
     *
     * @param input String that is being checked.
     * @return True or False for whether it's a valid occupancy.
     */
    public boolean isValidOccupancy(String input) {
        return input.matches("[\\d][+][\\d]");
    }

    /**
     * Validates if String correctly represents a room type.
     *
     * @param input String that is being checked.
     * @return True or False for whether it's a valid room type.
     */
    public boolean isValidRoomType(String input) {
        String[] roomTypes = {"Deluxe Double", "Deluxe Twin", "Deluxe Single", "Deluxe Family", "Executive Double",
                "Executive Twin", "Executive Single", "Classic Double", "Classic Twin", "Classic Single"};
        for (int i = 0; i < roomTypes.length; i++) {
            if (roomTypes[i].equalsIgnoreCase(input)) {
                return true;
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
        return input.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    /**
     * Validates if String correctly represents a yes or no answer from user.
     *
     * @param input String that is being checked.
     * @return True or False for whether it's a valid yes or no answer.
     */
    public boolean inputIsYesOrNo(String input){
        return input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N");
    }
}


