package com.degree.abbylaura.demofourserver;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import static com.degree.abbylaura.demofourserver.MainActivity.*;

/**
 * Created by abbylaura on 01/03/2018.
 */

public class  App extends Application {
    private static Context context;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this.getApplicationContext();
        Intent intent = new Intent(this, BoundService.class);
        this.startService(intent);
    }

    public static Context getContext(){
        return context;
    }



}
