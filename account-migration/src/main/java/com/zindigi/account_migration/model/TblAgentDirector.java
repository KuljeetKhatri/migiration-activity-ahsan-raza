package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_AGENT_DIRECTOR database table.
 */
@Entity
@Table(name = "TBL_AGENT_DIRECTOR")
@NamedQuery(name = "TblAgentDirector.findAll", query = "SELECT t FROM TblAgentDirector t")
public class TblAgentDirector implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TBL_AGENT_DIRECTOR_AGENTDIRECTORID_GENERATOR", sequenceName = "TBL_AGENT_DIRECTOR_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_AGENT_DIRECTOR_AGENTDIRECTORID_GENERATOR")
    @Column(name = "AGENT_DIRECTOR_ID")
    private long agentDirectorId;

    private String cnic;

    @Temporal(TemporalType.DATE)
    private Date createdate;

    private BigDecimal createuser;

    private String cnicIssuanceDate;

    @Column(name = "IS_ACTIVE")
    private String isActive;

    @Temporal(TemporalType.DATE)
    private Date lastupdatedate;

    private BigDecimal lastupdateuser;

    @Column(name = "MOBILE_NO")
    private String mobileNo;

    private String name;

    private BigDecimal updateindex;

    private String cnicHash;
    private String mobileNoHash;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "AGENT_ID")
    private TblAgent tblAgent;

    public TblAgentDirector() {
    }

    public long getAgentDirectorId() {
        return this.agentDirectorId;
    }

    public void setAgentDirectorId(long agentDirectorId) {
        this.agentDirectorId = agentDirectorId;
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

    public String getCnicIssuanceDate() {
        return this.cnicIssuanceDate;
    }

    public void setCnicIssuanceDate(String dateOfIssue) {
        this.cnicIssuanceDate = dateOfIssue;
    }

    public String getIsActive() {
        return this.isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Date getLastupdatedate() {
        return this.lastupdatedate;
    }

    public void setLastupdatedate(Date lastupdatedate) {
        this.lastupdatedate = lastupdatedate;
    }

    public BigDecimal getLastupdateuser() {
        return this.lastupdateuser;
    }

    public void setLastupdateuser(BigDecimal lastupdateuser) {
        this.lastupdateuser = lastupdateuser;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUpdateindex() {
        return this.updateindex;
    }

    public void setUpdateindex(BigDecimal updateindex) {
        this.updateindex = updateindex;
    }

    public TblAgent getTblAgent() {
        return tblAgent;
    }

    public void setTblAgent(TblAgent tblAgent) {
        this.tblAgent = tblAgent;
    }

    public String getCnicHash() {
        return cnicHash;
    }

    public void setCnicHash(String cnicHash) {
        this.cnicHash = cnicHash;
    }

    public String getMobileNoHash() {
        return mobileNoHash;
    }

    public void setMobileNoHash(String mobileNoHash) {
        this.mobileNoHash = mobileNoHash;
    }
}