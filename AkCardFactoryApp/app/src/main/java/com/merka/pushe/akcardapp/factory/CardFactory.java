package com.merka.pushe.akcardapp.factory;

/**
 * Created by Akram Shokri on 16-02-09, 12:57 PM.
 */
public class CardFactory {

    public static AbstractCardFragment getCard(String title, String description, String soundUrl, String imageUrl, int code, String cardTag){
        switch (code){
            case 0: //image
                PictureCardFragment pc = new PictureCardFragment();
                pc.setCode(code);
                pc.setTitle(title);
                pc.setDescription(description);
                pc.setCardTag(cardTag);
                pc.setCardTheme(resolveTheme(cardTag));
                pc.setImgPath(imageUrl);
                return pc;


            case 1: //vibrate
                ViberatorCardFragment vc = new ViberatorCardFragment();
                vc.setCode(code);
                vc.setTitle(title);
                vc.setDescription(description);
                vc.setCardTag(cardTag);
                vc.setCardTheme(resolveTheme(cardTag));
                return vc;

            case 2: //sound
                SoundCardFragment sc = new SoundCardFragment();
                sc.setCode(code);
                sc.setTitle(title);
                sc.setDescription(description);
                sc.setCardTag(cardTag);
                sc.setCardTheme(resolveTheme(cardTag));
                sc.setSoundPath(soundUrl);
                return sc;

        }
        return null;
    }

    /**
     * @param tag
     * @return CardTheme
     */
    private static CardTheme resolveTheme(String tag){
        switch (tag.toLowerCase()){
            case "sport":
                return CardTheme.SPORT;

            case "art":
                return CardTheme.ART;

            case "fun":
                return CardTheme.FUN;
        }
        return null;
    }
}
