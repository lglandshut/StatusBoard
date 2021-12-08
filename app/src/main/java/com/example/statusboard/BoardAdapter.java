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

    @NonNull
    private final List<Board> boardList;

    public BoardAdapter(@NonNull List<Board> list) {
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

        //Befüllt die board_rows im Hauptmenü
        public void bind(Board board) {

            itemBinding.boardTxt.setText(board.getName());
            itemBinding.descriptionsTxt.setText(board.getDescription());
            itemBinding.getRoot().setOnClickListener(view -> {
                openBoard(board);
            });
        }

        public void openBoard(Board clickedBoard) {
            Context context = itemBinding.getRoot().getContext();
            final Intent intent = new Intent(context, BoardActivity.class);
            intent.putExtra("board", clickedBoard);
            context.startActivity(intent);
        }
    }
}
