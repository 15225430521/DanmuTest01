package com.apan.danmutest01;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    private static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this.getApplicationContext();
    }


    public static Context getContext(){
        return applicationContext;
    }
}
