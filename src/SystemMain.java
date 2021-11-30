/**
 * This class uses ReservationSystem which brings up the command line interface with all the menus
 * and sub menus so that the project can be run.
 *
 * @author 20238029 Sergiu Mereacre
 * @author Edison Cai
 * @since 15/11/2021
 */
public class SystemMain{
    public static void main(String[] args) {
        ReservationSystem system = new ReservationSystem();
        system.run();
    }

    public static void checkReservationsFlag(String flag, String[] args){
        int flagIndex = findFlag(flag, args);
        if(flagIndex == -1){
            return;
        }
    }

    public static int findFlag(String flag, String[] args){
        int index = -1;
        for(int i = 0; i < args.length; i++){
            if(args[i].equals(flag)){
                index = i;
                return index;
            }
        }
        return index;
    }
}
