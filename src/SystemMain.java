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
        checkReservationsFlag(args);
        checkCancellationsFlag(args);
        checkHotelsFlag(args);
        checkAdminsFlag(args);

        ReservationSystem system = new ReservationSystem();
        system.run();
    }

    /**
     * Checking for the -r or reservations flag.
     * @param args The args array.
     */
    public static void checkReservationsFlag(String[] args){
        int flagIndex = findFlag("-r", args);
        if(flagIndex == -1){
            return;
        }

        try{
            ConstantReferences.RESERVATIONS = args[flagIndex + 1];
        } catch (ArrayIndexOutOfBoundsException ignored){
        }
    }

    /**
     * Checking for the -c or cancellations flag.
     * @param args The args array.
     */
    public static void checkCancellationsFlag(String[] args){
        int flagIndex = findFlag("-c", args);
        if(flagIndex == -1){
            return;
        }

        try{
            ConstantReferences.CANCELLATIONS = args[flagIndex + 1];
        } catch (ArrayIndexOutOfBoundsException ignored){
        }
    }

    /**
     * Checking for the -h or hotels flag.
     * @param args The args array.
     */
    public static void checkHotelsFlag(String[] args){
        int flagIndex = findFlag("-h", args);
        if(flagIndex == -1){
            return;
        }

        try{
            ConstantReferences.HOTELS = args[flagIndex + 1];
        } catch (ArrayIndexOutOfBoundsException ignored){
        }
    }

    /**
     * Checking for the -a or admins flag.
     * @param args The args array.
     */
    public static void checkAdminsFlag(String[] args){
        int flagIndex = findFlag("-a", args);
        if(flagIndex == -1){
            return;
        }

        try{
            ConstantReferences.ADMINS = args[flagIndex + 1];
        } catch (ArrayIndexOutOfBoundsException ignored){
        }
    }

    // Finding a flag by going through the args array.
    private static int findFlag(String flag, String[] args){
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
