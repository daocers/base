package co.bugu.data.model;

import java.math.BigDecimal;
import java.util.Date;

public class FactoringAsset {
    private Long id;

    private String assetsCode;

    private Integer assetsType;

    private Integer assetCtype;

    private Date valueDate;

    private Date expiringDate;

    private Integer newDeadline;

    private Integer deadline;

    private BigDecimal rate;

    private String rePricingMode;

    private BigDecimal assetAmount;

    private Byte interestCalculation;

    private String guaranteeMode;

    private String creditor;

    private String debtor;

    private String originalDebtor;

    private String securedParty;

    private String debtorCusNum;

    private String contractNum;

    private Long pushParty;

    private String pushPartyOgrName;

    private Date pushDate;

    private Date useDate;

    private Date assetExpireDate;

    private Integer pushingResidualMaturity;

    private BigDecimal creditorRightsTransferCost;

    private BigDecimal slottingAllowance;

    private BigDecimal assetBalanceRepeat;

    private BigDecimal assetBalance;

    private Integer residualMaturity;

    private Byte status;

    private Boolean baseCreditorRightsContract;

    private Boolean creditorRightsTransferApplication;

    private Boolean receivableCreditTransferApplication;

    private Boolean creditAssetTransferContract;

    private Boolean transferMoneyCertificate;

    private Boolean creditorRightsTransferProtocol;

    private String remark;

    private BigDecimal useableBalance;

    private BigDecimal freezeAmount;

    private BigDecimal pauseAmount;

    private Boolean delFlag;

    private Date createTime;

    private Date modifyTime;

    private String rejectReason;

    private String pauseReason;

    private Integer assetGtype;

    private BigDecimal originalRate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetsCode() {
        return assetsCode;
    }

    public void setAssetsCode(String assetsCode) {
        this.assetsCode = assetsCode;
    }

    public Integer getAssetsType() {
        return assetsType;
    }

    public void setAssetsType(Integer assetsType) {
        this.assetsType = assetsType;
    }

    public Integer getAssetCtype() {
        return assetCtype;
    }

    public void setAssetCtype(Integer assetCtype) {
        this.assetCtype = assetCtype;
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

    public Integer getNewDeadline() {
        return newDeadline;
    }

    public void setNewDeadline(Integer newDeadline) {
        this.newDeadline = newDeadline;
    }

    public Integer getDeadline() {
        return deadline;
    }

    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getRePricingMode() {
        return rePricingMode;
    }

    public void setRePricingMode(String rePricingMode) {
        this.rePricingMode = rePricingMode;
    }

    public BigDecimal getAssetAmount() {
        return assetAmount;
    }

    public void setAssetAmount(BigDecimal assetAmount) {
        this.assetAmount = assetAmount;
    }

    public Byte getInterestCalculation() {
        return interestCalculation;
    }

    public void setInterestCalculation(Byte interestCalculation) {
        this.interestCalculation = interestCalculation;
    }

    public String getGuaranteeMode() {
        return guaranteeMode;
    }

    public void setGuaranteeMode(String guaranteeMode) {
        this.guaranteeMode = guaranteeMode;
    }

    public String getCreditor() {
        return creditor;
    }

    public void setCreditor(String creditor) {
        this.creditor = creditor;
    }

    public String getDebtor() {
        return debtor;
    }

    public void setDebtor(String debtor) {
        this.debtor = debtor;
    }

    public String getOriginalDebtor() {
        return originalDebtor;
    }

    public void setOriginalDebtor(String originalDebtor) {
        this.originalDebtor = originalDebtor;
    }

    public String getSecuredParty() {
        return securedParty;
    }

    public void setSecuredParty(String securedParty) {
        this.securedParty = securedParty;
    }

    public String getDebtorCusNum() {
        return debtorCusNum;
    }

    public void setDebtorCusNum(String debtorCusNum) {
        this.debtorCusNum = debtorCusNum;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public Long getPushParty() {
        return pushParty;
    }

    public void setPushParty(Long pushParty) {
        this.pushParty = pushParty;
    }

    public String getPushPartyOgrName() {
        return pushPartyOgrName;
    }

    public void setPushPartyOgrName(String pushPartyOgrName) {
        this.pushPartyOgrName = pushPartyOgrName;
    }

    public Date getPushDate() {
        return pushDate;
    }

    public void setPushDate(Date pushDate) {
        this.pushDate = pushDate;
    }

    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    public Date getAssetExpireDate() {
        return assetExpireDate;
    }

    public void setAssetExpireDate(Date assetExpireDate) {
        this.assetExpireDate = assetExpireDate;
    }

    public Integer getPushingResidualMaturity() {
        return pushingResidualMaturity;
    }

    public void setPushingResidualMaturity(Integer pushingResidualMaturity) {
        this.pushingResidualMaturity = pushingResidualMaturity;
    }

    public BigDecimal getCreditorRightsTransferCost() {
        return creditorRightsTransferCost;
    }

    public void setCreditorRightsTransferCost(BigDecimal creditorRightsTransferCost) {
        this.creditorRightsTransferCost = creditorRightsTransferCost;
    }

    public BigDecimal getSlottingAllowance() {
        return slottingAllowance;
    }

    public void setSlottingAllowance(BigDecimal slottingAllowance) {
        this.slottingAllowance = slottingAllowance;
    }

    public BigDecimal getAssetBalanceRepeat() {
        return assetBalanceRepeat;
    }

    public void setAssetBalanceRepeat(BigDecimal assetBalanceRepeat) {
        this.assetBalanceRepeat = assetBalanceRepeat;
    }

    public BigDecimal getAssetBalance() {
        return assetBalance;
    }

    public void setAssetBalance(BigDecimal assetBalance) {
        this.assetBalance = assetBalance;
    }

    public Integer getResidualMaturity() {
        return residualMaturity;
    }

    public void setResidualMaturity(Integer residualMaturity) {
        this.residualMaturity = residualMaturity;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Boolean getBaseCreditorRightsContract() {
        return baseCreditorRightsContract;
    }

    public void setBaseCreditorRightsContract(Boolean baseCreditorRightsContract) {
        this.baseCreditorRightsContract = baseCreditorRightsContract;
    }

    public Boolean getCreditorRightsTransferApplication() {
        return creditorRightsTransferApplication;
    }

    public void setCreditorRightsTransferApplication(Boolean creditorRightsTransferApplication) {
        this.creditorRightsTransferApplication = creditorRightsTransferApplication;
    }

    public Boolean getReceivableCreditTransferApplication() {
        return receivableCreditTransferApplication;
    }

    public void setReceivableCreditTransferApplication(Boolean receivableCreditTransferApplication) {
        this.receivableCreditTransferApplication = receivableCreditTransferApplication;
    }

    public Boolean getCreditAssetTransferContract() {
        return creditAssetTransferContract;
    }

    public void setCreditAssetTransferContract(Boolean creditAssetTransferContract) {
        this.creditAssetTransferContract = creditAssetTransferContract;
    }

    public Boolean getTransferMoneyCertificate() {
        return transferMoneyCertificate;
    }

    public void setTransferMoneyCertificate(Boolean transferMoneyCertificate) {
        this.transferMoneyCertificate = transferMoneyCertificate;
    }

    public Boolean getCreditorRightsTransferProtocol() {
        return creditorRightsTransferProtocol;
    }

    public void setCreditorRightsTransferProtocol(Boolean creditorRightsTransferProtocol) {
        this.creditorRightsTransferProtocol = creditorRightsTransferProtocol;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getUseableBalance() {
        return useableBalance;
    }

    public void setUseableBalance(BigDecimal useableBalance) {
        this.useableBalance = useableBalance;
    }

    public BigDecimal getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(BigDecimal freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public BigDecimal getPauseAmount() {
        return pauseAmount;
    }

    public void setPauseAmount(BigDecimal pauseAmount) {
        this.pauseAmount = pauseAmount;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
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

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getPauseReason() {
        return pauseReason;
    }

    public void setPauseReason(String pauseReason) {
        this.pauseReason = pauseReason;
    }

    public Integer getAssetGtype() {
        return assetGtype;
    }

    public void setAssetGtype(Integer assetGtype) {
        this.assetGtype = assetGtype;
    }

    public BigDecimal getOriginalRate() {
        return originalRate;
    }

    public void setOriginalRate(BigDecimal originalRate) {
        this.originalRate = originalRate;
    }
}