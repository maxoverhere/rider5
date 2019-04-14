package com.blogspot.skooltchdev.tabbedapplicationmaster;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


public class StartScreen extends AppCompatActivity {

    FirstFragment mapStuff;
    private int editText;
    ViewPager buttonsView;
//    ViewButtonsAdapter theAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);


        ViewPager mapView = (ViewPager) findViewById(R.id.viewpager);
        setupViewMap(mapView);

//        buttonsView = (ViewPager) findViewById(R.id.viewButtons);
//        setupViewButtons(buttonsView);
    }

    public void switchToo2(View view){
        EditText firstEditText =(EditText) findViewById(R.id.firstEditText);
        Button firstButton = (Button) findViewById(R.id.firstButton);
        firstButton.setVisibility(View.INVISIBLE);
        firstEditText.setVisibility(View.INVISIBLE);
        Button secondButton = (Button) findViewById(R.id.secondButton);
        EditText secondBottom =(EditText) findViewById(R.id.secondBottom);
        EditText secondTop =(EditText) findViewById(R.id.secondTop);
        secondTop.setVisibility(View.VISIBLE);
        secondBottom.setVisibility(View.VISIBLE);
        secondButton.setVisibility(View.VISIBLE);
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

//    public  void switchTooSecond(View view){
////        EditText thing = (EditText) findViewById(R.id.editText);
////        (thing.getText().toString());
//        theAdapter.addFragment(new GoToFromScreen(), "Second");
//        buttonsView.setCurrentItem(1);
//
//    }
//
//    private void setupViewButtons(ViewPager viewButtons) {
//        ViewButtonsAdapter theAdapter = new ViewButtonsAdapter(getSupportFragmentManager());
//        theAdapter.addFragment(new BlankFragment(), "First");
//        viewButtons.setAdapter(theAdapter);
//    }
//
//    class ViewButtonsAdapter extends FragmentPagerAdapter {
//        private final List<Fragment> mFragmentList = new ArrayList<>();
//        private final List<String> mFragmentTitleList = new ArrayList<>();
//        public ViewButtonsAdapter(FragmentManager manager) {
//            super(manager);
//        }
//        @Override
//        public Fragment getItem(int position) {
//            return mFragmentList.get(position);
//        }
//        @Override
//        public int getCount() {
//            return mFragmentList.size();
//        }
//        public void addFragment(Fragment fragment, String title) {
//            mFragmentList.add(fragment);
//            mFragmentTitleList.add(title);
//        }
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mFragmentTitleList.get(position);
//        }
//    }

    private void setupViewMap(ViewPager viewMap) {
        ViewMapAdapter adapter = new ViewMapAdapter(getSupportFragmentManager());
        mapStuff = new FirstFragment();
        adapter.addFragment(mapStuff, "First");
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
