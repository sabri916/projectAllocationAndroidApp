package om.gov.ita.drawerbottomnavtabsmenu;

/**
 * Created by training-4 on 5/17/16.
 */
public class Proposal {
    private String title;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    private String author;
    private String description;
    private boolean isFavourite;

    public Proposal(String title, String author, boolean isFavourite) {
        this.title = title;
        this.author = author;
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
