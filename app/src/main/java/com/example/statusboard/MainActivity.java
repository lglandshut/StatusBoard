package com.example.statusboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.statusboard.databinding.ActivityMainBinding;
import com.example.statusboard.databinding.AddBoardDialogBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "StatusBoardApp";
    private ActivityMainBinding binding;
    private AlertDialog newBoardDialog;
    private final List<Board> boardList = new ArrayList<>();
    private BoardAdapter boardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final RecyclerView recyclerView = binding.boardRecyclerView;
        boardAdapter = new BoardAdapter(boardList);
        recyclerView.setAdapter(boardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        binding.addBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewBoardDialog();
            }
        });
    }

    //Creates Dialog window to add a new Board
    public void createNewBoardDialog() {
        final AddBoardDialogBinding dialogBinding = AddBoardDialogBinding.inflate(getLayoutInflater());
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//        dialogBuilder hat noch viele Methoden die du setzen kannst wie setMessage, setIcon,
//        setNeutralButton, etc. Eigene Buttons implementieren ist an sich nicht falsch, aber
//        die vom AlertDialog passen hier besser.
        dialogBuilder.setTitle("Create new Board");

        dialogBuilder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newBoardName = dialogBinding.popupName.getText().toString().trim();
                String newBoardDescription = dialogBinding.popupDescription.getText().toString().trim();
                Log.d(TAG, ""+newBoardName.length()+ " " + newBoardDescription.length());
                Log.d(TAG, ""+ (newBoardName.length() > 0 && newBoardDescription.length() > 0));
                if (newBoardName.length() > 0 && newBoardDescription.length() > 0) {
                    //create Board object and add to list
                    Board board = new Board(newBoardName, newBoardDescription);
                    Log.d(TAG, "onClick: created board= " + board.toString());
                    boardList.add(boardList.size(),board);
                    boardAdapter.notifyItemInserted(boardList.size() +1);
                    newBoardDialog.dismiss();
                }
                //if name has no input
                else {
                    //dialogBinding.errorText.setText(R.string.board_dialog_error_text);
                    //dialogBinding.errorText.setVisibility(View.VISIBLE);
                }
            }
        });

        dialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                öfter logs benutzen, sind gut zum debuggen
                Log.d(TAG, "onClick: canceled Dialog");
            }
        });
        dialogBuilder.setView(dialogBinding.getRoot());
        newBoardDialog = dialogBuilder.create();
        newBoardDialog.show();

    }

    //open board when clicked on CardView
    public void openBoard(View view) {
        final Intent intent = new Intent(this, BoardActivity.class);
        startActivity(intent);
    }
}