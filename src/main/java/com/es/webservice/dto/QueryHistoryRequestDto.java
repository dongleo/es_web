package com.es.webservice.dto;

import java.util.Date;

/**
 * Created by dongYer on 16/1/23.
 */
public class QueryHistoryRequestDto {
    private Integer accountId;
    private Date startDate;
    private Date endDate;
    private String mode;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
