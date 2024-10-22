package com.learningkids.quizapp.story;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learningkids.quizapp.R;

import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {

    private ArrayList<Story> dataSet;
    private OnClickListener onClickListener;

    // Constructor
    public StoryAdapter(ArrayList<Story> dataSet) {
        this.dataSet = dataSet;
    }

    // ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTile;
        public ImageView image;

        public ViewHolder(View view) {
            super(view);
            tvTile = view.findViewById(R.id.tvItemListTitle);
            image = view.findViewById(R.id.itemListImage);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTile.setText(dataSet.get(position).getTitle());
        holder.image.setImageResource(dataSet.get(position).getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    // Method to set the onClickListener
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    // Interface for onClick callback
    public interface OnClickListener {
        void onClick(int position);
    }
}