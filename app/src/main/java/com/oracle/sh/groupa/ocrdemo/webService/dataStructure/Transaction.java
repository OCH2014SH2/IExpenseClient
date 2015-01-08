
package service.webservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>transaction complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="transaction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="approverId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="empId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="expireDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="justification" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiptList" type="{http://webService/}receipt" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transaction", propOrder = {
    "approverId",
    "empId",
    "expireDate",
    "id",
    "justification",
    "receiptList",
    "status",
    "time",
    "totalAmount",
    "type"
})
public class Transaction {

    protected int approverId;
    protected int empId;
    protected String expireDate;
    protected int id;
    protected String justification;
    @XmlElement(nillable = true)
    protected List<Receipt> receiptList;
    protected int status;
    protected String time;
    protected double totalAmount;
    protected String type;

    /**
     * 获取approverId属性的值。
     * 
     */
    public int getApproverId() {
        return approverId;
    }

    /**
     * 设置approverId属性的值。
     * 
     */
    public void setApproverId(int value) {
        this.approverId = value;
    }

    /**
     * 获取empId属性的值。
     * 
     */
    public int getEmpId() {
        return empId;
    }

    /**
     * 设置empId属性的值。
     * 
     */
    public void setEmpId(int value) {
        this.empId = value;
    }

    /**
     * 获取expireDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpireDate() {
        return expireDate;
    }

    /**
     * 设置expireDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpireDate(String value) {
        this.expireDate = value;
    }

    /**
     * 获取id属性的值。
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * 获取justification属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJustification() {
        return justification;
    }

    /**
     * 设置justification属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJustification(String value) {
        this.justification = value;
    }

    /**
     * Gets the value of the receiptList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the receiptList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReceiptList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Receipt }
     * 
     * 
     */
    public List<Receipt> getReceiptList() {
        if (receiptList == null) {
            receiptList = new ArrayList<Receipt>();
        }
        return this.receiptList;
    }

    /**
     * 获取status属性的值。
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * 设置status属性的值。
     * 
     */
    public void setStatus(int value) {
        this.status = value;
    }

    /**
     * 获取time属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置time属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTime(String value) {
        this.time = value;
    }

    /**
     * 获取totalAmount属性的值。
     * 
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置totalAmount属性的值。
     * 
     */
    public void setTotalAmount(double value) {
        this.totalAmount = value;
    }

    /**
     * 获取type属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
