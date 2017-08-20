package com.zebpay.demo.shivang_trivedi.utils;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;

import com.zebpay.demo.shivang_trivedi.R;
import com.zebpay.demo.shivang_trivedi.SplashActivity;

/**
 * Created by  Shivang Trivedi.
 */

public class AnimationUtils {
    private Activity mActivity;
    private AnimEndListener animEndListener;

    public AnimationUtils(Activity mActivity) {
        this.mActivity = mActivity;
        animEndListener= (AnimEndListener) mActivity;
    }

    public void startAnimTranslate(View view) {
        Animation anim = android.view.animation.AnimationUtils.loadAnimation(mActivity, R.anim.translate_from);
        anim.reset();
        view.clearAnimation();
        view.startAnimation(anim);
    }

    public void setAnimRotate(View view) {
        final Animation animRotate = android.view.animation.AnimationUtils.loadAnimation(mActivity, R.anim.rotate);
        final Animation animAntiRotate = android.view.animation.AnimationUtils.loadAnimation(mActivity, R.anim.antirotate);
        final Animation animFadeOut = android.view.animation.AnimationUtils.loadAnimation(mActivity, R.anim.abc_fade_out);

        view.startAnimation(animAntiRotate);
        animAntiRotate.setAnimationListener(new AnimationListener(view, animRotate) {
        });
        animRotate.setAnimationListener(new AnimationListener(view, animFadeOut) {
        });
        animFadeOut.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                if (mActivity instanceof SplashActivity) {
                    animEndListener.onAnimationEnd();
                }
            }
        });
    }

    public interface AnimEndListener {
        void onAnimationEnd();
    }
}
