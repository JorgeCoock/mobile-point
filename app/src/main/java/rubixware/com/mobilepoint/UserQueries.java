package rubixware.com.mobilepoint;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class UserQueries {
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERS_ID = "id";
    public static final String COLUMN_USERS_USERNAME = "username";
    public static final String COLUMN_USERS_PASSWORD = "password";
    public static final String COLUMN_USERS_AGE = "age";

    public UserQueries(){}

    public void createUser(User user, DataBaseHelper dbHelper){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERS_USERNAME, user.getUsername());
        values.put(COLUMN_USERS_PASSWORD, user.getPassword());
        values.put(COLUMN_USERS_AGE, user.getAge());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public User showUser(String username, DataBaseHelper dbHelper){
        String query = "Select * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERS_USERNAME + " =  \"" + username + "\"";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();
        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setAge(Integer.parseInt(cursor.getString(3)));
            cursor.close();
        }else{
            user = null;
        }
        db.close();
        return user;
    }
    public boolean updateAdmin(User user, DataBaseHelper dbHelper, User newUser){
        boolean result = false;
        String query = "Select * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERS_USERNAME + " =  \"" + user.getUsername() + "\"";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERS_USERNAME, newUser.getUsername());
        values.put(COLUMN_USERS_PASSWORD, newUser.getPassword());
        values.put(COLUMN_USERS_AGE, newUser.getAge());
        if (cursor.moveToFirst()){
            user.setId(Integer.parseInt(cursor.getString(0)));
            db.update(TABLE_USERS, values, COLUMN_USERS_ID + "=" + user.getId(), null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }


    public boolean deleteUser(String username, DataBaseHelper dbHelper){
        boolean result = false;
        String query = "Select * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERS_USERNAME + " =  \"" + username + "\"";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();
        if (cursor.moveToFirst()){
            user.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_USERS, COLUMN_USERS_ID + " = ?",
                    new String[]{ String.valueOf(user.getId())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public ArrayList<User> getUserIndex(DataBaseHelper dbHelper){
        ArrayList<User> userList = new ArrayList<User>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try{
            Cursor cursor = db.rawQuery("Select * FROM "+TABLE_USERS+ " ORDER BY username", null);
            try{
                if (cursor.moveToFirst()){
                    do {
                        User user = new User();
                        user.setId(Integer.parseInt(cursor.getString(0)));
                        user.setUsername(cursor.getString(1));
                        user.setPassword(cursor.getString(2));
                        user.setAge(Integer.parseInt(cursor.getString(3)));
                        userList.add(user);
                    }while (cursor.moveToNext());
                }
            } finally {
                try { cursor.close();} catch (Exception ignore) {}
            }
        } finally {
            try{ db.close(); } catch (Exception ignore) {}
        }
        return userList;
    }

    public ArrayList<User> getUserUsernames(DataBaseHelper dbHelper){
        ArrayList<User> userList = new ArrayList<User>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try{
            Cursor cursor = db.rawQuery("Select username FROM "+TABLE_USERS+ " ORDER BY username", null);
            try{
                if (cursor.moveToFirst()){
                    do {
                        User user = new User();
                        user.setUsername(cursor.getString(0));
                        userList.add(user);
                    }while (cursor.moveToNext());
                }
            } finally {
                try { cursor.close();} catch (Exception ignore) {}
            }
        } finally {
            try{ db.close(); } catch (Exception ignore) {}
        }
        return userList;
    }


}
