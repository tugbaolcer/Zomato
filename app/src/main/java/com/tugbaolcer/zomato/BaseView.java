package com.tugbaolcer.zomato;

import retrofit2.Response;

public interface BaseView {
    void onConfirmDialog();
    void onRetryLayout();
    void onUserError(Response serverResponse);
}