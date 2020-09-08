package com.tugbaolcer.zomato.Model;

 public class RestaurantResult {
     private long id;
     private String name;
     private String thumb;//resimler burda
     private String cuisines;
     private String timings;
     private UserRating user_rating;


     public String getName() {
         return name;
     }

     public long getId() {
         return id;
     }

     public String getThumb() {
         return thumb;
     }

     public String getCuisines() {
         return cuisines;
     }

     public String getTimings() {
         return timings;
     }

    public UserRating getUser_rating() {
         return user_rating;
     }
 }
