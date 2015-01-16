package com.oracle.sh.groupa.ocrdemo.ocr;

/**
 * Created by lliyu on 1/7/2015.
 */
public class ReceiptDataInfo {

    public static final String FAPIAO_TITLE = "名称";
    public static final String FAPIAO_PRICE = "元";
    public static final String FAPIAO_DATE = "日期";

    private String name;
    private String price;
    private String date;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {

        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }
}
