import java.util.ArrayList;
import java.util.Arrays;

public class HotelInitialiser extends Initialiser {
    @Override
    public ArrayList<Hotel> initialise(ArrayList<String[]> cells) {
        ArrayList<Hotel> result = new ArrayList<>();
        for (int i = 0; i < cells.size(); i++) {
            String[] line = cells.get(i);
            // Once we reach a line where a new hotel is defined,
            // make a Hotel object and start adding room types.
            if (line.length == 12 && !line[HOTEL_INDEX].equals("")) {
                Hotel newHotel = new Hotel(line[HOTEL_INDEX]);
                TypeOfRoom newRoom = new TypeOfRoom(line[ROOM_INDEX], line[OCCUPANCY_MIN_INDEX],
                        line[OCCUPANCY_MAX_INDEX],
                        getRates(Arrays.copyOfRange(line, RATES_START_INDEX, RATES_END_INDEX + 1)));
                newHotel.addTypeOfRoom(newRoom);

                i++;

                // Keep adding rooms to the hotel until we reach another hotel
                while(cells.get(i)[HOTEL_INDEX].equals("")){
                    line = cells.get(i);
                    newRoom = new TypeOfRoom(line[ROOM_INDEX], line[OCCUPANCY_MIN_INDEX],
                            line[OCCUPANCY_MAX_INDEX],
                            getRates(Arrays.copyOfRange(line, RATES_START_INDEX, RATES_END_INDEX + 1)));
                    newHotel.addTypeOfRoom(newRoom);
                    i++;

                    if(i == cells.size()){
                        result.add(newHotel);
                        return result;
                    }
                }

                if(!cells.get(i)[HOTEL_INDEX].equals("")){
                    i--;
                }
                result.add(newHotel);
            }
        }
        return result;
    }
}
