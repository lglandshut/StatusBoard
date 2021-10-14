package com.example.statusboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText popup_name;
    private Button popup_cancel, popup_save;
    private RecyclerView recyclerView;
    private String s1[], s2[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RecyclerView erstellen
        recyclerView = findViewById(R.id.recyclerView);
        s1 = getResources().getStringArray(R.array.boards);
        s2 = getResources().getStringArray(R.array.descriptions);
        MyAdapter myAdapter = new MyAdapter(this, s1, s2);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //Open Dialog window to add a new Board
    public void openDialog (View view){

        createNewBoardDialog();
    }

    //Creates Dialog window to add a new Board
    public void createNewBoardDialog(){
        TextView newText = new TextView(this);

        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup, null);
        popup_name = (EditText) contactPopupView.findViewById(R.id.popup_name);
        popup_save = (Button) contactPopupView.findViewById(R.id.popup_save);
        popup_cancel = (Button) contactPopupView.findViewById(R.id.popup_cancel);


        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        //get name and add to list
        popup_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(popup_name.getText().length());
                if(popup_name.getText().length() > 0){
                    newText.setId(View.generateViewId());
                    newText.setText(popup_name.getText());
                    LinearLayout layout = findViewById(R.id.list);
                    layout.addView(newText);
                    dialog.dismiss();
                }
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
}