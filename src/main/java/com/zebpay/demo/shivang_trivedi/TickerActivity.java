package com.zebpay.demo.shivang_trivedi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.zebpay.demo.shivang_trivedi.entity.CurrencyCodeEntity;
import com.zebpay.demo.shivang_trivedi.manager.CurrencyCodeManager;
import com.zebpay.demo.shivang_trivedi.utils.ConnectivityUtils;
import com.zebpay.demo.shivang_trivedi.utils.DialogUtils;
import com.zebpay.demo.shivang_trivedi.utils.PrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TickerActivity extends AppCompatActivity implements CurrencyCodeManager.WebResponseListener {


    @BindView(R.id.txtBuyTicker)
    TextView txtBuyTicker;
    @BindView(R.id.txtCellTicker)
    TextView txtCellTicker;
    @BindView(R.id.btnFeed)
    Button btnFeed;
    private Activity mActivity;
    private DialogUtils dialogUtils;
    private int REPEATE = 5000 * 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticker);
        mActivity = this;
        initToolBar();
        setTitle(getString(R.string.title_ticker));
        ButterKnife.bind(this);

        dialogUtils = new DialogUtils(mActivity);
        callApiTicker();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                callApiTicker();
                handler.postDelayed(this, REPEATE);
            }
        }, REPEATE);

    }

    private void callApiTicker() {
        if (new ConnectivityUtils(mActivity).getConnectivityStatus()) {
            CurrencyCodeManager currencyCodeManager = new CurrencyCodeManager(TickerActivity.this);
            currencyCodeManager.callApi();
           /* CommonManager listManager = new CommonManager(mActivity, hasMap);
            listManager.HttpPOSTRequest(URLConstans.GETHNM, HNMEntity.class);*/
        } else {
            dialogUtils.okDialog(R.string.app_name, R.string.msg_no_internet);
        }
    }

    @Override
    public void OnWebResponse(Object loginEntity) {
        CurrencyCodeEntity currencyCodeEntity = (CurrencyCodeEntity) loginEntity;

        if (currencyCodeEntity != null) {
            txtBuyTicker.setText(String.format("%s", currencyCodeEntity.getBuy()));
            txtCellTicker.setText(String.format("%s", currencyCodeEntity.getSell()));
            PrefUtils prefUtils=new PrefUtils(mActivity);
            if(prefUtils.retrieveFromPref("MarketValue").length()>0){
                double oldMarketVal=Double.parseDouble(prefUtils.retrieveFromPref("MarketValue"));
                double newByPercentage=currencyCodeEntity.getMarket();
                double difference=(newByPercentage-oldMarketVal);
                double percentage=(difference*100)/oldMarketVal;
                double savedBypercentage= Double.parseDouble(prefUtils.retrieveFromPref("BYPERCENTAGE"));
                double savedByRupee=Double.parseDouble( prefUtils.retrieveFromPref("BYRUPEE"));

                if (percentage>=savedBypercentage){
                    DialogUtils dialogUtils=new DialogUtils(mActivity);
                    dialogUtils.okDialog("Market up by "+percentage+"%");
                }

                if(difference >= savedByRupee){
                    DialogUtils dialogUtils=new DialogUtils(mActivity);
                    dialogUtils.okDialog("Market up by "+percentage+" Rupees");
                }

                if(difference<=savedByRupee){
                    DialogUtils dialogUtils=new DialogUtils(mActivity);
                    dialogUtils.okDialog("Market down by "+percentage+" Rupees");
                }
            }



        } else {
            dialogUtils.okDialog("Unexpected Response");
        }
    }

    public void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_refresh, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_refresh:
                callApiTicker();
                break;
            case R.id.action_settings:
                startActivity(new Intent(mActivity,SettingsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnFeed)
    public void onViewClicked() {
        startActivity(new Intent(mActivity,MainActivity.class));
    }
}
