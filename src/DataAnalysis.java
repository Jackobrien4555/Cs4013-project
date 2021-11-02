import java.io.IOException;
import java.util.Scanner;

public class DataAnalysis {
    private Scanner input;



    public DataAnalysis() {
        input = new Scanner(System.in);
    }

    /**
     * Runs the data analysis tool
     * Edison Cai
     */
    public void run()
            throws IOException {
        boolean more = true;

        while (more) {
            System.out.println("R)un analyses Q)uit");
            String command = input.nextLine().toUpperCase();

            if (command.equals("R")) {
                System.out.println("Running analyses...");
            } else if (command.equals("Q")) {
                System.out.println("Quitting Data Analysis...");
                more = false;
            }

        }
    }
}