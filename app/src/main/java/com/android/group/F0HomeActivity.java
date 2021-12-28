package com.android.group;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.android.group.fragment.F0AllReportFrag;
import com.android.group.fragment.F0AppointmentFrag;
import com.android.group.fragment.F0HomeFrag;
import com.android.group.fragment.F0MsgFrag;

public class F0HomeActivity extends AppCompatActivity {
    NavBar navBar;
    F0HomeFrag f0HomeFrag;
    F0AllReportFrag f0AllReportFrag;
    F0AppointmentFrag f0AppointmentFrag;
    F0MsgFrag f0MsgFrag;
    int currentFrag = 0; // 0: Home; 1: Msg; 2: Map; 3: Appointment; 4: All reports; 5: Add reports; 6: View reports

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f0_home);
        navBar = findViewById(R.id.f0_home_navbar);

        f0HomeFrag = new F0HomeFrag();
        f0AllReportFrag = new F0AllReportFrag();
        switchToHomeFrag(findViewById(R.id.f0_home_navbar_home_btn));
    }

    public void switchToHomeFrag(View view) {
        currentFrag = 0;
        navBar.setEnabled(true);
        view.setEnabled(false);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.f0_home_frag_container, f0HomeFrag);
        ft.commit();
    }

    public void switchToMsgFrag(View view) {
        currentFrag = 1;
        navBar.setEnabled(true);
        view.setEnabled(false);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.f0_home_frag_container, f0MsgFrag);
        ft.commit();
    }

    public void switchToMapFrag(View view) {
        currentFrag = 2;
        navBar.setEnabled(true);
        view.setEnabled(false);
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
//        ft.replace(R.id.f0_home_frag_container, f0HomeFrag);
//        ft.commit();
    }

    public void switchToAppointmentFrag(View view) {
        currentFrag = 3;
        navBar.setEnabled(true);
        view.setEnabled(false);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.f0_home_frag_container, f0HomeFrag);
        ft.commit();
    }

    public void switchToAllReportFrag(View view) {
        currentFrag = 4;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.f0_home_frag_container, f0AllReportFrag);
        ft.commit();
    }

    public void switchToAddReportFrag(View view) {
        currentFrag = 5;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.f0_home_frag_container, f0HomeFrag);
        ft.commit();
    }

    public void switchToViewReportFrag(View view) {
        currentFrag = 6;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.f0_home_frag_container, f0HomeFrag);
        ft.commit();
    }
}