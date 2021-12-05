package com.example.statusboard;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.statusboard.databinding.ActivityBoardBinding;

import java.util.ArrayList;
import java.util.List;

public class BoardActivity extends AppCompatActivity {

    private ActivityBoardBinding binding;
    private final List<Status> statusList = new ArrayList<>();
    private StatusAdapter statusAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final RecyclerView recyclerView = binding.statusRecyclerView;
        statusAdapter = new StatusAdapter(statusList);
        recyclerView.setAdapter(statusAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        //ToDo: Titel dynamisch den Board-Namen geben
        this.setTitle("BoardName");
        createBoard();

        binding.addStatusButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Status status = new Status("TESTNAME", Color.CYAN);
                statusList.add(statusList.size(),status);
                statusAdapter.notifyItemInserted(statusList.size() +1);
            }
        });
    }

    public static void createBoard() {

    }
}