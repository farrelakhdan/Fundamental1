package com.example.fundamental1.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fundamental1.Adapter.FollowersAdapter;
import com.example.fundamental1.Adapter.FollowingAdapter;
import com.example.fundamental1.Adapter.UserAdapter;
import com.example.fundamental1.Api.ApiConfig;
import com.example.fundamental1.Api.FollowersResponse;
import com.example.fundamental1.Api.FollowersResponseItem;
import com.example.fundamental1.Api.ItemsItem;
import com.example.fundamental1.Api.UserData;
import com.example.fundamental1.DetailPage;
import com.example.fundamental1.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowersFragment extends Fragment {
    private static final String TAG = FollowersFragment.class.getSimpleName();
    public static String ARG_POSITION = "";
    public static String ARG_USERNAME = "farrel";

    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recv_followers);
        progressBar = view.findViewById(R.id.progressBar1);

        int position;
        String username;
        position = getArguments().getInt(ARG_POSITION);
        username = getArguments().getString(ARG_USERNAME);
        if (getArguments() != null) {
            Call<List<ItemsItem>> client = ApiConfig.getApiService().getFollowers(username);
            client.enqueue(new Callback<List<ItemsItem>>() {
                @Override
                public void onResponse(Call<List<ItemsItem>> call, Response<List<ItemsItem>> response) {
                    progressBar.setVisibility(View.GONE);
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            setFollowersData(response.body());
                        }
                    } else {
                        if (response.body() != null) {
                            Log.e(TAG, "onFailure: ");
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<ItemsItem>> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Log.e(TAG, "Fail");
                }
            });
        }
        if (getArguments() != null) {
            Call<List<ItemsItem>> client = ApiConfig.getApiService().getFollowing(username);
            client.enqueue(new Callback<List<ItemsItem>>() {
                @Override
                public void onResponse(Call<List<ItemsItem>> call, Response<List<ItemsItem>> response) {
                    progressBar.setVisibility(View.GONE);
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            setFollowingData(response.body());
                        }
                    } else {
                        if (response.body() != null) {
                            Log.e(TAG, "onFailure: ");
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<ItemsItem>> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Log.e(TAG, "Fail");
                }
            });
        }
        if (position == 1) {

        }else {

        }
    }

    private void setFollowersData(List<ItemsItem> items) {
        ArrayList<FollowersResponseItem> listItem = new ArrayList<FollowersResponseItem>();
        for (ItemsItem item : items) {
            FollowersResponseItem data = new FollowersResponseItem();
            data.setLogin(item.getLogin());
            data.setUrl(item.getAvatarUrl());
            listItem.add(data);
        }
        Log.e(TAG, "setFollowersData: " + listItem);
        FollowersAdapter adapter = new FollowersAdapter(listItem);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    private void setFollowingData(List<ItemsItem> items) {
        ArrayList<FollowersResponseItem> listItem = new ArrayList<FollowersResponseItem>();
        for (ItemsItem item : items) {
            FollowersResponseItem data = new FollowersResponseItem();
            data.setLogin(item.getLogin());
            data.setUrl(item.getAvatarUrl());
            listItem.add(data);
        }
        Log.e(TAG, "setFollowingData: " + listItem);
        FollowingAdapter adapter = new FollowingAdapter(listItem);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}