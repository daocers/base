package co.bugu.data.model;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private Long id;

    private String productCode;

    private Integer productType;

    private String productName;

    private BigDecimal issueAmount;

    private Integer productDeadline;

    private BigDecimal planAnnualYield;

    private Date raiseStartDate;

    private Date raiseEndDate;

    private BigDecimal startBidAmount;

    private BigDecimal incrementAmount;

    private Integer extendDeadline;

    private Date valueDate;

    private Date expiringDate;

    private Date extendExpiringDate;

    private Integer nterestMode;

    private Integer contractTemplate;

    private Date planCashDay;

    private BigDecimal interestPenaltyRate;

    private Integer status;

    private BigDecimal settleAmount;

    private String productDesc;

    private String projectDetail;

    private Integer refundSource;

    private BigDecimal repaymentAmount;

    private Date factraiseTime;

    private Date factknotTime;

    private Date repaymentTime;

    private Date createTime;

    private Date modifyTime;

    private Integer delFlag;

    private String ext;

    private Integer newOrOldType;

    private String contractUrl;

    private String continueStatus;

    private BigDecimal continueAmount;

    private Long pushPartyId;

    private Integer billType;

    private String billTypeName;

    private BigDecimal maxInvestAmount;

    private BigDecimal maxInvestTotalAmount;

    private Integer maxInvestNum;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public BigDecimal getPlanAnnualYield() {
        return planAnnualYield;
    }

    public void setPlanAnnualYield(BigDecimal planAnnualYield) {
        this.planAnnualYield = planAnnualYield;
    }

    public Date getRaiseStartDate() {
        return raiseStartDate;
    }

    public void setRaiseStartDate(Date raiseStartDate) {
        this.raiseStartDate = raiseStartDate;
    }

    public Date getRaiseEndDate() {
        return raiseEndDate;
    }

    public void setRaiseEndDate(Date raiseEndDate) {
        this.raiseEndDate = raiseEndDate;
    }

    public BigDecimal getStartBidAmount() {
        return startBidAmount;
    }

    public void setStartBidAmount(BigDecimal startBidAmount) {
        this.startBidAmount = startBidAmount;
    }

    public BigDecimal getIncrementAmount() {
        return incrementAmount;
    }

    public void setIncrementAmount(BigDecimal incrementAmount) {
        this.incrementAmount = incrementAmount;
    }

    public Integer getExtendDeadline() {
        return extendDeadline;
    }

    public void setExtendDeadline(Integer extendDeadline) {
        this.extendDeadline = extendDeadline;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public Date getExpiringDate() {
        return expiringDate;
    }

    public void setExpiringDate(Date expiringDate) {
        this.expiringDate = expiringDate;
    }

    public Date getExtendExpiringDate() {
        return extendExpiringDate;
    }

    public void setExtendExpiringDate(Date extendExpiringDate) {
        this.extendExpiringDate = extendExpiringDate;
    }

    public Integer getNterestMode() {
        return nterestMode;
    }

    public void setNterestMode(Integer nterestMode) {
        this.nterestMode = nterestMode;
    }

    public Integer getContractTemplate() {
        return contractTemplate;
    }

    public void setContractTemplate(Integer contractTemplate) {
        this.contractTemplate = contractTemplate;
    }

    public Date getPlanCashDay() {
        return planCashDay;
    }

    public void setPlanCashDay(Date planCashDay) {
        this.planCashDay = planCashDay;
    }

    public BigDecimal getInterestPenaltyRate() {
        return interestPenaltyRate;
    }

    public void setInterestPenaltyRate(BigDecimal interestPenaltyRate) {
        this.interestPenaltyRate = interestPenaltyRate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(BigDecimal settleAmount) {
        this.settleAmount = settleAmount;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProjectDetail() {
        return projectDetail;
    }

    public void setProjectDetail(String projectDetail) {
        this.projectDetail = projectDetail;
    }

    public Integer getRefundSource() {
        return refundSource;
    }

    public void setRefundSource(Integer refundSource) {
        this.refundSource = refundSource;
    }

    public BigDecimal getRepaymentAmount() {
        return repaymentAmount;
    }

    public void setRepaymentAmount(BigDecimal repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }

    public Date getFactraiseTime() {
        return factraiseTime;
    }

    public void setFactraiseTime(Date factraiseTime) {
        this.factraiseTime = factraiseTime;
    }

    public Date getFactknotTime() {
        return factknotTime;
    }

    public void setFactknotTime(Date factknotTime) {
        this.factknotTime = factknotTime;
    }

    public Date getRepaymentTime() {
        return repaymentTime;
    }

    public void setRepaymentTime(Date repaymentTime) {
        this.repaymentTime = repaymentTime;
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

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Integer getNewOrOldType() {
        return newOrOldType;
    }

    public void setNewOrOldType(Integer newOrOldType) {
        this.newOrOldType = newOrOldType;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }

    public String getContinueStatus() {
        return continueStatus;
    }

    public void setContinueStatus(String continueStatus) {
        this.continueStatus = continueStatus;
    }

    public BigDecimal getContinueAmount() {
        return continueAmount;
    }

    public void setContinueAmount(BigDecimal continueAmount) {
        this.continueAmount = continueAmount;
    }

    public Long getPushPartyId() {
        return pushPartyId;
    }

    public void setPushPartyId(Long pushPartyId) {
        this.pushPartyId = pushPartyId;
    }

    public Integer getBillType() {
        return billType;
    }

    public void setBillType(Integer billType) {
        this.billType = billType;
    }

    public String getBillTypeName() {
        return billTypeName;
    }

    public void setBillTypeName(String billTypeName) {
        this.billTypeName = billTypeName;
    }

    public BigDecimal getMaxInvestAmount() {
        return maxInvestAmount;
    }

    public void setMaxInvestAmount(BigDecimal maxInvestAmount) {
        this.maxInvestAmount = maxInvestAmount;
    }

    public BigDecimal getMaxInvestTotalAmount() {
        return maxInvestTotalAmount;
    }

    public void setMaxInvestTotalAmount(BigDecimal maxInvestTotalAmount) {
        this.maxInvestTotalAmount = maxInvestTotalAmount;
    }

    public Integer getMaxInvestNum() {
        return maxInvestNum;
    }

    public void setMaxInvestNum(Integer maxInvestNum) {
        this.maxInvestNum = maxInvestNum;
    }
}