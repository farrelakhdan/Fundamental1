package com.example.fundamental1.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.bumptech.glide.Glide;
import com.example.fundamental1.Api.UserData;
import com.example.fundamental1.databinding.CardViewBinding;

import java.util.ArrayList;

public class UserAdapter extends Adapter<UserAdapter.ListViewHolder> {

    private final ArrayList<UserData> list;
    private OnItemClickCallback onItemClickCallback;
    public UserAdapter(ArrayList<UserData> list) {
        this.list = list ;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public UserAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListViewHolder(CardViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ListViewHolder holder, int position) {
        holder.binding.tvItemName.setText(list.get(position).getName());
        Glide.with(holder.binding.getRoot()).
                load(list.get(position).getUrl())
                .into(holder.binding.imgItemImage);

        holder.itemView.setOnClickListener(v -> {
            onItemClickCallback.onItemClicked(
                    list.get(holder.getAdapterPosition())
            );
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        CardViewBinding binding;
        public ListViewHolder(@NonNull CardViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(UserData data);
    }
}
