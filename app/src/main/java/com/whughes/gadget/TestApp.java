package com.whughes.gadget;


import com.whughes.gadget.module.ApiModule;
import com.whughes.gadget.module.AppComponent;
import com.whughes.gadget.module.DaggerAppComponent;
import com.whughes.gadget.module.DaoModule;
import com.whughes.gadget.module.NetModule;
import com.whughes.gadget.module.RepositoryModule;
import com.whughes.gadget.module.RoomModule;
import com.whughes.gadget.module.TestAppModule;

public class TestApp extends App {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {return appComponent;}

    @Override
    public void onCreate() {
        super.onCreate();

        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder().
                    appModule(new TestAppModule(this)).
                    apiModule(new ApiModule()).
                    netModule(new NetModule("http://localhost:8080/")).
                    daoModule(new DaoModule()).
                    repositoryModule(new RepositoryModule()).
                    roomModule(new RoomModule()).
                    build();
        }
    }
}
