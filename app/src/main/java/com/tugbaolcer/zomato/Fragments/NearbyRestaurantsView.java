package com.tugbaolcer.zomato.Fragments;


import com.tugbaolcer.zomato.BaseView;
import com.tugbaolcer.zomato.Model.Nearby.GeocodeResponse;

public interface NearbyRestaurantsView extends BaseView {
    void onGetNearbyRestaurants(GeocodeResponse response);
}