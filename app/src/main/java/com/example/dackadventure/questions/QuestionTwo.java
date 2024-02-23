package com.example.dackadventure.questions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;

import com.example.dackadventure.MainActivity;
import com.example.dackadventure.R;
import com.example.dackadventure.Repository;
import com.example.dackadventure.databinding.ActivityQuestionTwoBinding;

public class QuestionTwo extends AppCompatActivity {
    ActivityQuestionTwoBinding binding;
    private boolean videoIsPlaying = false;
    private int count;
    private Repository repository;
    private MediaPlayer yesPlayer;
    private MediaPlayer noPlayer;
    private MediaPlayer clockPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivityQuestionTwoBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        yesPlayer = MediaPlayer.create(this, R.raw.yes);
        clockPlayer = MediaPlayer.create(this, R.raw.clock);
        noPlayer = MediaPlayer.create(this, R.raw.no);

        clockPlayer.setLooping(true);
        clockPlayer.start();


        repository= Repository.getInstance();
        Uri myVideoUri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.adam);
        binding.videoView.setVideoURI(myVideoUri);
        MediaController mediaController=new MediaController(this);
        binding.videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(binding.videoView);


        binding.back.setOnClickListener(v -> {
            clockPlayer.pause();
            Intent intent=new Intent(QuestionTwo.this, QuestionOne.class);
            startActivity(intent);
        });
        binding.Continue.setOnClickListener(v -> {
            clockPlayer.pause();
            Intent intent=new Intent(QuestionTwo.this, QuestionThree.class);
            startActivity(intent);

        });

        binding.btnOne.setOnClickListener(v -> {
            binding.btnOne.setBackgroundResource(R.drawable.button_false);
            binding.btnTwo.setBackgroundResource(R.drawable.button_false);
            binding.btnThree.setBackgroundResource(R.drawable.button_false);
            binding.btnFour.setBackgroundResource(R.drawable.button_true);

            clockPlayer.pause();
            noPlayer.start();
            binding.videoView.setAlpha(1.0F);
            binding.imageView.setAlpha(0.0F);
            videoIsPlaying=true;
            play();

        });
        binding.btnTwo.setOnClickListener(v -> {
            binding.btnOne.setBackgroundResource(R.drawable.button_false);
            binding.btnTwo.setBackgroundResource(R.drawable.button_false);
            binding.btnThree.setBackgroundResource(R.drawable.button_false);
            binding.btnFour.setBackgroundResource(R.drawable.button_true);

            clockPlayer.pause();
            noPlayer.start();
            binding.videoView.setAlpha(1.0F);
            binding.imageView.setAlpha(0.0F);
            videoIsPlaying=true;
            play();

        });
        binding.btnThree.setOnClickListener(v -> {
            binding.btnOne.setBackgroundResource(R.drawable.button_false);
            binding.btnTwo.setBackgroundResource(R.drawable.button_false);
            binding.btnThree.setBackgroundResource(R.drawable.button_false);
            binding.btnFour.setBackgroundResource(R.drawable.button_true);

            clockPlayer.pause();
            noPlayer.start();
            binding.videoView.setAlpha(1.0F);
            binding.imageView.setAlpha(0.0F);
            videoIsPlaying=true;
            play();

        });
        binding.btnFour.setOnClickListener(v -> {
            binding.btnOne.setBackgroundResource(R.drawable.button_false);
            binding.btnTwo.setBackgroundResource(R.drawable.button_false);
            binding.btnThree.setBackgroundResource(R.drawable.button_false);
            binding.btnFour.setBackgroundResource(R.drawable.button_true);

            clockPlayer.pause();
            yesPlayer.start();
            repository.increment();
            binding.videoView.setAlpha(1.0F);
            binding.imageView.setAlpha(0.0F);
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
        clockPlayer.stop();
        super.onPause();

    }
    @Override
    protected void onResume() {

        super.onResume();
    }

    public void play(){
        binding.videoView.start();
    }
    public void stop(){
        binding.videoView.pause();
    }
}