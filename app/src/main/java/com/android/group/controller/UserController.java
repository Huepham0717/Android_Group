package com.android.group.controller;

import android.content.Context;

import com.android.group.model.User;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class UserController {
    private static final String MOCK_URL = "https://my-json-server.typicode.com/hoang-10n/Android_Group";
    private static final String URL = MOCK_URL + "/doctors";
    private static final long REFRESH_REQUEST = 60 * 1000; // Refresh content interval = 60 secs
    public static final String USER_API_URL = "http://10.0.2.2:8080/user";
    private static Context context;
    User user;

    public UserController(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener1{
        void onError(String message);

        void onResponse(String message);
    }

    public interface VolleyResponseListener{
        void onError(String message);

        void onResponse(User user);
    }

    public void getUser(String UID, VolleyResponseListener volleyResponseListener){
        user = new User();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,USER_API_URL+"/"+UID, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    user.setUUID(response.getString("uuid"));
                    user.setUsername(response.getString("username"));
                    user.setEmail(response.getString("email"));
                    user.setdOB(response.getString("dob"));
                    user.setSex(response.getString("sex"));
                    user.setPhone(response.getString("phone"));
                    user.setAddress(response.getString("address"));
                    user.setPassport(response.getString("passport"));
                    user.setType(response.getString("type"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                volleyResponseListener.onResponse(user);
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

    public static void addUser(User user, UserController.VolleyResponseListener1 volleyResponseListener1) {
        JSONObject postData = new JSONObject();
        try {
            postData.put("uuid", user.getUUID());
            postData.put("username", user.getUsername());
            postData.put("email", user.getEmail());
            postData.put("dob", user.getdOB());
            postData.put("sex", user.getSex());
            postData.put("phone", user.getPhone());
            postData.put("passport", user.getPassport());
            postData.put("address", user.getAddress());
            postData.put("passport", user.getPassport());
            postData.put("type", user.getType());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        CustomJsonObjectRequest jsonObjectRequest = new CustomJsonObjectRequest(Request.Method.POST, USER_API_URL, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                volleyResponseListener1.onResponse("Your account registered successfully");
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

}
