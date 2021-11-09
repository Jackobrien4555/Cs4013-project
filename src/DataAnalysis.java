import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class calculates several metrics based off of the reservations
 * @author 20241135
 */
public class DataAnalysis {
    private static final int RES_NUMBER_INDEX = 0;
    private static final int RES_NAME_INDEX = 1;
    private static final int RES_TYPE_INDEX = 2;
    private static final int RES_START_INDEX = 3;
    private static final int RES_END_INDEX = 4;
    private static final int ROOM_NUMBER_INDEX = 5;
    private static final int FIRST_ROOM_INDEX = 6;

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

    public static void getOccupancyRatesAll(ArrayList<String[]> reservations, String startDate, String endDate, ArrayList<Hotel> hotelList){
        System.out.println("This is the occupancy rate for every hotel and room: ");

        // For every hotel
        for(Hotel h : hotelList){
            int hotelOccupancy = 0;
            HashMap<String, Integer> hotelRooms = getRoomsOfHotel(h.getTypeOfRoom());
            System.out.println("Hotel name: " + h.getHotelType());
            for(String[] cells : reservations){
                for(int i = FIRST_ROOM_INDEX; i < cells.length; i++){
                    if(hotelRooms.containsKey(cells[i])){
                        hotelRooms.replace(cells[i], hotelRooms.get(cells[i]) + 1);
                        hotelOccupancy++;
                    }
                }
            }
            System.out.println("Hotel occupancy: " + hotelOccupancy);

            for(Map.Entry<String, Integer> entry : hotelRooms.entrySet()){
                System.out.println("Room name: " + entry.getKey());
                System.out.println("Room occupancy: " + entry.getValue());
            }

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

    private static HashMap<String, Integer> getRoomsOfHotel(ArrayList<TypeOfRoom> rooms){
        HashMap<String, Integer> result = new HashMap<>();

        for(TypeOfRoom r : rooms){
            result.put(r.getRoomType(), 0);
        }

        return result;
    }
}