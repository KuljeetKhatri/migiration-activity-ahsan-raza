package com.zindigi.account_migration.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="TBL_CMS_MINIAPP_CONFIG")
@NamedQuery(name="TblCmsMiniappConfig.findAll", query="SELECT t FROM TblCmsMiniappConfig t")
public class TblCmsMiniappConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="TBL_CMS_MINIAPP_CONFIG_CMSMINIAPPCONFIGID_GENERATOR", sequenceName="TBL_CMS_MINIAPP_CONFIG_SEQ",allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_CMS_MINIAPP_CONFIG_CMSMINIAPPCONFIGID_GENERATOR")
    @Column(name="CMS_MINIAPP_CONFIG_ID")
    private long cmsMiniappConfigId;

    @Temporal(TemporalType.DATE)
    private Date createdate;

    private BigDecimal createuser;

    @Column(name="IS_ACTIVE")
    private String isActive;

    @Temporal(TemporalType.DATE)
    private Date lastupdatedate;

    private BigDecimal lastupdateuser;

    @Column(name="PRODUCT_NAME")
    private String productName;

    @Column(name="MINIAPP_ID")
    private String miniappId;

    @Column(name="PRODUCT_ID")
    private BigDecimal productId;

    @Column(name="SESSION_TIMEOUT")
    private BigDecimal sessionTimeout;

    @Column(name="STATUS_ID")
    private BigDecimal statusId;

    private BigDecimal updateindex;

    //bi-directional many-to-one association to TblMiniappSession
    @OneToMany(mappedBy="tblCmsMiniappConfig")
    private List<TblMiniappSession> tblMiniappSessions;

    public TblCmsMiniappConfig() {
    }

    public long getCmsMiniappConfigId() {
        return this.cmsMiniappConfigId;
    }

    public void setCmsMiniappConfigId(long cmsMiniappConfigId) {
        this.cmsMiniappConfigId = cmsMiniappConfigId;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMiniappId() {
        return this.miniappId;
    }

    public void setMiniappId(String miniappId) {
        this.miniappId = miniappId;
    }

    public BigDecimal getProductId() {
        return this.productId;
    }

    public void setProductId(BigDecimal productId) {
        this.productId = productId;
    }

    public BigDecimal getSessionTimeout() {
        return this.sessionTimeout;
    }

    public void setSessionTimeout(BigDecimal sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public BigDecimal getStatusId() {
        return this.statusId;
    }

    public void setStatusId(BigDecimal statusId) {
        this.statusId = statusId;
    }

    public BigDecimal getUpdateindex() {
        return this.updateindex;
    }

    public void setUpdateindex(BigDecimal updateindex) {
        this.updateindex = updateindex;
    }

    public List<TblMiniappSession> getTblMiniappSessions() {
        return this.tblMiniappSessions;
    }

    public void setTblMiniappSessions(List<TblMiniappSession> tblMiniappSessions) {
        this.tblMiniappSessions = tblMiniappSessions;
    }

    public TblMiniappSession addTblMiniappSession(TblMiniappSession tblMiniappSession) {
        getTblMiniappSessions().add(tblMiniappSession);
        tblMiniappSession.setTblCmsMiniappConfig(this);

        return tblMiniappSession;
    }

    public TblMiniappSession removeTblMiniappSession(TblMiniappSession tblMiniappSession) {
        getTblMiniappSessions().remove(tblMiniappSession);
        tblMiniappSession.setTblCmsMiniappConfig(null);

        return tblMiniappSession;
    }

}