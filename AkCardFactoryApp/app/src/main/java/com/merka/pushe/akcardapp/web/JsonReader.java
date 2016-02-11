package com.merka.pushe.akcardapp.web;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonReader {

	
	
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
                    entry.setImgUrl(jsonObj.getString("image"));
                }catch (JSONException e){
                    entry.setImgUrl("");
                }

                try {
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
