package om.gov.ita.drawerbottomnavtabsmenu;

/**
 * Created by training-4 on 5/24/16.
 */
public class Skill {
    private String skillName;
    private int score;

    public Skill(String skillName, int score) {
        this.skillName = skillName;
        this.score = score;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
