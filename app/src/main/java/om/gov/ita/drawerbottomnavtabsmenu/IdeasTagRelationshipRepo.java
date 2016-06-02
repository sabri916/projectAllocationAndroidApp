package om.gov.ita.drawerbottomnavtabsmenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;

/**
 * Created by training-4 on 6/2/16.
 */
public class IdeasTagRelationshipRepo {

    private Context context;

    final static public String IDEA_TAG_TABLE_NAME = "ideas_tag_relationship";
    final static public String IDEA_ID_COL = "idea_id";
    final static public String TAG_ID_COL = "tag_id";

    public IdeasTagRelationshipRepo(Context context) {
        this.context = context;
    }

    public static String getCreateTableQuery(){
        return "CREATE table " + IDEA_TAG_TABLE_NAME +
                "(" + IDEA_ID_COL + " integer, " +
                TAG_ID_COL + " text, " +
                "FOREIGN KEY(" + IDEA_ID_COL +
                ") REFERENCES " + IdeasDbRepo.IDEA_TABLE_NAME + "(" + IdeasDbRepo.IDEA_ID_COL + "), " +
                "FOREIGN KEY(" + TAG_ID_COL +
                ") REFERENCES " + TagDbRepo.TAGS_TABLE_NAME + "(" + TagDbRepo.TAG_ID_COL +
                "))";
    }

    public void insert(String ideaId, String TagId){
        SQLiteDatabase db = DbManager.getInstance(context).open();
        ContentValues values = new ContentValues();
        values.put(IDEA_ID_COL, ideaId);
        values.put(TAG_ID_COL, TagId);

        db.insert(IDEA_TAG_TABLE_NAME, null, values);
        DbManager.getInstance(context).close();
    }
}
