package com.bitstech.generatexmlpost.model;

import lombok.Data;

@Data
public class AccountDetails {

    private int instId;
    private String instName;
    private String accountNo;
    private String branchName;
    private String ifscCode;
    private String bankName;
    private int totStudent;
    private double totAmount;

    public  AccountDetails(){}

    public AccountDetails(int instId, String instName, String accountNo, String branchName, String ifscCode, String bankName, int totStudent, double totAmount) {
        this.instId = instId;
        this.instName = instName;
        this.accountNo = accountNo;
        this.branchName = branchName;
        this.ifscCode = ifscCode;
        this.bankName = bankName;
        this.totStudent = totStudent;
        this.totAmount = totAmount;
    }
}
