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
 * Created on 16-02-09, 12:51 PM.
 * @author Akram Shokri
 *
 * This class shows a card which contains a picture. The picture is loaded from a url with the help
 * of picasso library.
 * <br>This class is a subclass of {@link AbstractCardFragment} and inherits main features of a
 * 'Card Fragment' from it.
 *
 * <p> @see AbstractCardFragment</p>
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

    /**
     * Inherited form AbstractCardFragment,this method binds class fields with
     * layout file components
     * @param view
     */
    @Override
    public void cardFindViewById(View view) {
        super.cardFindViewById(view);
        cardIV = (ImageView) view.findViewById(R.id.cardIV);
        containerCardView = (CardView) view.findViewById(R.id.pictureCardview);
        fillViewComponents();
    }

    /**
     * Inherited from AbstractCardFragment. This method loads a picture from <code>imgPath</code>
     * using Picasso library.
     */
    @Override
    protected void fillViewComponents() {
        super.fillViewComponents();
        Picasso.with(getContext())
                .load(imgPath)
                .config(Bitmap.Config.RGB_565)
                .placeholder(R.drawable.preview)
                .error(R.drawable.load_failed)
                .fit()
                .centerInside()
                .into(cardIV);
    }
}
