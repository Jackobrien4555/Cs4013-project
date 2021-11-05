import java.util.Arrays;

public class typeOfRoom {
	private String typeOfRoom;
	private int[] dailyRates;
	private String occuMin;
	private String occuMax;
	private int numAvailable;

	/**
	 * creates room object with type occupancy and rate
	 * @param typeOfRoom The room type eg. Deluxe Single

	 * @param dailyRates rates of the room based on day, eg. [0] monday,[1] tuesday etc...
	 */
	public typeOfRoom(String typeOfRoom, String occuMin, String occuMax, int[] dailyRates ) {
		this.typeOfRoom = typeOfRoom;
		this.occuMin = occuMin;
	    this.occuMax = occuMax;
		this.dailyRates = dailyRates;
	}

	/** sets type of room */
	public void setRoomType(String type){
		this.typeOfRoom = type;} 

	/** returns room type*/
	public String getRoomType(){
		return typeOfRoom;} 

	public int numAvailable() {
		return numAvailable;
		
	}
	public String toString() {
		return typeOfRoom + "," + numAvailable + occuMin + "," + occuMax + "," + Arrays.toString(dailyRates);
	}
	}

