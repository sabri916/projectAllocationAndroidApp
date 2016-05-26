package om.gov.ita.drawerbottomnavtabsmenu;

import java.util.ArrayList;

/**
 * Created by training-4 on 5/26/16.
 */
public class Team {

    private String teamName;
    private Person founder;
    private ArrayList<Person> teamMembers;

    public Team(String teamName, Person founder, ArrayList<Person> teamMembers) {
        this.teamName = teamName;
        this.founder = founder;
        this.teamMembers = teamMembers;
    }

    public Team(String teamName, Person founder) {
        this.teamName = teamName;
        this.founder = founder;
        teamMembers = new ArrayList<Person>();
        teamMembers.add(founder);
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void addMember(Person person){
        teamMembers.add(person);
    }

    public void removeMember(Person person){
        teamMembers.remove(person);
    }

    public Person getFounder() {
        return founder;
    }

    public void setFounder(Person founder) {
        this.founder = founder;
    }
}
