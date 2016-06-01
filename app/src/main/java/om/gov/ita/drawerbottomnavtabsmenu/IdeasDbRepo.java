package om.gov.ita.drawerbottomnavtabsmenu;

/**
 * Created by training-4 on 6/1/16.
 */
public class IdeasDbRepo {

    //Ideas Table
    final static public String IDEA_TABLE_NAME = "ideas";
    final static public String IDEA_ID = "id";
    public static final String IDEA_TITLE = "title";
    public static final String IDEA_AUTHOR = "author";
    public static final String IDEA_DESCRIPTION = "description";
    public static final String IDEA_SUBMISSION_DATETIME = "submission_date_time";
    public static final String IDEA_BODY_URL = "body_url";

    public IdeasDbRepo(){

    }

    public static String getCreateTableQuery(){
        return "CREATE table " + IDEA_TABLE_NAME +
                "(" +  IDEA_ID + " integer primary key, "+
                IDEA_TITLE + " text , " +
                IDEA_AUTHOR + " integer , " +
                IDEA_DESCRIPTION + " text , " +
                IDEA_SUBMISSION_DATETIME + " numeric , " +
                IDEA_BODY_URL + " text)";
    }
}
