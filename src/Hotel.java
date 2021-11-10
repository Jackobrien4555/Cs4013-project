import java.util.ArrayList;

	/**
	 * The hotel class defines the hotel and its info
	 * @version 1.0
	 * @author Jack O Brien
	 *@since 2021-10-28
	 */

	public class Hotel {
		private String typeHotel;
		private ArrayList<TypeOfRoom> typeOfRooms;
		//private String name;
	   // private String rating;
	   // private String location;
	    
		/**
		 * Creates hotel object
		 * @param typeOfHotel the type of hotel
		 */
		public Hotel(String typeOfHotel) {
			this.typeHotel = typeOfHotel;
			typeOfRooms = new ArrayList<TypeOfRoom>();
		}

		/**
		 * returns hotel
		 * @return typeHotel returns type of hotel
		 */
	public String getHotelType(){
	    return typeHotel;
	  }
	
	/**
	 * returns the type of room at i
	 * @param i the index
	 * @return the room type at i
	 */
	public TypeOfRoom getRoomType(int i) {
		return typeOfRooms.get(i);
	}
	
	/**
	 * gets the total number of rooms
	 * @return t the total number of rooms
	 */
	//public int getTotalRooms() {
	//	int t=0;
	//	for(int i=0 ; i < typeOfRooms.size();i++) {
	//		t += typeOfRooms.get(i).getNumAvailable();
	//		}
	//	return t;
	//	}
	
	/**
	 * checks if hotel has the type of room asked for
	 * @param type the type of room asked for
	 * @return true or false whether or not room exists
	 */
	public boolean hasType(String type) {
		for(int i=0; i < typeOfRooms.size();i++) {
		if(typeOfRooms.get(i).getRoomType().equals(type)) {
			return true;
	}
	}
		return false;
	}
	
	/**
	 * returns array of roomtypes
	 * @return typeOfRoom list of all roomtypes 
	 */
	public ArrayList<TypeOfRoom> getTypeOfRooms(){
	    return typeOfRooms;
	  }
	
	/**
	 * adds new room type to hotel
	 * @param newR new type of room for hotel
	 */
	public void addTypeOfRoom ( TypeOfRoom newR) {
		typeOfRooms.add(newR);
	}
	}
	

