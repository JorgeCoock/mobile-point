package rubixware.com.mobilepoint;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;


public class Rec_Pages {
    public static final String TABLE_Rec_PAGES = "pages";
    public static final String COLUMN_Rec_PAGES_ID = "id";
    public static final String COLUMN_Rec_PAGES_USERNAME = "userpage";


    public AdminQueries() {}

    //Add Admins
    public void createRec_Pages(Rec_Pages admin, DataBaseHelper dbHelper){
        ContentValues values = new ContentValues();
        values.put(COLUMN_Rec_PAGES_USERNAME, Rec_pages.getUsername());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(TABLE_Rec_PAGES, null, values);
        db.close();
    }

    public Rec_Pages showPages(String adminName, DataBaseHelper dbHelper){
        String query = "Select * FROM " + TABLE_Rec_PAGES + " WHERE " + COLUMN_Rec_PAGES_USERNAME + " =  \"" + pagesName + "\"";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Rec_Pages rec_pages = new Rec_Pages();

        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            rec_pages.setId(Integer.parseInt(cursor.getString(0)));
            rec_pages.setUsername(cursor.getString(1));
            rec_pages.setPassword(cursor.getString(2));
            cursor.close();
        }else {
            rec_pages = null;
        }
        db.close();
        return rec_pages;
    }
    public ArrayList<Rec_Pages> getRec_PagesIndex(DataBaseHelper dbHelper){
        ArrayList<Rec_Pages> rec_pagesList = new ArrayList<Rec_Pages>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try{
            Cursor cursor = db.rawQuery("Select * FROM "+TABLE_Rec_PAGES, null);
            try{
                if (cursor.moveToFirst()){
                    do {
                        Page pages = new Pages();
                        rec_pages.setId(Integer.parseInt(cursor.getString(0)));
                        rec_pages.setUsername(cursor.getString(1));
                        rec_pages.setPassword(cursor.getString(2));
                        rec_pagesList.add(admin);
                    }while (cursor.moveToNext());
                }
            } finally {
                try { cursor.close();} catch (Exception ignore) {}
            }
        } finally {
            try{ db.close(); } catch (Exception ignore) {}
        }
        return rec_pagesList;
    }
}