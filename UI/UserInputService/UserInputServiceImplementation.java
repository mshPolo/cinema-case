package UI.UserInputService;

import Cinema.Cinema;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.function.Function;
import java.util.function.Predicate;

public class UserInputServiceImplementation implements UserInputService{

    public int getPositiveIntegerFromUser(BufferedReader reader, String inputQuery) throws IOException {
        return getIntegerFromUser(reader, inputQuery, x -> x > 0, "Error: The input should be a positive integer");
    }

    public int getNumberFromUserBetween(BufferedReader reader, int min, int max, String inputQuery, String inputErrorMessage) throws IOException {
        return getIntegerFromUser(reader, inputQuery, x -> x >= min && x <= max, inputErrorMessage);
    }

    public int offsetInput(int input){
        return input - 1;
    }

    private int getIntegerFromUser(BufferedReader reader, String inputQuery, Predicate<Integer> predicate, String inputErrorMessage) throws IOException {
        while (true) {
            try {
                System.out.println(inputQuery);
                String userInput = reader.readLine();
                userInput = userInput.trim();
                int i = Integer.parseInt(userInput);
                if (predicate.test(i)) {
                    return i;
                } else {
                    System.out.println(inputErrorMessage);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: input has the wrong format");
            }
        }
    }

    public String getAnyStringFromUser(BufferedReader reader, String inputQuery) throws IOException {
        System.out.println(inputQuery);
        String userInput = reader.readLine();
        return userInput;
    }

    public int getRoomFromUser(BufferedReader reader, Cinema cinema) throws IOException {
        for(int i = 0; i < cinema.getNumberOfRooms(); i++){
            System.out.println((i + 1) + ".\t" + cinema.getRoom(i).getName());
        }
        String inputQuery = "Please select a room (enter the number of the room to select a room) use 0 to go back";
        String inputErrorMessage = "Error: No room with the given number";
        int userInput = getNumberFromUserBetween(reader, 0, cinema.getNumberOfRooms(), inputQuery,  inputErrorMessage);
        return userInput - 1;
    }

    @Override
    public boolean getConfirmation(BufferedReader reader, String inputQuery) throws IOException {
        while (true) {
            try {
                System.out.println(inputQuery);
                String userInput = reader.readLine();
                userInput = userInput.trim();
                if(userInput.equals("y")) return true;
                else if (userInput.equals("n")) return false;
            } catch (NumberFormatException e) {
                System.out.println("Error: input has the wrong format");
            }
        }
    }
}