package com.merka.pushe.akcardapp.web;

/**
 * Created on 16-02-09, 1:58 PM.
 * @author  Akram Shokri
 *
 * A simple bean, for storing fields of a json object representing a card.
 */
public class JsonData {
    private String title;
    private String description;
    private String tag;
    private String imgUrl;
    private String soundUrl;

    private int code;

    public JsonData() {
    }

    public JsonData(String title, String description, String tag, String imgUrl, String soundUrl, int code) {
        this.title = title;
        this.description = description;
        this.tag = tag;
        this.imgUrl = imgUrl;
        this.soundUrl = soundUrl;
        this.code = code;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSoundUrl() {
        return soundUrl;
    }

    public void setSoundUrl(String soundUrl) {
        this.soundUrl = soundUrl;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object obj) {
        if(! (obj instanceof JsonData) )
            return false;
        JsonData jd = (JsonData) obj;
        if(     this.code == jd.code &&
                this.tag ==  null && jd.tag == null &&
                this.title == null && jd.title == null &&
                this.soundUrl == null && jd.soundUrl == null &&
                this.imgUrl == null && jd.imgUrl == null &&
                this.description == null && jd.description == null)
            return true;

        if(     ((this.description == null && jd.description == null) || (this.description !=  null && jd.description != null &&  this.description.equals(jd.description)))  &&
                ( (this.imgUrl == null && jd.imgUrl == null ) || (this.imgUrl !=  null && jd.imgUrl!=null &&  this.imgUrl.equals(jd.imgUrl)) )  &&
                ( (this.soundUrl == null && jd.soundUrl == null) ||  (this.soundUrl !=  null && jd.soundUrl != null &&  this.soundUrl.equals(jd.soundUrl) )) &&
                ( (this.tag ==  null && jd.tag == null)||(this.tag !=  null && jd.tag != null &&  this.tag.equals(jd.tag) )) &&
                ( (this.title == null && jd.title == null) || (this.title !=  null && jd.title != null &&  this.title.equals(jd.title) )) &&
                this.code == jd.code )
            return true;

        return false;
    }
}
