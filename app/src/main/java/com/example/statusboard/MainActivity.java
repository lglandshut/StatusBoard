package com.example.statusboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AlertDialog dialog;
    private EditText popup_name, popup_description;
    private final List<Board> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create RecyclerView
        RecyclerView recyclerView = findViewById(R.id.board_recycler_view);
        MyAdapter myAdapter = new MyAdapter(this, list);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button addBoardButton = findViewById(R.id.add_board_button);
        addBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewBoardDialog();
            }
        });
    }

    //Creates Dialog window to add a new Board
    public void createNewBoardDialog(){
        TextView newText = new TextView(this);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup, null);
        popup_name = (EditText) contactPopupView.findViewById(R.id.popup_name);
        popup_description = (EditText) contactPopupView.findViewById(R.id.popup_description);
        Button popup_save = (Button) contactPopupView.findViewById(R.id.popup_save);
        Button popup_cancel = (Button) contactPopupView.findViewById(R.id.popup_cancel);


        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        //get name and add to list
        popup_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if name is set
                if(popup_name.getText().length() > 0){

                    //create Board object and add to list
                    Board board = new Board(popup_name.getText().toString(), popup_description.getText().toString());
                    list.add(board);

                    dialog.dismiss();
                }
                //if name has no input
                else {
                    TextView errorText = (TextView) contactPopupView.findViewById(R.id.errorText);
                    errorText.setText("Choose a name!");
                    errorText.setVisibility(View.VISIBLE);
                }


            }
        });

        //close dialog
        popup_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    //open board when clicked on CardView
    public void openBoard(View view){
        Intent intent = new Intent(this,BoardActivity.class);
        startActivity(intent);
    }
}