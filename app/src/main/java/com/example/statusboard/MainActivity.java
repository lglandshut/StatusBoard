package com.example.statusboard;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.statusboard.databinding.ActivityMainBinding;
import com.example.statusboard.databinding.AddBoardDialogBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BoardSelectedCallback {

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
        dialogBuilder.setTitle("Create new Board");

        final Button button_save = dialogBinding.buttonSave;
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newBoardName = dialogBinding.popupName.getText().toString().trim();
                String newBoardDescription = dialogBinding.popupDescription.getText().toString().trim();
                Log.d(TAG, "" + newBoardName.length() + " " + newBoardDescription.length());
                Log.d(TAG, "" + (newBoardName.length() > 0 && newBoardDescription.length() > 0));
                if (newBoardName.length() > 0) {
                    //create Board object and add to list
                    Board board = new Board(newBoardName, newBoardDescription);
                    Log.d(TAG, "onClick: created board= " + board.toString());
                    boardList.add(boardList.size(), board);
                    boardAdapter.notifyItemInserted(boardList.size() + 1);
                    newBoardDialog.dismiss();
                }
                //if name has no input
                else {
                    dialogBinding.errorText.setText(R.string.board_dialog_error_text);
                    dialogBinding.errorText.setVisibility(View.VISIBLE);
                }
            }
        });

        dialogBuilder.setView(dialogBinding.getRoot());
        newBoardDialog = dialogBuilder.create();
        newBoardDialog.show();

    }

//    Die Methode is jetzt im BoardAdapter, kannst hier löschen
    //open board when clicked on CardView (onclick in xml)
//    public void openBoard() {
//        String value = "Hello world";
//        final Intent intend = new Intent(this, BoardActivity.class);
//        //Deswegen brauch ich des ganze als onclicklistener
//        //intend.putExtra("board",board);
//        startActivity(intend);
//    }

    //edit board when clicked on CardView wrench (oncklick in xml)
    public void createEditBoardDialog(View view) {
        //TODO: 3) Selbes Spiel hier wie bei den ersten beiden To Dos
        //Deswegen brauch ich des ganze als onclicklistener
        final AddBoardDialogBinding dialogBinding = AddBoardDialogBinding.inflate(getLayoutInflater());
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBinding.textView.setText("Edit Board");
        //dialogBuilder.setTitle("Edit Board " + board.getName());

        final Button button_save = dialogBinding.buttonSave;
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newBoardName = dialogBinding.popupName.getText().toString().trim();
                String newBoardDescription = dialogBinding.popupDescription.getText().toString().trim();
                if (newBoardName.length() > 0) {
                    //Save new values to selected Board
                    //board.setName(newBoardName);
                    //board.setDescription(newBoardDescription);
                    newBoardDialog.dismiss();
                }
                //if name has no input
                else {
                    dialogBinding.errorText.setText(R.string.board_dialog_error_text);
                    dialogBinding.errorText.setVisibility(View.VISIBLE);
                }
            }
        });

        dialogBuilder.setView(dialogBinding.getRoot());
        newBoardDialog = dialogBuilder.create();
        newBoardDialog.show();
    }

    /**
     * Check irgendwie noch nicht so richtig wozu du eigentlich ein Callback brauchst. Du kannst
     * doch alles ausm BoardAdapter machen und muss nicht dein ausgewähltes Board der MainActivty
     * übergeben oder überseh ich was?
     *
     * Aber an sich ja Callbacks gibts, werden häufig benutzt und wie du schon richtig herausgefunden
     * hast einfach ein Interface mit der/den Methoden erstellen und dann letztendlich deine
     * Interface Methoden da aufrufen wo du dein Callback übergeben möchtes und an der Stelle
     * initalisieren wo du z.B. nen Button clickst oder eben in einer anderen Klasse/Activity
     * ein Objekt übergeben musst.
     * siehe: https://stackoverflow.com/questions/3398363/how-to-define-callbacks-in-android
     *
     * Reminder: Schau dir mal die Branches genauer an. Du bist die ganze Zeit dabei in correct-solution
     * zu arbeiten. Ich hab letztes Mal schon im anderen Branch gemerged.
     * @param board
     */
    @Override
    public void boardClicked(Board board) {

    }
}