package Room;

import Constants.CinemaConstants;
import Seat.Seat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Room {
    String name;
    private int rows;
    private int seatsPerRow;
    private Seat[][] seats;

    public Room(int rows, int seatsPerRow, String name){
        this.name = name;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.seats = new Seat[rows][seatsPerRow];
        createSeats(this.rows,this.seatsPerRow);
    }


    private void createSeats(int rows, int seatsPerRow){
        boolean splitPrice = rows * seatsPerRow > 50;
        for (int row = 0; row < rows; row++){
            BigDecimal price = splitPrice && row <= rows/2 ? CinemaConstants.FRONT_ROW_COST_AT_SPLIT_CAPACITY : CinemaConstants.REGULAR_COST;
            createRowOfSeats(row,price);
        }
    }

    private void createRowOfSeats(int row, BigDecimal price){
        for(int seatNumber = 0; seatNumber < seatsPerRow; seatNumber++){
            seats[row][seatNumber] = new Seat(price);
        }
    }

    public boolean isAvailable(int row, int seatInRow){
        if (row >= 0 && row < rows && seatsPerRow >= 0 && seatInRow < seatsPerRow){
            return !seats[row][seatInRow].isReserved();
        }
        return false;
    }

    public int getNumberOfSeats(){
        return rows * seatsPerRow;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public void setSeatsPerRow(int seatsPerRow) {
        this.seatsPerRow = seatsPerRow;
    }

    public Seat[][] getSeats() {
        return seats;
    }

    public void setSeats(Seat[][] seats) {
        this.seats = seats;
    }

    public Stream<Seat> getStreamOfSeats(){
        return Arrays.stream(seats).flatMap(row -> Arrays.stream(row));
    }

    public Seat getSeat(int row, int seatInRow){
        return row < rows && seatInRow < seatsPerRow ? seats[row][seatInRow] : null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
