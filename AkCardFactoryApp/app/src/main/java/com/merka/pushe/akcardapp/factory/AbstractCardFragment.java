package com.merka.pushe.akcardapp.factory;

import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.merka.pushe.akcardapp.R;

/**
 * Created on 16-02-09, 12:31 PM.
 * @author Akram Shokri
 *
 * This is the base class for different <b>Cards</b> containing common properties and behaviours of
 * all cards. Inheritance is used here for two reasons:<br> 1- code simplicity and avoiding code replication
 * <br>2-to implement <b>Factory Pattern</b> when generating a fragment for a random card.
 * <p>Picture Card, Sound Card and Vibrator Card are implemented as subclass of AbstractCardFragment and
 * different themes are given to them usig #cardTheme field.</p>
 * @see CardFactory
 * @see PictureCardFragment
 * @see SoundCardFragment
 * @see ViberatorCardFragment
 * @see CardTheme
 * @see Fragment
 */

public abstract class AbstractCardFragment extends Fragment {
    protected String title;
    protected String description;
    protected String cardTag;
    protected int code;
    protected CardTheme cardTheme;

    //view components
    protected TextView tagTV;
    protected TextView titleTV;
    protected TextView descTV;
    protected ImageView themeIconIV;
    protected CardView containerCardView;

    public AbstractCardFragment() {
    }

    /**
     * Finds component in the given view. These components are common components which all
     * subclasses of AbstractCardFragment share.
     * @param view
     */
    protected void cardFindViewById(View view){
        tagTV = (TextView) view.findViewById(R.id.tagTV);
        titleTV = (TextView) view.findViewById(R.id.titleTV);
        descTV = (TextView) view.findViewById(R.id.descTV);
        themeIconIV = (ImageView) view.findViewById(R.id.themeIconIV);
    }

    /**
     * Set values of view components.
     * This method also applies theme color and icons to underlying view. The underlying view
     * is determined according to the subclass object which call it.
     */
    protected void fillViewComponents(){
        tagTV.setText("#"+cardTag);
        titleTV.setText(title);
        descTV.setText(description);
        switch (cardTheme){
            case ART:
                themeIconIV.setImageResource(R.drawable.artist);
                containerCardView.setBackgroundColor(getResources().getColor(R.color.art_color));
                break;

            case SPORT:
                themeIconIV.setImageResource(R.drawable.football);
                containerCardView.setBackgroundColor(getResources().getColor(R.color.sport_color));
                break;

            case FUN:
                themeIconIV.setImageResource(R.drawable.clown);
                containerCardView.setBackgroundColor(getResources().getColor(R.color.fun_color));
                break;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getCardTag() {
        return cardTag;
    }

    public void setCardTag(String cardTag) {
        this.cardTag = cardTag;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CardTheme getCardTheme() {
        return cardTheme;
    }

    public void setCardTheme(CardTheme cardTheme) {
        this.cardTheme = cardTheme;
    }
}
