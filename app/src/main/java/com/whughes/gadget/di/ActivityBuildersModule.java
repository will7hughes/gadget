package com.whughes.gadget.di;



import com.whughes.gadget.activity.MainActivity;
import com.whughes.gadget.di.login.LoginModule;
import com.whughes.gadget.di.login.LoginScope;
import com.whughes.gadget.di.login.LoginViewModelsModule;
import com.whughes.gadget.di.main.MainFragmentBuildersModule;
import com.whughes.gadget.di.main.MainModule;
import com.whughes.gadget.di.main.MainScope;
import com.whughes.gadget.di.main.MainViewModelsModule;
import com.whughes.gadget.ui.login.LoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @LoginScope
    @ContributesAndroidInjector(
            modules = {LoginViewModelsModule.class, LoginModule.class})
    abstract LoginActivity contributeLoginActivity();

    @MainScope
    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class, MainViewModelsModule.class, MainModule.class}
    )
    abstract MainActivity contributeMainActivity();

}
