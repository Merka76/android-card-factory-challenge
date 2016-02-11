package com.merka.pushe.akcardapp.factory;

/**
 * Created on 16-02-09, 12:57 PM.
 * @author Akram Shokri
 *
 * This calss is an implementation of <b>Factory Desing Pattern</b>,see
 * <a href="http://www.blackwasp.co.uk/gofpatterns.aspx"> here</a> to learn more
 * about Design Patterns.
 */
public class CardFactory {
    /**
     * Instantiates and returns a <b>Card</b> according to the given arguments.
     * @param title
     * @param description
     * @param soundUrl
     * @param imageUrl
     * @param code
     * @param cardTag
     * @return a subclass of {@link AbstractCardFragment} object
     */
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
     * Determines the name of the theme for a card. This name is a field of {@link CardTheme}
     * enumeration and is specified according to the <code>tag</code> argument.
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
