package com.zindigi.account_migration.dto;

public class RiskProfileScoreAndRatingRequest {

    private Long customerType;//LKp_rp_customer_type (Lkp_Accont_level)
    private Long customerOccupation;//LKp_Occupation Hra KYC
    private String isBeneficialOwner;//Always N (because of Sit issues)
    private Long customerSourceOfFunds;//Source of Fund get data from lkpSourceOfFund HRA
    private Long purposeOfAccount;// getFrom Customer Kyc in case of HRA find from lkp_Account_purpose incase of ultra ultrausage searched from lkp_Account_purpose
    private String internalRiskAssessmentFound;//N
    private String isDirector;//N
    private Long otherNationalities;//yes or No in case of Ultra FreeLance
    private Long customerCountryID;// in case of dual national foriegn country while pakistan
    private Long productAndServices;//always null
    private Long channel;//channel Id
    private Long tradingCountries;
    private Long customerPermanentAddress;
    private Long customerMailingAddress;
    private Long customerExpectedAggregateDebitPerMonth;
    private Long customerExpectedAggregateCreditPerMonth;
    private Long customerCreditCountPerMonth;
    private Long customerDebitCountPerMonth;

    public Long getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Long customerType) {
        this.customerType = customerType;
    }

    public Long getCustomerOccupation() {
        return customerOccupation;
    }

    public void setCustomerOccupation(Long customerOccupation) {
        this.customerOccupation = customerOccupation;
    }

    public String getIsBeneficialOwner() {
        return isBeneficialOwner;
    }

    public void setIsBeneficialOwner(String isBeneficialOwner) {
        this.isBeneficialOwner = isBeneficialOwner;
    }

    public Long getCustomerSourceOfFunds() {
        return customerSourceOfFunds;
    }

    public void setCustomerSourceOfFunds(Long customerSourceOfFunds) {
        this.customerSourceOfFunds = customerSourceOfFunds;
    }

    public Long getPurposeOfAccount() {
        return purposeOfAccount;
    }

    public void setPurposeOfAccount(Long purposeOfAccount) {
        this.purposeOfAccount = purposeOfAccount;
    }

    public String getInternalRiskAssessmentFound() {
        return internalRiskAssessmentFound;
    }

    public void setInternalRiskAssessmentFound(String internalRiskAssessmentFound) {
        this.internalRiskAssessmentFound = internalRiskAssessmentFound;
    }

    public String getIsDirector() {
        return isDirector;
    }

    public void setIsDirector(String isDirector) {
        this.isDirector = isDirector;
    }

    public Long getOtherNationalities() {
        return otherNationalities;
    }

    public void setOtherNationalities(Long otherNationalities) {
        this.otherNationalities = otherNationalities;
    }

    public Long getCustomerCountryID() {
        return customerCountryID;
    }

    public void setCustomerCountryID(Long customerCountryID) {
        this.customerCountryID = customerCountryID;
    }

    public Long getProductAndServices() {
        return productAndServices;
    }

    public void setProductAndServices(Long productAndServices) {
        this.productAndServices = productAndServices;
    }

    public Long getChannel() {
        return channel;
    }

    public void setChannel(Long channel) {
        this.channel = channel;
    }

    public Long getTradingCountries() {
        return tradingCountries;
    }

    public void setTradingCountries(Long tradingCountries) {
        this.tradingCountries = tradingCountries;
    }

    public Long getCustomerPermanentAddress() {
        return customerPermanentAddress;
    }

    public void setCustomerPermanentAddress(Long customerPermanentAddress) {
        this.customerPermanentAddress = customerPermanentAddress;
    }

    public Long getCustomerMailingAddress() {
        return customerMailingAddress;
    }

    public void setCustomerMailingAddress(Long customerMailingAddress) {
        this.customerMailingAddress = customerMailingAddress;
    }

    public Long getCustomerExpectedAggregateDebitPerMonth() {
        return customerExpectedAggregateDebitPerMonth;
    }

    public void setCustomerExpectedAggregateDebitPerMonth(Long customerExpectedAggregateDebitPerMonth) {
        this.customerExpectedAggregateDebitPerMonth = customerExpectedAggregateDebitPerMonth;
    }

    public Long getCustomerExpectedAggregateCreditPerMonth() {
        return customerExpectedAggregateCreditPerMonth;
    }

    public void setCustomerExpectedAggregateCreditPerMonth(Long customerExpectedAggregateCreditPerMonth) {
        this.customerExpectedAggregateCreditPerMonth = customerExpectedAggregateCreditPerMonth;
    }

    public Long getCustomerCreditCountPerMonth() {
        return customerCreditCountPerMonth;
    }

    public void setCustomerCreditCountPerMonth(Long customerCreditCountPerMonth) {
        this.customerCreditCountPerMonth = customerCreditCountPerMonth;
    }

    public Long getCustomerDebitCountPerMonth() {
        return customerDebitCountPerMonth;
    }

    public void setCustomerDebitCountPerMonth(Long customerDebitCountPerMonth) {
        this.customerDebitCountPerMonth = customerDebitCountPerMonth;
    }
}