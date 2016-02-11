package com.merka.pushe.akcardapp.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

/**
 * This class sends an http get request to a given url and returns the response as a json object.
 */
public class JSONParser {

	public static final int CONN_TIME_OUT = 10000/*3000*/; //in milli seconds
    public static final int SOCKET_TIME_OUT = 20000/*5000*/;
	static String json = "";


	InputStream inputStream = null;
	BufferedReader reader = null;

	/**
	 *
	 * @param urlString
	 * @return a json object containing the response of the http request, or an exception
	 * @throws IOException
	 * @throws JSONException
	 */

	public JSONObject sendHttpRequest(String urlString)
			throws IOException, JSONException {

		StringBuffer output = new StringBuffer("");
		JSONObject jsonObject = null;
		DefaultHttpClient httpClient = new DefaultHttpClient();
		try {
			HttpParams httpParams = httpClient.getParams();
            // Set the timeout in milliseconds until a connection is established.
            // The default value is zero, that means the timeout is not used.
			HttpConnectionParams.setConnectionTimeout(httpParams, CONN_TIME_OUT);

            // Set the default socket timeout (SO_TIMEOUT)
            // in milliseconds which is the timeout for waiting for data.
            HttpConnectionParams.setSoTimeout(httpParams, SOCKET_TIME_OUT);

			HttpGet httpGet = new HttpGet(urlString);
			//HttpPost httpPost = new HttpPost(urlString);

		    HttpResponse httpResponse = httpClient.execute(httpGet);
		    
			HttpEntity httpEntity = httpResponse.getEntity();
			inputStream = httpEntity.getContent();

			reader = new BufferedReader(
					new InputStreamReader(inputStream, "UTF-8"), 8);
				String line = "";
				while ((line = reader.readLine()) != null)
					output.append(line + "\n");
				json = output.toString();
				if(!json.trim().startsWith("<!DOCTYPE"))//checking for error msg thrwon by .net servers
					jsonObject = new JSONObject(json);

		}catch(Exception e){
			e.printStackTrace();
		}finally {
			FileUtils.close(reader);
			httpClient.getConnectionManager().shutdown();
		}
		return jsonObject;
	}
	

}
