package com.android.group;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
/*
img_user = image of Doctor
tv_name = name of Doctor
tv_birthday = birthday of Doctor
tv_sex = sex of Doctor
tv_phone = phone number of Doctor
tv_address = address of Doctor
tv_nationalID = ID/Passport of Doctor
*/
public class DoctorProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);
    }
}