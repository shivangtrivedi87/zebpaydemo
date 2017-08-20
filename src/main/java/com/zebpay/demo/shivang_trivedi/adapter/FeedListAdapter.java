package com.zebpay.demo.shivang_trivedi.adapter;

/**
 * Created by vcollabera-st on 05-05-2016.
 */

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.zebpay.demo.shivang_trivedi.R;
import com.zebpay.demo.shivang_trivedi.model.ActivityFeedModel;
import com.zebpay.demo.shivang_trivedi.utils.VolleyImageRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.TaskListHolder> {

    private final List<ActivityFeedModel> modelTasks;
    private final Activity mContext;

    private boolean isCreated;
    private ImageLoader imageLoader;

    public FeedListAdapter(Activity context, List<ActivityFeedModel> modelTasks) {
        this.modelTasks = new ArrayList<>(modelTasks);
        this.mContext = context;
    }

    @Override
    public TaskListHolder onCreateViewHolder(ViewGroup viewGroup, final int position) {
        //View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_feed, null);

        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_feed, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rootView.setLayoutParams(lp);

        return new TaskListHolder(rootView);
    }

    @Override
    public void onBindViewHolder(final TaskListHolder taskListHolder, int position) {

        taskListHolder.txtDesc.setText(modelTasks.get(position).getDescription().trim());
        taskListHolder.txtTime.setText(modelTasks.get(position).getTime().trim());
        taskListHolder.txtTitle.setText(modelTasks.get(position).getTitle().trim());
        loadImage(modelTasks.get(position).getSourceImageUrl(),taskListHolder.imgFeedUser);
    }


    @Override
    public int getItemCount() {
        return modelTasks.size();
    }





    class TaskListHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgFeedUser)
        NetworkImageView imgFeedUser;
        @BindView(R.id.txtTitle)
        TextView txtTitle;
        @BindView(R.id.txtDesc)
        TextView txtDesc;
        @BindView(R.id.txtTime)
        TextView txtTime;
        /*final MenuItem menuItemEDit;*/


        public TaskListHolder(View convertView) {
            super(convertView);
            ButterKnife.bind(this, convertView);

        }

    }
    private void loadImage(String url,NetworkImageView imgFeedUser) {
        if (url != null && url.length() > 0) {
            imageLoader = VolleyImageRequest.getInstance(mContext)
                    .getImageLoader();
            imageLoader.get(url, ImageLoader.getImageListener(imgFeedUser,
                    R.drawable.ic_account_circle_black_48dp, android.R.drawable
                            .ic_dialog_alert));
            imgFeedUser.setImageUrl(url, imageLoader);
        }
    }

}
