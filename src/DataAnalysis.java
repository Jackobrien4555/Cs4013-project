import java.util.ArrayList;

/**
 * This class calculates several metrics based off of the reservations
 * @author 20241135
 */
public class DataAnalysis {
    public DataAnalysis(){

    }

    /**
     * Returns the occupancy figures for each hotel and room type
     * over a specific period of time.
     *
     * @param startDate The starting date
     * @param endDate The end date
     *
     */
    public static void getOccupancyRatesAll(String startDate, String endDate, ArrayList<Hotel> hotels){
        System.out.println("This is the occupancy rate for every hotel and room: ");
        for(Hotel hotel : hotels){
            getOccupancyRatesHotel(startDate, endDate, hotel);
        }
    }

    private static void getOccupancyRatesHotel(String startDate, String endDate, Hotel hotel){
        System.out.printf("Occupancy summary for: %s\n", hotel.getHotelType());

    }

    private static void getOccupancyRatesRoom(String startDate, String endDate){

    }

    /**
     * Returns the income for each hotel and room type
     * over a specific period of time.
     */
    public static void calculateIncomeAll(String startDate, String endDate, ArrayList<Hotel> hotels){
        for(Hotel hotel : hotels){
            calculateIncomeHotel(startDate, endDate, hotel);
        }
    }

    private static void calculateIncomeHotel(String startDate, String endDate, Hotel hotel){

    }

    private static void calculateIncomeRoom(){

    }

}