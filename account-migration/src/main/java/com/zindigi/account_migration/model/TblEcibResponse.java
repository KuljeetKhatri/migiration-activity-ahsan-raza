package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_ECIB_RESPONSE database table.
 */
@Entity
@Table(name = "TBL_ECIB_RESPONSE")
@NamedQuery(name = "TblEcibResponse.findAll", query = "SELECT t FROM TblEcibResponse t")
public class TblEcibResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TBL_ECIB_RESPONSE_GENERATOR", sequenceName = "TBL_ECIB_RESPONSE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_ECIB_RESPONSE_GENERATOR")
    @Column(name = "ECIB_RESPONSE_ID")
    private long ecibResponseId;

    @Column(name = "ACTIVE_ACCOUNTS")
    private BigDecimal activeAccounts;

    private String city;

    private String cnic;

    @Temporal(TemporalType.DATE)
    private Date createdate;

    private BigDecimal createuser;

    private String dob;

    @Column(name = "MESSAGE")
    private String message;

    private String messagecode;

    private String name;

    @Column(name = "PLUS_120")
    private String plus120;

    @Column(name = "PLUS_150")
    private String plus150;

    @Column(name = "PLUS_180")
    private String plus180;

    @Column(name = "PLUS_30")
    private String plus30;

    @Column(name = "PLUS_60")
    private String plus60;

    @Column(name = "PLUS_90")
    private String plus90;

    private String remarks;

    private String statuscode;

    @Column(name = "TOTAL_OUTSTANDING_BALANCE")
    private BigDecimal totalOutstandingBalance;

    @Column(name = "WRITE_OFF")
    private String writeOff;

    private String disclaimerText;

    public long getEcibResponseId() {
        return this.ecibResponseId;
    }

    public void setEcibResponseId(long ecibResponseId) {
        this.ecibResponseId = ecibResponseId;
    }

    public BigDecimal getActiveAccounts() {
        return this.activeAccounts;
    }

    public void setActiveAccounts(BigDecimal activeAccounts) {
        this.activeAccounts = activeAccounts;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCnic() {
        return this.cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public Date getCreatedate() {
        return this.createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public BigDecimal getCreateuser() {
        return this.createuser;
    }

    public void setCreateuser(BigDecimal createuser) {
        this.createuser = createuser;
    }

    public String getDob() {
        return this.dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessagecode() {
        return this.messagecode;
    }

    public void setMessagecode(String messagecode) {
        this.messagecode = messagecode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlus120() {
        return this.plus120;
    }

    public void setPlus120(String plus120) {
        this.plus120 = plus120;
    }

    public String getPlus150() {
        return this.plus150;
    }

    public void setPlus150(String plus150) {
        this.plus150 = plus150;
    }

    public String getPlus180() {
        return this.plus180;
    }

    public void setPlus180(String plus180) {
        this.plus180 = plus180;
    }

    public String getPlus30() {
        return this.plus30;
    }

    public void setPlus30(String plus30) {
        this.plus30 = plus30;
    }

    public String getPlus60() {
        return this.plus60;
    }

    public void setPlus60(String plus60) {
        this.plus60 = plus60;
    }

    public String getPlus90() {
        return this.plus90;
    }

    public void setPlus90(String plus90) {
        this.plus90 = plus90;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatuscode() {
        return this.statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    public BigDecimal getTotalOutstandingBalance() {
        return this.totalOutstandingBalance;
    }

    public void setTotalOutstandingBalance(BigDecimal totalOutstandingBalance) {
        this.totalOutstandingBalance = totalOutstandingBalance;
    }

    public String getWriteOff() {
        return this.writeOff;
    }

    public void setWriteOff(String writeOff) {
        this.writeOff = writeOff;
    }

    public String getDisclaimerText() {
        return disclaimerText;
    }

    public void setDisclaimerText(String disclaimerText) {
        this.disclaimerText = disclaimerText;
    }
}