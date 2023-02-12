package com.hasanbilgin.retrofitjava.model;

import com.google.gson.annotations.SerializedName;

public class CryptoModel {

    // @SerializedName("currency") de currency json formatındaki key aynı olmak zorundadır onun altındaki aynı olmasına gerekyok
    @SerializedName("currency")
    public String paraBirimi;

    @SerializedName("price")
    public  String para;

    @Override
    public String toString() {
        return "CryptoModel{" +
                "paraBirimi='" + paraBirimi + '\'' +
                ", para='" + para + '\'' +
                '}';
    }
}
