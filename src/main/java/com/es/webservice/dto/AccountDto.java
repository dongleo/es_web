package com.es.webservice.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dongYer on 15/11/26.
 */
public class AccountDto implements Serializable {
    private static final long serialVersionUID = 4390790689763825900L;

    private Integer accountId;
    private String accountName;
    private String tel;
    private String gender;
    private Date birth;
    private Double waistline;
    private Integer height;
    private String password;
    private String passwordCfm;
    private String ip;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Double getWaistline() {
        return waistline;
    }

    public void setWaistline(Double waistline) {
        this.waistline = waistline;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordCfm() {
        return passwordCfm;
    }

    public void setPasswordCfm(String passwordCfm) {
        this.passwordCfm = passwordCfm;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
