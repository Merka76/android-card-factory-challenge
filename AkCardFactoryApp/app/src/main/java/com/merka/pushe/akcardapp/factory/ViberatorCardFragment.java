package com.merka.pushe.akcardapp.factory;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.merka.pushe.akcardapp.R;

/**
 * Created by Akram Shokri on 16-02-09, 12:52 PM.
 */

public class ViberatorCardFragment extends AbstractCardFragment {

    public ViberatorCardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.card_vibrator_layout, container, false);
        cardFindViewById(v);
        return v;
    }

    @Override
    public void cardFindViewById(View view) {
        super.cardFindViewById(view);
        containerCardView = (CardView) view.findViewById(R.id.vibratorCardview);
        fillViewComponents();
    }
}
