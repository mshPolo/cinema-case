package UI;
import Cinema.Cinema;
import Room.Room;
import Room.RoomService.RoomService;
import Room.RoomService.RoomServiceImplementation;
import UI.UserInputService.UserInputService;
import UI.UserInputService.UserInputServiceImplementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class UserInterface {
    BufferedReader reader;
    Cinema cinema;
    UserInputService userInputService;
    RoomService roomService;

    public UserInterface(Cinema cinema){
        this.cinema = cinema;
        reader = new BufferedReader(new InputStreamReader(System.in));
        userInputService = new UserInputServiceImplementation();
        roomService = new RoomServiceImplementation();
    }

    public void run() throws IOException {
        while(true){
            int action = selectAction();
            switch (action) {
                case 1:
                    createRoom();
                    break;
                case 2:
                    showStatusOfSeats();
                    break;
                case 3:
                    reserveSeat();
                    break;
                case 4:
                    showMetricsForRoom();
                    break;
                case 5:
                    return;
                default:
                    continue;
            }
        }
    }

    public int selectAction() throws IOException {
        System.out.println("1. Add room");
        System.out.println("2. Show seats for a room and their status");
        System.out.println("3. Reserve a ticket");
        System.out.println("4. Show output metrics for a room");
        System.out.println("5. Exit program");
        int action = userInputService.getNumberFromUserBetween(reader, 1,5,
                "Choose an action (enter a number to choose)",
                "Error: not a valid action");
        return action;
    }

    public void createRoom() throws IOException {
        int rows = userInputService.getPositiveIntegerFromUser(reader,"Please input the number of rows (must be a positive integer)");
        int seatsPerRow = userInputService.getPositiveIntegerFromUser(reader,"Please input the number of seats in each row (must be a positive integer)");
        String name = userInputService.getAnyStringFromUser(reader,"Please enter a name");
        cinema.addRoom(rows, seatsPerRow, name);
    }

    public void showStatusOfSeats() throws IOException {

        int userInput = userInputService.getRoomFromUser(reader, cinema);
        if (userInput == -1) return;
        printRoomStatus(cinema.getRoom(userInput));
    }

    public void showMetricsForRoom() throws IOException {
        int roomNumber = userInputService.getRoomFromUser(reader, cinema);
        if (roomNumber == -1) return;
        printMetricsForRoom(cinema.getRoom(roomNumber));
    }

    private void printRoomStatus(Room room){
        if (room == null) return;
        System.out.println(roomService.getOverviewOfRoom(room));
    }

    private void printMetricsForRoom(Room room){
        if (room == null) return;
        System.out.println("Number of tickets purchased: " + roomService.getNumberOfTicketsPurchased(room));
        System.out.println("Percentage of seats occupied: " + roomService.getPercentageOccupied(room) + "%");
        System.out.println("Current Income: " + roomService.getSumPriceOfReservedTickets(room));
        System.out.println("Potential income if all seats are reserved: " + roomService.getPotentialIncome(room));
    }

    private void reserveSeat() throws IOException{
        int roomNumber = userInputService.getRoomFromUser(reader, cinema);
        if (roomNumber == -1) return;
        Room room = cinema.getRoom(roomNumber);
        printRoomStatus(room);
        int inputForRow = userInputService.getNumberFromUserBetween(reader,1, room.getRows(),
                "Please input the row number",
                "Error: row does not exist");
        int row = userInputService.offsetInput(inputForRow);
        int inputForSeatNumber = userInputService.getNumberFromUserBetween(reader,1, room.getSeatsPerRow(),
                "Please input the seat number",
                "Error: seat does not exist");
        int seatNumber = userInputService.offsetInput(inputForSeatNumber);
        if (!room.isAvailable(row, seatNumber)){
            System.out.println("seat is not available");
            return;
        }
        BigDecimal price = room.getSeat(row,seatNumber).getPrice();
        boolean confirmation = userInputService.getConfirmation(reader,"Price of the seat is $" + price.toString() + " confirm?[y/n]");
        if (!confirmation){
            System.out.println("Reveration cancelled");
            return;
        }
        boolean succeeded = roomService.reserveSeat(room, row, seatNumber);
        System.out.println(succeeded ? "seat reserved" : "seat is not available");
    }
}
