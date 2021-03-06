// This class just handles the main menu function.

import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String option, returnMainMenu;
        boolean programmeRunning = true;

        while (programmeRunning) {
            System.out.println("\nWelcome to Split-it\n\n"
                    + "\tAbout (A)\n"
                    + "\tCreate Project (C)\n"
                    + "\tEnter Votes (V)\n"
                    + "\tShow Project (S)\n"
                    + "\tQuit (Q)\n");
            System.out.print("\tPlease choose an option: ");
            option = in.next().toLowerCase();
            
            // Handles the main menu choices. Respective functions are done in the Submenus class
          
            switch(option) {
                case "a": 
                    Submenus.about();
                    break;
                case "c":
                    Submenus.createProject();
                    break;
                case "q":
                    SaveToFile.export();
                    programmeRunning = false;
                    break;
                case "v":
                    Submenus.enterVotes();
                    break;
                case "s":
                    System.out.println("\tOption not yet included, please try again.");
                    break; // blank for now
                default:
                    System.out.println("\tUnknown option, please try again.");
                    break;
            }

        }
    }
}