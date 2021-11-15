/**
 * This class uses InputValidator and allows users to actually input their choices
 * using the Scanner utility. Also allows them to re-enter until they reach a choice that is acceptable.
 *
 * @author 20238029 Sergiu Mereacre
 * @since 15/11/2021
 */

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

    // Returns the double if the input is a valid double.
    private double getDouble() {
        String input;
        double validDouble;
        input = sc.nextLine();
        while(!userValidator.inputIsDouble(input)) {
            System.out.print("The input is not a number. Try again: ");
            input = sc.nextLine();
        }
        validDouble = Double.parseDouble(input);
        return validDouble;
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
}
