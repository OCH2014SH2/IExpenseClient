package com.oracle.sh.groupa.ocrdemo.dataStructure;

/**
 * Created by Alfred on 15/1/8.
 */
public class LocalTransactionBriefInfo {
    private int id;                                 //when the instance created, the id will be set
                                                    //to 0. It will be modified when the server side
                                                    //create a transaction record. It will be used
                                                    //when query the whole information.
    private String dateTime;
    private int receiptAmount;
    private double totalPrice;
    private LocalUser applicant;
    private LocalTransaction.TransactionType type;
    private String expiredDate;
    private LocalTransaction.TransactionStatus status;
    private LocalUser approver;

    public LocalTransactionBriefInfo() {

    }

    public LocalTransactionBriefInfo(LocalTransaction localTransactionInfo) {
        this.dateTime = localTransactionInfo.getDateTime();
        this.totalPrice = localTransactionInfo.getTotalPrice();
        this.applicant = localTransactionInfo.getApplicant();
        this.type = localTransactionInfo.getType();
        this.expiredDate = localTransactionInfo.getExpiredDate();
        this.status = localTransactionInfo.getStatus();
        this.approver = localTransactionInfo.getApprover();
        this.receiptAmount = localTransactionInfo.getLocalReceiptInfos().size();
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

    public LocalUser getApplicant() {
        return applicant;
    }

    public LocalTransaction.TransactionType getType() {
        return type;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public LocalTransaction.TransactionStatus getStatus() {
        return status;
    }

    public LocalUser getApprover() {
        return approver;
    }
}
