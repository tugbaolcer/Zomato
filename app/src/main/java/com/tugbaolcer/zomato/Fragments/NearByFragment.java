package com.tugbaolcer.zomato.Fragments;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.gms.location.LocationResult;
import com.tugbaolcer.zomato.Adapter.NearByAdapter;
import com.tugbaolcer.zomato.Adapter.NearbyRestaurantsListener;
import com.tugbaolcer.zomato.BaseFragment;
import com.tugbaolcer.zomato.BuildConfig;
import com.tugbaolcer.zomato.Model.Nearby.GeocodeResponse;
import com.tugbaolcer.zomato.R;
import com.tugbaolcer.zomato.service.LocationService;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Response;

public class NearByFragment extends BaseFragment implements NearbyRestaurantsView, NearbyRestaurantsListener {
    private View parentView;
    private RelativeLayout container;
    private NearbyRestaurantsPresenter presenter;
    private Location lastLocation;
    private NearByAdapter adapter = new NearByAdapter(this);
    private RecyclerView nearbyRestaurantsRecyclerView;
    private SwipeRefreshLayout refreshLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (parentView == null) {
            parentView = inflater.inflate(R.layout.fragment_nearby, container, false);
            presenter = new NearbyRestaurantsPresenter(this);
            initViews();
            configurelocationPermission();
        }
        return parentView;
    }

    private void getNearbyRestaurants(){
        presenter.getNearbyRestaurants((AppCompatActivity) getActivity(),container,lastLocation.getLatitude(),lastLocation.getLongitude());
    }

    private void initViews() {
        refreshLayout = parentView.findViewById(R.id.refreshView);
        container = parentView.findViewById(R.id.relativelayout_container);
        nearbyRestaurantsRecyclerView = parentView.findViewById(R.id.recyclerview_nearbyrestaurants);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        nearbyRestaurantsRecyclerView.setLayoutManager(manager);
        nearbyRestaurantsRecyclerView.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Load data to your RecyclerView
                NearByFragment.this.startLocationService();
                refreshLayout.setRefreshing(false);

            }
        });
    }

    private void configurelocationPermission(){
        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.LOCATION)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        NearByFragment.this.startLocationService();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        new AlertDialog.Builder(NearByFragment.this.getContext())
                                .setTitle("Ne Yesem")
                                .setMessage(R.string.location_on_access_denied)
                                .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (AndPermission.hasAlwaysDeniedPermission(NearByFragment.this.getContext(), "")) {
                                            NearByFragment.this.getContext().startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID)));
                                        }
                                    }
                                })
                                .setCancelable(false).show();
                    }
                })
                .start();
    }

    private void startLocationService() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        getActivity().startService(new Intent(getContext(), LocationService.class));
    }

    private void stopLocationService() {
        EventBus.getDefault().unregister(this);
        getActivity().stopService(new Intent(getContext(), LocationService.class));
    }

    @SuppressLint("MissingPermission")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLocationCallback(LocationResult locationResult) {
        adapter.setLocation(locationResult);
        this.lastLocation = locationResult.getLastLocation();
        getNearbyRestaurants();
        stopLocationService();
    }


    @Override
    public void onGetNearbyRestaurants(GeocodeResponse response) {
        adapter.setList(response.getNearbyRestaurants());
    }

    @Override
    public void onConfirmDialog() {

    }

    @Override
    public void onRetryLayout() {

    }

    @Override
    public void onUserError(Response serverResponse) {

    }
    public static NearByFragment newInstance(){
        return new NearByFragment();
    }


    @Override
    public void onClickRestaurant(int restaurantId) {

    }
}
