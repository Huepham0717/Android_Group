package com.android.group;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.group.controller.DoctorController;
import com.android.group.controller.F0Controller;
import com.android.group.controller.ReportController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        F0Controller.init(this, findViewById(R.id.container));
        F0Controller.getAllF0s();
    }
}