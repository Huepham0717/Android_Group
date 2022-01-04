package com.android.group.controller;

import android.content.Context;

import com.android.group.model.MedicalRecord;
import com.android.group.model.Report;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordController {
    public static final String MEDICAL_RECORD_API_URL = "http://10.0.2.2:8080/medicalRecord";
    public static final String MEDICAL_RECORD_API_URL1 = "http://10.0.2.2:8080/medicalRecord/patientUUID/";
    public static final String MEDICAL_RECORD_API_URL2 = "http://10.0.2.2:8080/medicalRecord/medicalRecordId/";
    private static Context context;
    List<Report> reportList;
    MedicalRecord medicalRecord;

    public MedicalRecordController(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener1{
        void onError(String message);

        void onResponse(String message);
    }

    public interface VolleyResponseListener{
        void onError(String message);

        void onResponse(MedicalRecord medicalRecord, List<Report> reportList);

    }


    public static void addMedicalRecord(MedicalRecord medicalRecord, VolleyResponseListener1 volleyResponseListener1) {
        JSONObject postData = new JSONObject();
        try {
            postData.put("patientUUID", medicalRecord.getPatientUUID());
            postData.put("rtimestamp", medicalRecord.getrTimestamp());
            postData.put("vaxStat", medicalRecord.getfVaxStat());
            postData.put("infectStat", medicalRecord.getfInfectStat());
            postData.put("emergency", medicalRecord.getfEmergency());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        CustomJsonObjectRequest jsonObjectRequest = new CustomJsonObjectRequest(Request.Method.POST, MEDICAL_RECORD_API_URL, postData, new Response.Listener<JSONObject>() {
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

    public void findMedicalRecordListByPatient(String UID,VolleyResponseListener volleyResponseListener){
        // RequestQueue queue = Volley.newRequestQueue(ViewAllSitesActivity.this);
        medicalRecord = new MedicalRecord();
        reportList = new ArrayList<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,MEDICAL_RECORD_API_URL1+UID, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                                medicalRecord.setMedicalRecordId(response.getLong("medicalRecordId"));
                                medicalRecord.setPatientUUID(response.getString("patientUUID"));
                                medicalRecord.setrTimestamp(response.getString("rtimestamp"));
                                medicalRecord.setrContent(response.getString("rcontent"));
                                medicalRecord.setfVaxStat(response.getString("vaxStat"));
                                medicalRecord.setfInfectStat(response.getString("infectStat"));
                                medicalRecord.setfVitalStat(response.getString("vitalStat"));
                                medicalRecord.setfEmergency(response.getString("emergency"));
                            for(int i = 0; i < response.getJSONArray("reportList").length();i++){
                                JSONObject jsonObject = response.getJSONArray("reportList").getJSONObject(i);
                                Report report = new Report();
                                report.setReportId(jsonObject.getLong("reportId"));
                                report.setTemperature(jsonObject.getString("temperature"));
                                report.setCough(jsonObject.getString("cough"));
                                report.setBreath(jsonObject.getString("breath"));
                                report.setsThroat(jsonObject.getString("sthroat"));
                                report.setPneumonia(jsonObject.getString("pneumonia"));
                                report.setrTimestamp(response.getString("rtimestamp"));
                                report.setDiseases(jsonObject.getString("diseases"));
                                report.setPneumonia(jsonObject.getString("symptoms"));
                                reportList.add(report);
                                medicalRecord.setReportList(reportList);
                            }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        volleyResponseListener.onResponse(medicalRecord,reportList);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(context,"Something wrong",Toast.LENGTH_SHORT).show();
                        volleyResponseListener.onError(error.toString());
                    }
                });
            MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
        }

    public void getMedicalRecord(Long medicalRecordId,VolleyResponseListener volleyResponseListener){
        medicalRecord = new MedicalRecord();
        reportList = new ArrayList<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,MEDICAL_RECORD_API_URL2+medicalRecordId, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    medicalRecord.setMedicalRecordId(response.getLong("medicalRecordId"));
                    medicalRecord.setPatientUUID(response.getString("patientUUID"));
                    medicalRecord.setrTimestamp(response.getString("rtimestamp"));
                    medicalRecord.setrContent(response.getString("rcontent"));
                    medicalRecord.setfVaxStat(response.getString("vaxStat"));
                    medicalRecord.setfInfectStat(response.getString("infectStat"));
                    medicalRecord.setfVitalStat(response.getString("vitalStat"));
                    medicalRecord.setfEmergency(response.getString("emergency"));
                    for(int i = 0; i < response.getJSONArray("reportList").length();i++){
                        JSONObject jsonObject = response.getJSONArray("reportList").getJSONObject(i);
                        Report report = new Report();
                        report.setReportId(jsonObject.getLong("reportId"));
                        report.setTemperature(jsonObject.getString("temperature"));
                        report.setCough(jsonObject.getString("cough"));
                        report.setBreath(jsonObject.getString("breath"));
                        report.setsThroat(jsonObject.getString("sThroat"));
                        report.setPneumonia(jsonObject.getString("pneumonia"));
                        report.setrTimestamp(response.getString("rtimestamp"));
                        report.setDiseases(jsonObject.getString("diseases"));
                        report.setPneumonia(jsonObject.getString("symptoms"));
                        reportList.add(report);
                        medicalRecord.setReportList(reportList);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                volleyResponseListener.onResponse(medicalRecord,reportList);
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
