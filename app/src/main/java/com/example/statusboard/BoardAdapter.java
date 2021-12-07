package com.example.statusboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.statusboard.databinding.BoardItemBinding;

import java.util.List;

/*
    Adapter for the list of Boards in Main Activity
 */
public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder>  {

    private Context context;
    private static RecyclerViewClickListener itemListener;
    @NonNull
    private final List<Board> boardList;

    public BoardAdapter(@NonNull List<Board> list, Context context, RecyclerViewClickListener itemListener) {
        this.context = context;
        this.itemListener = itemListener;
        boardList = list;
    }

    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new BoardViewHolder(BoardItemBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        holder.bind(boardList.get(position));
    }

    @Override
    public int getItemCount() {
        return boardList.size();
    }

    public static class BoardViewHolder extends RecyclerView.ViewHolder {
        private final BoardItemBinding itemBinding;

        public BoardViewHolder(BoardItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        /**
         * Also, hast ja schon richtig erkannt, dass du mit putExtra Werte an eine neue Activity
         * übergeben kannst. Du kannst putExtra aber nur bestimmte Objekte übergeben siehe:
         * https://developer.android.com/reference/android/content/Intent#putExtra(java.lang.String,%20android.os.Parcelable)
         * <p>
         * Du hast zwei Optionen:
         * <p>
         * 1) Mit diesen paar Wenigen primitiven Typen arbeiten, also Name, Description, etc.
         * als String übergeben.
         * 2) Man kann auch eigene Objekte übergeben muss aber dafür etwas zusätztlich machen,
         * Stichwort Parcelable
         * https://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents
         *
         * Ich würds mit Parcelable machen, weils wie ich finde bequemer ist. In Java ist Parcelable
         * implementieren evtl. nervig in Kotlin ist des um einiges einfacher ¯\_(ツ)_/¯
         * Als erstes vlt. mal paar Strings, Ints, etc. übergeben und dann Parcelable anschauen, aber
         * wenn du auf des Parcelable Zeug kein Bock hast sind Strings auch in Ordnung
         */
        public void openBoard() {
            String value = "Hello world";
            Context context = itemBinding.getRoot().getContext();
            final Intent intent = new Intent(context, BoardActivity.class);
            //TODO: 1) Parameter an neue Activity übergeben
            intent.putExtra("board",board);
            context.startActivity(intent);
        }

        //Befüllt die board_rows im Hauptmenü
        public void bind(Board board) {
            itemBinding.getRoot().setOnClickListener(view -> openBoard());
            itemBinding.boardTxt.setText(board.getName());
            itemBinding.descriptionsTxt.setText(board.getDescription());
        }
    }
}
