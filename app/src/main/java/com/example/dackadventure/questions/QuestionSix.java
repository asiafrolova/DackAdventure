package com.example.dackadventure.questions;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dackadventure.R;
import com.example.dackadventure.Repository;
import com.example.dackadventure.databinding.ActivityQuestionFiveBinding;
import com.example.dackadventure.databinding.ActivityQuestionSixBinding;
import com.example.dackadventure.questions.QuestionFour;

public class QuestionSix extends AppCompatActivity {
    ActivityQuestionSixBinding binding;
    private boolean videoIsPlaying = false;
    private int count=0;
    private Repository repository;
    private MediaPlayer yesPlayer;
    private MediaPlayer noPlayer;
    private MediaPlayer clockPlayer;
    private MediaPlayer knowPlayer;
    private MediaPlayer hellPlayer;
    private MediaPlayer dadPlayer;
    private MediaPlayer respectPlayer;
    private boolean isKnow=false;
    private boolean isHell=false;
    private boolean isDad=false;
    private boolean isRespect=false;
    private boolean isClock=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding= ActivityQuestionSixBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        repository=Repository.getInstance();

        yesPlayer = MediaPlayer.create(this, R.raw.yes);
        clockPlayer = MediaPlayer.create(this, R.raw.clock);
        noPlayer = MediaPlayer.create(this, R.raw.no);
        knowPlayer = MediaPlayer.create(this, R.raw.youdidntnow);
        hellPlayer = MediaPlayer.create(this,R.raw.hellisforever);
        dadPlayer =MediaPlayer.create(this,R.raw.helldad);
        respectPlayer = MediaPlayer.create(this,R.raw.respectless);

        clockPlayer.setLooping(true);
        clockPlayer.start();

        binding.hellDad.setOnClickListener(v -> {
            if(isDad){
                stopMusic(dadPlayer);
                isDad=false;
                if(isClock){
                    clockPlayer.start();
                }
            }else{
                clockPlayer.pause();
                startMusic(dadPlayer);
                isDad=true;
            }
        });
        binding.hellIdForever.setOnClickListener(v -> {
            if(isHell){
                stopMusic(hellPlayer);
                isHell=false;
                if(isClock){
                    clockPlayer.start();
                }
            }else{
                clockPlayer.pause();
                startMusic(hellPlayer);
                isHell=true;
            }
        });
        binding.didtNow.setOnClickListener(v -> {
            if(isKnow){
                stopMusic(knowPlayer);
                isKnow=false;
                if(isClock){
                    clockPlayer.start();
                }
            }else{
                clockPlayer.pause();
                startMusic(knowPlayer);
                isKnow=true;
            }
        });
        binding.respectless.setOnClickListener(v -> {
            if(isRespect){
                stopMusic(respectPlayer);
                isRespect=false;
                if(isClock){
                    clockPlayer.start();
                }
            }else{
                clockPlayer.pause();
                startMusic(respectPlayer);
                isRespect=true;
            }
        });


        binding.back.setOnClickListener(v -> {
            hellPlayer.pause();
            dadPlayer.pause();
            knowPlayer.pause();
            respectPlayer.pause();
            clockPlayer.pause();
            Intent intent_one=new Intent(QuestionSix.this, QuestionFive.class);
            startActivity(intent_one);

        });
        binding.Continue.setOnClickListener(v -> {
            hellPlayer.pause();
            dadPlayer.pause();
            knowPlayer.pause();
            respectPlayer.pause();
            clockPlayer.pause();
            Intent intent_two=new Intent(QuestionSix.this, FinalActivity.class);
            startActivity(intent_two);

        });


        binding.btnOne.setOnClickListener(v -> {
            binding.btnOne.setBackgroundResource(R.drawable.button_true);
            binding.btnTwo.setBackgroundResource(R.drawable.button_true);
            binding.btnThree.setBackgroundResource(R.drawable.button_true);
            binding.btnFour.setBackgroundResource(R.drawable.button_true);

            binding.question.setText("Они все шикарные!!!");

            clockPlayer.pause();
            isClock=false;
            yesPlayer.start();

            repository.increment();




        });
        binding.btnTwo.setOnClickListener(v -> {
            binding.btnOne.setBackgroundResource(R.drawable.button_true);
            binding.btnTwo.setBackgroundResource(R.drawable.button_true);
            binding.btnThree.setBackgroundResource(R.drawable.button_true);
            binding.btnFour.setBackgroundResource(R.drawable.button_true);

            binding.question.setText("Они все шикарные!!!");

            clockPlayer.pause();
            isClock=false;
            yesPlayer.start();

            repository.increment();



        });
        binding.btnThree.setOnClickListener(v -> {
            binding.btnOne.setBackgroundResource(R.drawable.button_true);
            binding.btnTwo.setBackgroundResource(R.drawable.button_true);
            binding.btnThree.setBackgroundResource(R.drawable.button_true);
            binding.btnFour.setBackgroundResource(R.drawable.button_true);

            binding.question.setText("Они все шикарные!!!");

            clockPlayer.pause();
            isClock=false;
            yesPlayer.start();

            repository.increment();




        });
        binding.btnFour.setOnClickListener(v -> {
            binding.btnOne.setBackgroundResource(R.drawable.button_true);
            binding.btnTwo.setBackgroundResource(R.drawable.button_true);
            binding.btnThree.setBackgroundResource(R.drawable.button_true);
            binding.btnFour.setBackgroundResource(R.drawable.button_true);

            binding.question.setText("Они все шикарные!!!");

            clockPlayer.pause();
            isClock=false;
            yesPlayer.start();

            repository.increment();



        });



    }

    @Override
    protected void onPause() {
        hellPlayer.pause();
        dadPlayer.pause();
        knowPlayer.pause();
        respectPlayer.pause();
        clockPlayer.pause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void startMusic(MediaPlayer mediaPlayer){
        mediaPlayer.start();
    }
    public void stopMusic(MediaPlayer mediaPlayer){
        mediaPlayer.pause();

    }
}