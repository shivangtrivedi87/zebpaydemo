package com.zebpay.demo.shivang_trivedi.utils;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * Created by  Shivang Trivedi on 06-04-2017.
 */

public class ProgressDialogUtils {
    private  ProgressDialog pDialog;
    private  Activity mActivity;

    public ProgressDialogUtils(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public  void showProgressDialog() {
        pDialog = null;
        pDialog = new ProgressDialog(mActivity);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.getWindow().setBackgroundDrawableResource(android.R.color.white);
        //pDialog.setIndeterminateDrawable(ContextCompat.getDrawable(mActivity, R.drawable.rotateloader));


        if (pDialog!=null && (!pDialog.isShowing()))
            pDialog.show();
    }
    public  void showProgressDialog(final int loadingMSG) {
        pDialog = null;
        pDialog = new ProgressDialog(mActivity);
        pDialog.setMessage(mActivity.getString(loadingMSG));
        pDialog.setCancelable(false);
        if (pDialog!=null && (!pDialog.isShowing()))
            pDialog.show();
    }

    public  void hideProgressDialog() {
        try {
            if ((!mActivity.isFinishing()) && pDialog!=null && pDialog.isShowing()) {
                pDialog.hide();
                pDialog.cancel();
                pDialog.dismiss();
                pDialog=null;
            }
        } catch (Exception e){
            pDialog=null;
        }

    }
}
