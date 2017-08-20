package com.zebpay.demo.shivang_trivedi.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by  Shivang Trivedi .
 */

public class ConnectivityUtils {
    private final Activity mActivity;

    public ConnectivityUtils(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public  boolean getConnectivityStatus() {
        ConnectivityManager cm = (ConnectivityManager) mActivity
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return null != activeNetwork;
    }
}
