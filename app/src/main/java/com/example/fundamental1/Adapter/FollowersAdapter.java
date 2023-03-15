package com.example.fundamental1.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fundamental1.Api.FollowersResponse;
import com.example.fundamental1.Api.FollowersResponseItem;
import com.example.fundamental1.databinding.CardViewBinding;
import com.example.fundamental1.databinding.FragmentFollowersBinding;

import java.util.ArrayList;
import java.util.List;

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.ListViewHolder> {

    private final List<FollowersResponseItem> list;

    public FollowersAdapter(List<FollowersResponseItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public FollowersAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FollowersAdapter.ListViewHolder(CardViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FollowersAdapter.ListViewHolder holder, int position) {
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
