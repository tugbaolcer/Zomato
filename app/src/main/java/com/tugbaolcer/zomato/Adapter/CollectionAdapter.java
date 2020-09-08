package com.tugbaolcer.zomato.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.tugbaolcer.zomato.Model.Collection;
import com.tugbaolcer.zomato.R;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.CardTasarimTutucu> {
    private Context mcontex;
    private ArrayList<Collection> marrayList;
    public CollectionAdapter(Context mcontex, ArrayList<Collection> marrayList) {
        this.mcontex = mcontex;
        this.marrayList =marrayList ; }
    //Tasarımın içinde ne varsa burada bağlarız.
    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        private CardView card_view;
        private TextView text_card_name, txt_description;
        private ImageView image_card_cover,image_action_share;
        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);
            card_view=itemView.findViewById(R.id.card_view);
            text_card_name=itemView.findViewById(R.id.text_card_name);
            txt_description=itemView.findViewById(R.id.txt_description);
            image_card_cover=itemView.findViewById(R.id.image_card_cover);
            image_action_share=itemView.findViewById(R.id.image_action_share); }}
    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim, parent,false);
        return new CardTasarimTutucu(view); }
    @Override
    public void onBindViewHolder(@NonNull final CardTasarimTutucu holder, int position) {
        final Collection rA_collection = marrayList.get(position);
        holder.text_card_name.setText(rA_collection.getCollection().getTitle());
        holder.txt_description.setText(rA_collection.getCollection().getDescription());
        Picasso.get().load(rA_collection.getCollection().getImage_url()).into(holder.image_card_cover);
    }
    @Override
    public int getItemCount() {
        return marrayList.size();
    }
}
