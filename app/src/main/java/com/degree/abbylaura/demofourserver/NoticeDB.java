package com.degree.abbylaura.demofourserver;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.Date;

/**
 * Created by abbylaura on 16/02/2018.
 */

public class NoticeDB  {

    private int _clientId;
    private int _noticeId;
    private String _notice;
    private String _date;

    public NoticeDB() {

    }

    public NoticeDB(int nID, int cID, String notice, String date) {
        this._clientId = cID;
        this._noticeId = nID;
        this._notice = notice;
        this._date = date;
    }

    public NoticeDB(int cID, String notice, String date) {
        this._clientId = cID;
        this._notice = notice;
        this._date = date;
    }

    public void setClientId(int cID){
        this._clientId = cID;
    }

    public int getClientId(){
        return this._clientId;
    }

    public void setNoticeId(int nID) {
        this._noticeId = nID;
    }

    public int getNoticeId() {
        return this._noticeId;
    }

    public void setNotice(String notice) {
        this._notice = notice;
    }

    public String getNotice() {
        return this._notice;
    }

    public void setDate(String date) {
        this._date = date;
    }

    public String getDate() {
        return this._date;
    }

}
