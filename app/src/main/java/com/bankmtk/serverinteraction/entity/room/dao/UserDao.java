package com.bankmtk.serverinteraction.entity.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bankmtk.serverinteraction.entity.room.RoomUser;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {
    @Insert(onConflict = REPLACE)
    void insert(RoomUser user);

    @Insert(onConflict = REPLACE)
    void insert(RoomUser... users);

    @Insert(onConflict = REPLACE)
    void insert(List<RoomUser> users);


    @Update
    void update(RoomUser user);

    @Update
    void update(RoomUser... users);

    @Update
    void update(List<RoomUser> users);


    @Delete
    void delete(RoomUser user);

    @Delete
    void delete(RoomUser... users);

    @Delete
    void delete(List<RoomUser> users);

    @Query("SELECT * FROM roomuser")
    List<RoomUser> getAll();

    @Query("SELECT * FROM roomuser WHERE login = :login LIMIT 1")
    RoomUser findByLogin(String login);

}
