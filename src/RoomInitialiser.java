import java.util.ArrayList;

public class RoomInitialiser extends Initialiser{

    @Override
    public ArrayList<Room> initialise(ArrayList<String[]> cells) {
        ArrayList<Room> result = new ArrayList<>();
        for (int i = 0; i < cells.size(); i++) {
            String[] line = cells.get(i);
            if (line.length == 12 && !line[0].equals("")) {
                TypeOfRoom newRoomType = new TypeOfRoom("", "", "", new int[]{});
            }
        }
        return result;
    }
}
