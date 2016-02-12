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
 *  Created on 16-02-09, 12:25 PM.
 *  @author Akram Shokri
 *
 *  This calss is the main and only activity of the app. It starts the app, send a get request
 *  to a web address to retrieve the list of cards in json format. An {@link AsyncTask} is used for
 *  retrieving the initialization list.
 *  Then it shows a random card and repeat this when user hits the <u>try</u> button.
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
        getSupportActionBar().setDisplayShowTitleEnabled(false);//to hide the appname
        getSupportActionBar().setElevation(4);
        getSupportActionBar().setIcon(R.drawable.logo);

        initMsgTv = (TextView) findViewById(R.id.initMsgTV);
        initProgressBar = (ProgressBar) findViewById(R.id.loadingProgress);
        tryButton = (Button) findViewById(R.id.tryBtn);

        //if app is just started, retrieve the card list from the web inside an {@link AsyncTask}
        if(initList == null || (initList != null && initList.size() == 0)) {
            PollTask backgroundTask = new PollTask();
            backgroundTask.execute();
        }

        //when try button is clicked, show a random card which is not shown before.
        tryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showARandomCard();
            }
        });

    }

    /**
     * Picks a card from the card list randomly and constructs fragment to show this card using
     * {@link CardFactory#getCard(String, String, String, String, int, String)}. It replaces current
     * fragment with this constructed fragment and finishes its work.
     */
    private void showARandomCard(){
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

    /**
     * Finds an index which is not used till now. This index is used to get an unseen item from card list.
     * @return an int which is the index of an unseen card in card list.
     */

    private int getNextUnseenRandom(){
        int r = random.nextInt(initList.size());
        if(blackList.size() == initList.size()) {
            Snackbar.make(initMsgTv.getRootView(), R.string.try_round_restarted, Snackbar.LENGTH_LONG)
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

    /**
     * This class is an {@link AsyncTask} class which runs in background. It sends a http request
     * to <code>JSON_URL</code> and gets card initializations data a a json object.
     */
    class PollTask extends AsyncTask<Void, Void, ArrayList<JsonData>> {
        public static final String JSON_URL = "http://static.pushe.co/challenge/json";

        /**
         * shows a loading spinner before starting background task and hides <code>tryButton</code>
         */
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

        /**
         * sends http request to <code>JSON_URL</code> and receives card list as an {@link ArrayList}
         * of {@link JsonData} objects.
         * @param params
         * @return list of the initialization cards
         */

        @Override
        protected ArrayList<JsonData> doInBackground(Void... params) {
            try {
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = jsonParser.sendHttpRequest(JSON_URL);
                //todo: check for timeout and any othe failure in retrieving jsonObject from url

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


        /**
         * Runs when background task is finished. It sets the valus if <code>initList</code> with
         * the return value of <code>doInBackground</code> and then initialize <code>blackList</code>.
         * Finally, it makes <code>tryButton</code> disable and hides the loading spinner. Then calls
         * <code>showARandomCard</code> to start the app.
         * @param result
         */

        @Override
        protected void onPostExecute(ArrayList<JsonData> result) {
            super.onPostExecute(result);
            initProgressBar.setVisibility(View.GONE);

            if(result == null){ // failure in retrieving initial list
                Snackbar.make(initMsgTv.getRootView(), R.string.initialization_failed, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                initMsgTv.setText(R.string.initialization_failed);

                return;
            }

            initList = result;
            if(blackList == null){
                blackList = new ArrayList<Integer>();
            }

            tryButton.setVisibility(View.VISIBLE);
            initMsgTv.setVisibility(View.GONE);
            showARandomCard(); //when retrieving card list from web is done, show a random card
        }


    }

}
