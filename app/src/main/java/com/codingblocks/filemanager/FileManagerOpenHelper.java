package com.codingblocks.filemanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nagarro on 04/10/15.
 */
public class FileManagerOpenHelper extends SQLiteOpenHelper {
    public static final String FAV_TABLE = "favorites";
    public static final String FAV_TABLE_ID = "_ID";
    public static final String FAV_TABLE_FILE_PATH = "file_path";
    public static final String FAV_TABLE_CREATION_TIME = "created_at";

    public FileManagerOpenHelper(Context context, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "file_manager", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + FAV_TABLE + " ( " + FAV_TABLE_ID + " int," +
                   FAV_TABLE_FILE_PATH + " varchar(255)," +
                    FAV_TABLE_CREATION_TIME + " date)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
