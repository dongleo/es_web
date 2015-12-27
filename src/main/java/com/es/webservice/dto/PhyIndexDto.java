package com.es.webservice.dto;

import java.io.Serializable;

/**
 * Created by dongYer on 15/11/27.
 */
public class PhyIndexDto implements Serializable {
    private static final long serialVersionUID = -7645205617250436227L;
    private Integer accountId;
    private Double weight;
    private Double fatRatio;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getFatRatio() {
        return fatRatio;
    }

    public void setFatRatio(Double fatRatio) {
        this.fatRatio = fatRatio;
    }
}
