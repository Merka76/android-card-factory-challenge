package com.merka.pushe.akcardapp.web;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author Akram Shokri
 *
 * This class is for parsing the given json object and returns an {@link ArrayList} of {@link JsonData}
 */
public class JsonReader {

    /**
     * parses the given json object and returns an {@link ArrayList} of {@link JsonData}
     * @param jsonObject
     * @return
     * @throws JSONException
     */
	
	public static ArrayList<JsonData> extractJsonData(JSONObject jsonObject)
			throws JSONException {

        ArrayList<JsonData> initList = new ArrayList<JsonData>();
        JsonData entry;
		JSONArray jsonArray = jsonObject.getJSONArray("cards");

        for (int i = 0; i < jsonArray.length(); i++) {
            if (jsonArray.length() > 0) {
                entry = new JsonData();
                JSONObject jsonObj = jsonArray.getJSONObject(i);

                entry.setCode(jsonObj.getInt("code"));
                entry.setDescription(jsonObj.getString("description"));
                entry.setTitle(jsonObj.getString("title"));
                entry.setTag(jsonObj.getString("tag"));

                try{
                    //image field of a json object is an arbitrary field
                    entry.setImgUrl(jsonObj.getString("image"));
                }catch (JSONException e){
                    entry.setImgUrl("");
                }

                try {
                    //sound field of a json object is an arbitrary field
                    entry.setSoundUrl(jsonObj.getString("sound"));
                }catch (JSONException ex){
                    entry.setSoundUrl("");
                }
                initList.add(entry);
            }
        }

		return initList;
	}
	

}
