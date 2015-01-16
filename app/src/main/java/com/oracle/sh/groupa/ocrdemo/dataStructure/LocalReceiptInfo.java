package com.oracle.sh.groupa.ocrdemo.dataStructure;

import com.oracle.sh.groupa.ocrdemo.webService.dataStructure.Receipt;

import java.io.Serializable;

/**
 * Created by Alfred on 15/1/8.
 */
public class LocalReceiptInfo implements Serializable {

    private String title;
    private String dateTime;
    private double price;
    private String picPath;

    public LocalReceiptInfo() {

    }

    public LocalReceiptInfo(String title, String dateTime, double price, String picPath) {
        this.title = title;
        this.dateTime = dateTime;
        this.price = price;
        this.picPath = picPath;
    }

    public LocalReceiptInfo(Receipt receipt) {
        this.title = receipt.getTitle();
        this.dateTime = receipt.getDate().toString();
        this.price = receipt.getAmount();
        this.picPath = receipt.getImgUrl();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getPicPath() {

        return picPath;
    }

    public Receipt toReceipt() {
        Receipt receipt = new Receipt();
        receipt.setDate(this.getDateTime());
        receipt.setAmount(this.getPrice());
        receipt.setImgUrl(this.getPicPath());
        receipt.setTitle(this.getTitle());

        return receipt;
    }
}
