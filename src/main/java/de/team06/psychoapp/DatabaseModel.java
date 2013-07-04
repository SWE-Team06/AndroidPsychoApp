package de.team06.psychoapp;

/**
 * Created by Malte on 28.06.13.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseModel {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private Context context;

    public DatabaseModel(Context context) {
        dbHelper = new MySQLiteHelper(context);
        this.context = context;
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public SocialInteraction createSocialInteraction(int alarmtime, String code) {
        ContentValues values = new ContentValues();
        values.put("alarmtime", alarmtime);
        values.put("code", code);
       /* values.put("responsetime", 11880);
        values.put("skip", 0);
        values.put("contacts", 1);
        values.put("hours", 2);
        values.put("minutes", 3); */

        long insertId = dbHelper.getWritableDatabase().insert(MySQLiteHelper.TABLE_SOCIAL_INTERACTIONS, null,
                values);
      /*  String sql = "SELECT * FROM interactions";
       Cursor cursor = dbHelper.getReadableDatabase().rawQuery(sql, null);
       cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Toast.makeText(this.context, ""+cursor.getInt(cursor.getColumnIndex("_id")) , Toast.LENGTH_SHORT).show();
            cursor.moveToNext();
        }
        */
        Cursor cursor = database.query(MySQLiteHelper.TABLE_SOCIAL_INTERACTIONS,
                MySQLiteHelper.TABLE_SOCIAL_INTERACTIONS_ALL, MySQLiteHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(insertId)},
                null, null, null);


       cursor.moveToFirst();
       SocialInteraction newSocialInteraction = cursorToSocialInteraction(cursor);
       Toast.makeText(this.context, newSocialInteraction.toString(), Toast.LENGTH_SHORT).show();

        cursor.close();
        return newSocialInteraction;
    }

    /**
     * Delete given SocialInteraction from database
     * @param socialInteraction
     */
    public void deleteSocialInteraction(SocialInteraction socialInteraction) {
        long id = socialInteraction.getId();
        System.out.println("SocialInteraction with id " + id + " deleted.");
        database.delete(MySQLiteHelper.TABLE_SOCIAL_INTERACTIONS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public void updateSocialInteraction(SocialInteraction socialInteraction) {
        long id = socialInteraction.getId();
        System.out.println("SocialInteraction with id " + id + " updated.");
        database.update(MySQLiteHelper.TABLE_SOCIAL_INTERACTIONS, socialInteraction.toContentValues(), MySQLiteHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    /**
     * Get all social interactions from database as list of social interaction objects
     * @return ArrayList<SocialInteraction>
     */
    public List<SocialInteraction> getAllSocialInteractions() {
        List<SocialInteraction> socialInteractionArrayList = new ArrayList<SocialInteraction>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_SOCIAL_INTERACTIONS,
                MySQLiteHelper.TABLE_SOCIAL_INTERACTIONS_ALL, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            SocialInteraction socialInteraction = cursorToSocialInteraction(cursor);
            socialInteractionArrayList.add(socialInteraction);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return socialInteractionArrayList;
    }

    /**
     * Create a SocialInteraction object from db-cursor
     * @param cursor SQLite-Cursor
     * @return SocialInteraction
     */
    private SocialInteraction cursorToSocialInteraction(Cursor cursor) {
        SocialInteraction socialInteraction = new SocialInteraction();

        socialInteraction.setId(cursor.getInt(cursor.getColumnIndex("_id")));
        socialInteraction.setCode(cursor.getString(1));
        socialInteraction.setAlarmTime(cursor.getInt(2));
        socialInteraction.setResponseTime(cursor.getInt(3));
        socialInteraction.setSkipped(cursor.getInt(4));
        socialInteraction.setNumberOfContacts(cursor.getInt(5));
        socialInteraction.setHours(cursor.getInt(6));
        socialInteraction.setMinutes(cursor.getInt(7));

        return socialInteraction;
    }
}
