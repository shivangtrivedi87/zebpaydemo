package com.zebpay.demo.shivang_trivedi.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shivang Trivedi .
 */
public class CurrencyCodeEntity {

    @SerializedName("market")
    @Expose
    private Double market;
    @SerializedName("buy")
    @Expose
    private Double buy;
    @SerializedName("sell")
    @Expose
    private Double sell;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("volume")
    @Expose
    private Double volume;

    public Double getMarket() {
        return market;
    }

    public void setMarket(Double market) {
        this.market = market;
    }

    public Double getBuy() {
        return buy;
    }

    public void setBuy(Double buy) {
        this.buy = buy;
    }

    public Double getSell() {
        return sell;
    }

    public void setSell(Double sell) {
        this.sell = sell;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

}