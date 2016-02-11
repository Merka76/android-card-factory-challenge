package com.merka.pushe.akcardapp.factory;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.merka.pushe.akcardapp.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Akram Shokri on 16-02-09, 12:51 PM.
 */
public class PictureCardFragment extends AbstractCardFragment {
    private String imgPath;
    private ImageView cardIV;


    public PictureCardFragment() {
    }



    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.card_picture_layout, container, false);
        cardFindViewById(v);

        return v;
    }

    @Override
    public void cardFindViewById(View view) {
        super.cardFindViewById(view);
        cardIV = (ImageView) view.findViewById(R.id.cardIV);
        containerCardView = (CardView) view.findViewById(R.id.pictureCardview);
        fillViewComponents();
    }

    @Override
    protected void fillViewComponents() {
        super.fillViewComponents();
        Picasso.with(getContext())
                .load(imgPath)
                .config(Bitmap.Config.RGB_565)
                .error(R.drawable.p2)
                .fit()
                .centerInside()
                .into(cardIV);
    }
}
