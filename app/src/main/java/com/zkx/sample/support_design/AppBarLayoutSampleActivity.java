package com.zkx.sample.support_design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.zkx.sample.R;

import butterknife.ButterKnife;

/**
 * Created by zkx on 16/5/13.
 */
public class AppBarLayoutSampleActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_design_appbarlayout);
        ButterKnife.bind(this);
    }
}
