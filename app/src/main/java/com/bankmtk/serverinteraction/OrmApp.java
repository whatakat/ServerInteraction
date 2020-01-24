package com.bankmtk.serverinteraction;

import android.app.Application;

import androidx.room.Room;

import com.orm.SugarContext;

public class OrmApp extends Application {
    private static final String DATABASE_NAME = "DATABASE_USER_GIT";
    public static MyDatabase database;
    public static OrmApp INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }

}
