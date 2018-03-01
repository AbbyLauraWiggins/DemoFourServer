package com.degree.abbylaura.demofourserver;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;


/**
 * Created by abbylaura on 18/02/2018.
 *
 * Contains all the methods to service requests from client
 *
 */

public class ServerRequests{

    public ArrayList<String> notices;
    public String noticeString;
    private SQLiteDatabase sqlDB;
    public static Context mycontext;

    private static DatabaseHandler dbHandler;
    private static SQLiteOpenHelper sqlHelper;

    public ServerRequests(String noticeString, ArrayList<String> notices){
        super();

        this.noticeString = noticeString;

        this.notices = notices;

    }

    public static void setContext(Context context){
        mycontext = context;
    }

   /* public static synchronized void initializeInstance(SQLiteOpenHelper helper) {
        if (dbHandler == null) {
            dbHandler = new DatabaseHandler();
            sqlHelper = helper;
        }
    }

    public static synchronized DatabaseHandler getInstance() {
        if (dbHandler == null) {
            throw new IllegalStateException(DatabaseHandler.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }

        return dbHandler;

    }*/







    public Boolean validateLogin(String email, String password){ //maybe make this protected??

        //TODO valid email and password with database

        return true;
    }

    public void addToNotices(String string){

        //System.out.println("passed to notice string, currently: " + string);

        //noticeString = noticeString + string;

        //System.out.println("added to notice string, currently: " + noticeString);
        notices.add(string);
    }

    public String getNotices(){
        return noticeString;
    }

    public ArrayList<String> getNoticeArray(){
        return notices;
    }


    public void addNoticeToDB(String clientID, String noticestring, String date) {
        DatabaseHandler dbHandler = new DatabaseHandler(mycontext);


        NoticeDB notice = new NoticeDB(Integer.parseInt(clientID), noticestring, date);

        dbHandler.addNotice(notice);

    }

    public Long getNoticeCount(){
        DatabaseHandler dbHandler = new DatabaseHandler(mycontext);
        return dbHandler.countNotice();
    }

    public String[][] getMissingRows(int rowNum){

        DatabaseHandler dbHandler = new DatabaseHandler(mycontext);
        return dbHandler.getMissingRows(rowNum);
    }


}
