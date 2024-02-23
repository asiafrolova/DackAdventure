package com.example.dackadventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.dackadventure.databinding.ActivityScoreBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {
    ActivityScoreBinding binding;
    Repository repository;
    List<Count> list=new ArrayList<>();
    AppDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivityScoreBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        db=AppDataBase.getInstance(getApplicationContext());


        CountAdapter adapter = new CountAdapter();
        adapter.setCountList(db.countDao().getAll());
        binding.ReView.setAdapter(adapter);

        binding.clean.setOnClickListener(v -> {
            db.countDao().deleteAll();
            adapter.setCountList(list);
            binding.ReView.setAdapter(adapter);
        });
        binding.back.setOnClickListener(v -> {
            Intent intent=new Intent(ScoreActivity.this,StartGameActivity.class);
            startActivity(intent);
        });





    }
}