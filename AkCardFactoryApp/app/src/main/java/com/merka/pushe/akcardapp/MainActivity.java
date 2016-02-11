package com.merka.pushe.akcardapp;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.merka.pushe.akcardapp.factory.AbstractCardFragment;
import com.merka.pushe.akcardapp.factory.CardFactory;
import com.merka.pushe.akcardapp.web.JSONParser;
import com.merka.pushe.akcardapp.web.JsonData;
import com.merka.pushe.akcardapp.web.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *  Created by Akram Shokri on 16-02-09, 12:25 PM.
 */
public class MainActivity extends AppCompatActivity {
    private ArrayList<JsonData> initList;
    static Random random = new Random(System.currentTimeMillis());
    ArrayList<Integer> blackList;
    private ProgressBar initProgressBar;
    private Button tryButton;
    private TextView initMsgTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initMsgTv = (TextView) findViewById(R.id.initMsgTV);
        initProgressBar = (ProgressBar) findViewById(R.id.loadingProgress);
        tryButton = (Button) findViewById(R.id.tryBtn);
        if(initList == null || (initList != null && initList.size() == 0)) {
            PollTask backgroundTask = new PollTask();
            backgroundTask.execute();
        }

        tryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showACard();
            }
        });

       /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }

    private void showACard(){
        int index = getNextUnseenRandom();
        JsonData data = initList.get(index);
        AbstractCardFragment aCardFragmnet = CardFactory.getCard(data.getTitle(), data.getDescription(),
                data.getSoundUrl(), data.getImgUrl(), data.getCode(), data.getTag());

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment, aCardFragmnet);
        //transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    private int getNextUnseenRandom(){
        int r = random.nextInt(initList.size());
        if(blackList.size() == initList.size()) {
            Snackbar.make(initMsgTv.getRootView(), "You have tried all items. Now another round of try starts.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            blackList.clear();
        }

        while(blackList.contains(r)){
            r = random.nextInt(initList.size());
        }
        blackList.add(r);
        return r;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class PollTask extends AsyncTask<Void, Void, ArrayList<JsonData>> {
        public static final String JSON_URL = "http://static.pushe.co/challenge/json";

        @Override
        protected void onPreExecute() {
            try {
                super.onPreExecute();
                initProgressBar.setVisibility(View.VISIBLE);
                tryButton.setVisibility(View.GONE);
                //initMsgTv.setVisibility(View.VISIBLE);
            } catch (Exception e) {

            }

        }


        @Override
        protected ArrayList<JsonData> doInBackground(Void... params) {
            try {
                JSONParser jsonParser = new JSONParser();

                JSONObject jsonObject = jsonParser.sendHttpRequest(JSON_URL);
                if (jsonObject != null) {
                    ArrayList<JsonData> initList = JsonReader.extractJsonData(jsonObject);
                    return initList;

                }

                return null;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }catch (Throwable e) {
                e.printStackTrace();
            }

            return null;
        }



        @Override
        protected void onPostExecute(ArrayList<JsonData> result) {
            super.onPostExecute(result);

            initList = result;
            if(blackList == null){
                blackList = new ArrayList<Integer>();
            }
            initProgressBar.setVisibility(View.GONE);
            tryButton.setVisibility(View.VISIBLE);
            initMsgTv.setVisibility(View.GONE);
            showACard();

        }


    }

}
