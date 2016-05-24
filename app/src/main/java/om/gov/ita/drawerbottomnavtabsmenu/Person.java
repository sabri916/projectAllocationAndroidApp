package om.gov.ita.drawerbottomnavtabsmenu;

/**
 * Created by training-4 on 5/24/16.
 */
public class Person {
    private String name;
    private String speciality;
    private String email;
    private String phoneNumber;
    private String interests;
    private String bio;

    public Person(String name, String speciality) {
        this.name = name;
        this.speciality = speciality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
