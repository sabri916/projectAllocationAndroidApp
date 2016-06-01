package om.gov.ita.drawerbottomnavtabsmenu;

import android.app.Application;
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
    final static public String IDEA_ID = "id";
    public static final String IDEA_TITLE = "title";
    public static final String IDEA_AUTHOR = "author";
    public static final String IDEA_DESCRIPTION = "description";
    public static final String IDEA_SUBMISSION_DATETIME = "submission_date_time";
    public static final String IDEA_BODY_URL = "body_url";

    public IdeasDbRepo(Context context){
        this.context = context;
    }

    public static String getCreateTableQuery(){
        return "CREATE table " + IDEA_TABLE_NAME +
                "(" +  IDEA_ID + " integer primary key autoincrement, "+
                IDEA_TITLE + " text , " +
                IDEA_AUTHOR + " integer , " +
                IDEA_DESCRIPTION + " text , " +
                IDEA_SUBMISSION_DATETIME + " numeric , " +
                IDEA_BODY_URL + " text)";
    }

    public void insert(Proposal proposal) {

        SQLiteDatabase db = DbManager.getInstance(context).open();
        ContentValues values = new ContentValues();
        values.put(IDEA_TITLE, proposal.getTitle());
        values.put(IDEA_AUTHOR, proposal.getAuthor());
        values.put(IDEA_DESCRIPTION, proposal.getDescription());
        values.put(IDEA_SUBMISSION_DATETIME, proposal.getDateTime());
        values.put(IDEA_BODY_URL, proposal.getUrl());

        // Inserting Row
        db.insert(IdeasDbRepo.IDEA_TABLE_NAME, IDEA_ID, values);
        DbManager.getInstance(context).close();

    }
}
