package com.zindigi.account_migration.model;

import javax.persistence.*;import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_DEVICE_INFO database table.
 * 
 */
@Entity
@Table(name="TBL_DEVICE_INFO")
@NamedQuery(name="TblDeviceInfo.findAll", query="SELECT t FROM TblDeviceInfo t")
public class TblDeviceInfo implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
	@SequenceGenerator(name="TBL_DEVICE_INFO_DEVICEINFOID_GENERATOR", sequenceName="TBL_DEVICE_INFO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_DEVICE_INFO_DEVICEINFOID_GENERATOR")
	@Column(name="DEVICE_INFO_ID")
	private long deviceInfoId;

	@Lob
	@Column(name="ADDITIONAL_PARAMS")
	private String additionalParams;

	@Column(name="ANDROID_API_LEVEL")
	private String androidApiLevel;

	@Column(name="ANDROID_UUID")
	private String androidUuid;

	@Column(name="APP_VERSION_CODE")
	private String appVersionCode;

	@Column(name="APP_VERSION_NAME")
	private String appVersionName;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	private String density;

	@Column(name="DEVICE_ID")
	private String deviceId;

	@Column(name="DEVICE_MODEL")
	private String deviceModel;

	@Column(name="DEVICE_TYPE")
	private String deviceType;

	@Column(name="GOOGLE_PLAY_SERVICES_VERSION")
	private String googlePlayServicesVersion;

	@Column(name="IP_ADDRESS")
	private String ipAddress;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="NETWORK_OPERATOR")
	private String networkOperator;

	@Column(name="NETWORK_TYPE")
	private String networkType;

	@Column(name="OS_VERSION")
	private String osVersion;

	@Column(name="LATITUDE")
	private String latitude;

	@Column(name="LONGITUDE")
	private String longitude;

	private String resolution;

	@Column(name="SCREEN_SIZE")
	private String screenSize;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblAppUser
	@ManyToOne
	@JoinColumn(name="APP_USER_ID")
	private TblAppUser tblAppUser;

	public TblDeviceInfo() {
	}

	public long getDeviceInfoId() {
		return this.deviceInfoId;
	}

	public void setDeviceInfoId(long deviceInfoId) {
		this.deviceInfoId = deviceInfoId;
	}

	public String getAdditionalParams() {
		return this.additionalParams;
	}

	public void setAdditionalParams(String additionalParams) {
		this.additionalParams = additionalParams;
	}

	public String getAndroidApiLevel() {
		return this.androidApiLevel;
	}

	public void setAndroidApiLevel(String androidApiLevel) {
		this.androidApiLevel = androidApiLevel;
	}

	public String getAndroidUuid() {
		return this.androidUuid;
	}

	public void setAndroidUuid(String androidUuid) {
		this.androidUuid = androidUuid;
	}

	public String getAppVersionCode() {
		return this.appVersionCode;
	}

	public void setAppVersionCode(String appVersionCode) {
		this.appVersionCode = appVersionCode;
	}

	public String getAppVersionName() {
		return this.appVersionName;
	}

	public void setAppVersionName(String appVersionName) {
		this.appVersionName = appVersionName;
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

	public String getDensity() {
		return this.density;
	}

	public void setDensity(String density) {
		this.density = density;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceModel() {
		return this.deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getGooglePlayServicesVersion() {
		return this.googlePlayServicesVersion;
	}

	public void setGooglePlayServicesVersion(String googlePlayServicesVersion) {
		this.googlePlayServicesVersion = googlePlayServicesVersion;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
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

	public String getNetworkOperator() {
		return this.networkOperator;
	}

	public void setNetworkOperator(String networkOperator) {
		this.networkOperator = networkOperator;
	}

	public String getNetworkType() {
		return this.networkType;
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

	public String getOsVersion() {
		return this.osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getResolution() {
		return this.resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getScreenSize() {
		return this.screenSize;
	}

	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public TblAppUser getTblAppUser() {
		return this.tblAppUser;
	}

	public void setTblAppUser(TblAppUser tblAppUser) {
		this.tblAppUser = tblAppUser;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}