package rubixware.com.mobilepoint;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class PageQueries {

    public static final String TABLE_PAGES = "pages";
    public static final String COLUMN_PAGES_ID = "id";
    public static final String COLUMN_PAGES_USER_ID = "user_id";
    public static final String COLUMN_PAGES_URL = "url";

    public PageQueries(){}

    public void createPage(Page page, DataBaseHelper dbHelper, boolean option){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PAGES_USER_ID, page.getUser_id());
        if (option){
            String url = page.getUrl();
            values.put(COLUMN_PAGES_URL, url);
        }else{
            String url = "http://"+page.getUrl();
            values.put(COLUMN_PAGES_URL, url);
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(TABLE_PAGES, null, values);
        db.close();
    }

    public Page showPage(String url, int userId, DataBaseHelper dbHelper){
        String query = "Select * FROM " +TABLE_PAGES+ " WHERE " + COLUMN_PAGES_URL +  " =  \""
                + url + "\" AND " + COLUMN_PAGES_USER_ID +  " =  \"" + userId + "\"";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Page page = new Page();
        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            page.setId(Integer.parseInt(cursor.getString(0)));
            page.setUser_id(Integer.parseInt(cursor.getString(1)));
            page.setUrl(cursor.getString(2));
            cursor.close();
        }else{
            page = null;
        }
        db.close();
        return page;
    }

    public boolean deletePage(String url, int userId, DataBaseHelper dbHelper){
        boolean result = false;
        String query = "Select * FROM " +TABLE_PAGES+ " WHERE " + COLUMN_PAGES_URL +  " =  \""
                + url + "\" AND " + COLUMN_PAGES_USER_ID +  " =  \"" + userId + "\"";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Page page = new Page();
        if (cursor.moveToFirst()){
            page.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_PAGES, COLUMN_PAGES_ID + " = ?",
                    new String[]{String.valueOf(page.getId())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public ArrayList<Page> getUserPages(int userId, DataBaseHelper dbHelper){
        ArrayList<Page> pages = new ArrayList<Page>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try{
            String query = "Select url FROM " +TABLE_PAGES+ " WHERE "+ COLUMN_PAGES_USER_ID + " =  \""
                    + userId + "\" ORDER BY url";
            Cursor cursor = db.rawQuery(query,null);
            try{
                if (cursor.moveToFirst()){
                    do {
                        Page page = new Page();
                        page.setUrl(cursor.getString(0));
                        pages.add(page);
                    }while (cursor.moveToNext());
                }
            } finally {
                try { cursor.close();} catch (Exception ignore) {}
            }
        } finally {
            try{ db.close(); } catch (Exception ignore) {}
        }
        return pages;
    }


}
