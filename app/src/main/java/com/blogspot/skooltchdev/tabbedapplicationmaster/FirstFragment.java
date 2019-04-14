package com.blogspot.skooltchdev.tabbedapplicationmaster;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class FirstFragment extends Fragment implements OnMapReadyCallback{

    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.first_fragment, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) mView.findViewById(R.id.map);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap){

        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        googleMap.addMarker(new MarkerOptions().position(new LatLng(50.109430, 14.451412)).title("Plus Prague").snippet("Hostel you can sleep in"));

        LatLng userCoord = new LatLng(50.107004, 14.454212);
        CameraPosition userLocation = CameraPosition.builder().target(userCoord).zoom(16).bearing(0).tilt(0).build();

        mGoogleMap.addMarker(new MarkerOptions().position(userCoord).title("You").icon(BitmapDescriptorFactory.fromResource(R.drawable.red_dot)));

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(userLocation));


        LatLng prague = new LatLng(50.089820, 14.438022);

        mGoogleMap.addMarker(new MarkerOptions().position(prague).title("Marker in prague"));
    }
}