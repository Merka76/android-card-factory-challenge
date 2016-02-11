package com.merka.pushe.akcardapp.media;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.widget.SeekBar;

/**
 * Created by Akram Shokri on 16/01/12, 10:08 PM.
 */
public class MediaPlayerSeekbar implements SeekBar.OnSeekBarChangeListener {
    private Context mContext;


    private MediaPlayer mp;
    // Handler to update UI timer, progress bar etc,.
    private Handler mHandler = new Handler();;
    private MediaUtility utils;
    SeekBar songProgressBar;

    public MediaPlayerSeekbar(MediaPlayer mp, SeekBar seekBar, Context mContext) {
        this.mContext = mContext;
        songProgressBar = seekBar;
        // Mediaplayer
        this.mp = mp;
        utils = new MediaUtility();

        // Listeners

        songProgressBar.setOnSeekBarChangeListener(this); // Important
        updateProgressBar();

    }



    public void updateProgressBar() {
        mHandler.postDelayed(mUpdateTimeTask, 100);
    }

    /**
     * Background Runnable thread
     * */
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            long totalDuration = mp.getDuration();
            long currentDuration = mp.getCurrentPosition();

//            // Displaying Total Duration time
//            songTotalDurationLabel.setText(""+utils.milliSecondsToTimer(totalDuration));
//            // Displaying time completed playing
//            songCurrentDurationLabel.setText(""+utils.milliSecondsToTimer(currentDuration));

            // Updating progress bar
            int progress = (int)(utils.getProgressPercentage(currentDuration, totalDuration));
            //Log.d("Progress", ""+progress);
            songProgressBar.setProgress(progress);

            // Running this thread after 100 milliseconds
            mHandler.postDelayed(this, 100);
        }
    };

    /**
     *
     * */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {

    }

    /**
     * When user starts moving the progress handler
     * */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // remove message Handler from updating progress bar
        mHandler.removeCallbacks(mUpdateTimeTask);
    }

    /**
     * When user stops moving the progress hanlder
     * */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mHandler.removeCallbacks(mUpdateTimeTask);
        int totalDuration = mp.getDuration();
        int currentPosition = utils.progressToTimer(seekBar.getProgress(), totalDuration);

        // forward or backward to certain seconds
        mp.seekTo(currentPosition);

        // update timer progress again
        updateProgressBar();
    }
}
