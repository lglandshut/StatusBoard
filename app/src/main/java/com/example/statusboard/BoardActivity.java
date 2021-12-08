package com.example.statusboard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.statusboard.databinding.ActivityBoardBinding;
import com.example.statusboard.databinding.ActivityMainBinding;
import com.example.statusboard.databinding.AddBoardDialogBinding;
import com.example.statusboard.databinding.AddStatusDialogBinding;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorChangedListener;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class BoardActivity extends AppCompatActivity {

    private static final String TAG = "StatusBoardApp";
    private AlertDialog newStatusDialog;
    private ActivityBoardBinding binding;
    private final List<Status> statusList = new ArrayList<>();
    private StatusAdapter statusAdapter;
    private int chosenColor;

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
        Intent i = getIntent();
        Board clickedBoard = (Board) i.getParcelableExtra("board");
        this.setTitle(clickedBoard.getName());
        createBoard();

        binding.addStatusButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) { createNewStatusDialog();}
        });
    }

    private void createNewStatusDialog(){
        final AddStatusDialogBinding dialogBinding = AddStatusDialogBinding.inflate(getLayoutInflater());
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Create new Status");

        ColorPickerView colorPickerView = dialogBinding.colorPickerView;
        colorPickerView.addOnColorChangedListener(new OnColorChangedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override public void onColorChanged(int selectedColor) {
                // Handle on color change
                chosenColor = selectedColor;
                Log.d("ColorPicker", "onColorChanged: 0x" + Integer.toHexString(selectedColor));
            }
        });
        colorPickerView.addOnColorSelectedListener(new OnColorSelectedListener() {
            @Override
            public void onColorSelected(int selectedColor) {
                Toast.makeText(
                        BoardActivity.this,
                        "selectedColor: " + Integer.toHexString(selectedColor).toUpperCase(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        final Button button_save = dialogBinding.statusButtonSave;
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newStatusName = dialogBinding.statusName.getText().toString().trim();
                String newStatusDescription = dialogBinding.statusDescription.getText().toString().trim();
                Log.d(TAG, "" + newStatusName.length() + " " + newStatusDescription.length());
                Log.d(TAG, "" + (newStatusName.length() > 0 && newStatusDescription.length() > 0));
                if (newStatusName.length() > 0) {
                    //create Status object and add to list
                    Status status = new Status(newStatusName, newStatusDescription, chosenColor);
                    Log.d(TAG, "onClick: created board= " + status.toString());
                    statusList.add(statusList.size(), status);
                    statusAdapter.notifyItemInserted(statusList.size() + 1);
                    newStatusDialog.dismiss();
                }
                //if name has no input
                else {
                    dialogBinding.statusErrorText.setText(R.string.board_dialog_error_text);
                    dialogBinding.statusErrorText.setVisibility(View.VISIBLE);
                }
            }
        });

        dialogBuilder.setView(dialogBinding.getRoot());
        newStatusDialog = dialogBuilder.create();
        newStatusDialog.show();
    }


    //TODO: Board mit Werten füllen
    public static void createBoard() {


    }

}