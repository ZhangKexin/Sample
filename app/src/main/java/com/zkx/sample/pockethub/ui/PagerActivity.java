package com.zkx.sample.pockethub.ui;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.zkx.sample.BaseActivity;

/**
 * Created by zkx on 16/5/17.
 */
public abstract class PagerActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    protected abstract FragmentProvider getProvider();

    public Fragment getFragment() {
        FragmentProvider fragmentProvider = getProvider();
        if (fragmentProvider != null) {
            return fragmentProvider.getSelected();
        } else {
            return null;
        }
    }

    @Override
    public void invalidateOptionsMenu() {
        super.invalidateOptionsMenu();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
