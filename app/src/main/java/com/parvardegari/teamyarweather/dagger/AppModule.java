package com.parvardegari.teamyarweather.dagger;


import android.app.Application;
import android.content.Context;

import com.parvardegari.teamyarweather.repository.ApiService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Singleton
    @Provides
    static Retrofit provideRetrofit(){
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(60*2, TimeUnit.SECONDS)
                .connectTimeout(60*2, TimeUnit.SECONDS)
                .writeTimeout(60*2, TimeUnit.SECONDS)
                .build();
        return new Retrofit.Builder()
                .baseUrl("https://weather.mafia-bazi.com")
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    @Singleton
    @Provides
    static ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }

    @Provides
    public static Context provideContext(Application application){
        return (Context) application;
    }
}
