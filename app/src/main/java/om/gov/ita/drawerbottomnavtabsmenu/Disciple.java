package om.gov.ita.drawerbottomnavtabsmenu;

import java.util.ArrayList;

/**
 * Created by training-4 on 5/24/16.
 */
public class Disciple extends Person {

    private ArrayList<Skill> skillArrayList;

    public Disciple(String name, String speciality) {
        super(name, speciality);
    }

    public ArrayList<Skill> getSkillArrayList() {
        return skillArrayList;
    }

    public void setSkillArrayList(ArrayList<Skill> skillArrayList) {
        this.skillArrayList = skillArrayList;
    }
}
