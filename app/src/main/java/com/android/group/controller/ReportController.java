package com.android.group.controller;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.group.R;
import com.android.group.model.Report;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReportController {
    private static final String MOCK_URL = "https://my-json-server.typicode.com/hoang-10n/Android_Group";
    private static final String URL = MOCK_URL + "/reports";
    private static final long REFRESH_REQUEST = 60 * 1000; // Refresh content interval = 60 secs
    private static RequestQueue queue = null;
    private static Context context;
    private static ListView container;

    public static void init(Context context, ListView container) {
        queue = Volley.newRequestQueue(context);
        ReportController.context = context;
        ReportController.container = container;
    }

    public static void getAllReports() {
        JsonArrayRequest userArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                response -> {
                    ArrayList<String> reports = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            reports.add(object.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    ArrayAdapter<String> reportArray = new ArrayAdapter<>(context, R.layout.adapter, reports);
                    container.setAdapter(reportArray);
//                    refreshContent();
                }, Throwable::printStackTrace);
        queue.add(userArrayRequest);
    }

    public static void addReport(Report report) {
        try {
            JSONObject jsonObject = new JSONObject(new Gson().toJson(report));
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonObject,
                    response -> Log.d("TAG", response.toString())
                    , Throwable::printStackTrace);
            queue.add(jsonRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        database.addReport(report);
    }

    public static void updateReport(Report report) {
        try {
            JSONObject jsonObject = new JSONObject(new Gson().toJson(report));
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.PUT, URL, jsonObject,
                    response -> Log.d("TAG", response.toString())
                    , Throwable::printStackTrace);
            queue.add(jsonRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        database.updateReport(report);
    }

    private static void refreshContent() {
        new CountDownTimer(REFRESH_REQUEST, 1000) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                getAllReports();
            }
        }.start();
    }
}
