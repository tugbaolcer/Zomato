package com.tugbaolcer.zomato.Activity;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.tugbaolcer.zomato.Network.ApiUtils;
import com.tugbaolcer.zomato.Network.ZomataDaoInterface;
import com.tugbaolcer.zomato.R;

public class HaritaActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mGoogleMap;
    private ZomataDaoInterface zomataDaoInterface;
    private LocationManager locationManager;
    private String konumGetir="gps";
    private int izinKontrolu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harita);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        zomataDaoInterface= ApiUtils.getZomatoDaoInterface();
        locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        izinKontrolu= ContextCompat.checkSelfPermission(HaritaActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(izinKontrolu!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(HaritaActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        }else{
            Location yer=locationManager.getLastKnownLocation(konumGetir); }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap=googleMap;
        Intent intent=getIntent();
        Double lat=intent.getDoubleExtra("latitude",0);
        Double lon=intent.getDoubleExtra("longitude",0);
        String name=intent.getStringExtra("name");
        LatLng konum=new LatLng(lat, lon);

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(konum));
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(konum, 15));
        mGoogleMap.addPolyline(new PolylineOptions().add(konum).color(Color.RED));
        mGoogleMap.addCircle(new CircleOptions()
                .center(konum)
                .radius(500.0)
                .strokeWidth(3f)
                .strokeColor(Color.RED)
                .fillColor(Color.argb(70, 150, 50,50))
        );
        mGoogleMap.setTrafficEnabled(true);
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        mGoogleMap.setMyLocationEnabled(true);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED &&
        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            return;
        }
        UiSettings uiSettings=googleMap.getUiSettings();
        uiSettings.setCompassEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setRotateGesturesEnabled(true);
        mGoogleMap.addMarker(new MarkerOptions().position(konum).title("Adres:"+name));
    }




}
