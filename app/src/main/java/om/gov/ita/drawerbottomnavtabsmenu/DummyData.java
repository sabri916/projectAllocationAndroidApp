package om.gov.ita.drawerbottomnavtabsmenu;

import java.util.ArrayList;

/**
 * Created by training-4 on 5/26/16.
 */
public class DummyData {

    private static String[] titles = {"Project Management Android Application", "Blue", "Green", "Red", "Orange",
            "Yellow", "Blue", "Green", "Red", "Orange",
            "Yellow", "Blue", "Green", "Red", "Orange",
            "Yellow", "Blue", "Green", "Red", "Orange",
            "Yellow", "Blue", "Green", "Red", "Orange",
            "Yellow", "Blue", "Green", "Red"};

    public static ArrayList<Proposal> createDummyProposals() {
        ArrayList<Proposal> dummyProposals = new ArrayList<Proposal>();
        for(int i=0; i<titles.length; i++){
            if(i%2 == 0){
                dummyProposals.add(new Proposal(titles[i],"Sabri K","Proposal Description Proposal Description Proposal Description Proposal Description Proposal Description Proposal Description Proposal Description Proposal Description Proposal Description ",true));

            }
            else{
                dummyProposals.add(new Proposal(titles[i],"Sabri K","Proposal Description Proposal Description Proposal Description Proposal Description Proposal Description Proposal Description Proposal Description Proposal Description Proposal Description ",false));
            }
        }
        return dummyProposals;
    }

    public static ArrayList<Person> createDummyPersons() {
        ArrayList<Person> dummyPersons = new ArrayList<Person>();
        for(int i=0; i<20 ; i++){
            if(i%2 == 0){
                dummyPersons.add(new Person("Sabri K","Computer Science"));

            }
            else{
                dummyPersons.add(new Person("Ian Grimstead","Discrete Maths"));
            }
        }
        return dummyPersons;
    }
}
