import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * This class is used to generate random reservations
 *
 * @author Edison Cai 20241135
 */
public class TestDataAnalysis {
    private final static String[] hotels = {"5-star", "4-star", "3-star"};
    private final static String lowerLetters = "abcdefghijklmnopqrstuvwxyz";
    private final static String upperLetters = lowerLetters.toUpperCase();
    private final static String allLetters = lowerLetters + upperLetters;
    private static HashMap<String, int[]> roomToRatesThreeStars = new HashMap<>();
    private static HashMap<String, int[]> roomToRatesFourStars = new HashMap<>();
    private static HashMap<String, int[]> roomToRatesFiveStars = new HashMap<>();

    public static void main(String[] args) throws IOException {
        roomToRatesFiveStars.put("Deluxe Double", new int[]{35, 1, 2, 75, 75, 75, 80, 90, 90, 75});
        roomToRatesFiveStars.put("Deluxe Twin", new int[]{25, 1, 2, 75, 75, 75, 80, 90, 90, 75});
        roomToRatesFiveStars.put("Deluxe Single", new int[]{10, 1, 2, 70, 70, 70, 75, 80, 80, 65});
        roomToRatesFiveStars.put("Deluxe Family", new int[]{10, 1, 3, 80, 80, 80, 80, 100, 100, 80});

        roomToRatesFourStars.put("Executive Double", new int[]{40, 1, 2, 70, 70, 70, 70, 80, 85, 85});
        roomToRatesFourStars.put("Executive Twin", new int[]{32, 1, 2, 70, 70, 70, 70, 80, 85, 85});
        roomToRatesFourStars.put("Executive Single", new int[]{12, 1, 1, 65, 65, 65, 65, 70, 75, 80});

        roomToRatesThreeStars.put("Classic Double", new int[]{45, 1, 2, 65, 65, 70, 70, 75, 80, 65});
        roomToRatesThreeStars.put("Classic Twin", new int[]{45, 1, 2, 65, 65, 70, 70, 80, 85, 65});
        roomToRatesThreeStars.put("Classic Single", new int[]{10, 1, 1, 50, 50, 50, 60, 75, 75, 50});

        System.out.println(randomReservation());
        //generate();
    }

    private static void generate() {
        try {
            File file = new File("reservations/test.csv");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists");
            }

        } catch (IOException e) {
            System.out.println("Error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter("reservations/test.csv");
            //writer.write();
            writer.close();
            System.out.println("Wrote to file.");
        } catch (IOException e) {
            System.out.println("Error occurred.");
            e.printStackTrace();
        }
    }

    private static String randomReservation() {
        StringBuilder result = new StringBuilder();
        GregorianCalendar gc = new GregorianCalendar();

        String reservationNumber = Integer.toString((int) (Math.random() * 1000));

        // Make random reservation name
        StringBuilder reservationName = new StringBuilder();
        for (int i = 0; i <= 10; i++) {
            reservationName.append(allLetters.charAt((int) (Math.random() * allLetters.length())));
        }

        String reservationType = (int) (Math.random() * 2) == 0 ? "S" : "AP";
        String checkInDate = getRandomizeDate(gc);
        String checkOutDate = getRandomizeDate(gc);

        // Swap dates if they are in the wrong order
        if (compareDates(checkInDate, checkOutDate) <= 0) {
            String temp = checkOutDate;
            checkOutDate = checkInDate;
            checkInDate = temp;
        }

        int roomNumber = (int) (Math.random() * 10 + 1);
        String numberOfRooms = Integer.toString(roomNumber);

        result.append(reservationNumber).append(",");
        result.append(reservationName).append(",");
        result.append(reservationType).append(",");
        result.append(checkInDate).append(",");
        result.append(checkOutDate).append(",");
        result.append(numberOfRooms).append(",");

        int cost = 0;

        for (int i = 0; i < roomNumber; i++) {
            String hotel = hotels[(int) (Math.random() * hotels.length)];
            String room = "";
            int occupancy = (int) (Math.random() * 6 + 1);

            // Check which hotel is selected
            // Then assign random room
            switch (hotel) {
                case "5-star":
                    Object[] keysFiveStar = roomToRatesFiveStars.keySet().toArray();
                    room = (String) keysFiveStar[(int) (Math.random() * keysFiveStar.length)];
                    break;
                case "4-star":
                    Object[] keysFourStar = roomToRatesFourStars.keySet().toArray();
                    room = (String) keysFourStar[(int) (Math.random() * keysFourStar.length)];
                    break;
                case "3-star":
                    Object[] keysThreeStar = roomToRatesThreeStars.keySet().toArray();
                    room = (String) keysThreeStar[(int) (Math.random() * keysThreeStar.length)];
                    break;
            }
            result.append(room).append(",");
            result.append(Integer.toString(occupancy)).append(",");
            cost += getCostOfRoom();
        }

        String totalCost = Integer.toString(cost);
        result.append(totalCost);

        return result.toString();
    }

    private static int randomBetween(int start, int end) {
        return (int) ((Math.random() * (end - start + 1)) + start);
    }

    private static String getRandomizeDate(GregorianCalendar calendar) {
        // Set random year
        int year = randomBetween(2021, 2022);
        calendar.set(Calendar.YEAR, year);

        // Set random day of the year
        int dayOfYear = randomBetween(1, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
        calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);

        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
    }

    private static int getCostOfRoom(String room, String checkIn, String checkOut) {
        // To get the cost, I need to get the days of the different dates
        // I need to get the costs of every room then add them at the end
        if(roomToRatesFiveStars.containsKey(room)){

        }
        else if (roomToRatesFourStars.containsKey(room)){

        }
        else if (roomToRatesThreeStars.containsKey(room)){

        }

        int result = 0;
        return result;
    }

    /**
     * Compares 2 dates.
     *
     * @param date1
     * @param date2
     * @return
     */
    private static int compareDates(String date1, String date2) {
        StringTokenizer tk1 = new StringTokenizer(date1, "-");
        StringTokenizer tk2 = new StringTokenizer(date2, "-");

        int year1 = Integer.parseInt(tk1.nextToken());
        int year2 = Integer.parseInt(tk2.nextToken());
        if (year1 != year2) {
            return year2 - year1;
        }


        int month1 = Integer.parseInt(tk1.nextToken());
        int month2 = Integer.parseInt(tk2.nextToken());
        if (month1 != month2) {
            return month2 - month1;
        }

        int day1 = Integer.parseInt(tk1.nextToken());
        int day2 = Integer.parseInt(tk2.nextToken());
        if (day1 != day2) {
            return day2 - day1;
        }

        return 0;
    }
}
