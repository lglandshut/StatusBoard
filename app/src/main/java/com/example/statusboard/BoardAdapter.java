package com.example.statusboard;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.statusboard.databinding.BoardItemBinding;

import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {
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

//        Des ist ne convention im ViewHolder die Werte dem Layout zuweisen.
//        Du hattest des in onBindViewHolder was auch funktioniert, aber kannst es dir angewöhnen,
//        im ViewHolder ne Methode bind() zu erstellen wo die dein Layout befüllst und die Methode
//        rufst du dann nur in onBindViewHolder() auf.
        public void bind(Board board) {
            itemBinding.boardsTxt.setText(board.getName());
            itemBinding.descriptionsTxt.setText(board.getDescription());
        }
    }
}
