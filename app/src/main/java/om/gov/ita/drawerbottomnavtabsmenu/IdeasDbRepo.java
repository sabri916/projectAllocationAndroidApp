package om.gov.ita.drawerbottomnavtabsmenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by training-4 on 6/1/16.
 */
public class IdeasDbRepo {

    private Context context;

    //Ideas Table
    final static public String IDEA_TABLE_NAME = "ideas";
    final static public String IDEA_ID_COL = "id";
    public static final String IDEA_TITLE_COL = "title";
    public static final String IDEA_AUTHOR_COL = "author";
    public static final String IDEA_DESCRIPTION_COL = "description";
    public static final String IDEA_SUBMISSION_DATETIME_COL = "submission_date_time";
    public static final String IDEA_BODY_URL_COL = "body_url";

    public IdeasDbRepo(Context context){
        this.context = context;
    }

    public static String getCreateTableQuery(){
        return "CREATE table " + IDEA_TABLE_NAME +
                "(" + IDEA_ID_COL + " integer primary key autoincrement, "+
                IDEA_TITLE_COL + " text , " +
                IDEA_AUTHOR_COL + " integer , " +
                IDEA_DESCRIPTION_COL + " text , " +
                IDEA_SUBMISSION_DATETIME_COL + " numeric , " +
                IDEA_BODY_URL_COL + " text)";
    }

    public void insert(Proposal proposal) {

        SQLiteDatabase db = DbManager.getInstance(context).open();
        ContentValues values = new ContentValues();
        values.put(IDEA_TITLE_COL, proposal.getTitle());
        values.put(IDEA_AUTHOR_COL, proposal.getAuthor());
        values.put(IDEA_DESCRIPTION_COL, proposal.getDescription());
        values.put(IDEA_SUBMISSION_DATETIME_COL, proposal.getDateTime());
        values.put(IDEA_BODY_URL_COL, proposal.getUrl());

        // Inserting Row
        db.insert(IdeasDbRepo.IDEA_TABLE_NAME, IDEA_ID_COL, values);
        DbManager.getInstance(context).close();

    }
}
