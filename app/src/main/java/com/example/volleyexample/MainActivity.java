package com.example.volleyexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "https://api.github.com/users";
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkCall();
        recyclerView=findViewById(R.id.programming_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void networkCall() {
        StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("JAVA CODE", response);
                Toast.makeText(getApplicationContext(), "Success.", Toast.LENGTH_SHORT).show();

                GsonBuilder gsonBuilder = new GsonBuilder();  //using gson to convert JSON(request) to java object.
                Gson gson = gsonBuilder.create();
                User[] users=gson.fromJson(response, User[].class);
                recyclerView.setAdapter(new GithubAdapter(MainActivity.this, users));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error has been occured.", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}
