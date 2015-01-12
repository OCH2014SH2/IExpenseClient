package com.oracle.sh.groupa.ocrdemo.dataStructure;

import com.oracle.sh.groupa.ocrdemo.webService.dataStructure.User;

/**
 * Created by Alfred on 15/1/8.
 */
public class LocalUser {
    private int empId;
    private String name;
    private int managerId;
    private String managerName;
    private String contactInfo;
    private String costCenter;
    private String bankAccount;
    private String email;
    private String password;
    private String bankName;

    public LocalUser() {

    }

    public LocalUser(int empId, String name, int managerId, String managerName, String contactInfo, String costCenter, String bankAccount, String email, String bankName) {
        this.empId = empId;
        this.name = name;
        this.managerId = managerId;
        this.managerName = managerName;
        this.contactInfo = contactInfo;
        this.costCenter = costCenter;
        this.bankAccount = bankAccount;
        this.email = email;
        this.bankName = bankName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public User toUser() {
        User user = new User();
        user.setEmployeId(String.valueOf(this.getEmpId()));
        user.setBankAccount(this.getBankAccount());
        user.setBankName(this.getBankName());
        user.setCostCenter(this.getCostCenter());
        user.setEmail(this.getEmail());
        user.setManagerId(this.getManagerName());
        user.setPassword(this.getPassword());
        user.setName(this.getName());
        user.setContact(this.getContactInfo());

        return user;
    }
}
