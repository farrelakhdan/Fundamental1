package com.example.fundamental1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.fundamental1.Adapter.UserAdapter;
import com.example.fundamental1.Api.ApiConfig;
import com.example.fundamental1.Api.GithubResponse;
import com.example.fundamental1.Api.ItemsItem;
import com.example.fundamental1.Api.UserData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;

    private ProgressBar progressBar;
    private static String KEY = "farrel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recv_main);
        progressBar = findViewById(R.id.progressBar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);

        getData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            SearchView searchView = (SearchView) (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    KEY = query;
                    getData();
                    return true;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }

        return true;
    }
    private void getData() {
        showLoading(true);
        Call<GithubResponse> client = ApiConfig.getApiService().getList(KEY);
        client.enqueue(new Callback<GithubResponse>() {
            @Override
            public void onResponse(@NotNull Call<GithubResponse> call,@NotNull Response<GithubResponse> response) {
                showLoading(false);
                
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        setGithubData(response.body().getItems());
                    }
                } else {
                    if (response.body() != null) {
                        Log.e(TAG, "onFailure: ");
                    }
                }
            }

            @Override
            public void onFailure(Call<GithubResponse> call, Throwable t) {
                showLoading(false);
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void setGithubData(List<ItemsItem> items) {
        ArrayList<UserData> listItem = new ArrayList<UserData>();
        for (ItemsItem item : items) {
            UserData data = new UserData();
            data.setName(item.getLogin());
            data.setUrl(item.getAvatarUrl());
            listItem.add(data);
        }
        UserAdapter adapter = new UserAdapter(listItem);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickCallback(data -> {
            Intent intentToDetail = new Intent(this, DetailPage.class);
            intentToDetail.putExtra("username", data.getName());
            intentToDetail.putExtra("avatar", data.getUrl());
            startActivity(intentToDetail);
        });
    }

    private void showLoading(Boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}