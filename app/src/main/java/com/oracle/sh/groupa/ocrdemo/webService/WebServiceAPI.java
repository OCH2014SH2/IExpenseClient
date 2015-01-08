package com.oracle.sh.groupa.ocrdemo.webService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oracle.sh.groupa.ocrdemo.webService.dataStructure.Transaction;
import com.oracle.sh.groupa.ocrdemo.webService.dataStructure.User;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.List;

public class WebServiceAPI {
    public static Gson gson;
    // ����ռ�
    public static String nameSpace = "http://webService/";

    // EndPoint  
    public static String endPoint = "http://192.168.1.110:8080/Hackathon/hackService?wsdl";

    public WebServiceAPI() {
        gson = new Gson();
    }

    public SoapObject execute(SoapObject rpc, String soapAction) {
        // ��ɵ���WebService������SOAP������Ϣ,��ָ��SOAP�İ汾
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.bodyOut = rpc;
        // �����Ƿ���õ���dotNet������WebService  
        envelope.dotNet = false;
        // �ȼ���envelope.bodyOut = rpc;  
        envelope.setOutputSoapObject(rpc);

        HttpTransportSE transport = new HttpTransportSE(endPoint);
        try {
            // ����WebService  
            transport.call(soapAction, envelope);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ��ȡ���ص����  
        SoapObject object = (SoapObject) envelope.bodyIn;
        // ��ȡ���صĽ��  
        return object;
    }

    public int addTransaction(Transaction trans) {
        // ���õķ������  
        String methodName = "addTransaction";

        // SOAP Action  
        String soapAction = nameSpace + methodName;

        // ָ��WebService������ռ�͵��õķ�����  
        SoapObject rpc = new SoapObject(nameSpace, methodName);

        // ���������WebService�ӿ���Ҫ����Ĳ���
        ;
        rpc.addProperty("arg0", gson.toJson(trans, Transaction.class));

        // ��ȡ���ص����  
        SoapObject object = execute(rpc, soapAction);
        // ��ȡ���صĽ��  
        String result = object.getProperty(0).toString();
        return Integer.valueOf(result);
    }

    public Transaction queryTransactionDetail(int transactionId) {
        // ���õķ������  
        String methodName = "queryTransactionDetail";

        // SOAP Action  
        String soapAction = nameSpace + methodName;

        // ָ��WebService������ռ�͵��õķ�����  
        SoapObject rpc = new SoapObject(nameSpace, methodName);

        // ���������WebService�ӿ���Ҫ����Ĳ���  
        rpc.addProperty("arg0", transactionId);

        // ��ȡ���ص����  
        SoapObject object = execute(rpc, soapAction);

        // ��ȡ���صĽ��  
        Transaction result = gson.fromJson(object.getProperty(0).toString(), Transaction.class);

        return result;
    }

    public List<Transaction> queryTransactionList(String userId, int status) {
        // ���õķ������  
        String methodName = "queryTransactionList";

        // SOAP Action  
        String soapAction = nameSpace + methodName;

        // ָ��WebService������ռ�͵��õķ�����  
        SoapObject rpc = new SoapObject(nameSpace, methodName);

        // ���������WebService�ӿ���Ҫ����Ĳ���  
        rpc.addProperty("arg0", userId);
        rpc.addProperty("arg1", status);

        // ��ȡ���ص����  
        SoapObject object = execute(rpc, soapAction);

        // ��ȡ���صĽ��  
        List<Transaction> result = gson.fromJson(object.getProperty(0).toString(), new TypeToken<List<Transaction>>() {
        }.getType());

        return result;
    }

    public List<Transaction> queryWaitApprovedTransactionList(String userId, int status) {
        // ���õķ������
        String methodName = "queryWaitApprovedTransactionList";

        // SOAP Action
        String soapAction = nameSpace + methodName;

        // ָ��WebService������ռ�͵��õķ�����
        SoapObject rpc = new SoapObject(nameSpace, methodName);

        // ���������WebService�ӿ���Ҫ����Ĳ���
        rpc.addProperty("arg0", userId);
        rpc.addProperty("arg1", -1);

        // ��ȡ���ص����
        SoapObject object = execute(rpc, soapAction);

        // ��ȡ���صĽ��
        List<Transaction> result = gson.fromJson(object.getProperty(0).toString(), new TypeToken<List<Transaction>>() {
        }.getType());

        return result;
    }


    public int processTransaction(int transactionId, int status) {
        // ���õķ������  
        String methodName = "processTransaction";

        // SOAP Action  
        String soapAction = nameSpace + methodName;

        // ָ��WebService������ռ�͵��õķ�����  
        SoapObject rpc = new SoapObject(nameSpace, methodName);

        // ���������WebService�ӿ���Ҫ����Ĳ���  
        rpc.addProperty("arg0", transactionId);
        rpc.addProperty("arg1", status);

        // ��ȡ���ص����  
        SoapObject object = execute(rpc, soapAction);

        // ��ȡ���صĽ��  
        String result = object.getProperty(0).toString();
        return Integer.valueOf(result);
    }


    public int updateUser(User user) {
        // ���õķ������  
        String methodName = "updateUser";

        // SOAP Action  
        //String soapAction = nameSpace + methodName;
        String soapAction = null;
        // ָ��WebService������ռ�͵��õķ�����  
        SoapObject rpc = new SoapObject(nameSpace, methodName);

        // ���������WebService�ӿ���Ҫ����Ĳ���  
        rpc.addProperty("arg0", gson.toJson(user, User.class));

        // ��ȡ���ص����  
        SoapObject object = execute(rpc, soapAction);

        // ��ȡ���صĽ��  
        String result = object.getProperty(0).toString();
        return Integer.valueOf(result);
    }


    public User queryUser(String empId) {
        // ���õķ������  
        String methodName = "queryUser";

        // SOAP Action  
        String soapAction = nameSpace + methodName;

        // ָ��WebService������ռ�͵��õķ�����  
        SoapObject rpc = new SoapObject(nameSpace, methodName);

        // ���������WebService�ӿ���Ҫ����Ĳ���  
        rpc.addProperty("arg0", empId);

        // ��ȡ���ص����  
        SoapObject object = execute(rpc, soapAction);
        // ��ȡ���صĽ��  

        Gson gson = new Gson();
        User result = gson.fromJson(object.getProperty(0).toString(), User.class);
        return result;
    }
}
