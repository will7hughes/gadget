package com.whughes.gadget.module;

import android.content.Context;


import com.whughes.gadget.viewmodel.LoginViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {AppModule.class, RoomModule.class, NetModule.class, RepositoryModule.class, ApiModule.class, DaoModule.class}
) // Methods defined in com.whughes.gadget.util.Injector
public interface AppComponent {
    void inject(LoginViewModel viewModelModule);

    void inject(Context content);
}