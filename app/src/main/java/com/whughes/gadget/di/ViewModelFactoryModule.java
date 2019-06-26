package com.whughes.gadget.di;

import androidx.lifecycle.ViewModelProvider;


import com.whughes.gadget.util.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;


@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelFactory);

}
