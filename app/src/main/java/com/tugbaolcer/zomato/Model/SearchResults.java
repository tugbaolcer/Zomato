package com.tugbaolcer.zomato.Model;

import java.util.ArrayList;

public class SearchResults {

    private int results_found;

    private ArrayList<SearchRestaurant> restaurants;

    public ArrayList<SearchRestaurant> getRestaurants() {
        return restaurants;
    }

    public int getResults_found() {
        return results_found;
    }
}
