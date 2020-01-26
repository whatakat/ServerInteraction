package com.bankmtk.serverinteraction.entity.room.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.bankmtk.serverinteraction.entity.room.RoomRepository;
import com.bankmtk.serverinteraction.entity.room.RoomUser;

@Database(entities = {RoomUser.class, RoomRepository.class}, version = 1)
public class UserDatabase extends RoomDatabase {
    private static final String DB_NAME = "userDatabase.db";
    private static volatile UserDatabase instance;

    public static synchronized UserDatabase getInstance(){
        if(instance == null){
            throw new RuntimeException("Database has not been created. Please call create()");
        }
        return instance;
    }

    public static void create(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, UserDatabase.class, DB_NAME).build();
        }
    }

    public abstract UserDao getUserDao();
    public abstract RepositoryDao getRepositoryDao();
}
