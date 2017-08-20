package com.zebpay.demo.shivang_trivedi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shivang Trivedi .
 */

public class ActivityFeedModel {

    @SerializedName("AcitvityType")
    @Expose
    private Integer acitvityType;
    @SerializedName("SourceImageUrl")
    @Expose
    private String sourceImageUrl;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Time")
    @Expose
    private String time;
    @SerializedName("RefNumber")
    @Expose
    private String refNumber;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("RefGuid")
    @Expose
    private String refGuid;
    @SerializedName("PaymentTypeId")
    @Expose
    private Object paymentTypeId;
    @SerializedName("PaymentTypeGuid")
    @Expose
    private Object paymentTypeGuid;

    public Integer getAcitvityType() {
        return acitvityType;
    }

    public void setAcitvityType(Integer acitvityType) {
        this.acitvityType = acitvityType;
    }

    public String getSourceImageUrl() {
        return sourceImageUrl;
    }

    public void setSourceImageUrl(String sourceImageUrl) {
        this.sourceImageUrl = sourceImageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRefGuid() {
        return refGuid;
    }

    public void setRefGuid(String refGuid) {
        this.refGuid = refGuid;
    }

    public Object getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Object paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public Object getPaymentTypeGuid() {
        return paymentTypeGuid;
    }

    public void setPaymentTypeGuid(Object paymentTypeGuid) {
        this.paymentTypeGuid = paymentTypeGuid;
    }

}