package com.android.group.controller;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.group.R;
import com.android.group.model.F0;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class F0Controller {
    private static final String MOCK_URL = "https://my-json-server.typicode.com/hoang-10n/Android_Group";
    private static final String URL = MOCK_URL + "/f0s";
    private static final long REFRESH_REQUEST = 60 * 1000; // Refresh content interval = 60 secs
    private static RequestQueue queue = null;
    private static Context context;
    private static ListView container;

    public static void init(Context context, ListView container) {
        queue = Volley.newRequestQueue(context);
        F0Controller.context = context;
        F0Controller.container = container;
    }

    public static void getAllF0s() {
        JsonArrayRequest userArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                response -> {
                    ArrayList<String> f0s = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            f0s.add(object.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    ArrayAdapter<String> f0Array = new ArrayAdapter<>(context, R.layout.adapter, f0s);
                    container.setAdapter(f0Array);
//                    refreshContent();
                }, Throwable::printStackTrace);
        queue.add(userArrayRequest);
    }

    public static void addF0(F0 f0) {
        try {
            JSONObject jsonObject = new JSONObject(new Gson().toJson(f0));
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonObject,
                    response -> Log.d("TAG", response.toString())
                    , Throwable::printStackTrace);
            queue.add(jsonRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        database.addF0(f0);
    }

    public static void updateF0(F0 f0) {
        try {
            JSONObject jsonObject = new JSONObject(new Gson().toJson(f0));
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.PUT, URL, jsonObject,
                    response -> Log.d("TAG", response.toString())
                    , Throwable::printStackTrace);
            queue.add(jsonRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        database.updateF0(f0);
    }

    private static void refreshContent() {
        new CountDownTimer(REFRESH_REQUEST, 1000) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                getAllF0s();
            }
        }.start();
    }

}
