package com.merka.pushe.akcardapp.factory;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
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
 * Created on 16-02-09, 12:51 PM.
 * @author Akram Shokri
 *
 * This class shows a card which contains an audio resource. The audio resource is
 * in fact a web url contained in <code>soundPath</code>. This class shows a simple UI to play/pause the audio, showing its duration
 * and progress of playing.
 * <br>This class is a subclass of {@link AbstractCardFragment} and inherits main features of a
 * 'Card Fragment' from it.
 *
 * <p> @see AbstractCardFragment</p>
 */

public class SoundCardFragment extends AbstractCardFragment {
    private String soundPath;
    private MediaPlayer mediaPlayer;
    private int seekProgress;

    private SeekBar audioSeekBar;
    private TextView totalTimeTV;
    private TextView currentTimeTV;
    private ImageView audioPlayIV;

    private boolean mediaIsReady = false; //This field is use to check if media is buffered to start playing or not

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
        prepareAudio();
        return v;
    }

    /**
     * Inherited from AbstractCardFragment, this method binds class fields to layout
     * file components.
     * @param view
     */
    @Override
    public void cardFindViewById(View view) {
        super.cardFindViewById(view);
        containerCardView = (CardView) view.findViewById(R.id.soundCardview);
        audioSeekBar = (SeekBar) view.findViewById(R.id.audioSeekbar);
        audioPlayIV = (ImageView) view.findViewById(R.id.audioPlayIV);
        currentTimeTV = (TextView) view.findViewById(R.id.currentTimeTV);
        totalTimeTV = (TextView) view.findViewById(R.id.totalTimeTV);

        //listener for play button
        audioPlayIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!mediaIsReady){
                    Snackbar.make(view, R.string.media_not_ready, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }
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
        //parent class's method, set values for different components of the current layout
        fillViewComponents();
    }

    /**
     * This handler is for running seekBar updater
     */
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

            audioSeekBar.setProgress(seekProgress);

            // Running this thread after 100 milliseconds
            mHandler.postDelayed(this, 100);
        }
    };

    /**
     * Preparing audio file so it plays instantly when user hits the play button
     */
    private void prepareAudio()
    {
        killMediaPlayer();
        mediaIsReady = false;
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(soundPath);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer player) {
                    mediaIsReady = true;
                    totalTimeTV.setText(""+ MediaUtility.milliSecondsToTimer(mediaPlayer.getDuration()));
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Releasing the resources associated with mediaPlayer object to preserve battery
     *  and memory and also avoiding playback errors
     */
    private void killMediaPlayer() {
        if(mediaPlayer!=null) {
            try {
                mediaPlayer.release();
                mediaIsReady = false;
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mUpdateTimeTask);
        killMediaPlayer();
    }
}
