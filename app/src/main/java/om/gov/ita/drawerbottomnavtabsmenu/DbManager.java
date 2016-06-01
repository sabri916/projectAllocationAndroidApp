package om.gov.ita.drawerbottomnavtabsmenu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by training-4 on 6/1/16.
 */
public class DbManager {

    private static DbManager instance;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public DbManager(Context context) {
        this.context = context;
    }

    public static synchronized DbManager getInstance(Context context){
        if(instance == null){
            instance = new DbManager(context);
        }
        return instance;
    }

    public synchronized SQLiteDatabase open(){
        sqLiteDatabase = DbHelper.getInstance(context).getWritableDatabase();
        return sqLiteDatabase;
    }

    public synchronized void close(){
        sqLiteDatabase.close();
    }
}
