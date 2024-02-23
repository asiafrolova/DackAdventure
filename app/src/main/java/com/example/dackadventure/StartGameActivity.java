package com.example.dackadventure;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.SeekBar;

import com.example.dackadventure.databinding.ActivityStartGameBinding;
import com.google.android.material.snackbar.Snackbar;

public class StartGameActivity extends AppCompatActivity {
    private ActivityStartGameBinding binding;
    private Intent intent;
    private MediaPlayer mPlayer;
    private Repository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityStartGameBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());



        mPlayer = MediaPlayer.create(this, R.raw.millpops);
        mPlayer.setLooping(true);
        mPlayer.start();


        repository = Repository.getInstance();





        binding.startGame.setOnClickListener(v -> {
            repository.setReturnStart(true);
            intent = new Intent(StartGameActivity.this
                    , MainActivity.class);
            mPlayer.pause();
            startActivity(intent);
            overridePendingTransition(R.anim.alpha_two, R.anim.alpha);

        });
        binding.historyScore.setOnClickListener(v -> {
            intent = new Intent(StartGameActivity.this
                    , ScoreActivity.class);
            mPlayer.pause();
            startActivity(intent);
            overridePendingTransition(R.anim.alpha_two, R.anim.alpha);
        });




        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curValue = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        binding.volume.setMax(maxVolume);
        binding.volume.setProgress(curValue);
        binding.volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(repository.isReturnStart()){
            ConstraintLayout layout=binding.layout;
            Snackbar snackbar=Snackbar.make(layout,"Если  у вас андроид ниже 12.0, пожалуйста перезапустите приложение",Snackbar.LENGTH_SHORT);
            snackbar.setAction("Тык", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCreateDialog().show();
                }
            });
            snackbar.show();}


    }

    @Override
    protected void onPause() {
        mPlayer.pause();
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();


    }


    protected Dialog onCreateDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(StartGameActivity.this);
        builder.setTitle("Особенности работы андроид ниже 12.0")
                .setMessage("В андроид ниже 12.0 при повторном прохождении видео и звук могут работать не корректно")
                .setIcon(R.drawable.baseline_warning_24)
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        return builder.create();

    }
}