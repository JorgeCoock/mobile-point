package rubixware.com.mobilepoint;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class PageQueries {

    public static final String TABLE_PAGES = "pages";
    public static final String COLUMN_PAGES_ID = "id";
    public static final String COLUMN_PAGES_USER_ID = "user_id";
    public static final String COLUMN_PAGES_URL = "url";

    public PageQueries(){}

    public void createUser(Page page, DataBaseHelper dbHelper){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PAGES_USER_ID, page.getUser_id());
        values.put(COLUMN_PAGES_URL, page.getUrl());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(TABLE_PAGES, null, values);
        db.close();
    }


}
