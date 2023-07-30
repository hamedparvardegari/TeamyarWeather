package com.parvardegari.teamyarweather.dagger;

import com.parvardegari.teamyarweather.adapters.CityRecyclerAdapter;
import com.parvardegari.teamyarweather.repository.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class RecyclerViewModule {

    @Provides
    public static CityRecyclerAdapter provideCityRecycler(ApiService apiService){
        return new CityRecyclerAdapter(apiService);

    }
}
