package om.gov.ita.drawerbottomnavtabsmenu;

/**
 * Created by training-4 on 5/17/16.
 */
public class Proposal {
    private String title;
    private String description;
    private boolean isFavourite;

    public Proposal(String title, boolean isFavourite) {
        this.title = title;
        this.isFavourite = isFavourite;
    }

    public Proposal(String title, String description, boolean isFavourite) {
        this.title = title;
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
