package com.tugbaolcer.zomato.Utils;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.text.DecimalFormat;

public class NeyesemExtensions {
    public static boolean isServiceRunning(Context ctx, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    public static void loadImage(ImageView view, Object from, Priority priority, boolean circle, int placeholder) {
        MultiTransformation<Bitmap> transformation;
        if(circle){
            transformation = new MultiTransformation<>(new CenterCrop(),new CircleCrop());
        }else{
            transformation = new MultiTransformation<>(new CenterCrop());
        }
        RequestOptions requestOptions = new RequestOptions().priority(priority).transform(transformation).error(placeholder);
        Glide.with(view.getContext()).load(from).apply(requestOptions).into(view);
    }
    public static String getDistance(double lat_a, double lng_a, double lat_b, double lng_b) {
        DecimalFormat s = new DecimalFormat("###");
        double theta = lng_a - lng_b;
        double dist = Math.sin(deg2rad(lat_a)) * Math.sin(deg2rad(lat_b)) + Math.cos(deg2rad(lat_a)) * Math.cos(deg2rad(lat_b)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        if(dist > 100)
            return "100+ km";
        else if(dist > 1)
            return s.format(dist) + "km";
        return (s.format(dist * 100) + "m");
    }
    private static double deg2rad(double deg) { return (deg * Math.PI / 180.0); }
    private static double rad2deg(double rad) { return (rad * 180.0 / Math.PI); }
}