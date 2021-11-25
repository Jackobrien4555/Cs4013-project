import java.util.Arrays;
/**class Room defines a room
 * 
 * @author Jack O Brien
 * @since 05/11/2021
 */
public class Room {
	  private String typeOfRoom;
	  private int amountOfOccupants;

	  /**
	   * 
	   * @param type the type of room
	   * @param occupancy the amount of occupants able to stay in the room
	   */
	  public Room(String type, int occupancy) {
			this.typeOfRoom = type;
			this.amountOfOccupants = occupancy;
		}
	  
	  /** 
	   * returns type of room
	   * @return typeOfRoom the type of room
	   */
	  public String getRoomType() {
		  return typeOfRoom;
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
	   * sets the amount able to stay in room
	   * @param amount the amount able to stay in room
	   */
	  public void setRoomOccupancy(int amount) {
		 amountOfOccupants = amount;
	  }
}
