package com.example.statusboard;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.statusboard.databinding.BoardItemBinding;
import com.example.statusboard.databinding.StatusItemBinding;

import java.util.List;

/*
    Adapter for the list of Status in BoardActivity
 */
public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.StatusViewHolder> {
    @NonNull
    private final List<Status> statusList;

    public StatusAdapter(@NonNull List<Status> list) {
        statusList = list;
    }

    @NonNull
    @Override
    public StatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new StatusViewHolder(StatusItemBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StatusViewHolder holder, int position) {
        holder.bind(statusList.get(position));
    }

    @Override
    public int getItemCount() {
        return statusList.size();
    }

    public static class StatusViewHolder extends RecyclerView.ViewHolder {
        private final StatusItemBinding itemBinding;

        public StatusViewHolder(StatusItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(Status status) {
            itemBinding.statusTxt.setText(status.getName());
            itemBinding.statusRow.setCardBackgroundColor(status.getColor());
        }
    }
}
