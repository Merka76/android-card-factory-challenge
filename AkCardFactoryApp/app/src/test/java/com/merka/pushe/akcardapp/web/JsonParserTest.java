package com.merka.pushe.akcardapp.web;

import com.merka.pushe.akcardapp.factory.AbstractCardFragment;
import com.merka.pushe.akcardapp.factory.CardFactory;
import com.merka.pushe.akcardapp.factory.PictureCardFragment;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created on 16-02-11, 7:00 PM.
 *
 * @author Akram Shokri
 */

public class JsonParserTest {
    /**
     * Tests {@link JSONParser#sendHttpRequest(String)} but needs android.jar so should be reimplemented
     * as an instrumental test.
     * @throws Exception
     */

   /*
    @Test
    public void testSendHttpRequest() throws Exception{
        String jsonUrl = "http://static.pushe.co/challenge/json";
        JSONParser jp = new JSONParser();

        JSONObject jsonObject = jp.sendHttpRequest(jsonUrl);
        String expectedResult = "{\"cards\":[{\"code\":1,\"title\":\"Exercise\",\"description\":\"Exercise on a regular basis.\",\"tag\":\"sport\"},{\"code\":0,\"title\":\"Painting\",\"description\":\"Look at this beautiful painting\",\"image\":\"http:\\/\\/static.pushe.co\\/challenge\\/sky.jpg\",\"tag\":\"art\"},{\"code\":2,\"title\":\"Let's have fun\",\"description\":\"Listen to the music\",\"sound\":\"http:\\/\\/static.pushe.co\\/challenge\\/sound.mp3\",\"tag\":\"fun\"},{\"code\":1,\"title\":\"Hey!\",\"description\":\"Have you called your parents lately!\",\"tag\":\"fun\"},{\"code\":0,\"title\":\"Sports\",\"description\":\"Have you ever played one of theses sports?\",\"image\":\"http:\\/\\/static.pushe.co\\/challenge\\/sport.jpg\",\"tag\":\"sport\"}]}\"";
        String actualResult = jsonObject.toString();

        assertEquals(expectedResult, actualResult);
    }*/ //todo: reimplement this test as an instrument test

}
