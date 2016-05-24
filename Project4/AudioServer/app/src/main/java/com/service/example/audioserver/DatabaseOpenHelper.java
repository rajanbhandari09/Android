package com.service.example.audioserver;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by USER on 4/6/2016.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    final private Context mContext;

    //Database name, version and table details
    final public static String DB_NAME = "transactions_details_db";
    final public static Integer VERSION = 1;
    final public static String TABLE_NAME = "transactions";
    final public static String ID = "id";
    final public static String DATE_TIME = "dateandtime";
    final public static String REQUEST_TYPE = "requesttype";
    final public static String CLIP_NUMBER = "clipnumber";
    final public static String CURRENT_STATE = "currentstate";
    final public static String[] COLUMNS = {ID,DATE_TIME,REQUEST_TYPE,CLIP_NUMBER,CURRENT_STATE};

    final private static String CREATE_TABLE_QUERY =
            "CREATE TABLE transactions ("+ID+","+
                    DATE_TIME+","+
                    REQUEST_TYPE+","+
                    CLIP_NUMBER+","+
                    CURRENT_STATE+")";
    public DatabaseOpenHelper(Context context){
super(context,DB_NAME,null,VERSION);
this.mContext = context;
    }

    @Override
    //create database table
    public void onCreate(SQLiteDatabase db) {
db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
//method to delete database when no longer needed
    public void deleteDatabase(){
mContext.deleteDatabase(DB_NAME);
    }
}
