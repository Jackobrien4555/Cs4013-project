/**
 * This class uses ReservationSystem which brings up the command line interface with all the menus
 * and sub menus so that the project can be run.
 *
 * @author 20238029 Sergiu Mereacre
 * @since 15/11/2021
 */
public class SystemMain{
    public static void main(String[] args) {
        ReservationSystem system = new ReservationSystem();
        system.run();
    }
}
