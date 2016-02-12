package com.merka.pushe.akcardapp.factory;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.merka.pushe.akcardapp.R;

/**
 * Created on 16-02-09, 12:52 PM.
 * @author Akram Shokri
 * This class vibrates when it shows a card.
 * <br>This class is a subclass of {@link AbstractCardFragment} and inherits main features of a
 * 'Card Fragment' from it.
 *
 * <p> @see AbstractCardFragment</p>
 */

public class ViberatorCardFragment extends AbstractCardFragment {

    public ViberatorCardFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.card_vibrator_layout, container, false);
        cardFindViewById(v);

        Vibrator vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(500);

        return v;
    }

    @Override
    public void cardFindViewById(View view) {
        super.cardFindViewById(view);
        containerCardView = (CardView) view.findViewById(R.id.vibratorCardview);
        fillViewComponents();
    }
}
