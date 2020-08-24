package com.bitstech.generatexmlpost.model;


import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


@XmlRootElement
public class XMLResponse
{

    private String ipAddress;
    private String date;
    private double totAmount;
    private double totStudent;
    private String hashKey;
    private List<AccountDetails> accountDetails = new LinkedList<>();


    public List<AccountDetails> getAccountDetails() {
        return accountDetails;
    }

    @XmlElement
    public void setAccountDetails(List<AccountDetails> accountDetails) {
        this.accountDetails = accountDetails;
    }


    public String getIpAddress() {
        return ipAddress;
    }

    @XmlElement
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDate() {
        return date;
    }

    @XmlElement
    public void setDate(String date) {
        this.date = date;
    }

    public double getTotAmount() {
        return totAmount;
    }

    @XmlElement
    public void setTotAmount(double totAmount) {
        this.totAmount = totAmount;
    }

    public double getTotStudent() {
        return totStudent;
    }

    @XmlElement
    public void setTotStudent(double totStudent) {
        this.totStudent = totStudent;
    }

    public String getHashKey() {
        return hashKey;
    }

    @XmlElement
    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    @Override
    public String toString() {
        return "XMLResponse{" +
                "ipAddress='" + ipAddress + '\'' +
                ", date='" + date + '\'' +
                ", totAmount=" + totAmount +
                ", totStudent=" + totStudent +
                ", hashKey='" + hashKey + '\'' +
                ", accountDetails=" + accountDetails +
                '}';
    }
}
