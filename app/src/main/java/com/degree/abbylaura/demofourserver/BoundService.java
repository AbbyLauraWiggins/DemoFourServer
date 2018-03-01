package com.degree.abbylaura.demofourserver;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


/**
 * Created by abbylaura on 21/02/2018.
 */

public class BoundService extends Service {

    private final IBinder binder = new MyBinder();
    private Server server;

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("in BS onbind");
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        System.out.println("in on create");


    }

    public void startServer(){
       server = new Server();
    }

    public void giveContext(){
        ServerRequests.setContext(this.getApplicationContext());
    }




    public class MyBinder extends Binder {
        public BoundService getService(){

            System.out.println("in BS my binder getservice");

            return BoundService.this;
        }
    }
}