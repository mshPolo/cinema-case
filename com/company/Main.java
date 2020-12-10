package com.company;

import Cinema.Cinema;
import Room.Room;
import Room.RoomService.RoomService;
import Room.RoomService.RoomServiceImplementation;
import UI.UserInterface;

import java.io.IOException;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) throws IOException {
        Cinema cinema = new Cinema();
        UserInterface userInterface = new UserInterface(cinema);
        userInterface.run();
    }
}
