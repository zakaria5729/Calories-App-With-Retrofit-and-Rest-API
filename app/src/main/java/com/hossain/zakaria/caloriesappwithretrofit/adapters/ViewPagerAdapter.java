package com.hossain.zakaria.caloriesappwithretrofit.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hossain.zakaria.caloriesappwithretrofit.fragments.FruitsFragment;
import com.hossain.zakaria.caloriesappwithretrofit.fragments.VegetablesFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fragment) {
        super(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FruitsFragment();

            case 1:
                return new VegetablesFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Fruits";

            case 1:
                return "Vegetables";

            default:
                return null;
        }
    }
}
