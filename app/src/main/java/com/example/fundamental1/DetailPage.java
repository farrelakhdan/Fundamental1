package com.example.fundamental1;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fundamental1.Adapter.SectionPagerAdapter;
import com.example.fundamental1.Api.ApiConfig;
import com.example.fundamental1.Api.DetailResponse;
import com.example.fundamental1.databinding.ActivityDetailPageBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPage extends AppCompatActivity {
    private static final String TAG = DetailPage.class.getSimpleName();
    TextView txtName, txtUsername, txtFollowers, txtFollowing;
    ImageView imgProfile;
    private ActivityDetailPageBinding binding;

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.tab_text_1,
            R.string.tab_text_2
    };

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailPageBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_detail_page);

        txtName = findViewById(R.id.txt_name_detail);
        txtUsername = findViewById(R.id.txt_username_detail);
        txtFollowers = findViewById(R.id.txt_followers_detail);
        txtFollowing = findViewById(R.id.txt_following_detail);
        imgProfile = findViewById(R.id.img_profile_detail);
        progressBar = findViewById(R.id.progressBar3);

        getData(getIntent().getStringExtra("username"));

        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(this);
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        sectionPagerAdapter.setUsername(getIntent().getStringExtra("username"));
        viewPager.setAdapter(sectionPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        new TabLayoutMediator(tabs, viewPager,
                (tab, position) -> tab.setText(getResources().getString(TAB_TITLES[position]))
        ).attach();
        if(getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }
    }

    private void getData(String username) {
        progressBar.setVisibility(View.VISIBLE);
        Call<DetailResponse> client = ApiConfig.getApiService().getDetailUser(username);
        client.enqueue(new Callback<DetailResponse>() {
            @Override
            public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        txtName.setText(response.body().getName());
                        txtUsername.setText(username.toString());
                        txtFollowers.setText(response.body().getFollowers() + " Followers");
                        txtFollowing.setText(response.body().getFollowing() + " Following");

                        Glide.with(DetailPage.this).
                                load(getIntent().getStringExtra("avatar"))
                                .into(imgProfile);
                    }
                } else {
                    if (response.body() != null) {
                        progressBar.setVisibility(View.GONE);
                        Log.e(TAG, "onFailure: ");
                    }
                }
            }

            @Override
            public void onFailure(Call<DetailResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}