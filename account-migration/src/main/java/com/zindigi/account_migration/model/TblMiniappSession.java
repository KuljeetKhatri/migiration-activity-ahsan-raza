package com.zindigi.account_migration.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "TBL_MINIAPP_SESSION")
@NamedQuery(name = "TblMiniappSession.findAll", query = "SELECT t FROM TblMiniappSession t")
public class TblMiniappSession implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TBL_MINIAPP_SESSION_MINIAPPSESSIONID_GENERATOR", sequenceName = "TBL_MINIAPP_SESSION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TBL_MINIAPP_SESSION_MINIAPPSESSIONID_GENERATOR")
    @Column(name = "MINIAPP_SESSION_ID")
    private long miniappSessionId;

    @Temporal(TemporalType.DATE)
    private Date createdate;

    private BigDecimal createuser;

    @Temporal(TemporalType.DATE)
    @Column(name = "SESSION_START_TIME")
    private Date sessionStartTime;

    @Temporal(TemporalType.DATE)
    @Column(name = "SESSION_END_TIME")
    private Date sessionEndTime;

    private String status;

    //bi-directional many-to-one association to TblCmsMiniappConfig
    @ManyToOne
    @JoinColumn(name = "CMS_MINIAPP_CONFIG_ID")
    private TblCmsMiniappConfig tblCmsMiniappConfig;

    //bi-directional many-to-one association to TblCustomer
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private TblCustomer tblCustomer;

    public TblMiniappSession() {
    }

    public Date getSessionEndTime() {
        return sessionEndTime;
    }

    public void setSessionEndTime(Date sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }

    public long getMiniappSessionId() {
        return this.miniappSessionId;
    }

    public void setMiniappSessionId(long miniappSessionId) {
        this.miniappSessionId = miniappSessionId;
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

    public Date getSessionStartTime() {
        return this.sessionStartTime;
    }

    public void setSessionStartTime(Date sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TblCmsMiniappConfig getTblCmsMiniappConfig() {
        return this.tblCmsMiniappConfig;
    }

    public void setTblCmsMiniappConfig(TblCmsMiniappConfig tblCmsMiniappConfig) {
        this.tblCmsMiniappConfig = tblCmsMiniappConfig;
    }

    public TblCustomer getTblCustomer() {
        return this.tblCustomer;
    }

    public void setTblCustomer(TblCustomer tblCustomer) {
        this.tblCustomer = tblCustomer;
    }

}
