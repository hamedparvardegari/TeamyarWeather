package com.parvardegari.teamyarweather.dagger;

import androidx.lifecycle.ViewModel;

import com.parvardegari.teamyarweather.ui.MainActivityViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    public abstract ViewModel bindMainActivityViewModel(MainActivityViewModel viewModel);
}
