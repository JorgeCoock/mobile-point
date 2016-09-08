package rubixware.com.mobilepoint;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;


public class Page {
    public static final String TABLE_PAGES = "pages";
    public static final String COLUMN_PAGES_ID = "id";
    public static final String COLUMN_PAGES_USERNAME = "userpage";


    public AdminQueries() {
    }
    //Add Pages
    public void createPages(Pages admin, DataBaseHelper dbHelper){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PAGES_USERNAME, pages.getUsername());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(TABLE_PAGES, null, values);
        db.close();
    }
    public Pages showPages(String adminName, DataBaseHelper dbHelper){
        String query = "Select * FROM " + TABLE_PAGES + " WHERE " + COLUMN_PAGES_USERNAME + " =  \"" + pagesName + "\"";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Admin admin = new Admin();

        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            pages.setId(Integer.parseInt(cursor.getString(0)));
            pages.setUsername(cursor.getString(1));
            pages.setPassword(cursor.getString(2));
            cursor.close();
        }else {
            pages = null;
        }
        db.close();
        return pages;
    }

}