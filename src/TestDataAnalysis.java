import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

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

    private static int reservationNumber = 0;

    private final static int MAX_ROOMS = 0;
    private final static int OCCUPANCY_MIN = 1;
    private final static int OCCUPANCY_MAX = 2;
    private final static int ROOMS_TAKEN = 10;

    private final static int tolerance = 2;

    public static void main(String[] args) throws IOException {

        // Adding a 0 at the end to indicate how many rooms are taken up
        roomToRatesFiveStars.put("Deluxe Double", new int[]{35, 1, 2, 75, 75, 75, 80, 90, 90, 75, 0});
        roomToRatesFiveStars.put("Deluxe Twin", new int[]{25, 1, 2, 75, 75, 75, 80, 90, 90, 75, 0});
        roomToRatesFiveStars.put("Deluxe Single", new int[]{10, 1, 2, 70, 70, 70, 75, 80, 80, 65, 0});
        roomToRatesFiveStars.put("Deluxe Family", new int[]{10, 1, 3, 80, 80, 80, 80, 100, 100, 80, 0});

        roomToRatesFourStars.put("Executive Double", new int[]{40, 1, 2, 70, 70, 70, 70, 80, 85, 85, 0});
        roomToRatesFourStars.put("Executive Twin", new int[]{32, 1, 2, 70, 70, 70, 70, 80, 85, 85, 0});
        roomToRatesFourStars.put("Executive Single", new int[]{12, 1, 1, 65, 65, 65, 65, 70, 75, 80, 0});

        roomToRatesThreeStars.put("Classic Double", new int[]{45, 1, 2, 65, 65, 70, 70, 75, 80, 65, 0});
        roomToRatesThreeStars.put("Classic Twin", new int[]{45, 1, 2, 65, 65, 70, 70, 80, 85, 65, 0});
        roomToRatesThreeStars.put("Classic Single", new int[]{10, 1, 1, 50, 50, 50, 60, 75, 75, 50, 0});

        generate(20);
    }

    private static void generate(int iterations) {
        File directory = new File("../reservations");
        if (!directory.exists()) {
            directory.mkdir();
        }

        try {
            File file = new File("reservations/random_res.csv");
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
            FileWriter writer = new FileWriter("reservations/random_res.csv");
            for (int i = 0; i < iterations; i++) {
                writer.write(randomReservation() + "\n");
            }
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

        // Assign reservation numbers starting from 0
        String reservationNum = Integer.toString(reservationNumber);
        reservationNumber += 1;

        // Make random reservation name
        StringBuilder reservationName = new StringBuilder();
        for (int i = 0; i <= 10; i++) {
            reservationName.append(allLetters.charAt((int) (Math.random() * allLetters.length())));
        }

        String reservationType = (int) (Math.random() * 2) == 0 ? "S" : "AP";
        String checkInDate = getRandomizeDate(gc);
        String checkOutDate = getRandomizeDate(gc, checkInDate, tolerance);

        while (checkInDate.equals(checkOutDate)) {
            checkOutDate = getRandomizeDate(gc, checkInDate, tolerance);
        }

        // Swap dates if they are in the wrong order
        if (compareDates(checkInDate, checkOutDate) <= 0) {
            String temp = checkOutDate;
            checkOutDate = checkInDate;
            checkInDate = temp;
        }

        // Random number of rooms
        int roomNumber = (int) (Math.random() * 10 + 1);
        String numberOfRooms = Integer.toString(roomNumber);

        result.append(reservationNumber).append(",");
        result.append(reservationName).append(",");
        result.append(reservationType).append(",");
        result.append(checkInDate).append(",");
        result.append(checkOutDate).append(",");
        result.append(numberOfRooms).append(",");

        // Total cost of reservation
        int cost = 0;

        int roomsAssigned = 0;

        // Keep assigning random rooms for the number of rooms given
        while (roomsAssigned < roomNumber) {

            // Get random hotel
            String hotel = hotels[(int) (Math.random() * hotels.length)];

            String room = "";
            int occupancy = randomBetween(1, 3);
            int[] roomValues = roomToRatesFiveStars.get(room);

            // Check which hotel is selected
            // Then assign random room
            switch (hotel) {
                case "5-star":
                    Object[] keysFiveStar = roomToRatesFiveStars.keySet().toArray();
                    room = (String) keysFiveStar[(int) (Math.random() * keysFiveStar.length)];

                    // Storing all properties(number of rooms, occupancy, etc. into roomValues array
                    roomValues = roomToRatesFiveStars.get(room);

                    // Making sure the occupancy is legal and the rooms haven't been used up
                    if (occupancy > roomValues[OCCUPANCY_MAX] || occupancy < roomValues[OCCUPANCY_MIN]
                            || roomValues[ROOMS_TAKEN] > roomValues[MAX_ROOMS]) {
                        room = "";
                        continue;
                    }

                    roomsAssigned += 1;
                    roomValues[ROOMS_TAKEN] += 1;
                    break;
                case "4-star":
                    Object[] keysFourStar = roomToRatesFourStars.keySet().toArray();
                    room = (String) keysFourStar[(int) (Math.random() * keysFourStar.length)];

                    roomValues = roomToRatesFourStars.get(room);

                    if (occupancy > roomValues[OCCUPANCY_MAX] || occupancy < roomValues[OCCUPANCY_MIN]
                            || roomValues[ROOMS_TAKEN] > roomValues[MAX_ROOMS]) {
                        room = "";
                        continue;
                    }

                    roomsAssigned += 1;
                    roomValues[ROOMS_TAKEN] += 1;
                    break;
                case "3-star":
                    Object[] keysThreeStar = roomToRatesThreeStars.keySet().toArray();
                    room = (String) keysThreeStar[(int) (Math.random() * keysThreeStar.length)];

                    roomValues = roomToRatesThreeStars.get(room);

                    if (occupancy > roomValues[OCCUPANCY_MAX] || occupancy < roomValues[OCCUPANCY_MIN]
                            || roomValues[ROOMS_TAKEN] > roomValues[MAX_ROOMS]) {
                        room = "";
                        continue;
                    }

                    roomsAssigned += 1;
                    roomValues[ROOMS_TAKEN] += 1;
                    break;
            }

            result.append(room).append(",");
            result.append(Integer.toString(occupancy)).append(",");
            cost += getCostOfRoom(roomValues, checkInDate, checkOutDate);
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
        int year = 2021;
        calendar.set(Calendar.YEAR, year);

        // Set random day of the year
        int dayOfYear = randomBetween(1, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
        calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);

        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
    }

    private static String getRandomizeDate(GregorianCalendar calendar, String referenceDate, int tolerance) {
        String[] dateValues = referenceDate.split("-");

        // Set year
        calendar.set(Calendar.YEAR, Integer.parseInt(dateValues[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(dateValues[1]) - 1);
        calendar.set(Calendar.DATE, Integer.parseInt(dateValues[2]));

        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);

        // Set random day of the year within a tolerance
        dayOfYear = randomBetween(dayOfYear - tolerance, dayOfYear + tolerance);
        calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);

        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
    }

    private static int getCostOfRoom(int[] rates, String checkIn, String checkOut) {
        // To get the cost, I need to get the days of the different dates
        // I need to get the costs of every room then add them at the end
        String[] dateValuesIn = checkIn.split("-");
        String[] dateValuesOut = checkOut.split("-");

        LocalDate checkInDate = LocalDate.of(Integer.parseInt(dateValuesIn[0]), Integer.parseInt(dateValuesIn[1]), Integer.parseInt(dateValuesIn[2]));
        LocalDate checkOutDate = LocalDate.of(Integer.parseInt(dateValuesOut[0]), Integer.parseInt(dateValuesOut[1]), Integer.parseInt(dateValuesOut[2]));

        int result = 0;

        // Iterate through the dates
        while (checkInDate.compareTo(checkOutDate) < 0) {
            // checkInDate.getDayOfWeek().getValue() returns an int
            // 1 is Monday, 7 is Sunday
            result += rates[2 + checkInDate.getDayOfWeek().getValue()];
            checkInDate = checkInDate.plusDays(1);
        }

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
