package om.gov.ita.drawerbottomnavtabsmenu;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by training-4 on 6/1/16.
 */
public class DbHelper extends SQLiteOpenHelper {
    final static private String DB_NAME = "project_allocation.sqlite";
    final static private int DB_VERSION = 1;
    public Context cxt;

    //Ideas Table
    final static public String IDEA_TABLE_NAME = "ideas";
    final static public String IDEA_ID = "id";
    public static final String IDEA_TITLE = "title";
    public static final String IDEA_AUTHOR = "author";
    public static final String IDEA_DESCRIPTION = "description";
    public static final String IDEA_SUBMISSION_DATETIME = "submission_date_time";
    public static final String IDEA_BODY_URL = "body_url";

    //Other Table


    public DbHelper(Context cxt) {
        super(cxt, DB_NAME, null, DB_VERSION);
        this.cxt = cxt;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Ideas Table
        String q = "CREATE table " + IDEA_TABLE_NAME +
                "(" +  IDEA_ID + " integer primary key, "+
                IDEA_TITLE + " text , " +
                IDEA_AUTHOR + " integer , " +
                IDEA_DESCRIPTION + " text , " +
                IDEA_SUBMISSION_DATETIME + " numeric , " +
                IDEA_BODY_URL + " text)";

        try {
            db.execSQL(q);
        }catch(SQLException e){
            Log.i("DB","Error Creating Ideas table");
            Log.i("DB", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
