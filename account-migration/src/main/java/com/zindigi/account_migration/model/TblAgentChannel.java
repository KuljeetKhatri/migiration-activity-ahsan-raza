package com.zindigi.account_migration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "TBL_AGENT_CHANNEL", schema = "MFS")
public class TblAgentChannel {

    @Id
    @SequenceGenerator(name="TBL_AGENT_CHANNELID_GENERATOR", sequenceName="TBL_AGENT_CHANNEL_SEQ",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_AGENT_CHANNELID_GENERATOR")
    private Long agentChannelId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "AGENT_ID")
    private TblAgent tblAgent;

    @OneToOne
    @JoinColumn(name = "CHANNEL_ID")
    private LkpChannel lkpChannel;

    private String isActive;

    private Long createuser;

    private Date createdate;

    private BigDecimal lastupdateuser;

    private Date lastupdatedate;

    private Long updateindex;

    @OneToOne
    @JoinColumn(name = "PRODUCT_CATALOG_ID")
    private TblProductCatalog tblProductCatalog;


    public TblAgent getTblAgent() {
        return tblAgent;
    }

    public void setTblAgent(TblAgent tblAgent) {
        this.tblAgent = tblAgent;
    }

    public LkpChannel getLkpChannel() {
        return lkpChannel;
    }

    public void setLkpChannel(LkpChannel lkpChannel) {
        this.lkpChannel = lkpChannel;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Long getCreateuser() {
        return createuser;
    }

    public void setCreateuser(Long createUser) {
        this.createuser = createUser;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createDate) {
        this.createdate = createDate;
    }

    public BigDecimal getLastupdateuser() {
        return lastupdateuser;
    }

    public void setLastupdateuser(BigDecimal lastUpdateUser) {
        this.lastupdateuser = lastUpdateUser;
    }

    public Date getLastupdatedate() {
        return lastupdatedate;
    }

    public void setLastupdatedate(Date lastUpdateDate) {
        this.lastupdatedate = lastUpdateDate;
    }

    public Long getUpdateindex() {
        return updateindex;
    }

    public void setUpdateindex(Long updateIndex) {
        this.updateindex = updateIndex;
    }

    public TblProductCatalog getTblProductCatalog() {
        return tblProductCatalog;
    }

    public void setTblProductCatalog(TblProductCatalog tblProductCatalog) {
        this.tblProductCatalog = tblProductCatalog;
    }
}
