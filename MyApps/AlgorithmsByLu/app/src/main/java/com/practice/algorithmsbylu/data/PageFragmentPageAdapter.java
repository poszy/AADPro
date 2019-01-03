package com.practice.algorithmsbylu.data;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.practice.algorithmsbylu.Fragments.FragmentBeginner;

public class PageFragmentPageAdapter extends FragmentPagerAdapter {

    private static  final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"Beginner", "Intermediate", "Advanced"};
    private Context context;

    public PageFragmentPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentBeginner.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }


}
