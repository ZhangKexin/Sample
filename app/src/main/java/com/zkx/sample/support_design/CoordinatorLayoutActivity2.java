package com.zkx.sample.support_design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.zkx.sample.BaseActivity;
import com.zkx.sample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by zkx on 16/5/17.
 */
public class CoordinatorLayoutActivity2 extends BaseActivity {

    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected int getContentView() {
        return R.layout.activity_support_design_coordinatorlayout_2;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabs.setTabMode(TabLayout.MODE_FIXED);

        List<String> titles = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Fragment fragment = new MyListFragment();
            fragmentList.add(fragment);
            titles.add("药仓" + 1);
            tabs.addTab(tabs.newTab().setText("药仓" + 1));
        }
        viewPager.setAdapter(new TabFragmentAdapter(getSupportFragmentManager(), fragmentList, titles));
        tabs.setupWithViewPager(viewPager);
    }

    public class TabFragmentAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> mFragments;
        private List<String> mTitles;

        public TabFragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
            super(fm);
            mFragments = fragments;
            mTitles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }
    }
}
