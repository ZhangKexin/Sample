package com.zkx.sample.welcome;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.zkx.sample.R;
import com.zkx.sample.ui.DotPagerActivity;

/**
 * Created by zkx on 16/5/11.
 */
public class WelcomeActivity extends DotPagerActivity {
    private int[] colors;
    @ColorInt
    int accentsColorDark;

    private View divider;
    private Button doneBtn;
    private Button skipBtn;
    private ImageButton nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureDotPager();
        loadColors();
    }

    @Override
    public int getContentView() {
        return R.layout.welcome_activity;
    }

    @Override
    protected PagerAdapter createAdapter() {
        return new WelcomePagerAdapter(R.layout.welcome_pager_item, new int[]{R.drawable.waving_android, R.drawable.welcome_app_icon, R.drawable.thumb_up}, getResources().getStringArray(R.array.welcome_texts), getResources().getStringArray(R.array.welcome_titles));
    }

    private void loadColors() {
        TypedArray ta = getResources().obtainTypedArray(R.array.welcome_screen_colors);
        colors = new int[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            colors[i] = ta.getColor(i, 0);
        }
        ta.recycle();
        accentsColorDark = ContextCompat.getColor(this, R.color.primary);
//        accentsColorDark = ContextCompat.get
    }
}
