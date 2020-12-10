package Main;

import Cinema.Cinema;
import UI.UserInterface;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Cinema cinema = new Cinema();
        UserInterface userInterface = new UserInterface(cinema);
        userInterface.run();
    }
}
