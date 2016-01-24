package com.es.webservice.model;

import javax.persistence.*;

/**
 * Created by dongYer on 16/1/24.
 */
@Entity
@Table(name = "T_PHY_IDX")
public class PhyIndexHistory {
    private Integer accountId;
    private Double weight;
    private Double bmi;
    private Double fatRatio;
    private String submitTime;

    @Column(name = "ACCOUNT_ID")
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    @Column(name = "WEIGHT")
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Column(name = "BMI")
    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    @Column(name = "FAT_RATIO")
    public Double getFatRatio() {
        return fatRatio;
    }

    public void setFatRatio(Double fatRatio) {
        this.fatRatio = fatRatio;
    }

    @Id
    @Column(name = "CREATE_TIME")
    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }
}
