package com.oracle.sh.groupa.ocrdemo.dataStructure;

import com.oracle.sh.groupa.ocrdemo.webService.ExpenseManager;
import com.oracle.sh.groupa.ocrdemo.webService.dataStructure.Receipt;
import com.oracle.sh.groupa.ocrdemo.webService.dataStructure.Transaction;

import java.util.ArrayList;

/**
 * Created by Alfred on 15/1/8.
 */
public class LocalTransaction {
    private int id;                                 //when the instance created, the id will be set
                                                    //to 0. It will be modified when the server side
                                                    //create a transaction record. It will be used
                                                    //when query the whole information.
    private String dateTime;
    private ArrayList<LocalReceiptInfo> localReceiptInfos;
    private double totalPrice;
    private LocalUser applicant;
    private TransactionType type;
    private String expiredDate;
    private TransactionStatus status;
    private String justification;
    private LocalUser approver;

    public LocalTransaction() {

    }

    public LocalTransaction(String dateTime, double totalPrice,
                            LocalUser applicant, TransactionType type, String expiredDate,
                            TransactionStatus status, String justification, LocalUser approver) {
        this.id = 0;
        this.dateTime = dateTime;
        this.localReceiptInfos = new ArrayList<>();
        this.totalPrice = totalPrice;
        this.applicant = applicant;
        this.type = type;
        this.expiredDate = expiredDate;
        this.status = status;
        this.justification = justification;
        this.approver = approver;
        this.status = TransactionStatus.Pending;
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

    public ArrayList<LocalReceiptInfo> getLocalReceiptInfos() {
        return localReceiptInfos;
    }

    public void setLocalReceiptInfos(ArrayList<LocalReceiptInfo> localReceiptInfos) {
        this.localReceiptInfos = localReceiptInfos;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalUser getApplicant() {
        return applicant;
    }

    public void setApplicant(LocalUser applicant) {
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

    public LocalUser getApprover() {
        return approver;
    }

    public void setApprover(LocalUser approver) {
        this.approver = approver;
    }

    public void addReceiptinfo(LocalReceiptInfo localReceiptInfo) {
        this.localReceiptInfos.add(localReceiptInfo);
        this.totalPrice += localReceiptInfo.getPrice();

    }

    public Transaction toTransaction() {
        Transaction transaction = new Transaction();
        transaction.setApplicant(this.getApplicant().getName());
        transaction.setTotalAmount(this.getTotalPrice());

        Receipt[] localReceiptInfosArray = new Receipt[this.getLocalReceiptInfos().size()];

        for (int i = 0; i < this.getLocalReceiptInfos().size(); i++) {
            localReceiptInfosArray[i] = this.getLocalReceiptInfos().get(i).toReceipt();
            localReceiptInfosArray[i].setImgUrl(ExpenseManager.bytesStr2Image(localReceiptInfosArray[i].getImgUrl()));
        }

        transaction.setReceiptList(localReceiptInfosArray);

        transaction.setJustification(this.getJustification());
        transaction.setType(this.getType().toString());
        transaction.setDate(this.getDateTime());
        transaction.setStatus(this.getStatus().ordinal());
        transaction.setApprover(this.getApprover().getName());
        transaction.setExpireDate(this.getExpiredDate());
        transaction.setTotalAmount(this.getTotalPrice());

        return transaction;
    }
}
