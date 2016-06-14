package om.gov.ita.drawerbottomnavtabsmenu;

import java.util.ArrayList;

/**
 * Created by training-4 on 5/24/16.
 */
public class Person {
    private String uid;
    private String name;
    private String speciality;
    private String email;
    private String phoneNumber;
    private String bio;
    private String avatarUrl;
    private String gender;
    private boolean isAvailable;
    private boolean isMentor;

    private ArrayList<String> interests;
    private ArrayList<String> skills;
    private ArrayList<String> favouriteIdeas;
    private ArrayList<String> teams;
    private ArrayList<String> ideas;
    private ArrayList<String> notifications;
    private ArrayList<String> personalTags;


    public Person(String name, String speciality) {
        this.name = name;
        this.speciality = speciality;
    }

    public Person() {
        //default blank constructor
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public ArrayList<String> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public ArrayList<String> getFavouriteIdeas() {
        return favouriteIdeas;
    }

    public void setFavouriteIdeas(ArrayList<String> favouriteIdeas) {
        this.favouriteIdeas = favouriteIdeas;
    }

    public ArrayList<String> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<String> teams) {
        this.teams = teams;
    }

    public ArrayList<String> getIdeas() {
        return ideas;
    }

    public void setIdeas(ArrayList<String> ideas) {
        this.ideas = ideas;
    }

    public ArrayList<String> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<String> notifications) {
        this.notifications = notifications;
    }

    public ArrayList<String> getPersonalTags() {
        return personalTags;
    }

    public void setPersonalTags(ArrayList<String> personalTags) {
        this.personalTags = personalTags;
    }

    public boolean isMentor() {
        return isMentor;
    }

    public void setMentor(boolean mentor) {
        isMentor = mentor;
    }
}
