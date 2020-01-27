package com.bankmtk.serverinteraction;

import android.app.Application;

import com.bankmtk.serverinteraction.entity.room.db.UserDatabase;
import com.orm.SugarContext;

import io.paperdb.Paper;
import timber.log.Timber;

public class App extends Application {
    static private App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Timber.plant(new Timber.DebugTree());
        UserDatabase.create(this);
        Paper.init(this);

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);
    }

    public static App getInstance() {
        return instance;
    }
}
