package com.blogspot.skooltchdev.tabbedapplicationmaster;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


public class StartScreen extends AppCompatActivity {

    FirstFragment mapStuff;
    private int editText;
    ViewPager buttonsView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);


        ViewPager mapView = (ViewPager) findViewById(R.id.viewpager);
        setupViewMap(mapView);

        ViewPager buttonsView = (ViewPager) findViewById(R.id.viewButtons);
        setupViewButtons(buttonsView);
    }

    public  void switchTooSecond(String str){

    }

    private void setupViewButtons(ViewPager viewButtons) {
        ViewButtonsAdapter adapter = new ViewButtonsAdapter(getSupportFragmentManager());
        adapter.addFragment(new BlankFragment(), "First");
        adapter.addFragment(new GoToFromScreen(), "Second");
        viewButtons.setAdapter(adapter);
    }

    class ViewButtonsAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        public ViewButtonsAdapter(FragmentManager manager) {
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
