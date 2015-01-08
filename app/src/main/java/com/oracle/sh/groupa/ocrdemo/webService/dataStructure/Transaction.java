package com.oracle.sh.groupa.ocrdemo.webService.dataStructure;

import com.oracle.sh.groupa.ocrdemo.dataStructure.LocalReceiptInfo;
import com.oracle.sh.groupa.ocrdemo.dataStructure.LocalTransaction;
import com.oracle.sh.groupa.ocrdemo.dataStructure.LocalUser;

import java.util.ArrayList;

public class Transaction implements java.io.Serializable {

    private int id;
    private String applicant;
    private String date;
    private double totalAmount;
    private String type;
    private String expireDate;
    private int status;
    private String justification;
    private String approver;
    private Receipt[] receiptList;
    private String receiptsStr;

    public String getReceiptsStr() {
        return receiptsStr;
    }

    public void setReceiptsStr(String receiptsStr) {
        this.receiptsStr = receiptsStr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public Receipt[] getReceiptList() {
        return receiptList;
    }

    public void setReceiptList(Receipt[] receiptList) {
        this.receiptList = receiptList;
    }

    public LocalTransaction toLocalTransation() {
        LocalTransaction localTransaction = new LocalTransaction();
        localTransaction.setDateTime(this.getDate());
        LocalUser user = new LocalUser();
        user.setName(this.getApplicant());
        localTransaction.setApplicant(user);
        localTransaction.setDateTime(this.getDate());
        switch (this.getStatus()) {
            case 0:
                localTransaction.setStatus(LocalTransaction.TransactionStatus.Pending);
                break;
            case 1:
                localTransaction.setStatus(LocalTransaction.TransactionStatus.Approved);
                break;
            case 2:
                localTransaction.setStatus(LocalTransaction.TransactionStatus.Denied);
                break;
        }
        LocalUser user2 = new LocalUser();
        user2.setName(this.getApprover());
        localTransaction.setApprover(user2);
        localTransaction.setTotalPrice(this.getTotalAmount());
        ArrayList<LocalReceiptInfo> receiptInfo = new ArrayList<LocalReceiptInfo>();
        for (Receipt receipt : this.getReceiptList()) {
            receiptInfo.add(receipt.toLocalReceiptInfo());
        }
        localTransaction.setLocalReceiptInfos(receiptInfo);
        localTransaction.setJustification(this.getJustification());
        localTransaction.setExpiredDate(this.getExpireDate());
        switch (this.getType()) {
            case "Accommodation":
                localTransaction.setType(LocalTransaction.TransactionType.Accommodation);
                break;
            case "ClubCost":
                localTransaction.setType(LocalTransaction.TransactionType.ClubCost);
                break;
            case "Meals":
                localTransaction.setType(LocalTransaction.TransactionType.Meals);
                break;
            case "Social":
                localTransaction.setType(LocalTransaction.TransactionType.Social);
                break;
            case "Taxi":
                localTransaction.setType(LocalTransaction.TransactionType.Taxi);
                break;
            case "Training":
                localTransaction.setType(LocalTransaction.TransactionType.Training);
                break;
        }

        return localTransaction;

    }
}
