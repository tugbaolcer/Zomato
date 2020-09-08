package com.tugbaolcer.zomato.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.tugbaolcer.zomato.Activity.HaritaActivity;
import com.tugbaolcer.zomato.Model.Restaurant;
import com.tugbaolcer.zomato.R;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RestorantDetayAdapter extends RecyclerView.Adapter<RestorantDetayAdapter.CardTasarimTutucuDetay>{

    private Context context;
    private ArrayList<Restaurant> restaurantArrayList;

    public RestorantDetayAdapter(Context context, ArrayList<Restaurant> restaurantArrayList) {
        this.context = context;
        this.restaurantArrayList = restaurantArrayList;
    }

    @NonNull
    @Override
    public CardTasarimTutucuDetay onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.restorant_detay_card_tasarim, parent,false);
        return new CardTasarimTutucuDetay(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardTasarimTutucuDetay holder, int position) {
        final Restaurant restaurant= restaurantArrayList.get(position);
        holder.txt_baslik.setText(restaurant.getName());
        holder.txt_mutfak.setText(restaurant.getCuisines());
        holder.txt_saat.setText(restaurant.getTimings());
        //holder.txt_rating.setText((int) restaurant.getUserRating().getAggregate_rating());
        if (restaurant.getPhone_number() != null)
            holder.txt_telefon.setText(restaurant.getPhone_number());
        else
            holder.txt_telefon.setText("Telefon numarası yok");

        Picasso.get().load(restaurant.getThumb()).into(holder.rest_image);
        holder.txt_adres.setText(restaurant.getLocation().getAddress());
        holder.gecis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(view.getContext(), HaritaActivity.class);
                intent.putExtra("latitude", restaurant.getLocation().getLatitude());
                intent.putExtra("longitude", restaurant.getLocation().getLongitude());
                intent.putExtra("name", restaurant.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantArrayList.size();
    }

    public class CardTasarimTutucuDetay extends RecyclerView.ViewHolder{
        private TextView txt_baslik, txt_mutfak, txt_telefon, txt_saat, txt_adres, txt_rating;
        private LinearLayout gecis;
        private ImageView rest_image;

        public CardTasarimTutucuDetay(@NonNull View itemView) {
            super(itemView);
            txt_baslik=itemView.findViewById(R.id.txt_baslik);
            txt_mutfak=itemView.findViewById(R.id.txt_mutfak);
            txt_telefon=itemView.findViewById(R.id.txt_telefon);
            txt_saat=itemView.findViewById(R.id.txt_saat);
            rest_image=itemView.findViewById(R.id.rest_image);
            txt_adres=itemView.findViewById(R.id.txt_adres);
            gecis=itemView.findViewById(R.id.gecis);
            txt_rating=itemView.findViewById(R.id.txt_rating);

        }
    }
}
