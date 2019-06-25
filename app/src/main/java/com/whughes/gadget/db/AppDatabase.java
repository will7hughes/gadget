package com.whughes.gadget.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.whughes.gadget.db.dao.UserDao;
import com.whughes.gadget.db.entity.UserEntity;


@Database(entities = {
        UserEntity.class}, version=1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
