package com.android.group.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.group.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Objects;

public class F0MapFrag extends Fragment {
    private static final int MY_PERMISSION_REQUEST_LOCATION = 99;
    private static final long UPDATE_INTERVAL = 10 * 1000; //10 sec
    private static final long FASTEST_INTERVAL = 2 * 1000; //2 sec
    private GoogleMap mMap;
    private Marker oldCurrentPosMarker, oldPlacingMarker;
    protected FusedLocationProviderClient client;
    private final LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            onLocationChanged(locationResult.getLastLocation());
        }
    };

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        public void onMapReady(@NonNull GoogleMap googleMap) {
            requestPermission();
            client = LocationServices.getFusedLocationProviderClient(requireActivity());
            mMap = googleMap;
            mMap.getUiSettings().setZoomControlsEnabled(true);

            mMap.setOnMapClickListener(latLng -> {
                if (oldPlacingMarker != null) oldPlacingMarker.remove();
                oldPlacingMarker = mMap.addMarker(new MarkerOptions().position(latLng)
                        .title("Appointment's new location")
                        .icon(BitmapDescriptorFactory.defaultMarker()));
            });
            startLocationUpdate();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_f0_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    private Bitmap getBitmap(int drawableRes) {
        @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable = getResources().getDrawable(drawableRes);
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(requireActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSION_REQUEST_LOCATION);
    }

    private void stopLocationUpdate() {
        client.removeLocationUpdates(locationCallback);
    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdate() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        client.requestLocationUpdates(mLocationRequest, locationCallback, null);
    }

    private void onLocationChanged(Location lastLocation) {
        try {
            if (oldCurrentPosMarker != null) oldCurrentPosMarker.remove();
            LatLng newPosition = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            MarkerOptions marker = new MarkerOptions().position(newPosition).title("My location")
                    .icon(BitmapDescriptorFactory.fromBitmap(getBitmap(R.drawable.ic_current_pos)));
            oldCurrentPosMarker = mMap.addMarker(marker);
        } catch (IllegalStateException e) {
            stopLocationUpdate();
        }
    }
}