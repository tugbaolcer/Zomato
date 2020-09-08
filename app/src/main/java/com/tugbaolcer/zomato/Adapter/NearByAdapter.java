package com.tugbaolcer.zomato.Adapter;

import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Priority;
import com.google.android.gms.location.LocationResult;
import com.tugbaolcer.zomato.Model.Nearby.NearbyRestaurant;
import com.tugbaolcer.zomato.Model.Nearby.Restaurant;
import com.tugbaolcer.zomato.R;
import com.tugbaolcer.zomato.Utils.NeyesemExtensions;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class NearByAdapter extends RecyclerView.Adapter<NearByAdapter.ViewHolder> {
    private Location locationResult;
    private List<NearbyRestaurant> restaurantList = new ArrayList<>();
    private NearbyRestaurantsListener nearbyRestaurantsListener;


    public NearByAdapter(NearbyRestaurantsListener nearbyRestaurantsListener) {
        this.nearbyRestaurantsListener = nearbyRestaurantsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.nearby_card, parent, false);
        return new NearByAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position).getRestaurant();
        holder.ratingTextView.setText(restaurant.getUserRating().getAggregateRating());
        String cuisineString = restaurant.getCuisines().split(",")[0];
        holder.cuisineTextView.setText(cuisineString);
        holder.restaurantNameTextView.setText(restaurant.getName());
        NeyesemExtensions.loadImage(holder.thumbnailImageView, restaurant.getThumb(), Priority.HIGH, false, R.drawable.header);
        if(locationResult == null)
            holder.distanceView.setVisibility(View.GONE);
        else{
            holder.distanceView.setVisibility(View.VISIBLE);
            String distance = NeyesemExtensions.getDistance(restaurant.getLocation().getLatitude(),restaurant.getLocation().getLongitude(),locationResult.getLatitude(),locationResult.getLongitude());
            holder.distanceTextView.setText(distance);
        }
        //holder.cardView.setOnClickListener(v -> nearbyRestaurantsListener.onClickRestaurant(restaurant.getR().getResId()));
    }
    public void setList(List<NearbyRestaurant> restaurants) {
        this.restaurantList.clear();
        this.restaurantList.addAll(restaurants);
        notifyDataSetChanged();
    }
    public void setLocation(LocationResult locResult) {
        this.locationResult = locResult.getLastLocation();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView ratingTextView;
        TextView cuisineTextView;
        TextView restaurantNameTextView;
        ImageView thumbnailImageView;
        TextView distanceTextView;
        RelativeLayout distanceView;

        public ViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.card_nearbyrestaurant);
            ratingTextView = view.findViewById(R.id.textview_itemnearbyrestaurant_rating);
            cuisineTextView = view.findViewById(R.id.textview_itemnearbyrestaurant_cuisine);
            restaurantNameTextView = view.findViewById(R.id.textview_itemnearbyrestaurant_name);
            thumbnailImageView = view.findViewById(R.id.imageview_itemnearbyrestaurant_thumbnail);
            distanceTextView = view.findViewById(R.id.textview_itemnearbyrestaurant_distance);
            distanceView = view.findViewById(R.id.reletivelayout_nearbyrestaurant_distance);

        }
    }
}
