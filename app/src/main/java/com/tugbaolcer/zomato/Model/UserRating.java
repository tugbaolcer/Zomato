package com.tugbaolcer.zomato.Model;

public class UserRating {
    private float aggregate_rating;//Toplam puan
    private String rating_text;// değerlerndirme açıklaması
    private String rating_color;//değerlendirme rengi
    private float votes; //alınan derecelendirme sayısı

    public float getAggregate_rating() {
        return aggregate_rating;
    }

    public String getRating_text() {
        return rating_text;
    }

    public String getRating_color() {
        return rating_color;
    }

    public float getVotes() {
        return votes;
    }
}
