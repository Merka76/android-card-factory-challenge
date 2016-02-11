package com.merka.pushe.akcardapp.factory;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.merka.pushe.akcardapp.R;
import com.merka.pushe.akcardapp.media.MediaUtility;

import java.io.IOException;

/**
 * Created by Akram Shokri on 16-02-09, 12:51 PM.
 */

public class SoundCardFragment extends AbstractCardFragment {
    private String soundPath;
    private MediaPlayer mediaPlayer;
    private int seekProgress;

    private SeekBar audioSeekBar;
    private TextView totalTimeTV;
    private TextView currentTimeTV;
    private ImageView audioPlayIV;

    public SoundCardFragment() {
    }

    public String getSoundPath() {
        return soundPath;
    }

    public void setSoundPath(String soundPath) {
        this.soundPath = soundPath;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.card_sound_layout, container, false);
        cardFindViewById(v);
        prepareAudio(); //todo: put this in a thread
        return v;
    }

    @Override
    public void cardFindViewById(View view) {
        super.cardFindViewById(view);
        containerCardView = (CardView) view.findViewById(R.id.soundCardview);
        audioSeekBar = (SeekBar) view.findViewById(R.id.audioSeekbar);
        audioPlayIV = (ImageView) view.findViewById(R.id.audioPlayIV);
        currentTimeTV = (TextView) view.findViewById(R.id.currentTimeTV);
        totalTimeTV = (TextView) view.findViewById(R.id.totalTimeTV);


        audioPlayIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mediaPlayer!=null && mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    // Changing button image to play button
                    audioPlayIV.setImageResource(R.drawable.btn_play);
                    mHandler.removeCallbacks(mUpdateTimeTask);

                } else {
                    // Resume song
                    if (mediaPlayer != null) {
                        mediaPlayer.start();
                        // Changing button image to pause button
                        audioPlayIV.setImageResource(R.drawable.btn_pause);
                        mHandler.postDelayed(mUpdateTimeTask, 100);
                    }
                }
            }
        });
        fillViewComponents();
    }

    private Handler mHandler = new Handler();

    /**
     * This runnable is for updating seekbar while the audio file is playing
     */
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {

//            // Displaying Total Duration time
            totalTimeTV.setText(""+ MediaUtility.milliSecondsToTimer(mediaPlayer.getDuration()));
//            // Displaying time completed playing
            currentTimeTV.setText(""+MediaUtility.milliSecondsToTimer(mediaPlayer.getCurrentPosition()));

            // Updating progress bar
            seekProgress = (int)(MediaUtility.getProgressPercentage( mediaPlayer.getCurrentPosition(), mediaPlayer.getDuration()));
            //Log.d("Progress", ""+progress);
            audioSeekBar.setProgress(seekProgress);
            //System.out.println("progress:"+progress);
            // Running this thread after 100 milliseconds
            mHandler.postDelayed(this, 100);
        }
    };

    private void prepareAudio()
    {
        killMediaPlayer();

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(soundPath);
            mediaPlayer.prepare();
            //mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void killMediaPlayer() {
        if(mediaPlayer!=null) {
            try {
                mediaPlayer.release();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

}
