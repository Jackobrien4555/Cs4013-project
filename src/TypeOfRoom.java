import java.util.Arrays;

/**
 * class typeOfRoom defines room types
 *
 * @author Jack O Brien
 * @since 05/11/2021
 */
public class TypeOfRoom {
    private final String typeOfRoom;
    private final double[] dailyRates;
    private final int occuMin;
    private final int occuMax;
    private int numAvailable;

    /**
     * creates room object with type occupancy and rate
     *
     * @param typeOfRoom The room type e.g. Deluxe Single
     * @param occuMax    the max amount of occupants
     * @param occuMin    the min amount of occupants
     * @param dailyRates rates of the room based on day, e.g. [0] monday,[1] tuesday etc...
     */
    public TypeOfRoom(String typeOfRoom, int occuMin, int occuMax, double[] dailyRates) {
        this.typeOfRoom = typeOfRoom;
        this.occuMin = occuMin;
        this.occuMax = occuMax;
        this.dailyRates = dailyRates;
    }

    /**
     * creates room object with type occupancy and rate
     *
     * @param typeOfRoom The room type e.g. Deluxe Single
     * @param occuMax    the max amount of occupants
     * @param occuMin    the min amount of occupants
     * @param dailyRates rates of the room based on day, e.g. [0] monday,[1] tuesday etc...
     * @param numAvailable THe max number of rooms available.
     */
    public TypeOfRoom(String typeOfRoom, int occuMin, int occuMax, double[] dailyRates, int numAvailable) {
        this.typeOfRoom = typeOfRoom;
        this.occuMin = occuMin;
        this.occuMax = occuMax;
        this.dailyRates = dailyRates;
        this.numAvailable = numAvailable;
    }

    /**
     * returns the type of the room
     *
     * @return typeOfRoom the type of the room
     */
    public String getRoomType() {
        return typeOfRoom;
    }

    /**
     * returns the number of this type of room available
     *
     * @return numAvailable returns the number of this type of room available
     */
    public int getNumAvailable() {
        return numAvailable;

    }

    public int getOccuMin() {
        return occuMin;
    }

    public int getOccuMax() {
        return occuMax;
    }

    public double[] getDailyRates() {
        return dailyRates;
    }

    /**
     * returns string that shows room type with details
     *
     * @return string that shows room type with details
     */
    public String toString() {
        return typeOfRoom + "," + numAvailable + occuMin + "," + occuMax + "," + Arrays.toString(dailyRates);
    }


}



