package com.parvardegari.teamyarweather.base;

import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public abstract class BaseViewModel extends ViewModel {


    protected CompositeDisposable compositeDisposable = new CompositeDisposable();



    @Override
    protected void onCleared() {
        super.onCleared();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }

    public <T> void RxJavaObservableRequest(@NotNull io.reactivex.rxjava3.core.Observable<T> request , Consumer<? super T> onNext, Consumer<? super Throwable> onError){
        compositeDisposable.add(request
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext,onError));
    }


}
