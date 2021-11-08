package com.example.statusboard;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.statusboard.databinding.ActivityBoardBinding;

public class BoardActivity extends AppCompatActivity {
    private ActivityBoardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        createBoard();
    }

    public static void createBoard() {

    }
}