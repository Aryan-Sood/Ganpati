package com.abhijeet.ganpatiapp.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.abhijeet.ganpatiapp.fragments.AartiFragment;
import com.abhijeet.ganpatiapp.fragments.KundaliFragment;
import com.abhijeet.ganpatiapp.fragments.PujaListFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new AartiFragment();
        } else if (position == 1) {
            return new PujaListFragment();
        } else {
            return new KundaliFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }


//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return super.getPageTitle(position);
//    }
}
