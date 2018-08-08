package com.example.esteplogic_android.androidpostapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.security.auth.login.LoginException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Declaring views
    private EditText editTextName;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextEmail;

    JSONObject paramObject;
    String company = "nssbzj";
    String storefront = "API";
    String email = "sb258fs125bsh@dbsb.sn";
    String basic_key =  "Basic "+"bmFuZC5ob3RzaGVsZkBnbWFpbC5jb206czc0MTlJNmYxbks4Z2txTTc2ODgzdU4yODU2QTI0TzA=";

    JsonObject jbo;


    private Button buttonRegister;
    public static  String TAG ="MAINACT";
    //This is our root url
    public static final String ROOT_URL = "http://cartmodules.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing Views
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        //Adding listener to button
        buttonRegister.setOnClickListener(this);
    }


    private void insertUser(){
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter

try {

     paramObject = new JSONObject();
    paramObject.put("company", "nsdhsbzj");
    paramObject.put("storefront", "API");
    paramObject.put("email", editTextEmail.getText().toString());



}
catch (Exception e)
{

}

        // HttpLoggingInterceptor.Level logLevel = HttpLoggingInterceptor.Level.BODY;
        RestAdapter.LogLevel logLevel = RestAdapter.LogLevel.FULL;

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .setLogLevel(logLevel)
                .build(); //Finally building the adapter

        String bla = paramObject.toString();
        System.out.println(bla);
        String bla2 = bla.replaceAll("\\\\", "");
        System.out.println("bla2++"+bla2);

        jbo=new JsonObject();

//try {


   //  jbo = new JsonObject(bla2);


    jbo.addProperty("company", "nsdhsbzj");
    jbo.addProperty("storefront", "API");
    jbo.addProperty("email", editTextEmail.getText().toString());




    System.out.println("jbo116++"+jbo);
/*
}catch (Exception e)
{

}*/
        //Creating object for our interface
        RegisterAPI api = adapter.create(RegisterAPI.class);
        //Defining the method insertuser of our interface
        api.insertUser(

                //Passing the values by getting it from editTexts

               basic_key,


                jbo  ,


                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override


                    public void success(Response result, Response response) {
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader = null;

                        Log.e(TAG, "+insertUser+94++"+new Gson().toJson(response) );

                        Log.e(TAG, "+paramObject.toString()+120++"+paramObject.toString() );

                        //An string to store output from the server
                        String output = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();

                            Log.e(TAG, "success: "+"" );

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //Displaying the output as a toast
                        Toast.makeText(MainActivity.this, output, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Log.e(TAG, "+insertUser+120++"+error.toString());

                      /*  OkHttpClient.Builder client = new OkHttpClient.Builder();

                        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

                        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                        client.addInterceptor(loggingInterceptor);*/

                        Toast.makeText(MainActivity.this, error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    //Overriding onclick method
    @Override
    public void onClick(View v) {
        //Calling insertUser on button click
        insertUser();
    }
}