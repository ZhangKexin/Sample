package com.zkx.sample.android.apis.animation;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zkx.sample.R;

/**
 * Created by zkx on 16/5/10.
 */
public class AnimationCloning extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_cloning);
        LinearLayout container = (LinearLayout) findViewById(R.id.container);
        final MyAnimationView animView = new MyAnimationView(this);
        container.addView(animView);

        Button starter = (Button) findViewById(R.id.startButton);
        starter.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                animView.startAnimation();
            }
        });
    }

    public class MyAnimationView extends View implements ValueAnimator.AnimatorUpdateListener {

        public MyAnimationView(Context context) {
            super(context);
        }

        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {

        }
    }
}
