package com.example.dackadventure.questions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.SeekBar;

import com.example.dackadventure.MainActivity;
import com.example.dackadventure.R;
import com.example.dackadventure.Repository;
import com.example.dackadventure.databinding.ActivityQuestionFourBinding;
import com.example.dackadventure.databinding.ActivityQuestionThreeBinding;

public class QuestionFour extends AppCompatActivity {

    ActivityQuestionFourBinding binding;
    private boolean videoIsPlaying = false;
    private int count=0;
    private Repository repository;
    private MediaPlayer yesPlayer;
    private MediaPlayer noPlayer;
    private MediaPlayer clockPlayer;
    private MediaPlayer songPlayer;
    private AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding= ActivityQuestionFourBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        repository=Repository.getInstance();

        yesPlayer = MediaPlayer.create(this, R.raw.yes);
        clockPlayer = MediaPlayer.create(this, R.raw.clock);
        noPlayer = MediaPlayer.create(this, R.raw.no);
        songPlayer = MediaPlayer.create(this, R.raw.imposter);



        audioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int MaxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int CurValue = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        binding.seekbar.setMax(MaxVolume);
        binding.seekbar.setProgress(CurValue);
        binding.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        binding.play.setOnClickListener(v -> {
            clockPlayer.pause();
            songPlayer.start();
        });
        binding.pause.setOnClickListener(v -> {
            songPlayer.pause();
            clockPlayer.start();
        });
        songPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                clockPlayer.start();
            }
        });

        clockPlayer.setLooping(true);
        clockPlayer.start();


        Uri myVideoUri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.imposter_video);
        binding.videoView.setVideoURI(myVideoUri);
        MediaController mediaController=new MediaController(this);
        binding.videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(binding.videoView);


        binding.back.setOnClickListener(v -> {
            songPlayer.pause();
            clockPlayer.pause();
            Intent intent=new Intent(QuestionFour.this, QuestionThree.class);
            startActivity(intent);
        });
        binding.Continue.setOnClickListener(v -> {
            songPlayer.pause();
            clockPlayer.pause();
            Intent intent=new Intent(QuestionFour.this, QuestionFive.class);
            startActivity(intent);

        });

        binding.btnOne.setOnClickListener(v -> {
            binding.btnOne.setBackgroundResource(R.drawable.button_false);
            binding.btnTwo.setBackgroundResource(R.drawable.button_false);
            binding.btnThree.setBackgroundResource(R.drawable.button_true);
            binding.btnFour.setBackgroundResource(R.drawable.button_false);
            songPlayer.pause();

            clockPlayer.pause();
            noPlayer.start();
            binding.videoView.setVisibility(View.VISIBLE);
            binding.videoView.setAlpha(1.0F);
            binding.volumeLinear.setAlpha(0.0F);
            videoIsPlaying=true;
            play();

        });
        binding.btnTwo.setOnClickListener(v -> {
            binding.btnOne.setBackgroundResource(R.drawable.button_false);
            binding.btnTwo.setBackgroundResource(R.drawable.button_false);
            binding.btnThree.setBackgroundResource(R.drawable.button_true);
            binding.btnFour.setBackgroundResource(R.drawable.button_false);
            songPlayer.pause();

            clockPlayer.pause();
            noPlayer.start();

            binding.videoView.setVisibility(View.VISIBLE);
            binding.videoView.setAlpha(1.0F);
            binding.volumeLinear.setAlpha(0.0F);
            videoIsPlaying=true;
            play();

        });
        binding.btnThree.setOnClickListener(v -> {
            binding.btnOne.setBackgroundResource(R.drawable.button_false);
            binding.btnTwo.setBackgroundResource(R.drawable.button_false);
            binding.btnThree.setBackgroundResource(R.drawable.button_true);
            binding.btnFour.setBackgroundResource(R.drawable.button_false);
            songPlayer.pause();

            clockPlayer.pause();
            yesPlayer.start();
            repository.increment();
            binding.videoView.setVisibility(View.VISIBLE);
            binding.videoView.setAlpha(1.0F);
            binding.volumeLinear.setAlpha(0.0F);
            videoIsPlaying=true;
            play();

        });
        binding.btnFour.setOnClickListener(v -> {
            binding.btnOne.setBackgroundResource(R.drawable.button_false);
            binding.btnTwo.setBackgroundResource(R.drawable.button_false);
            binding.btnThree.setBackgroundResource(R.drawable.button_true);
            binding.btnFour.setBackgroundResource(R.drawable.button_false);
            songPlayer.pause();

            clockPlayer.pause();
            noPlayer.start();
            binding.videoView.setVisibility(View.VISIBLE);
            binding.videoView.setAlpha(1.0F);
            binding.volumeLinear.setAlpha(0.0F);
            videoIsPlaying=true;
            play();
        });

        binding.videoView.setOnClickListener(v -> {

            if (videoIsPlaying){
                stop();
            }else{
                play();
            }
        });
    }

    @Override
    protected void onPause() {
        songPlayer.stop();
        clockPlayer.stop();
        super.onPause();
    }
    @Override
    protected void onResume() {
        Uri myVideoUri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.imposter_video);
        binding.videoView.setVideoURI(myVideoUri);
        MediaController mediaController=new MediaController(this);
        binding.videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(binding.videoView);
        super.onResume();
    }

    public void play(){
        binding.videoView.start();
    }
    public void stop(){
        binding.videoView.pause();
    }
}