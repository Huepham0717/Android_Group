package com.android.group;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/*
tv_name = name of F0
tv_birthday = birthday of F0
tv_sex = sex of F0
tv_phone = phone number of F0
tv_address = address of F0
tv_emergency = emergency of F0
tv_nationalID = ID/Passport of F0

*/
public class F0ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f0_profile);
    }
}