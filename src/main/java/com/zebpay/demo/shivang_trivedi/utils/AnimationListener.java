package com.zebpay.demo.shivang_trivedi.utils;

import android.view.View;
import android.view.animation.Animation;

/**
 * Created by  Shivang Trivedi .
 */

public class AnimationListener implements Animation.AnimationListener {
    private View view;
    private Animation mAnimation;

    public AnimationListener(){

    }
    public AnimationListener(View view, Animation animation) {
        this.view = view;
        this.mAnimation = animation;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        view.startAnimation(mAnimation);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
