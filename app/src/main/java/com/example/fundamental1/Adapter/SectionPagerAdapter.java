package com.example.fundamental1.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fundamental1.Fragment.FollowersFragment;
import com.example.fundamental1.Fragment.FollowingFragment;

public class SectionPagerAdapter extends FragmentStateAdapter {
    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
    public SectionPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public SectionPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public SectionPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        FollowersFragment fragment = new FollowersFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(FollowersFragment.ARG_POSITION, position+1);
        bundle.putString(FollowersFragment.ARG_USERNAME, username);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
