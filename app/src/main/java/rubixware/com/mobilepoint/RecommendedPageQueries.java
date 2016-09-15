package rubixware.com.mobilepoint;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class RecommendedPageQueries {

    public static final String TABLE_RECOMMENDED_PAGES = "recommended_pages";
    public static final String COLUMN_RECOMMENDED_PAGES_ID = "id";
    public static final String COLUMN_RECOMMENDED_PAGES_URL = "url";
    public static final String COLUMN_RECOMMENDED_PAGES_AGE = "age";

    public RecommendedPageQueries() {
    }

    public void createRecommendedPage(RecommendedPage recommendedPage, DataBaseHelper dataBaseHelper){
        ContentValues values = new ContentValues();
        values.put(COLUMN_RECOMMENDED_PAGES_URL, recommendedPage.getUrl());
        values.put(COLUMN_RECOMMENDED_PAGES_AGE, recommendedPage.getAge());
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        db.insert(TABLE_RECOMMENDED_PAGES, null, values);
    }

    public ArrayList<String> getRecommendedPagesUrls(int age, DataBaseHelper dbHelper){
        ArrayList<String> pages = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try{
            String query = "Select url FROM "+TABLE_RECOMMENDED_PAGES+" WHERE "+COLUMN_RECOMMENDED_PAGES_AGE + " =  \""
                    + age + "\" ORDER BY url";
            Cursor cursor = db.rawQuery(query,null);
            try{
                if(cursor.moveToFirst()){
                    do {
                        pages.add(cursor.getString(0));
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
