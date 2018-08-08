package com.example.esteplogic_android.androidpostapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by Belal on 11/5/2015.
 */

public interface RegisterAPI
{


    @POST("/demo/hotshelf/api/VendorsRequest")
    @Headers({"Content-Type: application/json"})
    public void insertUser (@Header("Authorization") String authkey, @Body() JsonObject body, Callback<Response> callback);

}