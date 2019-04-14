package com.blogspot.skooltchdev.tabbedapplicationmaster;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


    /**
     * A simple {@link Fragment} subclass.
     */
    public class GoToFromScreen extends Fragment {


        public GoToFromScreen() {
            // Required empty public constructor
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_go_to_from_screen, container, false);
        }

    }
