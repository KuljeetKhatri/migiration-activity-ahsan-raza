package com.zindigi.account_migration.dto;

import java.math.BigDecimal;
import java.util.Date;

public class RiskProfileScoreAndRatingResponse {

    private BigDecimal score;
    private String rating;
    private Date lastKycDate;
    private Date crpDate;
    private Date nextCrpDate;


    public Date getCrpDate() {
        return crpDate;
    }

    public void setCrpDate(Date crpDate) {
        this.crpDate = crpDate;
    }

    public Date getNextCrpDate() {
        return nextCrpDate;
    }

    public void setNextCrpDate(Date nextCrpDate) {
        this.nextCrpDate = nextCrpDate;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Date getLastKycDate() {
        return lastKycDate;
    }

    public void setLastKycDate(Date lastKycDate) {
        this.lastKycDate = lastKycDate;
    }
}
