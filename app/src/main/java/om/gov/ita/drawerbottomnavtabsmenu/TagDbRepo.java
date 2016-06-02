package om.gov.ita.drawerbottomnavtabsmenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by training-4 on 6/2/16.
 */
public class TagDbRepo {

    private Context context;

    //Tag Table
    final static public String TAGS_TABLE_NAME = "tags";
    final static public String TAG_ID_COL = "id";
    public static final String TAG_TEXT_COL = "title";

    public TagDbRepo(Context context) {
        this.context = context;
    }

    public static String getCreateTableQuery(){
        return "CREATE table " + TAGS_TABLE_NAME +
                "(" + TAG_ID_COL + " integer primary key autoincrement, "+
                TAG_TEXT_COL + " text)";
    }

    public void insert(String tag) {

        SQLiteDatabase db = DbManager.getInstance(context).open();
        ContentValues values = new ContentValues();
        values.put(TAG_TEXT_COL, tag);

        // Inserting Row
        db.insert(IdeasDbRepo.IDEA_TABLE_NAME, TAG_ID_COL, values);
        DbManager.getInstance(context).close();

    }
}
