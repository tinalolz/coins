package com.domain;

public enum Bpi {
    USD("美元"),
    GBP("英鎊"),
    EUR("歐元");

    public final String value;

    Bpi(String value) {
        this.value = value;
    }

//    public String getValue() {
//        return this.value;
//    }
}
