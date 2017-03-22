package co.bugu.data.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by user on 2017/3/22.
 */
public class ProductTongji {

    private Long id;
    //产品编号
    private String productCode;
    //产品类型（1：票据、2：保理）
    private Integer productType;
    //产品名称
    private String productName;
    //发标金额
    private BigDecimal issueAmount;
    //产品期限
    private Integer productDeadline;
    //预计年化收益率
    private BigDecimal planAnnualYield;
    //募集开始时间
    private Date raiseStartDate;
    //募集开始时间(字符串 不参与数据库)
    private String raiseStartDateStr;
    //募集结束时间
    private Date raiseEndDate;
    //募集结束时间(字符串 不参与数据库)
    private String raiseEndDateStr;
    //起投金额
    private BigDecimal startBidAmount;
    //递增金额
    private BigDecimal incrementAmount;
    //宽限期
    private Integer extendDeadline;
    //起息日
    private Date valueDate;
    //起息日(字符串 不参与数据库)
    private String valueDateStr;
    //到期日
    private Date expiringDate;
    //到期日(字符串 不参与数据库)
    private String expiringDateStr;
    //宽限期到期日
    private Date extendExpiringDate;
    //宽限期到期日(字符串 不参与数据库)
    private String extendExpiringDateStr;
    //计息方式
    private Integer nterestMode;
    //合同模板
    private Integer contractTemplate;
    //计划兑付日
    private Date planCashDay;
    //计划兑付日期(字符串 不参与数据库)
    private String planCashDayStr;
    //罚息利率
    private BigDecimal interestPenaltyRate;
    //状态
    private Integer status;
    //结标金额
    private BigDecimal settleAmount;
    //产品描述
    private String productDesc;
    //项目描述
    private String projectDetail;
    //还款来源 1其他 2债务人还款
    private Integer refundSource;
    //还款金额
    private BigDecimal repaymentAmount;
    //实际募集时间
    private Date factraiseTime;
    //实际结标金额
    private Date factknotTime;
    //实际还款时间
    private Date repaymentTime;
    //创建时间
    private Date createTime;
    //修改时间
    private Date modifyTime;
    //删除标识（0：未删除，1：已删除）
    private Integer delFlag;
    //扩展辅助字段
    private Map<String,String> extMap;
    //扩展字段
    private String ext;

    //新再标识(0新1再)
    private Integer newOrOldType;
    //合同模板url
    private String contractUrl;
    //接续标识
    private String continueStatus;
    //接续金额
    private BigDecimal continueAmount;
    //推送方id
    private Long pushPartyId;
    //票据类型
    private Integer billType;
    //票据类型
    private String billTypeName;

    //涉及资产对标的字段start
    private String assetsCode;//资产编号
    private Long passiveId;//被替票id
    private Date billRateDueDate;//资产到日期
    private Integer billRemainingDays;//资产剩余期限
    private BigDecimal assetsInterest;//资产利息
    private String remark;
    private String pushPartyCode;
    private String contractNum;//原始资产合同编号
    private String debtorCusNum;//债务人客户编号
    private String originalDebtor;//债务人
    private BigDecimal creditorRightsTransferCost;//平台采购成本
    private Integer assetStatus;
    //涉及资产对标的字段end


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

    public String getRaiseStartDateStr() {
        return raiseStartDateStr;
    }

    public void setRaiseStartDateStr(String raiseStartDateStr) {
        this.raiseStartDateStr = raiseStartDateStr;
    }

    public Date getRaiseEndDate() {
        return raiseEndDate;
    }

    public void setRaiseEndDate(Date raiseEndDate) {
        this.raiseEndDate = raiseEndDate;
    }

    public String getRaiseEndDateStr() {
        return raiseEndDateStr;
    }

    public void setRaiseEndDateStr(String raiseEndDateStr) {
        this.raiseEndDateStr = raiseEndDateStr;
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

    public String getValueDateStr() {
        return valueDateStr;
    }

    public void setValueDateStr(String valueDateStr) {
        this.valueDateStr = valueDateStr;
    }

    public Date getExpiringDate() {
        return expiringDate;
    }

    public void setExpiringDate(Date expiringDate) {
        this.expiringDate = expiringDate;
    }

    public String getExpiringDateStr() {
        return expiringDateStr;
    }

    public void setExpiringDateStr(String expiringDateStr) {
        this.expiringDateStr = expiringDateStr;
    }

    public Date getExtendExpiringDate() {
        return extendExpiringDate;
    }

    public void setExtendExpiringDate(Date extendExpiringDate) {
        this.extendExpiringDate = extendExpiringDate;
    }

    public String getExtendExpiringDateStr() {
        return extendExpiringDateStr;
    }

    public void setExtendExpiringDateStr(String extendExpiringDateStr) {
        this.extendExpiringDateStr = extendExpiringDateStr;
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

    public String getPlanCashDayStr() {
        return planCashDayStr;
    }

    public void setPlanCashDayStr(String planCashDayStr) {
        this.planCashDayStr = planCashDayStr;
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

    public Map<String, String> getExtMap() {
        return extMap;
    }

    public void setExtMap(Map<String, String> extMap) {
        this.extMap = extMap;
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

    public String getAssetsCode() {
        return assetsCode;
    }

    public void setAssetsCode(String assetsCode) {
        this.assetsCode = assetsCode;
    }

    public Long getPassiveId() {
        return passiveId;
    }

    public void setPassiveId(Long passiveId) {
        this.passiveId = passiveId;
    }

    public Date getBillRateDueDate() {
        return billRateDueDate;
    }

    public void setBillRateDueDate(Date billRateDueDate) {
        this.billRateDueDate = billRateDueDate;
    }

    public Integer getBillRemainingDays() {
        return billRemainingDays;
    }

    public void setBillRemainingDays(Integer billRemainingDays) {
        this.billRemainingDays = billRemainingDays;
    }

    public BigDecimal getAssetsInterest() {
        return assetsInterest;
    }

    public void setAssetsInterest(BigDecimal assetsInterest) {
        this.assetsInterest = assetsInterest;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPushPartyCode() {
        return pushPartyCode;
    }

    public void setPushPartyCode(String pushPartyCode) {
        this.pushPartyCode = pushPartyCode;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getDebtorCusNum() {
        return debtorCusNum;
    }

    public void setDebtorCusNum(String debtorCusNum) {
        this.debtorCusNum = debtorCusNum;
    }

    public String getOriginalDebtor() {
        return originalDebtor;
    }

    public void setOriginalDebtor(String originalDebtor) {
        this.originalDebtor = originalDebtor;
    }

    public BigDecimal getCreditorRightsTransferCost() {
        return creditorRightsTransferCost;
    }

    public void setCreditorRightsTransferCost(BigDecimal creditorRightsTransferCost) {
        this.creditorRightsTransferCost = creditorRightsTransferCost;
    }

    public Integer getAssetStatus() {
        return assetStatus;
    }

    public void setAssetStatus(Integer assetStatus) {
        this.assetStatus = assetStatus;
    }
}
