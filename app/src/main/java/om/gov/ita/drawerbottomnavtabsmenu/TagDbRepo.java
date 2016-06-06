package om.gov.ita.drawerbottomnavtabsmenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.res.TypedArrayUtils;

import java.util.ArrayList;

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

    public long insert(String tag) {

        SQLiteDatabase db = DbManager.getInstance(context).open();
        ContentValues values = new ContentValues();
        values.put(TAG_TEXT_COL, tag);

        // Inserting Row
        long insertedId = db.insert(TAGS_TABLE_NAME, TAG_ID_COL, values);
        DbManager.getInstance(context).close();
        return insertedId;
    }

    public long[] insert(String[] tags) {

        SQLiteDatabase db = DbManager.getInstance(context).open();
        long[] idArray = new long[tags.length];

        for(int i = 0 ; i < tags.length; i++){
            ContentValues values = new ContentValues();
            values.put(TAG_TEXT_COL, tags[i]);
            idArray[i] = db.insert(TAGS_TABLE_NAME, TAG_ID_COL, values);
        }
        DbManager.getInstance(context).close();
        return idArray;
    }

    public ArrayList<String> getAllTags(){
        SQLiteDatabase db = DbManager.getInstance(context).open();
        String[] columnArray = {TAG_TEXT_COL};
        Cursor cursor = db.query(TAGS_TABLE_NAME,columnArray,null,null,null,null,TAG_TEXT_COL);

        ArrayList<String> tagList = new ArrayList<String>();

        cursor.moveToFirst();
        do{
            tagList.add(cursor.getString(cursor.getColumnIndex(TAG_TEXT_COL)));
        }
        while(cursor.moveToNext());
        DbManager.getInstance(context).close();
        return tagList;
    }
}
