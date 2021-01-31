package com.example.simplecrudsqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseManager extends DatabaseHelper {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseManager(Context c) {
        super(c);
    }

    public void open() {
        database = this.getWritableDatabase();
        database.isOpen();
    }

    public Cursor fetch() {
        String[] columns = new String[]{dbHelper._ID, dbHelper.TITLE, dbHelper.DESC};
        Cursor cursor = database.query(dbHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    public void insert(String myInputTitle, String myInputDesc) {
        database = this.getWritableDatabase();
        String query = "INSERT INTO " + dbHelper.TABLE_NAME + " (Title, Description) " +
                "VALUES ('" + myInputTitle + "', '" + myInputDesc + "')";

        Log.e("insert sqlite ", "" + query);
        database.execSQL(query);
        database.close();
    }

    public void update(int Id, String newTitle, String newDesc) {
        database = this.getWritableDatabase();
        String query = "UPDATE " + dbHelper.TABLE_NAME + " SET "
                + dbHelper.TITLE + "='" + newTitle + "', "
                + dbHelper.DESC + "='" + newDesc + "'"
                + " WHERE " + dbHelper._ID + "=" + "'" + Id + "'";

        Log.e("update sqlite ", query);
        database.execSQL(query);
        database.close();
    }

    public void delete(int Id) {
        database = this.getWritableDatabase();
        String query = "DELETE FROM " + dbHelper.TABLE_NAME + " WHERE " + dbHelper._ID + "=" + "'" + Id + "'";

        Log.e("delete sqlite ", query);
        database.execSQL(query);
        database.close();
    }

}
