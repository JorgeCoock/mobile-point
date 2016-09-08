package rubixware.com.mobilepoint;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;


public class Page {
    public static final String TABLE_PAGES = "pages";
    public static final String COLUMN_PAGES_ID = "id";
    public static final String COLUMN_PAGES_USERNAME = "userpage";


    public AdminQueries() {}

    //Add Admins
    public void createPages(Pages admin, DataBaseHelper dbHelper){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PAGES_USERNAME, admin.getUsername());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(TABLE_PAGES, null, values);
        db.close();
    }

}