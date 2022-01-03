package com.android.group.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.group.F0HomeActivity;
import com.android.group.R;

public class F0ViewAppointmentFrag extends Fragment {
    private static final int MY_PERMISSION_REQUEST_LOCATION = 99;
    TextView latTxt, longTxt;
    ImageButton mapBtn;
    Double latitude, longitude;

    final LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            latTxt.setText(location.getLatitude() + "\"E");
            longTxt.setText(location.getLongitude() + "\"N");
        }
    };

    public F0ViewAppointmentFrag(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        // Required empty public constructor
    }

    @SuppressLint("MissingPermission")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f0_view_appointment, container, false);
        latTxt = view.findViewById(R.id.frag_f0_view_appointment_f0_lat_txt);
        longTxt = view.findViewById(R.id.frag_f0_view_appointment_f0_long_txt);
        mapBtn = view.findViewById(R.id.frag_f0_view_appointment_f0_map_btn);

        LocationManager manager = (LocationManager) requireContext().getSystemService(Context.LOCATION_SERVICE);

        // TODO request permission before
        if (latitude == 0.0 && longitude == 0.0)
            manager.requestSingleUpdate(new Criteria(), listener, null);
        else {
            latTxt.setText(latitude + "\"E");
            longTxt.setText(longitude + "\"N");
        }

        mapBtn.setOnClickListener(v -> {
            ((F0HomeActivity) requireContext()).switchToMapFrag();
        });

        return view;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(requireActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                MY_PERMISSION_REQUEST_LOCATION);
    }
}