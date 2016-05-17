package com.zkx.sample.pockethub.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ProgressBar;

import com.zkx.sample.R;

import butterknife.Bind;

/**
 * Created by zkx on 16/5/17.
 */
public abstract class DotPagerActivity<V extends PagerAdapter> extends PagerActivity {
    @Bind(R.id.vp_pages)
    ViewPager viewPager;
    @Bind(R.id.progress_bar)
    ProgressBar progressBar;
    @Bind(R.id.dot_page_indicator)
    DotPageIndicator dotPageIndicator;

    protected V adapter;

    protected abstract V createAdapter();

    private void createPager() {
        adapter = createAdapter();
        invalidateOptionsMenu();
        viewPager.setAdapter(adapter);
    }

    protected void configureDotPager() {
        if (adapter == null) {
            createPager();
            dotPageIndicator.setViewPager(viewPager);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.pager_with_dots;
    }

    @Override
    protected FragmentProvider getProvider() {
        return null;
    }

    public ViewPager getViewPager() {
        return viewPager;
    }
}
