package com.tugbaolcer.zomato.Model;

import java.io.Serializable;

public class Location implements Serializable {
   /* "location": {
        "address": "1 5th Avenue, New York, NY 10003",
                "locality": "Greenwich Village",
                "city": "New York City",
                "latitude": "40.732013",
                "longitude": "-73.996155",
                "zipcode": "10003",
                "country_id": "216"
                }
    */
   private String address;
   private String city;
   private int city_id;
   private String locality;
   private double latitude;
   private double longitude;

    public int getCity_id() {
        return city_id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getLocality() {
        return locality;
    }
}
