package com.example.audioplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    ImageView play;
    ImageView pause;
    ImageView buttonNextMusic;
    ImageView buttonPastMusic;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.waves);

        play = findViewById(R.id.play);
        pause = findViewById(R.id.stop);
        buttonPastMusic = findViewById(R.id.buttonPastMusic);
        buttonNextMusic = findViewById(R.id.buttonNextMusic);

        play.setOnClickListener(view -> pauseStartMusic());
        buttonNextMusic.setOnClickListener(view -> onEndMusic());
        buttonPastMusic.setOnClickListener(view -> onStartMusic());

        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(mediaPlayer.getDuration());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    mediaPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 1000);

    }

    public void onStartMusic() {
        seekBar.setProgress(0);
        mediaPlayer.seekTo(0);
        pause.animate().alpha(0);
        play.animate().alpha(1);
        mediaPlayer.pause();
    }

    public void onEndMusic() {
        seekBar.setProgress(mediaPlayer.getDuration());
        mediaPlayer.seekTo(mediaPlayer.getDuration());
        pause.animate().alpha(0);
        play.animate().alpha(1);
        mediaPlayer.pause();

    }

    public void pauseStartMusic() {
        if (mediaPlayer.isPlaying()) {
            pause.animate().alpha(0);
            play.animate().alpha(1);
            mediaPlayer.pause();
        } else {
            play.animate().alpha(0);
            pause.animate().alpha(1);
            mediaPlayer.start();
        }
    }
}