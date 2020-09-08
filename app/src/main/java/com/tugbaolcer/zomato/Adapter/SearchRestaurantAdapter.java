package com.tugbaolcer.zomato.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.tugbaolcer.zomato.Activity.RestorantDetayActivity;
import com.tugbaolcer.zomato.Model.SearchRestaurant;
import com.tugbaolcer.zomato.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SearchRestaurantAdapter extends RecyclerView.Adapter<SearchRestaurantAdapter.RestCardTutucu> {
    private Context restContex;
    private ArrayList<SearchRestaurant> restaurantArrayList;
    public SearchRestaurantAdapter(Context restContex, ArrayList<SearchRestaurant> restaurantArrayList ) {
        this.restContex = restContex;
        this.restaurantArrayList = restaurantArrayList; }
    @Override
    public int getItemCount() {
        return restaurantArrayList.size();
    }
    public class RestCardTutucu extends RecyclerView.ViewHolder{
        private ImageView restaurant_image;
        private TextView txt_rest_baslik,txt_ear_cuisinus;
        private RatingBar ratingBar;
        private CardView card_view;
        public RestCardTutucu(@NonNull View itemView) {
            super(itemView);
            restaurant_image=itemView.findViewById(R.id.restaurant_image);
            txt_rest_baslik=itemView.findViewById(R.id.txt_rest_baslik);
            txt_ear_cuisinus=itemView.findViewById(R.id.txt_ear_cuisinus);
            ratingBar=itemView.findViewById(R.id.ratingBar);
            card_view=itemView.findViewById(R.id.card_view); }}
    @NonNull
    @Override
    public RestCardTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_card_tutucu, parent,false);
        return new RestCardTutucu(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RestCardTutucu holder, int position) {
        final SearchRestaurant restaurant=restaurantArrayList.get(position);
        holder.txt_rest_baslik.setText(restaurant.getRestaurant().getName());
        holder.txt_ear_cuisinus.setText(restaurant.getRestaurant().getCuisines());
        holder.ratingBar.setRating(restaurant.getRestaurant().getUser_rating().getVotes());
        Picasso.get().load(restaurant.getRestaurant().getThumb()).into(holder.restaurant_image);
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), RestorantDetayActivity.class);
                intent.putExtra( "veri",restaurant.getRestaurant().getId());
                restContex.startActivity(intent);
            }
        });
    }
}
