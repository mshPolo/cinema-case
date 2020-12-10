package Room.RoomService;

import Room.Room;

import java.math.BigDecimal;

public interface RoomService {
    public long getNumberOfTicketsPurchased(Room room);
    public BigDecimal getPercentageOccupied(Room room);
    public BigDecimal getSumPriceOfReservedTickets(Room room);
    public BigDecimal getPotentialIncome(Room room);
    public String getOverviewOfRoom(Room room);
    public boolean reserveSeat(Room room, int row, int seatInRow);
}
