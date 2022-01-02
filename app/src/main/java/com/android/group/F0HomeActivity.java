package com.android.group;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.android.group.fragment.F0AddReportFrag;
import com.android.group.fragment.F0AllReportFrag;
import com.android.group.fragment.F0AppointmentFrag;
import com.android.group.fragment.F0HomeFrag;
import com.android.group.fragment.F0MapFragment;
import com.android.group.fragment.F0MsgFrag;
import com.android.group.fragment.F0ViewReportFrag;

public class F0HomeActivity extends AppCompatActivity {
    NavBar navBar;
    F0HomeFrag f0HomeFrag;
    F0AllReportFrag f0AllReportFrag;
    F0AppointmentFrag f0AppointmentFrag;
    F0MapFragment f0Map;
    F0MsgFrag f0MsgFrag;
    F0AddReportFrag f0AddReportFrag;
    F0ViewReportFrag f0ViewReportFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f0_home);
        navBar = findViewById(R.id.f0_home_navbar);

        f0HomeFrag = new F0HomeFrag();
        f0AllReportFrag = new F0AllReportFrag();
        f0AddReportFrag = new F0AddReportFrag();
        f0Map = new F0MapFragment();
        switchToHomeFrag(findViewById(R.id.f0_home_navbar_home_btn));
    }

    public void switchToHomeFrag(View view) {
        navBar.setEnabled(true);
        view.setEnabled(false);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.f0_home_frag_container, f0HomeFrag);
        ft.commit();
    }

    public void switchToMsgFrag(View view) {
        navBar.setEnabled(true);
        view.setEnabled(false);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.f0_home_frag_container, f0MsgFrag);
        ft.commit();
    }

    public void switchToMapFrag(View view) {
        navBar.setEnabled(true);
        view.setEnabled(false);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.f0_home_frag_container, f0Map);
        ft.commit();
    }

    public void switchToAppointmentFrag(View view) {
        navBar.setEnabled(true);
        view.setEnabled(false);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.f0_home_frag_container, f0HomeFrag);
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

    public void switchToViewReportFrag(int position, Long reportId) {
        f0ViewReportFrag = new F0ViewReportFrag(position,reportId);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.f0_home_frag_container, f0ViewReportFrag);
        ft.commit();
    }
}