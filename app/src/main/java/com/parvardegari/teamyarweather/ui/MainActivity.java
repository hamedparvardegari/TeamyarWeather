package com.parvardegari.teamyarweather.ui;


import android.os.Bundle;
import android.view.View;

import com.parvardegari.teamyarweather.Viewmodels.ViewModelFactory;
import com.parvardegari.teamyarweather.base.BaseActivity;
import com.parvardegari.teamyarweather.databinding.ActivityMainBinding;
import org.neshan.common.model.LatLng;


import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    ViewModelFactory factory;

    MainActivityViewModel viewModel;


    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);


        viewModel = getViewModel(this,factory, MainActivityViewModel.class);

        LatLng latLng = new LatLng(35.764221,51.022159);

        binding.mapView.addMarker(viewModel. createMarker(latLng));
        binding.mapView.moveCamera(latLng,(float) 4);
        binding.mapView.setZoom(10f,14f);


        viewModel.getCities(binding.cityRecycler,binding.mapView);



    }
}