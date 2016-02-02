package com.example.marc.taxifarecomparateur;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Marc on 24/01/2016.
 */
public class PageAdapter extends FragmentPagerAdapter {

    List<Fragment> fragments;
    public PageAdapter(FragmentManager fm, List<Fragment> fragmentList){
        super(fm);
        this.fragments = fragmentList;

    }
    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Approximation of the Taxi Fare";
            case 1:
                return "Map";
        }

        return null;
    }
}
