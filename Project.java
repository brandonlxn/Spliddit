//
// The purpose of this class is to create an object type Project which we can use to store our relevant project information.
// As of now Votes is a separate class as we were fiddling with ways to store voting / voter information (meaning that 
// the Vote class used to contain more information types) thus we will work on improving this in the next deliverable

public class Project {
    private String projectName;
    private int numberTeamMembers;
    private String[] listOfTeamMembers;
    private Votes[] listOfVotes;

    public Project(String projectName, int numberTeamMembers, String[] listOfTeamMembers, Votes[] listOfVotes) {
        this.projectName = projectName;
        this.numberTeamMembers = numberTeamMembers;
        this.listOfTeamMembers = listOfTeamMembers;
        this.listOfVotes = listOfVotes;
    }

    public String getProjectName() {
        return projectName;
    }

    public int getNumberTeamMembers() {
        return numberTeamMembers;
    }

    public String[] getListOfTeamMembers() {
        return listOfTeamMembers;
    }
  
    public Votes[] getListOfVotes() {
        return listOfVotes;
    }

}