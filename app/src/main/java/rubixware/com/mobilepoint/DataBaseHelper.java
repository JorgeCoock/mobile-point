package rubixware.com.mobilepoint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;


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

    //Add Admins
    public void createAdmin(Admin admin){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, admin.getUsername());
        values.put(COLUMN_PASSWORD, admin.getPassword());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_ADMINS, null, values);
        db.close();
    }

    public Admin showAdmin(String adminName){
        String query = "Select * FROM " + TABLE_ADMINS + " WHERE " + COLUMN_USERNAME + " =  \"" + adminName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Admin admin = new Admin();

        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            admin.setId(Integer.parseInt(cursor.getString(0)));
            admin.setUsername(cursor.getString(1));
            admin.setPassword(cursor.getString(2));
            cursor.close();
        }else {
            admin = null;
        }
        db.close();
        return admin;
    }

    public boolean deleteAdmin(String adminName) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_ADMINS + " WHERE " + COLUMN_USERNAME + " =  \"" + adminName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Admin admin = new Admin();

        if (cursor.moveToFirst()) {
            admin.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_ADMINS, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(admin.getId()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }


}
