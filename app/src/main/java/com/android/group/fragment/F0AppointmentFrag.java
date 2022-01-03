package com.android.group.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.group.R;

public class F0AppointmentFrag extends Fragment {
    private static final int MY_PERMISSION_REQUEST_LOCATION = 99;
    TextView latTxt, longTxt;
    final LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            latTxt.setText(location.getLatitude() + "\"E");
            longTxt.setText(location.getLongitude() + "\"N");
        }
    };

    public F0AppointmentFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f0_appointment, container, false);
        latTxt = view.findViewById(R.id.frag_f0_appointment_f0_lat_txt);
        longTxt = view.findViewById(R.id.frag_f0_appointment_f0_long_txt);
        LocationManager manager = (LocationManager) requireContext().getSystemService(Context.LOCATION_SERVICE);


        // TODO request permission before
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
        }
        manager.requestSingleUpdate(new Criteria(), listener, null);

        return view;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(requireActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                MY_PERMISSION_REQUEST_LOCATION);
    }
}