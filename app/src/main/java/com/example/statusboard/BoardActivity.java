package com.example.statusboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        createBoard();
    }

    public static void createBoard() {

    }
}