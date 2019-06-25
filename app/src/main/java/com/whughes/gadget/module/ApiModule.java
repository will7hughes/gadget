package com.whughes.gadget.module;


import com.whughes.gadget.api.UserApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {
    @Provides
    @Singleton
    public UserApi providesUserApi(Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }

}
