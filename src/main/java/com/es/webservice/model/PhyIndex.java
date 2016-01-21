package com.es.webservice.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by dongYer on 15/11/25.
 */
@Entity
@Table(name = "T_PHY_IDX")
public class PhyIndex implements Serializable {
    private static final long serialVersionUID = -8693897638031373249L;

    private Integer id;
    private Integer accountId;
    private Integer age;
    private Double waistline;
    private Double hipline;
    private Integer height;
    private Double weight;
    private Double bmi;
    private Double fatRatio;
    private Double score;
    private Double scoreRatio;
    private Date submitTime;
    private Integer isValid;
    private Date updateTime;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "ACCOUNT_ID")
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    @Column(name = "AGE")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "WAISTLINE")
    public Double getWaistline() {
        return waistline;
    }

    public void setWaistline(Double waistline) {
        this.waistline = waistline;
    }

    @Column(name = "HIPLINE")
    public Double getHipline() {
        return hipline;
    }

    public void setHipline(Double hipline) {
        this.hipline = hipline;
    }

    @Column(name = "HEIGHT")
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
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

    @Column(name = "SCORE")
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Column(name = "SCORE_RATIO")
    public Double getScoreRatio() {
        return scoreRatio;
    }

    public void setScoreRatio(Double scoreRatio) {
        this.scoreRatio = scoreRatio;
    }

    @Column(name = "SUBMIT_TIME")
    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    @Column(name = "IS_VALID")
    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    @Column(name = "UPDATE_TIME")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
