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

    private static DbHelper instsance;

    public Context context;

    private DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    public static DbHelper getInstance(Context context){
        if(instsance == null){
            instsance = new DbHelper(context);
            return instsance;
        }
        return instsance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(IdeasDbRepo.getCreateTableQuery());
        }catch(SQLException e){
            Log.i("DB","Error Creating Ideas table");
            Log.i("DB", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
