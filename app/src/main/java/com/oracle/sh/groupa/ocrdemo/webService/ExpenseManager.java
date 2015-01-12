package com.oracle.sh.groupa.ocrdemo.webService;

import com.oracle.sh.groupa.ocrdemo.dataStructure.LocalTransaction;
import com.oracle.sh.groupa.ocrdemo.webService.dataStructure.Receipt;
import com.oracle.sh.groupa.ocrdemo.webService.dataStructure.Transaction;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Decoder.BASE64Decoder;
import it.sauronsoftware.base64.Base64;

/**
 * Created by Alfred on 15/1/8.
 */
public class ExpenseManager {
    public static WebServiceAPI webServiceAPI;
    public static String STORE_DIR = "/mnt/sdcard/";

    public ExpenseManager() {
        this.webServiceAPI = new WebServiceAPI();
    }

    public static boolean submitTransaction(LocalTransaction localTransactionInfo) {

        try {
            Transaction transaction = localTransactionInfo.toTransaction();
            for (Receipt receipt : transaction.getReceiptList()) {
                String path = receipt.getImgUrl();

                receipt.setImgUrl(image2BytesStr(receipt.getImgUrl()));
            }

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

    public static String bytesStr2Image(String imgDataStr) {
        String path = null;
        FileOutputStream fout = null;

        if (imgDataStr == null || imgDataStr.length() == 0) {
            return null;
        }

        try {
            // Generate file name
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
            String formatDate = sdf.format(new Date());
            String fileName = formatDate + ".jpg";

            // Convert bytes string into image and store it
            byte[] data = new BASE64Decoder().decodeBuffer(imgDataStr);   //∂‘android¥´π˝¿¥µƒÕº∆¨◊÷∑˚¥ÆΩ¯––Ω‚¬Î
            File destDir = new File(STORE_DIR);
            if (!destDir.exists())
                destDir.mkdir();
            fout = new FileOutputStream(new File(destDir, fileName));   //±£¥ÊÕº∆¨
            fout.write(data);
            fout.flush();
            fout.close();
            path = STORE_DIR + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path;
    }

    public static String image2BytesStr(String filePath) {
        String bytesStr = null;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = fis.read(buffer)) >= 0) {
                baos.write(buffer, 0, count);
            }
            bytesStr = new String(Base64.encode(baos.toByteArray()));  //Ω¯––Base64±‡¬Î
            //hs.processPicture(uploadBuffer, new Receipt());
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytesStr;
    }


}
