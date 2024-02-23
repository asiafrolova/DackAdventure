package com.example.dackadventure.questions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.dackadventure.AppDataBase;
import com.example.dackadventure.Count;
import com.example.dackadventure.R;
import com.example.dackadventure.Repository;
import com.example.dackadventure.StartGameActivity;
import com.example.dackadventure.databinding.ActivityFinalBinding;

import java.util.Calendar;

public class FinalActivity extends AppCompatActivity {
    private ActivityFinalBinding binding;
    private Repository repository;

    private MediaPlayer perfectPlayer;
    private MediaPlayer goodPlayer;
    private MediaPlayer badPlayer;
    private Calendar calendar;
    AppDataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivityFinalBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        calendar= Calendar.getInstance();

        repository=Repository.getInstance();

        perfectPlayer = MediaPlayer.create(this,R.raw.perfect);
        badPlayer = MediaPlayer.create(this,R.raw.bad);
        goodPlayer = MediaPlayer.create(this,R.raw.good);
        repository.setCount(repository.getCount()%7);

        binding.Continue.setOnClickListener(v -> {
            Intent intent=new Intent(FinalActivity.this, StartGameActivity.class);
            startActivity(intent);
        });
        if(repository.getCount()==1){
            binding.result.setText("Поздравляем, вы набрали "+repository.getCount()+" очко!");
        }else if(repository.getCount()>1 && repository.getCount()<5){
            binding.result.setText("Поздравляем, вы набрали "+repository.getCount()+" очка!");
        }else if(repository.getCount()>4 | repository.getCount()==0){
            binding.result.setText("Поздравляем, вы набрали "+repository.getCount()+" очков!");
        }

        if (repository.getCount()<3){
            binding.story.setText("К сожалению вы очень плохо отвечали на вопросы и маньяк решил вас убить:(");
            binding.imageView.setImageResource(R.drawable.bad_game);
            badPlayer.start();

        }else if (repository.getCount()>=3 && repository.getCount()<5){
            binding.story.setText("Вы хорошо отвечали на вопросы и маньяк вас отпустил:)");
            binding.imageView.setImageResource(R.drawable.good_game);
            goodPlayer.start();

        } else if (repository.getCount()>=5) {
            binding.story.setText("Вы настолько умны, что маньяк решил усыновить вас. Теперь вы живете в шикарном особняке маньяка :D");
            binding.imageView.setImageResource(R.drawable.perfect_game);
            perfectPlayer.start();


        }
        db=AppDataBase.getInstance(getApplicationContext());
        String date= new StringBuilder().append(calendar.get(Calendar.MONTH)+1)
                .append(".").append(calendar.get(Calendar.DAY_OF_MONTH)).append(".")
                .append(calendar.get(Calendar.YEAR)).append(" ").toString();
        String time=new StringBuilder().append(calendar.get(Calendar.HOUR_OF_DAY)).append(":")
                .append(calendar.get(Calendar.MINUTE)).append(" ").toString();
       Count count =new Count(repository.getCount(),date,time);

        db.countDao().insert(count);
       repository.setNull();
       onStop();










    }

    @Override
    protected void onPause() {

        super.onPause();
    }
}