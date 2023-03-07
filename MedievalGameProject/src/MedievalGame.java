import java.util.Scanner;
import java.util.Objects;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class MedievalGame {

    /* Instance Variables */
    private Player player;
    private static final long serialVersionUID = 1L;
    /* Main Method */
    public static void main(String[] args)  {

        Scanner console = new Scanner(System.in);
        MedievalGame game = new MedievalGame();

        game.player = game.start(console);

        game.addDelay(500);
        System.out.println("\nLet's take a quick look at you to make sure you're ready to head out the door.");
        System.out.println(game.player);

        game.addDelay(1000);
        System.out.println("\nWell, you're off to a good start, let's get your game saved so we don't lose it.");
        game.save();

        game.addDelay(2000);
        System.out.println("We just saved your game...");
        System.out.println("Now we are going to try to load your character to make sure the save worked...");

        game.addDelay(1000);
        System.out.println("Deleting character...");
        String charName = game.player.getName();
        game.player = null;

        game.addDelay(1500);
        game.player = game.load(charName, console);
        System.out.println("Loading character...");

        game.addDelay(2000);
        System.out.println("Now let's print out your character again to make sure everything loaded:");

        game.addDelay(500);
        System.out.println(game.player);
    } // End of main

    /* Instance Methods */
    private Player start(Scanner console) {
        // Add start functionality here
        Player player;
        System.out.println("Welcome to your latest adventure!");
        System.out.println("Tell me traveler, have you been here before?");
        System.out.print("   Enter 'y' to load a game, 'n' to create a new game: ");

        Art.homeScreen();

        String answer = console.next().toLowerCase();
        while (true) {
            addDelay(500);
            if (answer.equals("y")) {
                System.out.print("\nAhh... I knew I remembered you, what was your name again? Let me see if I can find your backpack: ");
                player = load(console.next(), console);
                break;
            } else if (answer.equals("n")) {
                System.out.print("\nWell then, don't be shy, go ahead and tell me your name: ");
                String possibleName = console.next();
                while (true) {
                    System.out.println("Welcome " + possibleName + ", am I pronouncing that correctly? (Enter 'y' to confirm, 'n' to enter a new name");
                    String nameResponse = console.next().toLowerCase();
                    if (Objects.equals(nameResponse, "y")) break;
                    System.out.println("So sorry, can you spell it for me again?");
                    possibleName = console.next();
                }
                player = new Player(possibleName);
                break;
            } else {
                System.out.print("Sorry adventurer, I only speak the common tongue. Please enter 'y' to load a game or 'n' to start a new game: ");
                answer = console.next().toLowerCase();
            }
        }
        return player;
    } // End of start


    private void save() {
        //This takes care of the files name, while playerSaver.writeObject(this.player) writes the file.
        String fileName = this.player.getName() + ".svr";
        try {
            FileOutputStream userSaveFile = new FileOutputStream(fileName);
            //Step 13 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream); becomes:
            //The step below creates an object which writes to the text document using the properties of (this.player on line 64);
            ObjectOutputStream playerSaver = new ObjectOutputStream(userSaveFile);
            //Step 14 instead of  objectOutputStream.writeObject(toyota);, becomes:
            //playersave.writeObject(player) works as well.
            playerSaver.writeObject(this.player);
        } catch (IOException e)
        {
            System.out.println("There was an error saving your game, your file might not be available the next time you go toload a game");
        }
    }


    private Player load(String playerName, Scanner console) {
        // Add load functionality here
        Player loadedPlayer;
        try {
            FileInputStream userSaveFile = new FileInputStream(playerName + ".svr");
            ObjectInputStream playerLoader = new ObjectInputStream(userSaveFile);
            loadedPlayer = (Player) playerLoader.readObject();
        } catch (IOException | ClassNotFoundException e)
        {
            addDelay(1500);
            System.out.println("There was a problem loading your save Player. Stand by!");
            System.out.println("We've created another Player for you called " + playerName);
            addDelay(2000);
            loadedPlayer = new Player(playerName);
        }
        return loadedPlayer;
    }


    // Adds a delay to the console so it seems like the computer is "thinking"
    // or "responding" like a human, not instantly like a computer.
    private void addDelay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}