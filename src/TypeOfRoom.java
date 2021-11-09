import java.util.Arrays;
/**
 * class typeOfRoom defines room types 
 * @author Jack O Brien
 * @since 05/11/2021
 * 
 */
public class TypeOfRoom {
	private String typeOfRoom;
	private int[] dailyRates;
	private String occuMin;
	private String occuMax;
	private int numAvailable;

	/**
	 * creates room object with type occupancy and rate
	 * @param typeOfRoom The room type eg. Deluxe Single
     * @param occuMax the max amount of occupants
     * @param occuMin the min amount of occupants
	 * @param dailyRates rates of the room based on day, eg. [0] monday,[1] tuesday etc...
	 */
	public TypeOfRoom(String typeOfRoom, String occuMin, String occuMax, int[] dailyRates ) {
		this.typeOfRoom = typeOfRoom;
		this.occuMin = occuMin;
	    this.occuMax = occuMax;
		this.dailyRates = dailyRates;
	}	

	/**
	 * returns the type of the room
	 * @return typeOfRoom the type of the room
	 */
	public String getRoomType(){
		return typeOfRoom;} 
/**
 * returns the number of this type of room available
 * @return numAvailable returns the number of this type of room available
 */
	public int getNumAvailable() {
		return numAvailable;
		
	}
	/**
	 * returns string that shows room type with details
	 *@return string that shows room type with details
	 */
	public String toString() {
		return typeOfRoom + "," + numAvailable + occuMin + "," + occuMax + "," + Arrays.toString(dailyRates);
	}
	}
