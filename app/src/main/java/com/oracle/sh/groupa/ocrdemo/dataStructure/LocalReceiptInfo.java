package com.oracle.sh.groupa.ocrdemo.dataStructure;

import android.os.Parcelable;

import com.oracle.sh.groupa.ocrdemo.webService.dataStructure.Receipt;

import java.io.Serializable;

/**
 * Created by Alfred on 15/1/8.
 */
public class LocalReceiptInfo implements Serializable{
    private String title;
    private String dateTime;
    private double price;
    private String picName;
    
    public LocalReceiptInfo() {
        
    }

    public LocalReceiptInfo(String title, String dateTime, double price, String picName) {
        this.title = title;
        this.dateTime = dateTime;
        this.price = price;
        this.picName = picName;
    }

    public LocalReceiptInfo(Receipt receipt) {
        this.title = receipt.getTitle();
        this.dateTime = receipt.getDate().toString();
        this.price = receipt.getAmount();
        this.picName = receipt.getImgUrl();
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

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getPicName() {

        return picName;
    }

    public Receipt toReceipt() {
        Receipt receipt = new Receipt();
        receipt.setDate(this.getDateTime());
        receipt.setAmount(this.getPrice());
        receipt.setImgUrl(this.getPicName());
        receipt.setTitle(this.getTitle());

        return receipt;
    }
}
