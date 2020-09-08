package com.tugbaolcer.zomato.Network;

import com.tugbaolcer.zomato.Model.CollectionList;
import com.tugbaolcer.zomato.Model.NearByList;
import com.tugbaolcer.zomato.Model.Nearby.GeocodeResponse;
import com.tugbaolcer.zomato.Model.Restaurant;
import com.tugbaolcer.zomato.Model.SearchResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ZomataDaoInterface {
    @Headers("user-key: 9da36d3bc8d0820b0ec07ad58255f204")
    @GET("collections")
    Call<CollectionList> getColletion(@Query("city_id") int city_id);

    @Headers("user-key: 9da36d3bc8d0820b0ec07ad58255f204")
    @GET("restaurant")
    Call<Restaurant> getRestaurant(@Query("res_id") long res_id);

    @Headers("user-key: 9da36d3bc8d0820b0ec07ad58255f204")
    @GET("search")
    Call<SearchResults> getSearch(@Query("q") String searchText);

    @Headers("user-key: 9da36d3bc8d0820b0ec07ad58255f204")
    @GET("geocode")
    Call<GeocodeResponse> getGeo(@Query("lat") Double lat, @Query("lon") Double lon);
}
