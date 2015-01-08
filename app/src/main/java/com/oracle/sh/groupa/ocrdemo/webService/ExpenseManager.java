package com.oracle.sh.groupa.ocrdemo.webService;

import com.oracle.sh.groupa.ocrdemo.dataStructure.LocalTransaction;
import com.oracle.sh.groupa.ocrdemo.webService.dataStructure.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alfred on 15/1/8.
 */
public class ExpenseManager {
    public static WebServiceAPI webServiceAPI;

    public ExpenseManager(){
        this.webServiceAPI = new WebServiceAPI();
    }

    public static boolean submitTransaction(LocalTransaction localTransactionInfo) {

        try {
            webServiceAPI.addTransaction(localTransactionInfo.toTransaction());
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static List<LocalTransaction> queryAllTransactStatus(String userId) {
        List<Transaction> transactionList = webServiceAPI.queryTransactionList(userId, -1);

        List<LocalTransaction> localTransactionsList = new ArrayList<LocalTransaction>();

        for (Transaction transaction : transactionList) {
            localTransactionsList.add(transaction.toLocalTransation());
        }
        return localTransactionsList;
    }

    public static int querySpecificTransactStatus(String userId, int status) {
        List<Transaction> transactionList = new ArrayList<Transaction>();
        try {
            transactionList = webServiceAPI.queryTransactionList(userId, status);
        } catch (Exception e) {
            return -1;
        }

        return transactionList.size();

    }

    public static int querySpecificNeedApprovedTransact(String userId, int status) {
        List<Transaction> transactionList = new ArrayList<Transaction>();
        try {
            transactionList = webServiceAPI.queryTransactionList(userId, status);
        } catch (Exception e) {
            return -1;
        }

        return transactionList.size();
    }
}
