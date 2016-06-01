package om.gov.ita.drawerbottomnavtabsmenu;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by training-4 on 5/17/16.
 */
public class Proposal {
    private String title;
    private String author;
    private String description;
    private long dateTime;
    private String url;
    private boolean isFavourite;

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Proposal(String title, String author, boolean isFavourite) {
        this.title = title;
        this.author = author;
        this.isFavourite = isFavourite;
    }

    public Proposal(String title, String author, String description, long dateTime, String url, boolean isFavourite) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.dateTime = dateTime;
        this.url = url;
        this.isFavourite = isFavourite;
    }

    public Proposal(String title, String author, String description, boolean isFavourite) {
        this.title = title;
        this.author = author;
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
