package co.bugu.data.model;

import java.math.BigDecimal;
import java.util.Date;

public class AssetBenchmarkingStatistics {
    private Long id;

    private String pushPartyCode;

    private Integer refundSource;

    private Integer newOrOldType;

    private String productName;

    private String assetCode;

    private String productCode;

    private BigDecimal issueAmount;

    private Integer productDeadline;

    private Integer assetRemainingDays;

    private String pushPartyName;

    private String pushPartyCusNum;

    private String contractNum;

    private BigDecimal creditorRightsTransferCost;

    private Date productExpiringDate;

    private Date assetExpiringDate;

    private Integer delFlag;

    private Date createTime;

    private Date modifyTime;

    private Integer productType;

    private Integer productStatus;

    private Integer assetStatus;

    private Integer assetType;
    
    private Date factknotTime;
    
    //起息日
    private Date valueDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPushPartyCode() {
        return pushPartyCode;
    }

    public void setPushPartyCode(String pushPartyCode) {
        this.pushPartyCode = pushPartyCode == null ? null : pushPartyCode.trim();
    }

    public Integer getRefundSource() {
        return refundSource;
    }

    public void setRefundSource(Integer refundSource) {
        this.refundSource = refundSource;
    }

    public Integer getNewOrOldType() {
        return newOrOldType;
    }

    public void setNewOrOldType(Integer newOrOldType) {
        this.newOrOldType = newOrOldType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public BigDecimal getIssueAmount() {
        return issueAmount;
    }

    public void setIssueAmount(BigDecimal issueAmount) {
        this.issueAmount = issueAmount;
    }

    public Integer getProductDeadline() {
        return productDeadline;
    }

    public void setProductDeadline(Integer productDeadline) {
        this.productDeadline = productDeadline;
    }

    public Integer getAssetRemainingDays() {
        return assetRemainingDays;
    }

    public void setAssetRemainingDays(Integer assetRemainingDays) {
        this.assetRemainingDays = assetRemainingDays;
    }

    public String getPushPartyName() {
        return pushPartyName;
    }

    public void setPushPartyName(String pushPartyName) {
        this.pushPartyName = pushPartyName == null ? null : pushPartyName.trim();
    }

    public String getPushPartyCusNum() {
        return pushPartyCusNum;
    }

    public void setPushPartyCusNum(String pushPartyCusNum) {
        this.pushPartyCusNum = pushPartyCusNum == null ? null : pushPartyCusNum.trim();
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum == null ? null : contractNum.trim();
    }

    public BigDecimal getCreditorRightsTransferCost() {
        return creditorRightsTransferCost;
    }

    public void setCreditorRightsTransferCost(BigDecimal creditorRightsTransferCost) {
        this.creditorRightsTransferCost = creditorRightsTransferCost;
    }

    public Date getProductExpiringDate() {
        return productExpiringDate;
    }

    public void setProductExpiringDate(Date productExpiringDate) {
        this.productExpiringDate = productExpiringDate;
    }

    public Date getAssetExpiringDate() {
        return assetExpiringDate;
    }

    public void setAssetExpiringDate(Date assetExpiringDate) {
        this.assetExpiringDate = assetExpiringDate;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public Integer getAssetStatus() {
        return assetStatus;
    }

    public void setAssetStatus(Integer assetStatus) {
        this.assetStatus = assetStatus;
    }

    public Integer getAssetType() {
        return assetType;
    }

    public void setAssetType(Integer assetType) {
        this.assetType = assetType;
    }

	public Date getFactknotTime() {
		return factknotTime;
	}

	public void setFactknotTime(Date factknotTime) {
		this.factknotTime = factknotTime;
	}

	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}
}