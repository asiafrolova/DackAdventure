package com.example.dackadventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.dackadventure.databinding.ActivityMainBinding;
import com.example.dackadventure.questions.QuestionOne;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MediaPlayer cowPlayer;
    private MediaPlayer hiPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cowPlayer = MediaPlayer.create(this, R.raw.polskaya_korova);
        hiPlayer = MediaPlayer.create(this, R.raw.o_privet);

        cowPlayer.setLooping(true);
        cowPlayer.start();
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());


        binding.back.setOnClickListener(v -> {
            cowPlayer.pause();
            Intent intent=new Intent(MainActivity.this,StartGameActivity.class);
            startActivity(intent);
        });
        binding.Continue.setOnClickListener(v -> {
            cowPlayer.pause();
            Intent intent=new Intent(MainActivity.this, QuestionOne.class);
            startActivity(intent);
            overridePendingTransition(R.anim.alpha_two,R.anim.alpha);
        });
        Animation animationDuck= AnimationUtils.loadAnimation(this,R.anim.duck_walk);
        animationDuck.setDuration(8000);
        binding.duck.startAnimation(animationDuck);



        Animation animationManiac= AnimationUtils.loadAnimation(this,R.anim.maniac);
        animationManiac.setDuration(2000);


        Animation animationText= AnimationUtils.loadAnimation(this,R.anim.text);
        Animation animationScream= AnimationUtils.loadAnimation(this,R.anim.alpha_three);
        animationScream.setDuration(2000);


        animationDuck.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {


                binding.maniac.setAlpha(1.0F);
                binding.maniac.startAnimation(animationManiac);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animationManiac.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                hiPlayer.start();


                binding.story.startAnimation(animationText);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animationText.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.story.setAlpha(1.0F);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    protected void onPause() {
        cowPlayer.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {

        super.onResume();
        cowPlayer.start();
    }
}