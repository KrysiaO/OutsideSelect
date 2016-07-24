package com.musicadd;

/**
 * Created by Krysia on 7/23/16.
 */


import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ArtistDataSource {

    // Database fields
    private SQLiteDatabase database;
    private artistdb dbHelper;
    private String[] allColumns = { artistdb.COLUMN_ID,
            artistdb.COLUMN_NAME, artistdb.COLUMN_LOC, artistdb.COLUMN_STIME, artistdb.COLUMN_ETIME };

    public ArtistDataSource(Context context) {
        dbHelper = new artistdb(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Artist createComment(String name, String loc, String stime, String etime) {
        ContentValues values = new ContentValues();
        values.put(artistdb.COLUMN_NAME, name);
        values.put(artistdb.COLUMN_LOC, loc);
        values.put(artistdb.COLUMN_STIME, stime);
        values.put(artistdb.COLUMN_ETIME, etime);
        long insertId = database.insert(artistdb.TABLE_COMMENTS, null,
                values);
        Cursor cursor = database.query(artistdb.TABLE_COMMENTS,
                allColumns, artistdb.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Artist artist = cursorToArtist(cursor);
        cursor.close();
        return artist;
    }

    public void deleteArtist(Artist comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(artistdb.TABLE_COMMENTS, artistdb.COLUMN_ID
                + " = " + id, null);
    }

    public List<Artist> getAllComments() {
        List<Artist> comments = new ArrayList<Artist>();

        Cursor cursor = database.query(artistdb.TABLE_COMMENTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Artist comment = cursorToArtist(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    private Artist cursorToArtist(Cursor cursor) {
        Artist artist = new Artist();
        artist.setId(cursor.getLong(0));
        artist.setName(cursor.getString(1));
        artist.setLocation(cursor.getString(2));
        artist.setStarttime(cursor.getString(3));
        artist.setEndtime(cursor.getString(4));
        return artist;
    }
}
