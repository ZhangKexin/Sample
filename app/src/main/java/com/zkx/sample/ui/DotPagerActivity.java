package com.zkx.sample.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;

import com.zkx.sample.R;
import com.zkx.sample.ui.view.DotPageIndicator;

/**
 * Created by zkx on 16/5/11.
 */
public class DotPagerActivity<V extends PagerAdapter> extends PagerActivity {
    private ViewPager pager;
    protected DotPageIndicator dotPageIndicator;
    protected V adapter;

    @Override
    protected FragmentProvider getProvider() {
        return null;
    }

    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
    }

    protected V createAdapter() {
        return null;
    }

    ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        if(toolbar != null) {
//            setSupportActionBar(toolbar);
// On Lollipop, the action bar shadow is provided by default, so have to remove it explicitly
//            getSupportActionBar().setElevation(0);
//        }
        pager = (ViewPager) findViewById(R.id.vp_pages);
        pager.addOnPageChangeListener(this);
        dotPageIndicator = (DotPageIndicator) findViewById(R.id.dot_page_indicator);
    }

    public int getContentView() {
        return R.layout.pager_with_dots;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pager.removeOnPageChangeListener(this);
    }

    private void createPager() {
        adapter = createAdapter();
        invalidateOptionsMenu();
        pager.setAdapter(adapter);
    }

    /**
     * Creates the pager and passes it to the {@link DotPageIndicator}
     */
    protected void configureDotPager() {
        if (adapter == null) {
            createPager();
            dotPageIndicator.setViewPager(pager);
        }
    }
}
