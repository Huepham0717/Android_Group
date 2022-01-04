package com.android.group;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.android.group.fragment.F0AddReportFrag;
import com.android.group.fragment.F0AllAppointmentFrag;
import com.android.group.fragment.F0AllReportFrag;
import com.android.group.fragment.F0ViewAppointmentFrag;
import com.android.group.fragment.F0HomeFrag;
import com.android.group.fragment.F0MapFrag;
import com.android.group.fragment.F0AllMsgFrag;
import com.android.group.fragment.F0ViewReportFrag;
import com.android.group.model.Appointment;

public class F0HomeActivity extends AppCompatActivity {
    NavBar navBar;
    F0HomeFrag f0HomeFrag;
    F0AllReportFrag f0AllReportFrag;
    F0MapFrag f0MapFrag;
    F0AllMsgFrag f0AllMsgFrag;
    F0AddReportFrag f0AddReportFrag;
    F0AllAppointmentFrag f0AllAppointmentFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f0_home);
        navBar = findViewById(R.id.f0_home_navbar);

        f0HomeFrag = new F0HomeFrag();
        f0AllReportFrag = new F0AllReportFrag();
        f0AddReportFrag = new F0AddReportFrag();
        f0MapFrag = new F0MapFrag();
        f0AllAppointmentFrag = new F0AllAppointmentFrag();
        f0AllMsgFrag = new F0AllMsgFrag();
        switchToAllMsgFrag(findViewById(R.id.f0_home_navbar_home_btn));
    }

    public void switchToHomeFrag(View view) {
        navBar.setEnabled(true);
        view.setEnabled(false);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.f0_home_frag_container, f0HomeFrag);
        ft.commit();
    }

    public void switchToAllMsgFrag(View view) {
        navBar.setEnabled(true);
        view.setEnabled(false);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.f0_home_frag_container, f0AllMsgFrag);
        ft.commit();
    }

    public void switchToAllAppointmentFrag(View view) {
        navBar.setEnabled(true);
        view.setEnabled(false);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.f0_home_frag_container, f0AllAppointmentFrag);
        ft.commit();
    }

    public void switchToAllReportFrag(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.f0_home_frag_container, f0AllReportFrag);
        ft.commit();
    }

    public void switchToAddReportFrag(View view) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.f0_home_frag_container, f0AddReportFrag);
        ft.commit();
    }

    public void switchToMapFrag() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.f0_home_frag_container, f0MapFrag);
        ft.commit();
    }

    public void switchToViewReportFrag(int position, long id) {
        F0ViewReportFrag f0ViewReportFrag = new F0ViewReportFrag(position, id);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.f0_home_frag_container, f0ViewReportFrag);
        ft.commit();
    }

    // TODO call constructor for each appointment
    public void switchToViewAppointmentFrag(Double latitude, Double longitude) {
        F0ViewAppointmentFrag f0ViewAppointmentFrag = new F0ViewAppointmentFrag(latitude, longitude);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.f0_home_frag_container, f0ViewAppointmentFrag);
        ft.commit();
    }
}
