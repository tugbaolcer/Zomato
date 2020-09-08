package com.tugbaolcer.zomato.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.maps.GoogleMap;
import com.tugbaolcer.zomato.Adapter.RestorantDetayAdapter;
import com.tugbaolcer.zomato.Model.Restaurant;
import com.tugbaolcer.zomato.Network.ApiUtils;
import com.tugbaolcer.zomato.Network.ZomataDaoInterface;
import com.tugbaolcer.zomato.R;

import java.util.ArrayList;

public class RestorantDetayActivity extends AppCompatActivity{
    private GoogleMap mGoogleMap;
    private RecyclerView recyclerView;
    private ZomataDaoInterface zomataDaoInterface;
    private RestorantDetayAdapter restorantDetayAdapter;
    private ArrayList<Restaurant> restaurants;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restorant_detay);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Restorant Bilgileri");
        setSupportActionBar(toolbar);
        restaurants= new ArrayList<>();
        zomataDaoInterface= ApiUtils.getZomatoDaoInterface();
        recyclerView=findViewById(R.id.detayRv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent intent=getIntent();
        Long veri=intent.getLongExtra("veri",0);
        if(veri!=null){
            restorantWeb(veri);
        }
    }
    private void restorantWeb(long res_id){
        zomataDaoInterface.getRestaurant(res_id).enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                restaurants.clear();
                restaurants.add(response.body());
                restorantDetayAdapter=new RestorantDetayAdapter(RestorantDetayActivity.this, restaurants);
                recyclerView.setAdapter(restorantDetayAdapter);
            }
            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
            }
        });
    }



}
