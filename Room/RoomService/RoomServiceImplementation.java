package Room.RoomService;

import Constants.CinemaConstants;
import Room.Room;
import Seat.Seat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class RoomServiceImplementation implements RoomService{
    public long getNumberOfTicketsPurchased(Room room){
        return room.getStreamOfSeats()
                .filter(seat -> seat.isReserved())
                .count();
    }

    public BigDecimal getPercentageOccupied(Room room){
        BigDecimal numberOfTicketsPurchased = BigDecimal.valueOf(getNumberOfTicketsPurchased(room));
        BigDecimal numberOfSeats = BigDecimal.valueOf(room.getNumberOfSeats());
        return numberOfTicketsPurchased.multiply(BigDecimal.valueOf(100)).divide(numberOfSeats, 2,RoundingMode.HALF_UP);
    }

    public BigDecimal getSumPriceOfReservedTickets(Room room){
        return room.getStreamOfSeats()
                .filter(seat -> seat.isReserved())
                .map(seat -> seat.getPrice())
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public BigDecimal getPotentialIncome(Room room){
        return room.getStreamOfSeats()
                .map(seat -> seat.getPrice())
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public boolean reserveSeat(Room room, int row, int seatInRow){
        Seat seat = room.getSeat(row, seatInRow);
        if (seat != null && !seat.isReserved()){
            seat.setReserved(true);
            return true;
        }
        return false;
    }

    public String getOverviewOfRoom(Room room) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int row = room.getRows() - 1; row >= 0; row--){
            stringBuilder.append(row + 1 + ".\t");
            for(Seat seat : room.getSeats()[row]){
                stringBuilder.append(seat.isReserved() ? "R" : "A");
                stringBuilder.append(" ");
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append(getScreenString(room.getSeatsPerRow()));
        return stringBuilder.toString();
    }

    private String getScreenString(int seatsPerRow){
        StringBuilder result = new StringBuilder();
        int screenSize = CinemaConstants.SCREEN.length();
        int paddingNeeded = Math.max(2 * seatsPerRow - 1 - screenSize,0);
        result.append("\t");
        for (int i = 0; i < paddingNeeded/2; i++) result.append("-");
        result.append(CinemaConstants.SCREEN);
        for (int i = 0; i < paddingNeeded - paddingNeeded/2; i++) result.append("-");
        return result.toString();
    }

}
