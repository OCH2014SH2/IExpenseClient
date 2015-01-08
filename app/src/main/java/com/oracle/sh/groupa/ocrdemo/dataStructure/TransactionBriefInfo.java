package com.oracle.sh.groupa.ocrdemo.dataStructure;

/**
 * Created by Alfred on 15/1/8.
 */
public class TransactionBriefInfo {
    private int id;                                 //when the instance created, the id will be set
                                                    //to 0. It will be modified when the server side
                                                    //create a transaction record. It will be used
                                                    //when query the whole information.
    private String dateTime;
    private int receiptAmount;
    private double totalPrice;
    private User applicant;
    private Transaction.TransactionType type;
    private String expiredDate;
    private Transaction.TransactionStatus status;
    private User approver;

    public TransactionBriefInfo() {

    }

    public TransactionBriefInfo(Transaction transactionInfo) {
        this.dateTime = transactionInfo.getDateTime();
        this.totalPrice = transactionInfo.getTotalPrice();
        this.applicant = transactionInfo.getApplicant();
        this.type = transactionInfo.getType();
        this.expiredDate = transactionInfo.getExpiredDate();
        this.status = transactionInfo.getStatus();
        this.approver = transactionInfo.getApprover();
        this.receiptAmount = transactionInfo.getReceiptInfos().size();
    }

    public int getId() {
        return id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public int getReceiptAmount() {
        return receiptAmount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public User getApplicant() {
        return applicant;
    }

    public Transaction.TransactionType getType() {
        return type;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public Transaction.TransactionStatus getStatus() {
        return status;
    }

    public User getApprover() {
        return approver;
    }
}
