package com.parvardegari.teamyarweather.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parvardegari.teamyarweather.databinding.CityRecyclerBinding;
import com.parvardegari.teamyarweather.model.City;
import com.parvardegari.teamyarweather.repository.ApiService;

import org.jetbrains.annotations.NotNull;
import org.neshan.common.model.LatLng;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CityRecyclerAdapter extends RecyclerView.Adapter<CityRecyclerAdapter.ViewHolder> {


    ApiService apiService;

    ArrayList<City> cities = new ArrayList<>();
    ItemClickListener listener;

    CityRecyclerBinding binding;

    CompositeDisposable compositeDisposable = new CompositeDisposable();


    public CityRecyclerAdapter(ApiService apiService) {
        this.apiService = apiService;
    }

    public void setListener(ItemClickListener listener){
        this.listener=listener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<City> cities){
        this.cities.clear();
        this.cities.addAll(cities);
        notifyDataSetChanged();
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (compositeDisposable!=null){
            compositeDisposable.clear();
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = CityRecyclerBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final int finalPosition = position;
        binding.cityName.setText(cities.get(finalPosition).getCityName());

        RxJavaObservableRequest(apiService.getCityTemp(cities.get(finalPosition).getId()),detail->{
            holder.maximum.setText(String.valueOf(detail.getMaximumTemp()));
            holder.minimum.setText(String.valueOf(detail.getMinimumTemp()));
            holder.current.setText(String.valueOf(detail.getCurrentTemp()));
        },throwable -> {

        });


        holder.itemView.setOnClickListener(view -> {
            if (listener!=null){
                listener.onItemClickListener(cities.get(finalPosition));
            }
        });

        binding.imgLocate.setOnClickListener(view -> listener.onLocaleClickListener(new LatLng(cities.get(position).getLatitude(),cities.get(position).getLongitude())));
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView current;
        TextView minimum;
        TextView maximum;
        public ViewHolder(@NonNull CityRecyclerBinding binding) {
            super(binding.getRoot());

            current = binding.currentTemp;
            minimum=binding.txtMinimum;
            maximum=binding.txtMaximum;

        }
    }

    public <T> void RxJavaObservableRequest(@NotNull io.reactivex.rxjava3.core.Observable<T> request , Consumer<? super T> onNext, Consumer<? super Throwable> onError){
        compositeDisposable.add(request
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext,onError));
    }

    public interface ItemClickListener{
        void onLocaleClickListener(LatLng latLng);
        void onItemClickListener(City city);
    }


}
