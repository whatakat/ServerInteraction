package com.bankmtk.serverinteraction.entity.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bankmtk.serverinteraction.entity.room.RoomRepository;
import com.bankmtk.serverinteraction.entity.room.RoomUser;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface RepositoryDao {
    @Insert(onConflict = REPLACE)
    void insert(RoomRepository user);

    @Insert(onConflict = REPLACE)
    void insert(RoomRepository... users);

    @Insert(onConflict = REPLACE)
    void insert(List<RoomRepository> users);


    @Update
    void update(RoomRepository user);

    @Update
    void update(RoomRepository... users);

    @Update
    void update(List<RoomRepository> users);


    @Delete
    void delete(RoomUser user);

    @Delete
    void delete(RoomUser... users);

    @Delete
    void delete(List<RoomUser> users);


    @Query("SELECT * FROM roomrepository")
    List<RoomRepository> getAll();

    @Query("SELECT * FROM roomrepository WHERE userLogin = :login LIMIT 1")
    RoomRepository findForUser(String login);
}
