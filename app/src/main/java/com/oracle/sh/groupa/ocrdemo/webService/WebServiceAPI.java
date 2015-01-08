package com.oracle.sh.groupa.ocrdemo.webService;

import com.google.gson.Gson;
import com.oracle.sh.groupa.ocrdemo.dataStructure.LocalTransaction;
import com.oracle.sh.groupa.ocrdemo.webService.dataStructure.QueryConfig;
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
    public static String endPoint = "http://172.31.13.53:8080/Hackathon/hackService?wsdl";

    public WebServiceAPI() {
        gson = new Gson();
    }
    
    public SoapObject execute (SoapObject rpc,String soapAction){
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
	public int addTransaction(LocalTransaction trans){
        // ���õķ������  
        String methodName = "addTransaction";  
        
        // SOAP Action  
        String soapAction = nameSpace + methodName; 
  
        // ָ��WebService������ռ�͵��õķ�����  
        SoapObject rpc = new SoapObject(nameSpace, methodName);  
  
        // ���������WebService�ӿ���Ҫ����Ĳ���  
        rpc.addProperty("arg0", trans);
  
        // ��ȡ���ص����  
        SoapObject object = execute(rpc,soapAction);  
        // ��ȡ���صĽ��  
        String result = object.getProperty(0).toString(); 
        return Integer.valueOf(result);
	}
	
	public com.oracle.sh.groupa.ocrdemo.webService.dataStructure.Transaction queryTransactionDetail(int transactionId){
        // ���õķ������  
        String methodName = "queryTransactionDetail";  
        
        // SOAP Action  
        String soapAction = nameSpace + methodName; 
  
        // ָ��WebService������ռ�͵��õķ�����  
        SoapObject rpc = new SoapObject(nameSpace, methodName);  
  
        // ���������WebService�ӿ���Ҫ����Ĳ���  
        rpc.addProperty("arg0", transactionId);
  
        // ��ȡ���ص����  
        SoapObject object = execute(rpc,soapAction);   
        
        // ��ȡ���صĽ��  
        Transaction result =gson.fromJson(object.getProperty(0).toString(), Transaction.class);

        return result;
	}
	
	public List<Transaction> queryTransactionList(QueryConfig queryConfig){
        // ���õķ������  
        String methodName = "queryTransactionList";  
        
        // SOAP Action  
        String soapAction = nameSpace + methodName; 
  
        // ָ��WebService������ռ�͵��õķ�����  
        SoapObject rpc = new SoapObject(nameSpace, methodName);  
  
        // ���������WebService�ӿ���Ҫ����Ĳ���  
        rpc.addProperty("arg0", queryConfig);
  
        // ��ȡ���ص����  
        SoapObject object = execute(rpc,soapAction);  
        
        // ��ȡ���صĽ��  
        List<Transaction> result = (List<Transaction>)(object.getProperty(0));

        return result;
	}
	
	
	public int processTransaction(int transactionId, int status){
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
        SoapObject object = execute(rpc,soapAction);  
        
        // ��ȡ���صĽ��  
        String result = object.getProperty(0).toString(); 
        return Integer.valueOf(result);
	}
	
	
	
	public int updateUser(User user){
        // ���õķ������  
        String methodName = "updateUser";  
        
        // SOAP Action  
        //String soapAction = nameSpace + methodName;
        String soapAction = null;
        // ָ��WebService������ռ�͵��õķ�����  
        SoapObject rpc = new SoapObject(nameSpace, methodName);  
  
        // ���������WebService�ӿ���Ҫ����Ĳ���  
        rpc.addProperty("arg0", user);
  
        // ��ȡ���ص����  
        SoapObject object = execute(rpc,soapAction);  
       
        // ��ȡ���صĽ��  
       String result = object.getProperty(0).toString(); 
       return Integer.valueOf(result);
	}

	
	public User queryUser(String empId){
        // ���õķ������  
        String methodName = "queryUser";  
        
        // SOAP Action  
        String soapAction = nameSpace + methodName; 
  
        // ָ��WebService������ռ�͵��õķ�����  
        SoapObject rpc = new SoapObject(nameSpace, methodName);  
  
        // ���������WebService�ӿ���Ҫ����Ĳ���  
        rpc.addProperty("arg0", empId);
  
        // ��ȡ���ص����  
       SoapObject object = execute(rpc,soapAction);  
        // ��ȡ���صĽ��  

        Gson gson = new Gson();
       User result = gson.fromJson(object.getProperty(0).toString(), User.class);
       return result;
	}
}
