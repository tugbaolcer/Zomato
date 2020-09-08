package com.tugbaolcer.zomato.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tugbaolcer.zomato.Adapter.CollectionAdapter;
import com.tugbaolcer.zomato.Model.CollectionList;
import com.tugbaolcer.zomato.Network.ApiUtils;
import com.tugbaolcer.zomato.Network.ZomataDaoInterface;
import com.tugbaolcer.zomato.R;

public class CollectionFragment extends Fragment {

    RecyclerView recycler_view;
    CollectionAdapter collectionAdapter;
    ZomataDaoInterface zomataDaoInterface;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_collection, container, false);
        zomataDaoInterface= ApiUtils.getZomatoDaoInterface();
        recycler_view=view.findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_view.hasFixedSize();
        collectionWeb();
        return view;
    }
    private void collectionWeb(){
        zomataDaoInterface.getColletion(59).enqueue(new Callback<CollectionList>() {
            @Override
            public void onResponse(Call<CollectionList> call, Response<CollectionList> response) {
                if(response.isSuccessful()){

                    collectionAdapter= new CollectionAdapter(getActivity(),response.body().getCollections());
                    recycler_view.setAdapter(collectionAdapter); } }
            @Override
            public void onFailure(Call<CollectionList> call, Throwable t) {

            }
        });
    }
}
