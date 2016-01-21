package com.es.webservice.dto;

import java.io.Serializable;

/**
 * Created by dongYer on 15/11/27.
 */
public class PhyIndexDto implements Serializable {
    private static final long serialVersionUID = -7645205617250436227L;
    private Integer accountId;
    private Double weight;
    private Double bmi;
    private Double fatRatio;

    private Double score;
    private Double scoreRatio;

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

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    public Double getFatRatio() {
        return fatRatio;
    }

    public void setFatRatio(Double fatRatio) {
        this.fatRatio = fatRatio;
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
}