package com.merka.pushe.akcardapp.web;

import android.support.annotation.NonNull;

import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created on 16-02-11, 7:00 PM.
 *
 * @author Akram Shokri
 */

public class JsonReaderTest {

    @Test
    public void testExtractJsonData() throws Exception{
        JSONObject jsonObject = new JSONObject("{ \"cards\": [ { \"code\": 1, \"title\": \"Exercise\", \"description\": \"Exercise on a regular basis.\", \"tag\": \"sport\" }, { \"code\": 0, \"title\": \"Painting\", \"description\": \"Look at this beautiful painting\", \"image\": \"http://static.pushe.co/challenge/sky.jpg\", \"tag\": \"art\" }, { \"code\": 2, \"title\": \"Let's have fun\", \"description\": \"Listen to the music\", \"sound\": \"http://static.pushe.co/challenge/sound.mp3\", \"tag\": \"fun\" }, { \"code\": 1, \"title\": \"Hey!\", \"description\": \"Have you called your parents lately!\", \"tag\": \"fun\" }, { \"code\": 0, \"title\": \"Sports\", \"description\": \"Have you ever played one of theses sports?\", \"image\": \"http://static.pushe.co/challenge/sport.jpg\", \"tag\": \"sport\" } ] }");
        ArrayList<JsonData> actualResult = JsonReader.extractJsonData(jsonObject);
        ArrayList<JsonData> expectedResult = createExpectedResult();

        assertEquals(expectedResult, actualResult);
    }

    @NonNull
    private ArrayList<JsonData> createExpectedResult() {
        ArrayList<JsonData> expectedResult = new ArrayList<>(5);
        JsonData jd = new JsonData("Exercise", "Exercise on a regular basis.", "sport", "", "", 1);
        expectedResult.add(jd);

        jd = new JsonData("Painting", "Look at this beautiful painting", "art", "http://static.pushe.co/challenge/sky.jpg", "", 0);
        expectedResult.add(jd);

        jd = new JsonData("Let's have fun", "Listen to the music", "fun", "", "http://static.pushe.co/challenge/sound.mp3", 2);
        expectedResult.add(jd);

        jd = new JsonData("Hey!", "Have you called your parents lately!", "fun", "", "", 1);
        expectedResult.add(jd);

        jd = new JsonData("Sports", "Have you ever played one of theses sports?", "sport", "http://static.pushe.co/challenge/sport.jpg", "", 0);
        expectedResult.add(jd);
        return expectedResult;
    }

}
