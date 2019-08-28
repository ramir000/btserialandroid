package com.gg.reactionlight_0.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTag = new ArrayList<>();

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String tag) {
        mFragmentList.add(fragment);
        mFragmentTag.add(tag);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }


    public String getTag(int position) {
        if(position < mFragmentTag.size())
        return mFragmentTag.get(position);
        return "";
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
