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
import com.zebpay.demo.shivang_trivedi.MainActivity;
import com.zebpay.demo.shivang_trivedi.entity.FeedEntity;
import com.zebpay.demo.shivang_trivedi.utils.Logger;
import com.zebpay.demo.shivang_trivedi.utils.ProgressDialogUtils;

import static com.zebpay.demo.shivang_trivedi.utils.ConstantsURL.URL_FEED;


/**
 * Created by shivang Trivedi .
 */

public class FeedManager {

    private Activity mActivity;
    private ProgressDialogUtils progressDialogUtils;

    public FeedManager(Activity mActivity) {
        this.mActivity = mActivity;
        progressDialogUtils=new ProgressDialogUtils(mActivity);
    }

    public void callApi() {
        progressDialogUtils.showProgressDialog();
        RequestQueue queue = Volley.newRequestQueue(mActivity);
        StringRequest postRequest = new CustomStringRequest(URL_FEED, new CustomResponseListener() {
            @Override
            public void onResponse(String response) {
                Logger.error("log_tag", "response " + response);
                progressDialogUtils.hideProgressDialog();
                Gson gson = new Gson();
                FeedEntity headerEntity = gson.fromJson(response, FeedEntity.class);
                ((MainActivity) mActivity).OnWebResponse(headerEntity);

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
            ((MainActivity) mActivity).OnWebResponse(null);
        }
    }

    public interface WebResponseListener {
        void OnWebResponse(Object loginEntity);

    }


}
