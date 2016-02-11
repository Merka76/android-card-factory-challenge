package com.merka.pushe.akcardapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Akram Shokri
 * A placeholder fragment containing a simple view.
 * This class shows a view at the start of the applicatin, indicating that the app is initializing.
 */
public class InitialActivityFragment extends Fragment {

    public InitialActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.initial_fragment, container, false);
    }
}
