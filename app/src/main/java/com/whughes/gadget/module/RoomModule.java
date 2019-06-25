package com.whughes.gadget.module;

import android.app.Application;

import androidx.room.Room;


import com.whughes.gadget.db.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    @Provides
    @Singleton
    AppDatabase providesAppDatabase(Application app) {
        return Room.databaseBuilder(app, AppDatabase.class, "app-db")
                .fallbackToDestructiveMigration()
                .build();
    }
}
