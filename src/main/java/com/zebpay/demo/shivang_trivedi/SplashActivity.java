package com.zebpay.demo.shivang_trivedi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.zebpay.demo.shivang_trivedi.utils.AnimationUtils;


public class SplashActivity extends AppCompatActivity implements  AnimationUtils.AnimEndListener {
    private boolean isAnimComplete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setNoTitleFullScreen();
        setContentView(R.layout.activity_splash);
        setAnimation();

    }
    public void setNoTitleFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    private void setAnimation() {
        AnimationUtils animationUtils = new AnimationUtils(this);
        animationUtils.startAnimTranslate(findViewById(R.id.txtMainLogo));
        animationUtils.setAnimRotate(findViewById(R.id.imgMainLogo));
    }







    protected void goTo(final Class aClass) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent intent=new Intent(SplashActivity.this,aClass);
                startActivity(intent);
                finish();
            }
        }, getResources().getInteger(R.integer.splash_duration));
    }




    @Override
    public void onAnimationEnd() {
        goTo(TickerActivity.class);

    }




}