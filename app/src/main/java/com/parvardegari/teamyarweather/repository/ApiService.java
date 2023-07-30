package com.parvardegari.teamyarweather.repository;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

import com.parvardegari.teamyarweather.model.City;
import com.parvardegari.teamyarweather.model.Temp;

import java.util.ArrayList;

import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiService {
    @POST
    @FormUrlEncoded
    Observable<ArrayList<City>> getCities(@Url String url, @Field("id") int id);

    @POST("getcitydetail.php")
    @FormUrlEncoded
    Observable<Temp> getCityTemp(@Field("id")int id);
}
