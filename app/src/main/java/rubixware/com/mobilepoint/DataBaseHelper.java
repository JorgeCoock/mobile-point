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
            + COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT, UNIQUE ("+COLUMN_USERNAME+"))";

    // USER fields
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERS_ID = "id";
    public static final String COLUMN_USERS_USERNAME = "username";
    public static final String COLUMN_USERS_PASSWORD = "password";
    public static final String COLUMN_USERS_AGE = "age";
    public static final String CREATE_USER_TABLE = "CREATE TABLE "
            + TABLE_USERS + "(" + COLUMN_USERS_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_USERS_USERNAME + " TEXT, " + COLUMN_USERS_PASSWORD + " TEXT, "
            + COLUMN_USERS_AGE + " INTEGER, UNIQUE ("+COLUMN_USERS_USERNAME+"))";

    //Page fields
    public static final String TABLE_PAGES = "pages";
    public static final String COLUMN_PAGES_ID = "id";
    public static final String COLUMN_PAGES_USER_ID = "user_id";
    public static final String COLUMN_PAGES_URL = "url";
    public static final String CREATE_PAGE_TABLE = "CREATE TABLE "
            + TABLE_PAGES + "(" + COLUMN_PAGES_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_PAGES_USER_ID + " INTEGER, " + COLUMN_PAGES_URL + " TEXT)";

    //Recommended Page fields
    public static final String TABLE_RECOMMENDED_PAGES = "recommended_pages";
    public static final String COLUMN_RECOMMENDED_PAGES_ID = "id";
    public static final String COLUMN_RECOMMENDED_PAGES_URL = "url";
    public static final String COLUMN_RECOMMENDED_PAGES_AGE = "age";
    public static final String CREATE_RECOMMENDED_PAGES_TABLE = "CREATE TABLE "
            + TABLE_RECOMMENDED_PAGES + "(" + COLUMN_RECOMMENDED_PAGES_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_RECOMMENDED_PAGES_URL + " TEXT, " + COLUMN_RECOMMENDED_PAGES_AGE + " INTEGER";



    public DataBaseHelper(Context context, String name, CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    //Aquí se crea la bd
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_ADMIN_TABLE);
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PAGE_TABLE);
        db.execSQL(CREATE_RECOMMENDED_PAGES_TABLE);
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINS);
        onCreate(db);
    }






}
