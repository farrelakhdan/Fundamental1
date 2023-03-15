package com.example.fundamental1.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fundamental1.Api.FollowersResponseItem;
import com.example.fundamental1.databinding.CardViewBinding;

import java.util.List;

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.ListViewHolder> {

    private final List<FollowersResponseItem> list;

    public FollowingAdapter(List<FollowersResponseItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public FollowingAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FollowingAdapter.ListViewHolder(CardViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FollowingAdapter.ListViewHolder holder, int position) {
        holder.binding.tvItemName.setText(list.get(position).getLogin());
        Glide.with(holder.binding.getRoot()).
                load(list.get(position).getUrl())
                .into(holder.binding.imgItemImage);
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
}
