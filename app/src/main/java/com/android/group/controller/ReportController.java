package com.android.group.controller;

import android.content.Context;

import com.android.group.model.MedicalRecord;
import com.android.group.model.Report;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReportController {
    public static final String REPORT_API_URL = "http://10.0.2.2:8080/report";
    public static final String REPORT_API_URL1 = "http://10.0.2.2:8080/report/reportId/";
    private static Context context;
    Report report;

    public ReportController(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener1{
        void onError(String message);

        void onResponse(String message);
    }

    public interface VolleyResponseListener{
        void onError(String message);

        void onResponse(Report report);
    }

    public static void addReport(Report report, VolleyResponseListener1 volleyResponseListener1) {
        JSONObject postData = new JSONObject();
        try {
            postData.put("patientUUID", report.getPatientUUID());
            postData.put("temperature", report.getTemperature());
            postData.put("cough", report.getCough());
            postData.put("breath", report.getBreath());
            postData.put("pneumonia", report.getPneumonia());
            postData.put("diseases", report.getDiseases());
            postData.put("symptoms", report.getSymptoms());
            postData.put("sthroat", report.getsThroat());
            postData.put("rtimestamp", report.getrTimestamp());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        CustomJsonObjectRequest jsonObjectRequest = new CustomJsonObjectRequest(Request.Method.POST, REPORT_API_URL, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                volleyResponseListener1.onResponse("Medical record registered successfully");
                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener1.onError("Something wrong");
                error.printStackTrace();
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }


    public void getReport(Long reportId, VolleyResponseListener volleyResponseListener){
        report = new Report();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,REPORT_API_URL1+reportId, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    report.setReportId(response.getLong("reportId"));
                    report.setTemperature(response.getString("temperature"));
                    report.setCough(response.getString("cough"));
                    report.setBreath(response.getString("breath"));
                    report.setPneumonia(response.getString("pneumonia"));
                    report.setVitalStat(response.getString("vitalStat"));
                    report.setDiseases(response.getString("diseases"));
                    report.setSymptoms(response.getString("symptoms"));
                    report.setsThroat(response.getString("sthroat"));
                    report.setrTimestamp(response.getString("rtimestamp"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                volleyResponseListener.onResponse(report);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context,"Something wrong",Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Something wrong");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);

    }

}
