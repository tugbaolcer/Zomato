package com.tugbaolcer.zomato.Utils;

import android.app.Dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tugbaolcer.zomato.BaseView;
import com.tugbaolcer.zomato.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomCallBack<T> implements Callback<T> {


    ViewGroup viewGroup;
    BaseView baseView;
    View customView;
    Dialog dialog;
    AppCompatActivity activity;
    CustomCallBack.Type type;

    public enum Type{
        Dialog,
        Layout,
        None
    }

    protected CustomCallBack(AppCompatActivity activity, ViewGroup viewGroup, BaseView baseView, CustomCallBack.Type Type) {
        deleteDialogs();
        deleteAllLayouts();
        if (activity != null) {
            this.type = Type;
            this.activity = activity;
            this.baseView = baseView;
            this.viewGroup = viewGroup;
            if(type == CustomCallBack.Type.Dialog){
                dialog = new Dialog(activity, R.style.AppTheme_Dialog);
                dialog.setContentView(R.layout.dialog_loading);
                dialog.show();
            }
            else if(type == CustomCallBack.Type.Layout){
                customView = LayoutInflater.from(activity).inflate(R.layout.layout_custom,null);
                customView.setTag("layout_view");
                customView.setVisibility(View.VISIBLE);
                customView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                viewGroup.addView(customView);
            }
        }
    }


    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        deleteDialogs();
        deleteAllLayouts();
        if(!response.isSuccessful()){
            try{
                if(response.code() == 403){
                    baseView.onUserError(response);
                    //error dialog
                } else{
                    baseView.onUserError(response);
                }
            }catch (Exception ignore){}
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        deleteDialogs();
        deleteAllLayouts();
        if(type == CustomCallBack.Type.Dialog){
            dialog = new Dialog(activity,R.style.AppTheme_Dialog);
            dialog.setContentView(R.layout.dialog_custom);
            dialog.findViewById(R.id.button_dialogcustom_confirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    baseView.onConfirmDialog();
                }
            });
            dialog.show();
        }
        else if(type == CustomCallBack.Type.Layout){
            customView = LayoutInflater.from(activity).inflate(R.layout.layout_custom,null);
            customView.findViewById(R.id.progressbar_loadinglayout).setVisibility(View.GONE);
            customView.findViewById(R.id.imageview_loadinglayout_retry).setVisibility(View.VISIBLE);
            customView.findViewById(R.id.imageview_loadinglayout_retry).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomCallBack.this.deleteAllLayouts();
                    baseView.onRetryLayout();
                }
            });
            ((TextView)customView.findViewById(R.id.textview_loadinglayout_message)).setText(activity.getString(R.string.try_again));
            customView.setTag("layout_view");
            customView.setVisibility(View.VISIBLE);
            customView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            viewGroup.addView(customView);
        }
    }

    private void deleteAllLayouts(){
        if(viewGroup != null) {
            while(viewGroup.findViewWithTag("layout_view") != null){
                View v = viewGroup.findViewWithTag("layout_view");
                viewGroup.removeView(v);
            }
        }
    }
    private void deleteDialogs(){
        if (dialog != null)
            dialog.dismiss();
    }
}