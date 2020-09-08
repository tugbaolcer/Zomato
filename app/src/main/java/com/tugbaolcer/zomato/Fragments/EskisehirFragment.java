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
public class EskisehirFragment extends Fragment {

    RecyclerView recyclerView;
    CollectionAdapter collectionAdapter;
    ZomataDaoInterface zomataDaoInterface;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_eskisehir, container, false);
        zomataDaoInterface= ApiUtils.getZomatoDaoInterface();
        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.hasFixedSize();
        adanaWeb();
        return view;
    }
    private void adanaWeb(){
        zomataDaoInterface.getColletion(403).enqueue(new Callback<CollectionList>() {
            @Override
            public void onResponse(Call<CollectionList> call, Response<CollectionList> response) {
                if(response.isSuccessful()){
                    collectionAdapter=new CollectionAdapter(getActivity(), response.body().getCollections());
                    recyclerView.setAdapter(collectionAdapter);
                }
            }

            @Override
            public void onFailure(Call<CollectionList> call, Throwable t) {

            }
        });
    }




}
