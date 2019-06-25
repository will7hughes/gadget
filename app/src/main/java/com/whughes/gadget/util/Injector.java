package com.whughes.gadget.util;

import android.content.Context;

import com.whughes.gadget.App;
import com.whughes.gadget.TestApp;
import com.whughes.gadget.module.AppComponent;
import com.whughes.gadget.viewmodel.LoginViewModel;

// Switches injection between App and TestApp
public class Injector implements AppComponent {

    @Override
    public void inject(LoginViewModel viewModel) {
        if (TestApp.getAppComponent() == null)
            App.getAppComponent().inject(viewModel);
        else
            TestApp.getAppComponent().inject(viewModel);
    }

    @Override
    public void inject(Context content) {

    }
}
