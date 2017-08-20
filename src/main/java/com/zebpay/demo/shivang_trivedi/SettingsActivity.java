package com.zebpay.demo.shivang_trivedi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.zebpay.demo.shivang_trivedi.utils.PrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.edtSetVarianceByPercentage)
    EditText edtSetVarianceByPercentage;
    @BindView(R.id.edtSetVarianceByRuppe)
    EditText edtSetVarianceByRuppe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        initToolBar();


    }
    public void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_save, menu);
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
            case R.id.action_save:
                PrefUtils prefUtils=new PrefUtils(SettingsActivity.this);
                prefUtils.saveInPreference("BYPERCENTAGE",edtSetVarianceByPercentage.getText().toString());
                prefUtils.saveInPreference("BYRUPEE",edtSetVarianceByRuppe.getText().toString());
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
