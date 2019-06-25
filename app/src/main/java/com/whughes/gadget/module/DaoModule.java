package com.whughes.gadget.module;


import com.whughes.gadget.db.AppDatabase;
import com.whughes.gadget.db.dao.UserDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DaoModule {
    @Provides
    @Singleton
    public UserDao provideUserDao(AppDatabase appDatabase) {
        return appDatabase.userDao();
    }

}
