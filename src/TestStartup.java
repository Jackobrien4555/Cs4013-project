import java.util.ArrayList;

public class TestStartup {
    public static void main(String[] args) {
        HotelInitialiser test = new HotelInitialiser();

        ArrayList<String[]> cells = HotelInitialiser.getFileCells("l4Hotels.csv");
        ArrayList<Hotel> hotels = test.initialise(cells);

        PrintedMenus menus = new PrintedMenus();
        InputScanner scanner = new InputScanner();
        menus.printStartUpMenu();
        scanner.getStartUpChoice();
    }
}
