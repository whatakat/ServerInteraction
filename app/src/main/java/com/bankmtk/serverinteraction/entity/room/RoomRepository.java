package com.bankmtk.serverinteraction.entity.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = RoomUser.class, parentColumns = "login", childColumns = "userLogin", onDelete = CASCADE))
public class RoomRepository {
    @NonNull
    @PrimaryKey
    private String id;
    private String name;
    private String userLogin;

    public RoomRepository() {
    }

    public RoomRepository(@NonNull String id, String name, String userLogin) {
        this.id = id;
        this.name = name;
        this.userLogin = userLogin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
