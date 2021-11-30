import java.util.ArrayList;

	/**
	 * The hotel class defines the hotel and its info
	 * @version 1.0
	 * @author Jack O Brien
	 *@since 2021-10-28
	 */

	public class Hotel {
		private final String typeHotel;
		private final ArrayList<TypeOfRoom> typeOfRooms;
	    
		/**
		 * Creates hotel object
		 * @param typeOfHotel the type of hotel
		 */
		public Hotel(String typeOfHotel) {
			this.typeHotel = typeOfHotel;
			typeOfRooms = new ArrayList<>();
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

	public TypeOfRoom findTypeOfRoom(String roomName){
		for(TypeOfRoom r : getTypeOfRooms()){
			if (roomName.equals(r.getRoomType())){
				return r;
			}
		}
		return null;
	}
	}
	

