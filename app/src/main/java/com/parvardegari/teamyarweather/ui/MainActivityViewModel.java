package com.parvardegari.teamyarweather.ui;


import android.content.Context;

import android.graphics.Bitmap;

import android.graphics.Canvas;

import android.graphics.drawable.Drawable;


import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carto.styles.AnimationStyle;
import com.carto.styles.AnimationStyleBuilder;
import com.carto.styles.AnimationType;
import com.carto.styles.MarkerStyle;
import com.carto.styles.MarkerStyleBuilder;
import com.parvardegari.teamyarweather.R;
import com.parvardegari.teamyarweather.adapters.CityRecyclerAdapter;
import com.parvardegari.teamyarweather.base.BaseViewModel;
import com.parvardegari.teamyarweather.model.City;
import com.parvardegari.teamyarweather.repository.ApiService;

import org.neshan.common.model.LatLng;
import org.neshan.mapsdk.MapView;
import org.neshan.mapsdk.internal.utils.BitmapUtils;
import org.neshan.mapsdk.model.Marker;

import java.util.Objects;

import javax.inject.Inject;

public class MainActivityViewModel extends BaseViewModel {

    @Inject
    CityRecyclerAdapter adapter;

    private final ApiService apiService;
    private final Context context;
    @Inject
    public MainActivityViewModel(ApiService apiService, Context context) {
        this.apiService=apiService;
        this.context=context;

    }

    public void getCities(RecyclerView recyclerView, MapView mapView){
        RxJavaObservableRequest(apiService.getCities("cities.php",1),cities -> {
                    adapter.setData(cities);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    recyclerView.setAdapter(adapter);

                    adapter.setListener(new CityRecyclerAdapter.ItemClickListener() {
                        @Override
                        public void onLocaleClickListener(LatLng latLng) {
                            mapView.addMarker(createMarker(latLng));
                            mapView.moveCamera(latLng,(float) 0.5);
                        }

                        @Override
                        public void onItemClickListener(City city) {

                        }
                    });
        }
        ,throwable -> {

        });
    }

    protected Marker createMarker(LatLng loc) {

        AnimationStyle animSt;
        AnimationStyleBuilder animStBl = new AnimationStyleBuilder();
        animStBl.setFadeAnimationType(AnimationType.ANIMATION_TYPE_SMOOTHSTEP);
        animStBl.setSizeAnimationType(AnimationType.ANIMATION_TYPE_SPRING);
        animStBl.setPhaseInDuration(0.5f);
        animStBl.setPhaseOutDuration(0.5f);
        animSt = animStBl.buildStyle();
        MarkerStyleBuilder markStCr = new MarkerStyleBuilder();
        markStCr.setSize(30f);
        Bitmap resourceDecoded = createBitmap();
        markStCr.setBitmap(BitmapUtils.createBitmapFromAndroidBitmap(resourceDecoded));
        markStCr.setAnimationStyle(animSt);
        MarkerStyle markSt = markStCr.buildStyle();

        return new Marker(loc, markSt);
    }

    private Bitmap createBitmap(){

            Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_marker);
            Bitmap bitmap = Bitmap.createBitmap(Objects.requireNonNull(drawable).getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);

            return bitmap;

    }


}
