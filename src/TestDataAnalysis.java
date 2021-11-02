import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestDataAnalysis {
    String[] hotels = {"5-star", "4-star", "3-star"};
    String[] fiveStarRooms = {"Deluxe Double", "Deluxe Twin", "Deluxe Single", "Deluxe Family"};
    String[] fourStarRooms = {"Executive Double", "Executive Twin", "Executive Single"};
    String[] threeStarRooms = {"Classic Double", "Classic Twin", "Classic Single"};

    public static void main(String[] args) throws IOException {
        generate();
    }

    public static void generate(){
        try{
            File file = new File("reservations/test.csv");
            if(file.createNewFile()){
                System.out.println("File created: " + file.getName());
            }
            else {
                System.out.println("File already exists");
            }

        }
        catch (IOException e){
            System.out.println("Error occurred.");
            e.printStackTrace();
        }

        try{
            FileWriter writer = new FileWriter("reservations/test.csv");
            writer.write();
            writer.close();
            System.out.println("Wrote to file.");
        }
        catch (IOException e){
            System.out.println("Error occurred.");
            e.printStackTrace();
        }
    }
}
