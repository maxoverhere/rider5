package com.blogspot.skooltchdev.tabbedapplicationmaster;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;


public class StartScreen extends AppCompatActivity {

    FirstFragment mapStuff;
    int status;
    LatLng endPos;


    private static String stringMod(String in){
        StringBuffer s = new StringBuffer();

        for(int i = 0; i < in.length(); i++){
            if(in.charAt(i) != ' ')
                s.append(in.charAt(i));
            else
                s.append('+');
        }
        return s.toString();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        ViewPager mapView = (ViewPager) findViewById(R.id.viewpager);
        setupViewMap(mapView);
        status = 0;

    }

    public void switchToo2(View view){
        EditText firstEditText =(EditText) findViewById(R.id.firstEditText);

        if(firstEditText.getText().toString().equals("")){
            Toast.makeText(StartScreen.this, "Please type a destination", Toast.LENGTH_LONG).show();
        }
        else {
            final String destination = firstEditText.getText().toString();
            Button firstButton = (Button) findViewById(R.id.firstButton);
            firstButton.setVisibility(View.INVISIBLE);
            firstEditText.setVisibility(View.INVISIBLE);
            Button secondButton = (Button) findViewById(R.id.secondButton);
            EditText secondBottom = (EditText) findViewById(R.id.secondBottom);
            EditText secondTop = (EditText) findViewById(R.id.secondTop);
            secondTop.setVisibility(View.VISIBLE);
            secondBottom.setVisibility(View.VISIBLE);
            secondButton.setVisibility(View.VISIBLE);
            status = 1;
//            mapStuff.onMapReady(mapStuff.mGoogleMap);
            ViewPager mapView = (ViewPager) findViewById(R.id.viewpager);
            setupViewMap(mapView);
            String url = ("https://maps.googleapis.com/maps/api/geocode/json?address="
                    +firstEditText.getText().toString()+"&key=AIzaSyAh9zhm7S95KqVR2XG7zjsf4tOj1dkm8EI");
            try {
                String[] myJson = (new ConnectionTest().readStringFromURL(url).split("location")[1]).split(" : ");
                endPos = new LatLng(Double.parseDouble(myJson[2].split("\\,")[0]),Double.parseDouble(myJson[3].split(" ")[0]));
            }catch (Exception e){
                e.printStackTrace();
            }
            secondBottom.setText(firstEditText.getText().toString());
            secondTop.setText("your location");
        }

    }


    public void switchToo3(View view){
        Button secondButton = (Button) findViewById(R.id.secondButton);
        EditText secondBottom =(EditText) findViewById(R.id.secondBottom);
        EditText secondTop =(EditText) findViewById(R.id.secondTop);
        secondTop.setVisibility(View.INVISIBLE);
        secondBottom.setVisibility(View.INVISIBLE);
        secondButton.setVisibility(View.INVISIBLE);
        //send to server
        switchToo4(view);
    }

    public  void switchToo4(View view){

        Button cheapButton = (Button) findViewById(R.id.cheapButton);
        Button expenciveButton = (Button) findViewById(R.id.expenciveButton);

        cheapButton.setVisibility(View.VISIBLE);
        expenciveButton.setVisibility(View.VISIBLE);
    }

    public void switchToo5(View view){
        Button cheapButton = (Button) findViewById(R.id.cheapButton);
        Button expenciveButton = (Button) findViewById(R.id.expenciveButton);

        cheapButton.setVisibility(View.INVISIBLE);
        expenciveButton.setVisibility(View.INVISIBLE);

        Button payPalButton = (Button) findViewById(R.id.payPalButton);

        payPalButton.setVisibility(View.VISIBLE);
    }

    public void switchToo6(View view){
        Button payPalButton = (Button) findViewById(R.id.payPalButton);

        payPalButton.setVisibility(View.INVISIBLE);

        switchToo7(view);
    }

    public void switchToo7(View view){
        status = 2;
        ViewPager mapView = (ViewPager) findViewById(R.id.viewpager);
        setupViewMap(mapView);
    }

    private void setupViewMap(ViewPager viewMap) {
        ViewMapAdapter adapter = new ViewMapAdapter(getSupportFragmentManager());
        mapStuff = new FirstFragment();
        adapter.addFragment(mapStuff, "First");
        mapStuff.setStartScreen(this);
        viewMap.setAdapter(adapter);
    }

    class ViewMapAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        public ViewMapAdapter(FragmentManager manager) {
            super(manager);
        }
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }
        @Override
        public int getCount() {
            return mFragmentList.size();
        }
        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
