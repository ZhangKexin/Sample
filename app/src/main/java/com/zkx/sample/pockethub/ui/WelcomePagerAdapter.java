package com.zkx.sample.pockethub.ui;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zkx on 16/5/17.
 */
public class WelcomePagerAdapter extends PagerAdapter {

//    private final String[] info;
    private final String[] titles;
//    private final int[] images;
    private int view;

    public WelcomePagerAdapter(int layoutId, String[] titles) {
        view = layoutId;
//        this.images = images;
//        this.info = info;
        this.titles = titles;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
