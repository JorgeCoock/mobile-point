package rubixware.com.mobilepoint;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.Console;
import java.util.ArrayList;

public class AdminQueries{
    public static final String TABLE_ADMINS = "admins";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    public AdminQueries(){}

    //Add Admins
    public void createAdmin(Admin admin, DataBaseHelper dbHelper){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, admin.getUsername());
        values.put(COLUMN_PASSWORD, admin.getPassword());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(TABLE_ADMINS, null, values);
        db.close();
    }

    public Admin showAdmin(String adminName, DataBaseHelper dbHelper){
        String query = "Select * FROM " + TABLE_ADMINS + " WHERE " + COLUMN_USERNAME + " =  \"" + adminName + "\"";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
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

    public boolean updateAdmin(Admin admin, DataBaseHelper dbHelper){
        boolean result = false;
        String query = "Select * FROM " + TABLE_ADMINS + " WHERE " + COLUMN_USERNAME + " =  \"" + admin.getUsername() + "\"";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, admin.getUsername());
        values.put(COLUMN_PASSWORD, admin.getPassword());
        if (cursor.moveToFirst()){
            admin.setId(Integer.parseInt(cursor.getString(0)));
            db.update(TABLE_ADMINS, values, COLUMN_ID + "=" + admin.getId(), null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }


    public boolean deleteAdmin(String adminName, DataBaseHelper dbHelper) {
        boolean result = false;
        String query = "Select * FROM " + TABLE_ADMINS + " WHERE " + COLUMN_USERNAME + " =  \"" + adminName + "\"";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
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

    public ArrayList<Admin> getAdminIndex(DataBaseHelper dbHelper){
        ArrayList<Admin> adminList = new ArrayList<Admin>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try{
            Cursor cursor = db.rawQuery("Select * FROM "+TABLE_ADMINS+ " ORDER BY username", null);
            try{
                if (cursor.moveToFirst()){
                    do {
                        Admin admin = new Admin();
                        admin.setId(Integer.parseInt(cursor.getString(0)));
                        admin.setUsername(cursor.getString(1));
                        admin.setPassword(cursor.getString(2));
                        adminList.add(admin);
                    }while (cursor.moveToNext());
                }
            } finally {
                try { cursor.close();} catch (Exception ignore) {}
            }
        } finally {
            try{ db.close(); } catch (Exception ignore) {}
        }
        return adminList;
    }

    public ArrayList<Admin> getAdminUsernames(DataBaseHelper dbHelper){
        ArrayList<Admin> adminList = new ArrayList<Admin>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try{
            Cursor cursor = db.rawQuery("Select username FROM "+TABLE_ADMINS+ " ORDER BY username", null);
            try{
                if (cursor.moveToFirst()){
                    do {
                        Admin admin = new Admin();
                        admin.setUsername(cursor.getString(0));
                        adminList.add(admin);
                    }while (cursor.moveToNext());
                }
            } finally {
                try { cursor.close();} catch (Exception ignore) {}
            }
        } finally {
            try{ db.close(); } catch (Exception ignore) {}
        }
        return adminList;
    }

}
