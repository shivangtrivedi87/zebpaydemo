package com.zebpay.demo.shivang_trivedi;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.zebpay.demo.shivang_trivedi.adapter.FeedListAdapter;
import com.zebpay.demo.shivang_trivedi.entity.FeedEntity;
import com.zebpay.demo.shivang_trivedi.manager.FeedManager;
import com.zebpay.demo.shivang_trivedi.model.ActivityFeedModel;
import com.zebpay.demo.shivang_trivedi.utils.ConnectivityUtils;
import com.zebpay.demo.shivang_trivedi.utils.DialogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FeedManager.WebResponseListener {
    @BindView(R.id.rcvActivityFeed)
    RecyclerView rcvActivityFeed;
    @BindView(R.id.taskList_swipetorefresh)
    SwipeRefreshLayout taskListSwipetorefresh;
    @BindView(R.id.empty)
    TextView txtEmpty;

    private Activity mActivity;
    private DialogUtils dialogUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;
        initToolBar();
        setTitle(getString(R.string.title_activity_feed));
        ButterKnife.bind(this);
        rcvActivityFeed=(RecyclerView)findViewById(R.id.rcvActivityFeed) ;
        taskListSwipetorefresh=(SwipeRefreshLayout) findViewById(R.id.taskList_swipetorefresh);
        txtEmpty=(TextView) findViewById(R.id.empty);
        rcvActivityFeed.setLayoutManager(new LinearLayoutManager(mActivity));

        //rcvActivityFeed.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        rcvActivityFeed.setItemAnimator(new DefaultItemAnimator());
        dialogUtils = new DialogUtils(mActivity);
        getFeedList();
        taskListSwipetorefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getFeedList();
            }
        });


    }

    private void getFeedList() {
        if (new ConnectivityUtils(mActivity).getConnectivityStatus()) {
            FeedManager feedManager = new FeedManager(MainActivity.this);
            feedManager.callApi();
           /* CommonManager listManager = new CommonManager(mActivity, hasMap);
            listManager.HttpPOSTRequest(URLConstans.GETHNM, HNMEntity.class);*/
        } else {
            dialogUtils.okDialog(R.string.app_name, R.string.msg_no_internet);
        }
    }

    @Override
    public void OnWebResponse(Object object) {
        taskListSwipetorefresh.setRefreshing(false);
        FeedEntity feedEntity = (FeedEntity) object;
        if (feedEntity != null) {
             List<ActivityFeedModel> activityFeedModels=feedEntity.getActivityFeed();
            FeedListAdapter feedListAdapter = new FeedListAdapter(MainActivity.this, activityFeedModels);
            rcvActivityFeed.setAdapter(feedListAdapter);
        }
    }
    public void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
            case R.id.action_settings:

                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
