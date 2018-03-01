package com.degree.abbylaura.demofourserver;

/**
 * Created by abbylaura on 28/02/2018.
 */

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "noticeDB.db";
    private static final String TABLE_NOTICES = "notices";

    public static final String COLUMN_CLIENTID = "_clientId";
    public static final String COLUMN_NOTICEID = "_noticeId";
    public static final String COLUMN_NOTICE = "_notice";
    public static final String COLUMN_DATE = "_date";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTICE_TABLE = "CREATE TABLE " + TABLE_NOTICES + "(" +
                COLUMN_NOTICEID + "INTEGER PRIMARY KEY AUTOINCREMENT" +
                COLUMN_CLIENTID + " INTEGER NOT NULL," +
                COLUMN_NOTICE + " TEXT," +
                COLUMN_DATE + " TEXT" + ")";


        db.execSQL(CREATE_NOTICE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTICES);
        onCreate(db);

    }


    public void addNotice(NoticeDB notice) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_CLIENTID, notice.getClientId());
        values.put(COLUMN_NOTICE, notice.getNotice());
        values.put(COLUMN_DATE, String.valueOf(notice.getDate()));

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_NOTICES, null, values);
        db.close();
    }

    public NoticeDB findNotice(int clientID) {
        String query = "Select * FROM " + TABLE_NOTICES + " WHERE " + COLUMN_CLIENTID + " =  \"" + clientID + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null); //cursor hold all result of query

        NoticeDB notice = new NoticeDB();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            notice.setNoticeId(Integer.parseInt(cursor.getString(0)));
            notice.setClientId(Integer.parseInt(cursor.getString(1)));
            notice.setNotice(cursor.getString(2));
            notice.setDate(cursor.getString(3));
            cursor.close();
        } else {
            notice = null;
        }
        db.close();
        return notice;
    }


    public long countNotice(){
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NOTICES);
        db.close();
        return count;
    }

    public String[][] getMissingRows(int rowNum){
        String[][] missingRows = new String[rowNum][4];

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select TOP(" + String.valueOf(rowNum) + "FROM " + TABLE_NOTICES;

        Cursor cursor = db.rawQuery(query, null);

        for(int i = 0; i < rowNum; i++){
            cursor.moveToNext();
            missingRows[i][0] = String.valueOf(cursor.getColumnIndex(COLUMN_NOTICEID));
            missingRows[i][1] = String.valueOf(cursor.getColumnIndex(COLUMN_CLIENTID));
            missingRows[i][2] = String.valueOf(cursor.getColumnIndex(COLUMN_NOTICE));
            missingRows[i][3] = String.valueOf(cursor.getColumnIndex(COLUMN_DATE));
        }

        return missingRows;
    }

    public boolean deleteNotice(int noticeID ) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_NOTICES + " WHERE " + COLUMN_NOTICEID + " =  \"" + noticeID + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        NoticeDB notice = new NoticeDB();

        if (cursor.moveToFirst()) {
            notice.setNoticeId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_NOTICES, COLUMN_NOTICEID + " = ?", new String[] {
                    String.valueOf(notice.getNoticeId()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

}