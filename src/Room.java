import java.util.Arrays;
/**class Room defines a room
 * 
 * @author Jack O Brien
 * @since 05/11/2021
 */
public class Room {
	 private int roomNumber;
	  private String typeOfRoom;
	  private int amountOfOccupants;

	  /**
	   * 
	   * @param type the type of room
	   * @param occupancy the amount of occupants able to stay in the room
	   * @param number the number of that room 
	   */
	  public Room(String type, int occupancy, int number) {
			this.typeOfRoom = type;
			this.amountOfOccupants = occupancy;
			this.roomNumber = number;
		}
	  
	  /** 
	   * returns type of room
	   * @return typeOfRoom the type of room
	   */
	  public String getRoomType() {
		  return typeOfRoom;
	  }
	  /**
	   * gets the number of the room
	   * @return roomNumber number of the room
	   */
	  public int getNumber() {
		   return roomNumber;
	  }
	  /**
	   * gets occupancy of the room
	   * @return amountOfOccupants gets amount able to stay in room
	   */
	  public int getRoomOccupancy() {
		 return amountOfOccupants;
	  }
	  
	  /**
	   * sets the type of room
	   * @param type the type of room
	   */
	  public void setRoomType(String type) {
		   typeOfRoom = type ;
	  }
	  /**
	   * sets the number of the room
	   * @param number the number of the room
	   */
	  public void setNumber(int number) {
		   roomNumber = number;
	  }
	  /**
	   * sets the amount able to stay in room
	   * @param amount the amount able to stay in room
	   */
	  public void setRoomOccupancy(int amount) {
		 amountOfOccupants = amount;
	  }
}
