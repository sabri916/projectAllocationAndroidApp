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
    final static private int DB_VERSION = 4;

    private static DbHelper instance;

    public Context context;

    private DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    public static DbHelper getInstance(Context context){
        if(instance == null){
            instance = new DbHelper(context);
            return instance;
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbDestroyer(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dbDestroyer(db);
    }

    public void dbDestroyer(SQLiteDatabase db){
        try {
            db.execSQL(IdeasDbRepo.getCreateTableQuery());
            db.execSQL(TagDbRepo.getCreateTableQuery());
            db.execSQL(IdeasTagRelationshipRepo.getCreateTableQuery());
            Log.i("DB","Tables created");
        }catch(SQLException e){
            Log.i("DB","Error Creating table");
            Log.i("DB", e.getMessage());
        }
    }

}
