package com.zkx.sample.ui;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by zkx on 16/5/11.
 */
public abstract class PagerActivity extends DialogFragmentActivity implements ViewPager.OnPageChangeListener {

    private boolean menuCreated;

    protected abstract FragmentProvider getProvider();

    protected Fragment getFragment() {
        FragmentProvider fragmentProvider = getProvider();
        if (fragmentProvider != null) {
            return fragmentProvider.getSelected();
        } else {
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Fragment fragment = getFragment();
        if (fragment != null)
            fragment.onCreateOptionsMenu(menu, getMenuInflater());
        boolean created = super.onCreateOptionsMenu(menu);
        menuCreated = true;
        return created;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Fragment fragment = getFragment();
        if (fragment != null)
            fragment.onPrepareOptionsMenu(menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void invalidateOptionsMenu() {
        if (menuCreated)
            super.invalidateOptionsMenu();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fragment = getFragment();
        if (fragment != null) {
            fragment.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        invalidateOptionsMenu();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
