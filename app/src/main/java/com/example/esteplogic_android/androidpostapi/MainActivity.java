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


 /* Base64 Image
    package com.example.esteplogic_android.androidpostapi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private EditText editTextEmail;
    String company = "nssbzj";
    String storefront = "API";
    String basic_key = "Basic " + "bmFuZC5ob3RzaGVsZkBnbWFpbC5jb206czc0MTlJNmYxbks4Z2txTTc2ODgzdU4yODU2QTI0TzA=";
    JsonObject jbo;
    private Button buttonRegister;
    public static String TAG = "MAINACT";
    //This is our root url
    public static final String ROOT_URL = "http://velmm.com/apis";
   //@BindView(R.id.image)
   Button image;

    List<Example> movieList;

    static    String  base64String5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        image = (Button) findViewById(R.id.image);

        jbo = new JsonObject();
        buttonRegister.setOnClickListener(this);

        movieList = new ArrayList<>();



        image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 101);
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("onActivityResult++requestCode++" + requestCode);
        System.out.println("onActivityResult++resultCode++" + resultCode);
        System.out.println("onActivityResult++data++" + data);

        Uri uri = data.getData();

        File file = new File(uri.getPath());
        file.getName();

        System.out.println("onActivityResult+786+uri++" + uri);
        System.out.println("onActivityResult+787+file++" + file);


        if (requestCode == 101)
        {
            if (resultCode == RESULT_OK && data != null && data.getData() != null) {
                Uri selectedImageUri = data.getData();
                System.out.println("selectedImageUri++data++" + data);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                System.out.println("onActivityResult++baos++" + baos);

                Bitmap bitmap;

                try {
                    
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                    System.out.println("bitmap" + bitmap);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);

                    byte[] imageBytes = baos.toByteArray();
                    System.out.println("imageBytes" + imageBytes);
                    base64String5 = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    System.out.println("++142++base64String5++" + base64String5);

                ///    Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                 //   System.out.println("decodedImage" + decodedImage);

                 //   iv_Images4.setImageBitmap(decodedImage);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void insertUser()
    {

        RestAdapter.LogLevel logLevel = RestAdapter.LogLevel.FULL;

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .setLogLevel(logLevel)
                .build();

        //Creating object for our interface
        RegisterAPI api = adapter.create(RegisterAPI.class);
        //Defining the method insertuser of our interface

        api.insertUser (
                //Passing the values by getting it from editTexts
                //Creating an anonymous callback
                new Callback <List<Example>>()
                {
                    @Override
                    public void success(Response result, Response <List<Example>>  response)
                    {

                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object

                        BufferedReader reader = null;
                        Log.e(TAG, "+insertUser+94++" + new Gson().toJson(response));
                        //An string to store output from the server
                        String output = "";

                        try
                        {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
                            //Reading the output in the string
                            output = reader.readLine();

                    //        movieList = ;


                            Log.e(TAG, "success: " +output);
                        }

                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        //Displaying the output as a toast
                        Toast.makeText(MainActivity.this, output, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error)
                    {
                        //If any error occured displaying the error as toast
                        Log.e(TAG, "+insertUser+120++" + error.toString());
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    @Override
    public void onClick(View v)
    {
        insertUser();
    }
}
    
    */
    
