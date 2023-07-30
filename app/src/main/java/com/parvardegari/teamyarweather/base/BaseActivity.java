package com.parvardegari.teamyarweather.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    BaseViewModel baseViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public <T extends ViewModel> T getViewModel(@NonNull ViewModelStoreOwner owner, @NonNull ViewModelProvider.Factory factory, @NonNull Class<T> modelClass){
        T vm = new ViewModelProvider(owner,factory).get(modelClass);
        this.baseViewModel = (BaseViewModel) vm;
        return vm;
    }
}
