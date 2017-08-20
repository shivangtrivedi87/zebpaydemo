package com.zebpay.demo.shivang_trivedi.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zebpay.demo.shivang_trivedi.model.ActivityFeedModel;

import java.util.List;

/**
 * Created by shivang Trivedi .
 */



public class FeedEntity {

    @SerializedName("activityFeed")
    @Expose
    private List<ActivityFeedModel> activityFeed = null;
    @SerializedName("returncode")
    @Expose
    private Integer returncode;

    public List<ActivityFeedModel> getActivityFeed() {
        return activityFeed;
    }

    public void setActivityFeed(List<ActivityFeedModel> activityFeed) {
        this.activityFeed = activityFeed;
    }

    public Integer getReturncode() {
        return returncode;
    }

    public void setReturncode(Integer returncode) {
        this.returncode = returncode;
    }

}