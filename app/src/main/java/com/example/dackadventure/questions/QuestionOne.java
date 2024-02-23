package com.example.dackadventure.questions;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.dackadventure.MainActivity;
import com.example.dackadventure.R;
import com.example.dackadventure.Repository;
import com.example.dackadventure.StartGameActivity;
import com.example.dackadventure.databinding.ActivityQuestionOneBinding;

public class QuestionOne extends AppCompatActivity {
    ActivityQuestionOneBinding binding;
    private boolean videoIsPlaying = false;
    private int count=0;
    private Repository repository;
    private MediaPlayer yesPlayer;
    private MediaPlayer noPlayer;
    private MediaPlayer clockPlayer;
    private Uri myVideoUri;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivityQuestionOneBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        yesPlayer = MediaPlayer.create(this, R.raw.yes);
        clockPlayer = MediaPlayer.create(this, R.raw.clock);
        noPlayer = MediaPlayer.create(this, R.raw.no);

        clockPlayer.setLooping(true);
        clockPlayer.start();

        repository=Repository.getInstance();


        myVideoUri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.ot_vinta);
        binding.videoView.setVideoURI(myVideoUri);
        mediaController=new MediaController(this);
        binding.videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(binding.videoView);


        binding.back.setOnClickListener(v -> {
            clockPlayer.pause();
            Intent intent=new Intent(QuestionOne.this, MainActivity.class);
            startActivity(intent);
        });
        binding.Continue.setOnClickListener(v -> {
            clockPlayer.pause();
            Intent intent=new Intent(QuestionOne.this, QuestionTwo.class);
            intent.putExtra("count",count);
            startActivity(intent);

        });
        binding.btnOne.setOnClickListener(v -> {
            binding.btnOne.setBackgroundResource(R.drawable.button_true);
            binding.btnTwo.setBackgroundResource(R.drawable.button_false);
            binding.btnThree.setBackgroundResource(R.drawable.button_false);
            binding.btnFour.setBackgroundResource(R.drawable.button_false);

            repository.increment();
            clockPlayer.pause();
            yesPlayer.start();
            binding.videoView.setAlpha(1.0F);
            binding.imageView.setAlpha(0.0F);
            videoIsPlaying=true;
            play();

        });
        binding.btnTwo.setOnClickListener(v -> {
            binding.btnOne.setBackgroundResource(R.drawable.button_true);
            binding.btnTwo.setBackgroundResource(R.drawable.button_false);
            binding.btnThree.setBackgroundResource(R.drawable.button_false);
            binding.btnFour.setBackgroundResource(R.drawable.button_false);

            clockPlayer.pause();
            noPlayer.start();
            binding.videoView.setAlpha(1.0F);
            binding.imageView.setAlpha(0.0F);
            videoIsPlaying=true;
            play();

        });
        binding.btnThree.setOnClickListener(v -> {
            binding.btnOne.setBackgroundResource(R.drawable.button_true);
            binding.btnTwo.setBackgroundResource(R.drawable.button_false);
            binding.btnThree.setBackgroundResource(R.drawable.button_false);
            binding.btnFour.setBackgroundResource(R.drawable.button_false);

            clockPlayer.pause();
            noPlayer.start();
            binding.videoView.setAlpha(1.0F);
            binding.imageView.setAlpha(0.0F);
            videoIsPlaying=true;
            play();

        });
        binding.btnFour.setOnClickListener(v -> {
            binding.btnOne.setBackgroundResource(R.drawable.button_true);
            binding.btnTwo.setBackgroundResource(R.drawable.button_false);
            binding.btnThree.setBackgroundResource(R.drawable.button_false);
            binding.btnFour.setBackgroundResource(R.drawable.button_false);

            clockPlayer.pause();
            noPlayer.start();
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
        clockPlayer.pause();
        onStop();
        super.onPause();
    }

    @Override
    protected void onStart() {

        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        clockPlayer.start();



    }


    public void play(){
        binding.videoView.start();
    }
    public void stop(){
        binding.videoView.pause();
    }
}