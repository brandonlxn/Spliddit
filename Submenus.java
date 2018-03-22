// 
// This class contains and executes each submenu function

import java.util.Scanner;
import java.util.Arrays;
import java.lang.Exception;

public class Submenus {

    static String[] listOfTeamMembers;
    static int projectListSize = 0, voteListSize = 0;
    static final int LIST_LENGTH = 1000;
    static Scanner in = new Scanner(System.in);
    
    static Project[] listOfProjects = new Project[LIST_LENGTH];
    static Votes[] listOfScores = new Votes[LIST_LENGTH];

    // Returns the "About" blurb when prompted from main menu
    
    public static void about() {
        System.out.println("\n\tThe purpose of the application is to help teams allocate\n"
                + "\tthe credit for a project fairly so that all parties are\n"
                + "\tsatisfied with the outcome. The idea is inspired by the\n"
                + "\twork of Ariel Procaccia, a professor, and Jonathan Goldman,\n"
                + "\ta student, who were both at Carnegie Mellon University in\n"
                + "\tthe USA (Jonathan now works for Facebook). They went on to\n"
                + "\tproduce an application called Spliddit which offers provably\n"
                + "\tfair solutions for a variety of division problems including\n"
                + "\trent payments, restaurant bills and shared tasks. If you are\n"
                + "\tinterested, more information about Spliddit can be found in\n"
                + "\ta recent article published in XRDS which is a Computer Science\n"
                + "\tmagazine for students.\n\n");
        PressEnterToExit.pressAnyKeyToContinue();
    }

    // This method creates each project
  
    public static void createProject() {
        
        String projectName, createAnotherProject;
        int numberTeamMembers = 0;
        boolean programmeRunning = true, optionChecker = false, validInput = false;

        while (programmeRunning) {
            System.out.print("\nEnter the project name: ");
            projectName = in.next();

            System.out.print("\nEnter the number of team members: ");
          
            // Following lines included to ensure that only integer inputs are accepted
          
            while (!validInput) {
                try {
                    numberTeamMembers = in.nextInt();
                    validInput = true;
                }
                catch (Exception e) {
                    in.next();
                    System.out.print("Please input a valid integer. ");
                }
            }
            
          
          
            listOfTeamMembers = new String[numberTeamMembers];
          
            // Receives as many team member names as there are team members

            for (int teamCounter = 0; teamCounter < numberTeamMembers; teamCounter++) {
                System.out.print("\n\tEnter the first name of team member " + (teamCounter+1) + ": ");
                listOfTeamMembers[teamCounter] = in.next();
            }
          
            // Appends all this information to a specific listOfProjects index
            // The projectListSize increases every time a project is created so that the next time a user returns
            // to create a project, the previous one will not be overwritten

            listOfProjects[projectListSize] = new Project(projectName, numberTeamMembers, listOfTeamMembers);
            projectListSize++;

            if (projectListSize >= LIST_LENGTH) {
                listOfProjects = Arrays.copyOf(listOfProjects, 2 * listOfProjects.length);
            }
          
            // Checks if the user wants to create another project and only accepts 'y' or 'n'

            while (!optionChecker) {
                System.out.print("\nWould you like to create a new project? (y/n) ");
                createAnotherProject = in.next().toLowerCase();

                switch (createAnotherProject) {
                  case "y": 
                      programmeRunning = true;
                      optionChecker = true;
                      break;
                  case "n": 
                      PressEnterToExit.pressAnyKeyToContinue();
                      optionChecker = true;
                      programmeRunning = false;
                      break;
                  default: 
                      System.out.print("\nUnknown command, please try again.");
                      break;
                }
            }
            
        }

    }
  
    // Enables us to call this information in other classes
  
    public static Project[] getListOfProjects() {
        return listOfProjects;
    }
  
    public static int getProjectListSize() {
        return projectListSize;
    }

    public static void enterVotes() {
        String projectName, voteAgain, nameOfVoter;
        int voteCounter = 0, voteChecker = 0;
        final int MAX_SCORE = 100;
        boolean voteRunning = true, optionChecker = false, validInput = false;
        
        while (voteRunning) {
            System.out.print("\nEnter the project name: ");
            projectName = in.next();
          
            // This for loop will run through every project that has been created and check if the entered project name exists
          
            for (int projectListCounter = 0; projectListCounter < (projectListSize); projectListCounter++) {
                if (projectName.equals(listOfProjects[projectListCounter].getProjectName())) {
                    String[] nameOfCurrentMember = listOfProjects[projectListCounter].getListOfTeamMembers();
                    int[] votesForAllMembers = new int[LIST_LENGTH];
                    int numberTeamMembers = listOfProjects[projectListCounter].getNumberTeamMembers();
                    System.out.println("\nThere are " + numberTeamMembers + " members.");
                  
                    // This for loop enables us to enter the scores that one member gave the other members
                  
                    for (int nameCounter = 0; nameCounter < numberTeamMembers; nameCounter++) {
                        nameOfVoter = nameOfCurrentMember[nameCounter];
                        boolean maxVoteChecker = false;
                        System.out.println("\n\nEnter " + nameOfVoter + "'s votes, points must add up to 100:\n\n");
                        
                        // This while loop with a boolean maxVoteChecker forces the user to enter votes that total 100
                      
                        while (!maxVoteChecker) {
                            voteChecker = 0;
                            voteCounter = (nameCounter*(numberTeamMembers-1));
                          
                            // This for loop enables us to obtain the votes that one member gave the others.
                            // This information is stored in series, which is dependent on the total number of team members.
                            // For example, in a team of 3, Member1's scores are stored in indexes 0 and 1,
                            // Member2's scores are stored in indexes 2 and 3, and Member3's scores are stored in 4 and 5.
                            // This is achieved by voteCounter = (nameCounter*(numberTeamMembers-1)), starting each input 
                            // in the lower number that each member is assigned, then for each other member voteCounter += 1
                            // in the below for loop, to store the corresponding values in subsequent indexes
                          
                            for (int teamMemberCounter = 0; teamMemberCounter < numberTeamMembers; teamMemberCounter++) {
                                
                                if (nameOfVoter != nameOfCurrentMember[teamMemberCounter]) {
                                    System.out.print("\tEnter " + nameOfVoter + "'s points for ");
                                    System.out.print(nameOfCurrentMember[teamMemberCounter] + ": ");
                                    validInput = false;
                                    while (!validInput) { // Same as above
                                        try {
                                            votesForAllMembers[voteCounter] = in.nextInt();
                                            validInput = true;
                                        }
                                        catch (Exception e) {
                                            in.next();
                                            System.out.print("\tPlease input a valid integer. ");
                                        }
                                    }
                                    voteChecker += votesForAllMembers[voteCounter];
                                    voteCounter += 1;
                                 }
                             }
                             if (voteChecker == MAX_SCORE) {
                                 maxVoteChecker = true;
                             }
                             else {
                                 System.out.println("\nVotes do not add up to 100. Please enter again.\n");
                             }
                          }
                      }
                      listOfScores[projectListCounter] = new Votes(votesForAllMembers);
                    }
                 else;
               }

            while (!optionChecker) { // same as above
                System.out.print("\nWould you like to enter votes for another project? (y/n) ");
                voteAgain = in.next().toLowerCase();
                switch (voteAgain) { 
                case "y": 
                    voteRunning = true;
                    optionChecker = true;
                    break;
                case "n": 
                    PressEnterToExit.pressAnyKeyToContinue();
                    voteRunning = false;
                    optionChecker = true;
                    break;
                default: 
                    System.out.print("\nUnknown command, please try again.");
                    break;
              }
            }
            
         }
      }
  
    public static Votes[] getListOfScores() {
        return listOfScores;
      }
}


  
    