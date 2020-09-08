package com.tugbaolcer.zomato.Model;

import java.io.Serializable;

public class Restaurant implements Serializable {

    private int id;
    private String name;
    private String thumb;
    private String timings;
    private String cuisines;
    private String phone_number;
    private UserRating userRating;

    public UserRating getUserRating() {
        return userRating;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getCuisines() {
        return cuisines;
    }

    private Location location;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getThumb() {
        return thumb;
    }


    public String getTimings() {
        return timings;
    }

    public Location getLocation() {
        return location;
    }


}
