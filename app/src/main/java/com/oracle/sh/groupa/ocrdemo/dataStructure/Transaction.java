package com.oracle.sh.groupa.ocrdemo.dataStructure;

import java.util.ArrayList;

/**
 * Created by Alfred on 15/1/8.
 */
public class Transaction {
    private int id;                                 //when the instance created, the id will be set
                                                    //to 0. It will be modified when the server side
                                                    //create a transaction record. It will be used
                                                    //when query the whole information.
    private String dateTime;
    private ArrayList<ReceiptInfo> receiptInfos;
    private double totalPrice;
    private User applicant;
    private TransactionType type;
    private String expiredDate;
    private TransactionStatus status;
    private String justification;
    private User approver;

    public Transaction() {

    }

    public Transaction(String dateTime, double totalPrice,
                       User applicant, TransactionType type, String expiredDate,
                       TransactionStatus status, String justification, User approver) {
        this.id = 0;
        this.dateTime = dateTime;
        this.receiptInfos = new ArrayList<>();
        this.totalPrice = totalPrice;
        this.applicant = applicant;
        this.type = type;
        this.expiredDate = expiredDate;
        this.status = status;
        this.justification = justification;
        this.approver = approver;
    }

    public enum TransactionType {
        Taxi, Meals, Accommodation, Training, Social, ClubCost
    }

    public enum TransactionStatus {
        Pending, Approved, Denied
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public ArrayList<ReceiptInfo> getReceiptInfos() {
        return receiptInfos;
    }

    public void setReceiptInfos(ArrayList<ReceiptInfo> receiptInfos) {
        this.receiptInfos = receiptInfos;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }

    public void addReceiptinfo(ReceiptInfo receiptInfo) {
        this.receiptInfos.add(receiptInfo);
        this.totalPrice += receiptInfo.getPrice();

    }
}
