package com.parvardegari.teamyarweather.dagger;


import com.parvardegari.teamyarweather.ui.MainActivity;


import dagger.Module;

import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = {ViewModelModule.class,RecyclerViewModule.class})
    abstract MainActivity contributeMainActivity();


}
