// Code adapted from File IO lab exercise

import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class SaveToFile {
    public static void export () {
        Project [] listOfProjects = Submenus.getListOfProjects();
        Votes [] listOfVotes = Submenus.getListOfScores();
        int projectListSize = (Submenus.getProjectListSize());
      
        PrintWriter outputStream = null;
            try {
                outputStream 
                      = new PrintWriter(new FileOutputStream("projectinformation.txt"));
            } catch (FileNotFoundException e) {
                System.out.println("Error opening the file projectinformation.txt.");
                System.exit(0);
            }
        System.out.println("\nWriting project information to file projectinformation.txt.");
        
        // To check if there are any projects at all.
      
        if (projectListSize > 0) {
          
            // This for loop will run through every project that has been created
            // This works similarly to the enterVotes() method in Submenus
          
            for (int projectListCounter = 0; projectListCounter < projectListSize; projectListCounter++) {
            
            String projectName = listOfProjects[projectListCounter].getProjectName();
            int numberTeamMembers = listOfProjects[projectListCounter].getNumberTeamMembers();
            String [] listOfTeamMembers = listOfProjects[projectListCounter].getListOfTeamMembers();
            int[] currentVotes = listOfVotes[projectListCounter].getListOfScores();
          
            // This gets rid of the square brackets
            String listOfTeamMembersNoBrackets = (Arrays.toString(listOfTeamMembers)).substring(1, (Arrays.toString(listOfTeamMembers)).length()-1);
            
            outputStream.print(projectName + ", " + Integer.toString(numberTeamMembers) + ", " + listOfTeamMembersNoBrackets);
            outputStream.flush();
          
              // This for loop will append a member's name, other members' names and the voting scores the first member gave

              for (int nameCounter = 0; nameCounter < numberTeamMembers; nameCounter++) {
                  String nameOfVoter = listOfTeamMembers[nameCounter];
                  int voteCounter = (nameCounter*(numberTeamMembers-1));
                  outputStream.print(", " + nameOfVoter);
                  outputStream.flush();

                  // This for loop appends each other team member's name and their associated score, enabling the above for loop to work

                  for (int teamMemberCounter = 0; teamMemberCounter < numberTeamMembers; teamMemberCounter++) {

                      if (nameOfVoter != listOfTeamMembers[teamMemberCounter]) {
                          outputStream.print(", " + listOfTeamMembers[teamMemberCounter] + ", " + Integer.toString(currentVotes[voteCounter]));
                          outputStream.flush();
                          voteCounter++;
                       }
                   }
                 }
              }
              outputStream.println();
              outputStream.flush();
          }
          
          else {
              outputStream.println("There are no projects.");
              outputStream.flush();
          }
      
        outputStream.close();
        
        System.out.println("End of program.\n");
    }
}