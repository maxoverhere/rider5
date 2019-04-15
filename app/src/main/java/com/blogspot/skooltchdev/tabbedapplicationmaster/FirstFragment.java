package com.blogspot.skooltchdev.tabbedapplicationmaster;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;


public class FirstFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;
    StartScreen startScreen;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void setStartScreen(StartScreen startScreen) {
        this.startScreen = startScreen;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.first_fragment, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) mView.findViewById(R.id.map);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (startScreen.status == 0) {
            MapsInitializer.initialize(getContext());
            mGoogleMap = googleMap;
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

//            googleMap.addMarker(new MarkerOptions().position(new LatLng(50.109430, 14.451412)).title("Plus Prague").snippet("Hostel you can sleep in"));

            LatLng userCoord = new LatLng(50.087000, 14.420289);
            CameraPosition userLocation = CameraPosition.builder().target(userCoord).zoom(16).bearing(0).tilt(0).build();

            mGoogleMap.addMarker(new MarkerOptions().position(userCoord).title("You").icon(BitmapDescriptorFactory.fromResource(R.drawable.red_dot)));

            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(userLocation));


            LatLng prague = new LatLng(50.089820, 14.438022);

//            mGoogleMap.addMarker(new MarkerOptions().position(prague).title("Marker in prague"));
        } else if (startScreen.status == 1) {
            LatLng userCoord = new LatLng(50.087000, 14.420289);
            googleMap.addMarker(new MarkerOptions().position(userCoord).title("You").icon(BitmapDescriptorFactory.fromResource(R.drawable.red_dot)));
            LatLng focus = null;
            if (startScreen.endPos == null) {
                focus = new LatLng(50.109401, 14.451265);
            }else{
                focus = startScreen.endPos;
            }
            CameraPosition userLocation = CameraPosition.builder().target(focus).zoom(12).bearing(0).tilt(0).build();
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(userLocation));
            googleMap.addMarker(new MarkerOptions().position(focus).title("You").icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_dot)));
        } else if (startScreen.status == 2) {
            LatLng focus = new LatLng(50.109401, 14.451265);
            if (startScreen.endPos != null) {
                focus = startScreen.endPos;
            }
            LatLng userCoord = new LatLng(50.087000, 14.420289);
            googleMap.addMarker(new MarkerOptions().position(userCoord).title("You").icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_dot)));

            CameraPosition userLocation = CameraPosition.builder().target(focus).zoom(12).bearing(0).tilt(0).build();
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(userLocation));
            googleMap.addMarker(new MarkerOptions().position(focus).title("endgoal"));

            LatLng aCar = new LatLng(50.093014, 14.439070);
            googleMap.addMarker(new MarkerOptions().position(aCar).title("aCar").icon(BitmapDescriptorFactory.fromResource(R.drawable.purple)));
            addStuff(googleMap, userCoord, aCar);
            addStuff(googleMap, focus, aCar);
        }

        int i1 = 0;

        JSONObject json = null;
        try {
            json = JsonReader.readJsonFromUrl("https://www.rekola.cz/api/mobile/regions/1/trackables?mapLat=50.123456&mapLng=14.467562&mapZoom=17&gpsLat=50.344556&gpsLng=14.333462&gpsAcc=20.5");
            JSONArray rack = json.getJSONArray("racks").getJSONObject(0).getJSONArray("vehicles");

            double lat =  Double.parseDouble(rack.getJSONObject(0).getJSONObject("position").get("lat").toString());
            double lng =  Double.parseDouble(rack.getJSONObject(0).getJSONObject("position").get("lng").toString());
        } catch (Exception e) {
            //mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(50.107430, 14.451452)).title("3 debug bike").snippet("super not work").icon(BitmapDescriptorFactory.fromResource(R.drawable.green_dot)));

        }

//mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(50.109430, 14.451452)).title("debug biike").snippet("24 czk per 30 mins"));
        while(true){

            try {
        /*
        Map bikes = ((Map)json.get("racks"));
        Iterator<Map.Entry>itr2 = bikes.entrySet().iterator();
        Map.Entry pair = itr1.next();
        */

                int i2 = 0;
                JSONArray rack = null;

                rack = json.getJSONArray("racks").getJSONObject(i1).getJSONArray("vehicles");

                while (true) {
                    try {

                        double lat =  Double.parseDouble(rack.getJSONObject(i2).getJSONObject("position").get("lat").toString());
                        double lng =  Double.parseDouble(rack.getJSONObject(i2).getJSONObject("position").get("lng").toString());

                        mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title("Rekola Bike").snippet("24 czk per 30 mins").icon(BitmapDescriptorFactory.fromResource(R.drawable.purple)));

                        //mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(50.109950, 14.451412)).title("debug biike").snippet(" " + lat +" " + lng).icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_dot)));

                        Log.d("lol", "added bike at " + lat + " " + lng);
                    } catch (Exception e) {
                        break;
                    }
                    i2++;
                }
                i1++;
            }
            catch (Exception e){
                //mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(50.108430, 14.451452)).title("2 debug bike").snippet("speciallll work").icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_dot)));
                break;
            }
        }
    }


    void addStuff(GoogleMap googleMap, LatLng userCoord, LatLng focus ) {
        ConnectionTest myJson = new ConnectionTest(userCoord.latitude,userCoord.longitude,focus.latitude,focus.longitude);
        PolylineOptions plo = new PolylineOptions().clickable(true).add();
        String[] myStuff = myJson.myJson.substring(42).split("\\]\\]\\,\\\"")[0].split("\\],\\[");
        for (String cord : myStuff) {
            String temp[] = cord.split(",");
            plo.add(new LatLng(Double.parseDouble(temp[1]), Double.parseDouble(temp[0])));
        }
        googleMap.addPolyline(plo);
    }
}

class JsonReader {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}