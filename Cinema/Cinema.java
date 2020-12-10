package Cinema;

import Room.Room;

import java.util.ArrayList;
import java.util.List;

public class Cinema {

    private List<Room> rooms;

    public Cinema(){
        this.rooms = new ArrayList<Room>();
    }

    public void addRoom(int rows, int seatPerRow, String name){
        Room room = new Room(rows, seatPerRow, name);
        rooms.add(room);
    }

    public int getNumberOfRooms(){
        return rooms.size();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Room getRoom(int index) {
        return index >= 0 && index < rooms.size() ? rooms.get(index) : null;
    }
}
