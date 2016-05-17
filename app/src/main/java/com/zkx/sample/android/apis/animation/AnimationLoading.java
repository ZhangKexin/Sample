package com.zkx.sample.android.apis.animation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zkx.sample.R;

import java.util.ArrayList;

/**
 * Created by zkx on 16/5/12.
 */
public class AnimationLoading extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_loading);

        LinearLayout container = (LinearLayout) findViewById(R.id.container);
        final MyAnimationView animationView = new MyAnimationView(this);
        container.addView(animationView);

        findViewById(R.id.startButtonAnimatorLoading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationView.startAnimation();
            }
        });
    }

    public class MyAnimationView extends View implements ValueAnimator.AnimatorUpdateListener {
        private static final float BALL_SIZE = 100f;
        public final ArrayList<ShapeHolder> balls = new ArrayList<>();
        AnimatorSet animation = null;

        public MyAnimationView(Context context) {
            super(context);
            addBall(50f, 50f);
            addBall(200f, 50f);
            addBall(350f, 50f);
            addBall(500f, 50f, Color.GREEN);
        }

        private void createAnimation() {
            Context appContext = AnimationLoading.this;

            if (animation == null) {
                ObjectAnimator anim1 = ObjectAnimator.ofFloat(balls.get(0), "y", 0f, getHeight() - balls.get(0).getHeight()).setDuration(5000);
                anim1.addUpdateListener(this);

//                ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.
//                        loadAnimator(appContext, R.animator.object_animator);
//                anim.addUpdateListener(this);
//                anim.setTarget(balls.get(0));

                ValueAnimator fader = (ValueAnimator) AnimatorInflater.
                        loadAnimator(appContext, R.animator.animator);
                fader.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        balls.get(1).setAlpha((Float) animation.getAnimatedValue());
                    }
                });

                AnimatorSet seq = (AnimatorSet) AnimatorInflater.
                        loadAnimator(appContext, R.animator.animator_set);
                seq.setTarget(balls.get(2));

                ObjectAnimator colorizer = (ObjectAnimator) AnimatorInflater.
                        loadAnimator(appContext, R.animator.color_animator);
                colorizer.setTarget(balls.get(3));

                animation = new AnimatorSet();
                animation.play(anim1);
//                ((AnimatorSet) animation).playTogether(anim, fader, seq, colorizer);
            }


        }

        public void startAnimation() {
            createAnimation();
            animation.start();
        }

        private ShapeHolder createBall(float x, float y) {
            OvalShape circle = new OvalShape();
            circle.resize(BALL_SIZE, BALL_SIZE);
            ShapeDrawable drawable = new ShapeDrawable(circle);
            ShapeHolder shapeHolder = new ShapeHolder(drawable);
            shapeHolder.setX(x);
            shapeHolder.setY(y);
            return shapeHolder;
        }

        private void addBall(float x, float y, int color) {
            OvalShape circle = new OvalShape();
            circle.resize(BALL_SIZE, BALL_SIZE);
            ShapeDrawable drawable = new ShapeDrawable(circle);
            ShapeHolder shapeHolder = new ShapeHolder(drawable);
            shapeHolder.setX(x);
            shapeHolder.setY(y);


//            ShapeHolder shapeHolder = createBall(x, y);
            shapeHolder.setColor(color);
            balls.add(shapeHolder);
        }

        private void addBall(float x, float y) {
            OvalShape circle = new OvalShape();
            circle.resize(BALL_SIZE, BALL_SIZE);
            ShapeDrawable drawable = new ShapeDrawable(circle);
            ShapeHolder shapeHolder = new ShapeHolder(drawable);
            shapeHolder.setX(x);
            shapeHolder.setY(y);


//            ShapeHolder shapeHolder = createBall(x, y);
            int red = (int) (100 + Math.random() * 155);
            int green = (int) (100 + Math.random() * 155);
            int blue = (int) (100 + Math.random() * 155);
            int color = 0xff000000 | red << 16 | green << 8 | blue;
//            Paint paint = shapeHolder.getShape().getPaint();
            Paint paint = drawable.getPaint();
            int darkColor = 0xff000000 | red / 4 << 16 | green / 4 << 8 | blue / 4;
            RadialGradient gradient = new RadialGradient(37.5f, 12.5f,
                    50f, color, darkColor, Shader.TileMode.CLAMP);
            paint.setShader(gradient);
            shapeHolder.setPaint(paint);
            balls.add(shapeHolder);
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            invalidate();
//            ShapeHolder ball = balls.get(0);
//            ball.setY((Float) animation.getAnimatedValue());
        }
    }
}
