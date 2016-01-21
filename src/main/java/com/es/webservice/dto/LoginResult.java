package com.es.webservice.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by dongYer on 15/12/5.
 */
public class LoginResult {

    private Integer accountId;
    private String accountName;
    private String tel;
    private String gender;
    private Date birth;
    private Double waistline;
    private Double hipline;
    private Integer height;
    private Double score;
    private Double scoreRatio;

    private String token;

    private PhyIndexDto phyIdx;

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

    public Double getHipline() {
        return hipline;
    }

    public void setHipline(Double hipline) {
        this.hipline = hipline;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getScoreRatio() {
        return scoreRatio;
    }

    public void setScoreRatio(Double scoreRatio) {
        this.scoreRatio = scoreRatio;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public PhyIndexDto getPhyIdx() {
        return phyIdx;
    }

    public void setPhyIdx(PhyIndexDto phyIdx) {
        this.phyIdx = phyIdx;
    }
}
