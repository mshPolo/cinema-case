package UI.UserInputService;

import Cinema.Cinema;

import java.io.BufferedReader;
import java.io.IOException;

public interface UserInputService {
    public int getPositiveIntegerFromUser(BufferedReader reader, String inputQuery) throws IOException;
    public String getAnyStringFromUser(BufferedReader reader, String inputQuery) throws IOException;
    public int getRoomFromUser(BufferedReader reader, Cinema cinema) throws IOException;
    public int getNumberFromUserBetween(BufferedReader reader, int min, int max, String inputQuery, String inputErrorMessage) throws IOException;
    public int offsetInput(int input);
    public boolean getConfirmation(BufferedReader reader, String inputQuery) throws IOException ;
}
