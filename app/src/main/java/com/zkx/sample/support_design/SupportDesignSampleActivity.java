package com.zkx.sample.support_design;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.View;

import com.zkx.sample.R;

/**
 * Created by zkx on 16/5/13.
 */
public class SupportDesignSampleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_design_sample);
        final TextInputLayout inputlayout = (TextInputLayout) findViewById(R.id.inputlayout);
        inputlayout.setError("ErrorText");

        final Snackbar snackbar = Snackbar.make(inputlayout, "Toast shows!!!", Snackbar.LENGTH_SHORT);
        snackbar.show();
        snackbar.setAction("Cancel", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.show();
                inputlayout.setError("hhhaaa");
            }
        });
    }
}
