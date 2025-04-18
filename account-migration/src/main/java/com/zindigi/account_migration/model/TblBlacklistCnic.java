package com.zindigi.account_migration.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_BLACKLIST_CNIC database table.
 * 
 */
@Entity
@Table(name="TBL_BLACKLIST_CNIC")
@NamedQuery(name="TblBlacklistCnic.findAll", query="SELECT t FROM TblBlacklistCnic t")
public class TblBlacklistCnic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_BLACKLIST_CNIC_BLACKLISTCNICID_GENERATOR", sequenceName="TBL_BLACKLIST_CNIC_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_BLACKLIST_CNIC_BLACKLISTCNICID_GENERATOR")
	@Column(name="BLACKLIST_CNIC_ID")
	private long blacklistCnicId;

	private String cnic;

	private String comments;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_BLACKLIST")
	private BigDecimal isBlacklist;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	@Column(name="BULK_OPR_FILE_ID")
	private BigDecimal bulkOprFileId;

	public TblBlacklistCnic() {
	}

	public long getBlacklistCnicId() {
		return this.blacklistCnicId;
	}

	public void setBlacklistCnicId(long blacklistCnicId) {
		this.blacklistCnicId = blacklistCnicId;
	}

	public String getCnic() {
		return this.cnic;
	}

	public void setCnic(String cnic) {
		this.cnic = cnic;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public BigDecimal getIsBlacklist() {
		return this.isBlacklist;
	}

	public void setIsBlacklist(BigDecimal isBlacklist) {
		this.isBlacklist = isBlacklist;
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

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public BigDecimal getBulkOprFileId() {
		return bulkOprFileId;
	}

	public void setBulkOprFileId(BigDecimal bulkOprFileId) {
		this.bulkOprFileId = bulkOprFileId;
	}
}