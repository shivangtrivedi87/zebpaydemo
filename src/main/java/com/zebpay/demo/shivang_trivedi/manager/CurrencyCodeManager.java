package com.zebpay.demo.shivang_trivedi.manager;

import android.app.Activity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.zebpay.demo.shivang_trivedi.TickerActivity;
import com.zebpay.demo.shivang_trivedi.entity.CurrencyCodeEntity;
import com.zebpay.demo.shivang_trivedi.utils.Logger;
import com.zebpay.demo.shivang_trivedi.utils.ProgressDialogUtils;

import static com.zebpay.demo.shivang_trivedi.utils.ConstantsURL.URL_CURRENCYCODE;

/**
 * Created by shivang Trivedi .
 */

public class CurrencyCodeManager {

    private Activity mActivity;
    private ProgressDialogUtils progressDialogUtils;

    public CurrencyCodeManager(Activity mActivity) {
        this.mActivity = mActivity;
        progressDialogUtils=new ProgressDialogUtils(mActivity);
    }

    public void callApi() {
        progressDialogUtils.showProgressDialog();
        RequestQueue queue = Volley.newRequestQueue(mActivity);
        StringRequest postRequest = new CustomStringRequest(URL_CURRENCYCODE, new CustomResponseListener() {
            @Override
            public void onResponse(String response) {
                Logger.error("log_tag", "response " + response);
                progressDialogUtils.hideProgressDialog();
                Gson gson = new Gson();
                CurrencyCodeEntity headerEntity = gson.fromJson(response, CurrencyCodeEntity.class);
                ((TickerActivity) mActivity).OnWebResponse(headerEntity);

            }
        });
        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                120000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);
    }

    private class CustomResponseListener implements Response.Listener<String> {
        @Override
        public void onResponse(String response) {
            Logger.error("log_tag", "response " + response);
            progressDialogUtils.hideProgressDialog();
        }
    }

    private class CustomStringRequest extends StringRequest {
        CustomStringRequest(String url, Response.Listener<String> listener) {
            super(Request.Method.GET, url, listener, new CustomErrorListene());
        }


    }

    private class CustomErrorListene implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            progressDialogUtils.hideProgressDialog();
            ((TickerActivity) mActivity).OnWebResponse(null);
        }
    }

    public interface WebResponseListener {
        void OnWebResponse(Object loginEntity);

    }
}
