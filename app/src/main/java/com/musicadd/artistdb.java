package com.musicadd;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by Krysia on 7/23/16.
 * */
 public class artistdb extends SQLiteOpenHelper {

 public static final String TABLE_COMMENTS = "comments";
 public static final String COLUMN_ID = "_id";
 public static final String COLUMN_NAME = "name";
 public static final String COLUMN_LOC = "location";
 public static final String COLUMN_STIME = "startime";
 public static final String COLUMN_ETIME = "endtime";

 private static final String DATABASE_NAME = "commments.db";
 private static final int DATABASE_VERSION = 1;

 // Database creation sql statement
 private static final String DATABASE_CREATE = "create table "
 + TABLE_COMMENTS + "( " + COLUMN_ID
 + " integer primary key autoincrement, " + COLUMN_NAME
 + " text not null" + COLUMN_LOC + " text not null" + COLUMN_STIME + " text not null"
         + COLUMN_ETIME + " text not null);";

 public artistdb(Context context) {
 super(context, DATABASE_NAME, null, DATABASE_VERSION);
 }

 @Override
 public void onCreate(SQLiteDatabase database) {
 database.execSQL(DATABASE_CREATE);
 }

 @Override
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 Log.w(artistdb.class.getName(),
 "Upgrading database from version " + oldVersion + " to "
 + newVersion + ", which will destroy all old data");
 db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
 onCreate(db);
 }

 }