package com.tugbaolcer.zomato.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tugbaolcer.zomato.Adapter.SearchRestaurantAdapter;
import com.tugbaolcer.zomato.Model.SearchResults;
import com.tugbaolcer.zomato.Network.ApiUtils;
import com.tugbaolcer.zomato.Network.ZomataDaoInterface;
import com.tugbaolcer.zomato.R;



public class RestorantsFragment extends Fragment {

    private RecyclerView recycler_view;
    private EditText etSearch;
    private SearchRestaurantAdapter restaurantAdapter;
    private ZomataDaoInterface zomataDaoInterface;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_restorants, container, false);
        zomataDaoInterface= ApiUtils.getZomatoDaoInterface();
        recycler_view=view.findViewById(R.id.recycler_view);
        recycler_view.hasFixedSize();
        etSearch = view.findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length()>2) {
                    restorantSearchWeb(editable.toString());
                }
            }
        });
        return view;
    }

    private void restorantSearchWeb(String searchText){
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        zomataDaoInterface.getSearch(searchText).enqueue(new Callback<SearchResults>() {
            @Override
            public void onResponse(Call<SearchResults> call, final Response<SearchResults> response) {

                restaurantAdapter=new SearchRestaurantAdapter(getActivity(), response.body().getRestaurants());
                recycler_view.setAdapter(restaurantAdapter);
            }

            @Override
            public void onFailure(Call<SearchResults> call, Throwable t) {

            }
        });
    }

}
