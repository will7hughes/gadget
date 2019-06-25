package com.whughes.gadget.module;

import android.app.Application;

import androidx.room.Room;


import com.whughes.gadget.db.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TestAppModule extends AppModule{

    public TestAppModule(Application application){ super(application); }

    @Provides
    @Singleton
    AppDatabase providesAppDatabase(Application app) {
        return Room.inMemoryDatabaseBuilder(app, AppDatabase.class)
                .fallbackToDestructiveMigration()
                .build();
    }
}
