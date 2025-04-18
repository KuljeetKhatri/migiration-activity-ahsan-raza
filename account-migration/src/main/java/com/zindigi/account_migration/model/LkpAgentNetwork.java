package com.zindigi.account_migration.model;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LKP_AGENT_NETWORK database table.
 * 
 */
@Entity
@Table(name="LKP_AGENT_NETWORK")
@NamedQuery(name="LkpAgentNetwork.findAll", query="SELECT l FROM LkpAgentNetwork l")
public class LkpAgentNetwork implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_AGENT_NETWORK_AGENTNETWORKID_GENERATOR", sequenceName="LKP_AGENT_NETWORK_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_AGENT_NETWORK_AGENTNETWORKID_GENERATOR")
	@Column(name="AGENT_NETWORK_ID")
	private long agentNetworkId;

	@Column(name="AGENT_NETWORK_CODE")
	private String agentNetworkCode;

	@Column(name="AGENT_NETWORK_DESCR")
	private String agentNetworkDescr;

	@Column(name="AGENT_NETWORK_NAME")
	private String agentNetworkName;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="HIERARCHY_LEVEL")
	private BigDecimal hierarchyLevel;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="SERVICE_OP_ID")
	private BigDecimal serviceOpId;

	private BigDecimal updateindex;

	public LkpAgentNetwork() {
	}

	public long getAgentNetworkId() {
		return this.agentNetworkId;
	}

	public void setAgentNetworkId(long agentNetworkId) {
		this.agentNetworkId = agentNetworkId;
	}

	public String getAgentNetworkCode() {
		return this.agentNetworkCode;
	}

	public void setAgentNetworkCode(String agentNetworkCode) {
		this.agentNetworkCode = agentNetworkCode;
	}

	public String getAgentNetworkDescr() {
		return this.agentNetworkDescr;
	}

	public void setAgentNetworkDescr(String agentNetworkDescr) {
		this.agentNetworkDescr = agentNetworkDescr;
	}

	public String getAgentNetworkName() {
		return this.agentNetworkName;
	}

	public void setAgentNetworkName(String agentNetworkName) {
		this.agentNetworkName = agentNetworkName;
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

	public BigDecimal getHierarchyLevel() {
		return this.hierarchyLevel;
	}

	public void setHierarchyLevel(BigDecimal hierarchyLevel) {
		this.hierarchyLevel = hierarchyLevel;
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

	public BigDecimal getServiceOpId() {
		return this.serviceOpId;
	}

	public void setServiceOpId(BigDecimal serviceOpId) {
		this.serviceOpId = serviceOpId;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}
}