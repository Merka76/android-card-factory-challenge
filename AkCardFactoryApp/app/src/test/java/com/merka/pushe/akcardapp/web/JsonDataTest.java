package com.merka.pushe.akcardapp.web;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created on 16-02-11, 10:35 PM.
 *
 * @author Akram Shokri
 */
public class JsonDataTest {

    @Test
    public void testEqualMethodCase1(){
        JsonData jd1 = new JsonData("test", null, "my tag", "", "http://pushe.co", 0);
        JsonData jd2 = new JsonData("test", null, "my tag", "", "http://pushe.co", 0);
        assertTrue(jd1.equals(jd2));

    }

    @Test
    public void testEqualMethodCase2(){
        JsonData jd1 = new JsonData(null, null, "my tag", "", "http://pushe.co", 0);
        JsonData jd2 = new JsonData("test", null, "my tag", "", "http://pushe.co", 0);
        assertFalse(jd1.equals(jd2));

    }

    @Test
    public void testEqualMethodCase3(){
        JsonData jd1 = new JsonData(null, null, null, null, null, 2);
        JsonData jd2 = new JsonData("test", "description", "my tag", "", "http://pushe.co", 2);
        assertFalse(jd1.equals(jd2));

    }

    @Test
    public void testEqualMethodCase4(){
        JsonData jd1 = new JsonData(null, null, null, null, null, 2);
        JsonData jd2 = new JsonData(null, null, null, null, null, 2);
        assertTrue(jd1.equals(jd2));

    }

    @Test
    public void testEqualMethodCase5(){
        JsonData jd1 = new JsonData("test case 5", null, "tag 5", "abcd", "efj", 2);
        JsonData jd2 = new JsonData("test case 5", "a description ...", "tag 5", "abcd", "efj", 2);
        assertFalse(jd1.equals(jd2));
    }

    @Test
    public void testEqualMethodCase6(){
        JsonData jd1 = new JsonData("test case 6", "a description ...", "taG 5", "abcd", "efj", 2);
        JsonData jd2 = new JsonData("test case 6", "a description ...", "tag 5", "abcd", "efj", 2);
        assertFalse(jd1.equals(jd2));
    }

    @Test
    public void testEqualMethodCase7(){
        JsonData jd1 = new JsonData("Test", "a description ...", "tag 5", "abcd", "efj", 2);
        JsonData jd2 = new JsonData("Test", "a description ...", "tag 5", null, "efj", 2);
        assertFalse(jd1.equals(jd2));
    }

    @Test
    public void testEqualMethodCase8(){
        JsonData jd1 = new JsonData("testing ", "a description ...", "tag 5", "abcd", null, 2);
        JsonData jd2 = new JsonData("testing ", "a description ...", "tag 5", "abcd", "efj", 2);
        assertFalse(jd1.equals(jd2));
    }
}
