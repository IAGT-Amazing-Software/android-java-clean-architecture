package com.innopro.android.sample.presentation.view.adapter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.innopro.android.sample.presentation.view.fragment.SliderFragment;

public class SliderAdapter extends FragmentStatePagerAdapter {

    private final Integer NUM_PAG = 5;

    public SliderAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        Fragment fragment = new SliderFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return NUM_PAG;
    }
}
