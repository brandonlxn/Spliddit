// Code adapted from user E235 at https://stackoverflow.com/questions/19870467/how-do-i-get-press-any-key-to-continue-to-work-in-my-java-code
// 
// Since there is no actual attempt in the try block, 
// the catch block will always throw an exception which ends the programme, 
// however as System.in.read() requires pressing the Enter key to advance the programme, 
// the programme will only end after pressing the Enter key. 
// We append this method to the end of every submenu method where users subsequently return to the main menu. 
// This does not actually do anything, however it provides users with a more intuitive 
// interface than pressing any key and then Enter to exit to the main menu.
// 
public class PressEnterToExit {
    public static void pressAnyKeyToContinue() {
        System.out.print("\nPress Enter to return to the main menu ");
        try {
            System.in.read();
        }
        catch(Exception e) {}
    }
}