package com.android.google.store.json;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Items extends AppCompatActivity implements ItemsAdapter.OnItemClickListener {
    private RecyclerView recItemsList;
    private ItemsAdapter itemsAdapter;
    private RequestQueue requestQueue;
    private ArrayList<ItemsModel> itemList;
    public static final String KEY_JSONARRAY = "movies";
    public static final String KEY_CREATOR = "title";
    public static final String KEY_IMAGEURL = "photo1";
    public static final String KEY_LIKE = "description";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items);

        recItemsList = findViewById(R.id.recItemsList);
        recItemsList.setHasFixedSize(true);
        recItemsList.setLayoutManager(new LinearLayoutManager(Items.this));

        itemList = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(Items.this);
        parseJson();
    }

    private void parseJson() {
        String url = "https://myomyatthu7.github.io/TestJson/movies.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray(KEY_JSONARRAY);
                    for (int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String creator = jsonObject.getString(KEY_CREATOR);
                        String jsonImageUrl = jsonObject.getString(KEY_IMAGEURL);
                        //int likes = jsonObject.getInt(KEY_LIKE);
                        String likes = jsonObject.getString(KEY_LIKE);

                        itemList.add(new ItemsModel(jsonImageUrl,creator,likes));
                    }
                    itemsAdapter = new ItemsAdapter(Items.this,itemList);
                    recItemsList.setAdapter(itemsAdapter);
                    itemsAdapter.setOnItemClickListener(Items.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onItemClickListener(int position) {
        ItemsModel itemsPosition = itemList.get(position);
        Intent iDetail = new Intent(Items.this,ItemsDetail.class);
        iDetail.putExtra(KEY_CREATOR,itemsPosition.getCreator());
        iDetail.putExtra(KEY_IMAGEURL,itemsPosition.getImageUrl());
        iDetail.putExtra(KEY_LIKE,itemsPosition.getLikes());
        startActivity(iDetail);
    }
}