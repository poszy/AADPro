package com.practice.algorithmsbylu.data;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.practice.algorithmsbylu.Fragments.FragmentAdvanced;
import com.practice.algorithmsbylu.Fragments.FragmentBeginner;
import com.practice.algorithmsbylu.Fragments.FragmentIntermediate;

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

           switch (position) {
               case 0: // Fragment # 0 - This will show FirstFragment
                   FragmentBeginner FragmentOne = new FragmentBeginner();
                   return FragmentOne;
               case 1: // Fragment # 0 - This will show FirstFragment different title
                   FragmentIntermediate FragmentTwo = new FragmentIntermediate();
                   return FragmentTwo;
               case 2: // Fragment # 1 - This will show SecondFragment
                   FragmentAdvanced FragmentThree = new FragmentAdvanced();
                   return FragmentThree;
               default:
                   return null;
           }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}
