package com.example.statusboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Board> data1 = new ArrayList<Board>();
    Context context;

    public MyAdapter(Context ct, List<Board> list){
        context = ct;
        data1 = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.boards_txt.setText(data1.get(position).name);
        holder.descriptions_txt.setText(data1.get(position).description);
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView boards_txt, descriptions_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            boards_txt = itemView.findViewById(R.id.boards_txt);
            descriptions_txt = itemView.findViewById(R.id.descriptions_txt);
        }
    }
}
