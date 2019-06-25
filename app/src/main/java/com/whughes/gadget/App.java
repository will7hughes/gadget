package com.whughes.gadget;

import android.app.Application;

import com.whughes.gadget.module.ApiModule;
import com.whughes.gadget.module.AppComponent;
import com.whughes.gadget.module.AppModule;
import com.whughes.gadget.module.DaggerAppComponent;
import com.whughes.gadget.module.DaoModule;
import com.whughes.gadget.module.NetModule;
import com.whughes.gadget.module.RepositoryModule;
import com.whughes.gadget.module.RoomModule;


// TODO: implement app executors
public class App extends Application {
    public static final String TAG = "FIELD_APPLICATION";

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {return appComponent;}

    @Override
    public void onCreate() {
        super.onCreate();

        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder().
                    appModule(new AppModule(this)).
                    apiModule(new ApiModule()).
                    netModule(new NetModule("http://app01/fieldwork/")).
                    daoModule(new DaoModule()).
                    repositoryModule(new RepositoryModule()).
                    roomModule(new RoomModule()).
                    build();
        }
    }
}
