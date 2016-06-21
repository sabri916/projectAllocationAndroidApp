package om.gov.ita.drawerbottomnavtabsmenu;

import java.util.Map;

/**
 * Created by training-4 on 5/17/16.
 */
public class Proposal {
    private String ideaKey;
    private String title;
    private String authorUid;
    private String description;
    private Map dateTime;
    private String url;
    private boolean isFavourite;

    public String getIdeaKey() {
        return ideaKey;
    }

    public void setIdeaKey(String ideaKey) {
        this.ideaKey = ideaKey;
    }

    public Map getDateTime() {
        return dateTime;
    }

    public void setDateTime(Map dateTime) {
        this.dateTime = dateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthorUid() {
        return authorUid;
    }

    public void setAuthorUid(String authorUid) {
        this.authorUid = authorUid;
    }

    public Proposal(){}

    public Proposal(String title, String authorUid) {
        this.title = title;
        this.authorUid = authorUid;
        this.isFavourite = isFavourite;
    }

    public Proposal(String title, String authorUid, String description, Map dateTime, String url) {
        this.title = title;
        this.authorUid = authorUid;
        this.description = description;
        this.dateTime = dateTime;
        this.url = url;
        this.isFavourite = isFavourite;
    }

    public Proposal(String title, String authorUid, String description, boolean isFavourite) {
        this.title = title;
        this.authorUid = authorUid;
        this.description = description;
        this.isFavourite = isFavourite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}
