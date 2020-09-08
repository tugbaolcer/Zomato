package com.tugbaolcer.zomato.Fragments;


import android.view.ViewGroup;

import com.tugbaolcer.zomato.Model.Nearby.GeocodeResponse;
import com.tugbaolcer.zomato.Network.ZomataDaoInterface;
import com.tugbaolcer.zomato.Utils.CustomCallBack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Response;


public class NearbyRestaurantsPresenter {
    ZomataDaoInterface apiInterface ;
    private NearbyRestaurantsView nearbyRestaurantsView;

    public NearbyRestaurantsPresenter(NearbyRestaurantsView nearbyRestaurantsView) {
        this.nearbyRestaurantsView = nearbyRestaurantsView;
    }
    public void getNearbyRestaurants(AppCompatActivity activity, ViewGroup container, double latitude, double longitude) {
        apiInterface.getGeo(latitude,longitude).enqueue(new CustomCallBack<GeocodeResponse>(activity,container,nearbyRestaurantsView,CustomCallBack.Type.Layout) {
            @Override
            public void onResponse(@NonNull Call<GeocodeResponse> call, @NonNull Response<GeocodeResponse> response) {
                super.onResponse(call, response);
                if(response.isSuccessful())
                    nearbyRestaurantsView.onGetNearbyRestaurants(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<GeocodeResponse> call, @NonNull Throwable t) {
                super.onFailure(call, t);
            }
        });
    }


}