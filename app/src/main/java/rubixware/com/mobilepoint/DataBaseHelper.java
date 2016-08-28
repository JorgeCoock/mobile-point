package rubixware.com.mobilepoint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import java.util.ArrayList;


/**
 * Created by jorge on 21/08/16.
 */

public class DataBaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "mobile_point_DB.db";
    private static final int DATABASE_VERSION = 1;


    // ADMIN  fields
    public static final String TABLE_ADMINS = "admins";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String CREATE_ADMIN_TABLE = "CREATE TABLE "
            + TABLE_ADMINS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT)";



    public DataBaseHelper(Context context, String name, CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    //Aquí se crea la bd
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_ADMIN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINS);
        onCreate(db);
    }






}
