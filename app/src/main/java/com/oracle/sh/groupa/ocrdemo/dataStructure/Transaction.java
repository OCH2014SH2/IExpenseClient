package com.oracle.sh.groupa.ocrdemo.dataStructure;

import java.util.ArrayList;

/**
 * Created by Alfred on 15/1/8.
 */
public class Transaction {
    private String dateTime;
    private ArrayList<ReceiptInfo> receiptInfos;
    private double totalPrice;
    private User applicant;
    private TransactionType transactionType;
    private String expirData;

    public enum TransactionType{

    }
    
}
