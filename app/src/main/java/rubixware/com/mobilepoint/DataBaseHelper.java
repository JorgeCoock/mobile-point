package rubixware.com.mobilepoint;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jorge on 21/08/16.
 */
public class DataBaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "mobile_point_database";
    private static final int DATABASE_VERSION = 1;

    public static final String ADMIN_TABLE = "employee";

    // Admin fields
    public static final String ID_COLUMN = "id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    public static final String CREATE_ADMIN_TABLE = "CREATE TABLE "
            + ADMIN_TABLE + "(" + ID_COLUMN + " INTEGER PRIMARY KEY, "
            + USERNAME + " TEXT, " + PASSWORD + " TEXT)";

    private static DataBaseHelper instance;

    public static synchronized DataBaseHelper getHelper(Context context){
        if(instance == null)
            instance = new DataBaseHelper(context);
        return instance;
    }

    private DataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        if (!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    //Aquí se crea la bd
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_ADMIN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
