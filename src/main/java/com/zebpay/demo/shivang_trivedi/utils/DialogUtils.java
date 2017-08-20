package com.zebpay.demo.shivang_trivedi.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.zebpay.demo.shivang_trivedi.R;


/**
 * Created by  Shivang Trivedi.
 */

public class DialogUtils {
    private final Activity mActivity;

    public DialogUtils(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void okDialog(int msg) {
        setDialog(R.string.app_name, mActivity.getString(msg));
    }

    public void okDialog(String msg) {
        setDialog(R.string.app_name, msg);
    }

    public void okDialog(int title, int msg) {
        if(!mActivity.isFinishing()){
            setDialog(title, mActivity.getString(msg));
        }
    }

    public void okDialog(int title, String msg) {
        setDialog(title, msg);
    }

    public void okDialog(int msg, Class aClass) {
        setDialog(R.string.app_name, mActivity.getString(msg), aClass);
    }

    public void okDialog(String msg, Class aClass) {
        setDialog(R.string.app_name, msg, aClass);
    }




    private void setDialog(int title, String msg) {
        AlertDialog.Builder builder = getBuilder();
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton(android.R.string.ok,
                getOnClickListener());
        showAlert(builder);
    }
    private void setDialog(int title, String msg, final Class aClass) {
        AlertDialog.Builder builder = getBuilder();
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton(android.R.string.ok,
                getOnClickListener(aClass));
        showAlert(builder);
    }




    private void showAlert(AlertDialog.Builder builder) {
        AlertDialog alert = builder.create();
        alert.show();
    }
    private AlertDialog.Builder getBuilder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                mActivity);
        builder.setCancelable(true);
        builder.setIcon(R.mipmap.ic_launcher);
        return builder;
    }
    private DialogInterface.OnClickListener getOnClickListener(final Class aClass) {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog,
                                int which) {
                mActivity.startActivity(new Intent(mActivity, aClass));
                mActivity.finish();
                dialog.dismiss();
            }
        };
    }

    private DialogInterface.OnClickListener getOnClickListener() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog,
                                int which) {
                dialog.dismiss();
            }
        };
    }

}
