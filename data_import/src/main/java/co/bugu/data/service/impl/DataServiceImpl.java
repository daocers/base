package co.bugu.data.service.impl;

import co.bugu.data.model.*;
import co.bugu.data.service.*;
import co.bugu.framework.core.dao.BaseDao;
import com.alibaba.fastjson.JSON;
import com.microsoft.schemas.office.office.STInsetMode;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by user on 2017/2/23.
 */
@Service
public class DataServiceImpl implements IDataService {
    volatile Integer seqNo = 100;
    volatile Integer prodNo = 0;
    String dateInfo;
    String productDate;
    @Autowired
    IFactoringAssetService factoringAssetService;
    @Autowired
    IDicService dicService;
    @Autowired
    IProductService productService;
    @Autowired
    IPushPartyService pushPartyService;
    @Autowired
    IFactoringProductRelationService relationService;
    @Autowired
    BaseDao baseDao;

    private static Logger logger = LoggerFactory.getLogger(IDataService.class);

    @Override
    public void add(List<List<String>> assetData, List<List<String>> productData, Map<String, Integer> cType, Map<String, Integer> gType) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Map<String, Long> assetNoIdMap = new HashMap<>();
        Map<String, Long> assetNoProdIdMap = new HashMap<>();
        Map<Long, String> pushPartyIdNameMap = new HashMap<>();

        Map<String, Long> pushPartyMap = new HashMap<>();
        initPushPartyInfo(pushPartyIdNameMap, pushPartyMap);


        if (assetData != null && assetData.size() > 0) {
            assetData.remove(0);
        }

        Integer assetType = null;
        Dic dic = new Dic();
        dic.setName("内部资产");
        List<Dic> list = dicService.findByObject(dic);
        if (list.size() > 0) {
            dic = list.get(0);
        }
        int row = 1;

//       添加一个虚拟的资产信息，用于级联处理产品excel中没有对应资产信息的数据
        BigDecimal zero = new BigDecimal(0);
        FactoringAsset factoringAsset = new FactoringAsset();
        factoringAsset.setAssetsCode("中间数据0000001");
        factoringAsset.setAssetsType(dic.getId().intValue());
        factoringAsset.setAssetCtype(null);//资产类型
        factoringAsset.setAssetGtype(null);//子类型
        factoringAsset.setValueDate(null);
        factoringAsset.setExpiringDate(new Date());
        factoringAsset.setNewDeadline(0);//资产到期日 - 第一个新产品的起息日
        factoringAsset.setDeadline(0);//资产原始期限
        factoringAsset.setOriginalRate(zero);
        factoringAsset.setRePricingMode(null);//重定价方式
        factoringAsset.setAssetAmount(zero);
        factoringAsset.setInterestCalculation((byte) 78);//计息方式
        factoringAsset.setGuaranteeMode("");//担保方式
        factoringAsset.setCreditor("");//债权人
        factoringAsset.setDebtor("");//债务人
        factoringAsset.setOriginalDebtor("");//原始债务人
        factoringAsset.setSecuredParty("");//担保方
        factoringAsset.setDebtorCusNum("");//债务人客户编号
        factoringAsset.setContractNum("");//合同编号
        factoringAsset.setPushParty(1l);//供应商编号
        factoringAsset.setPushPartyOgrName("test");
        factoringAsset.setPushDate(null);//资产推送日期
        factoringAsset.setUseDate(null);
        factoringAsset.setAssetExpireDate(new Date());//资产到欺日
        factoringAsset.setPushingResidualMaturity(0);//推送时候剩余期限
//
//            资本采购成本
        factoringAsset.setRate(zero);
        factoringAsset.setSlottingAllowance(zero);//通道费
        factoringAsset.setAssetBalanceRepeat(zero);//资产剩余金额，再发布
        factoringAsset.setAssetBalance(zero);//资产剩余金额，原始
        factoringAsset.setResidualMaturity(0);//资产剩余期限
        if (factoringAsset.getResidualMaturity() < 0) {
            factoringAsset.setResidualMaturity(0);
        }
        factoringAsset.setStatus((byte) 10);
        factoringAsset.setBaseCreditorRightsContract(false);
        factoringAsset.setCreditorRightsTransferApplication(false);
        factoringAsset.setReceivableCreditTransferApplication(false);
        factoringAsset.setCreditAssetTransferContract(false);
        factoringAsset.setTransferMoneyCertificate(false);
        factoringAsset.setCreditorRightsTransferProtocol(false);//债权转让协议

        factoringAsset.setRemark("");
        factoringAsset.setDelFlag(false);
        factoringAsset.setCreateTime(new Date());
        factoringAsset.setModifyTime(new Date());
        factoringAssetService.save(factoringAsset);
        assetNoIdMap.put(factoringAsset.getAssetsCode(), factoringAsset.getId());


        for (List<String> line : assetData) {
            row++;
            logger.debug("当前第 {} 行 ******************", row);
            String assetNo = line.get(1);
//            StringBuffer buffer = new StringBuffer();
//            buffer.append()
//                    .append(asset.getc)
//            if (StringUtils.isEmpty(assetNo)) {
//                logger.warn("资产编号为空，数据行为： {}", JSON.toJSONString(line, true));
//                continue;
//            }
            FactoringAsset asset = new FactoringAsset();
            asset.setAssetsCode(assetNo);
            asset.setAssetsType(dic.getId().intValue());
            asset.setAssetCtype(cType.get(getCtype(line.get(2))));//资产类型
            asset.setAssetGtype(gType.get(getGtype(line.get(2))));//子类型
            asset.setValueDate(StringUtils.isEmpty(line.get(3)) ? null : format.parse(line.get(3)));
            asset.setExpiringDate(StringUtils.isEmpty(line.get(4)) ? null : format.parse(line.get(4)));
            asset.setNewDeadline(0);//资产到期日 - 第一个新产品的起息日
            asset.setDeadline(StringUtils.isEmpty(line.get(5)) ? null : Double.valueOf(line.get(5)).intValue());//资产原始期限
            asset.setOriginalRate(StringUtils.isEmpty(line.get(6)) ? null : BigDecimal.valueOf(Double.valueOf(line.get(21)) * 100));
            asset.setRePricingMode(line.get(7));//重定价方式
            asset.setAssetAmount(BigDecimal.valueOf(Double.valueOf(line.get(8))));
            asset.setInterestCalculation(getInterestCalculation(line.get(9)));//计息方式
            asset.setGuaranteeMode(line.get(10));//担保方式
            asset.setCreditor(line.get(11));//债权人
            asset.setDebtor(line.get(12));//债务人
            asset.setOriginalDebtor(line.get(13));//原始债务人
            asset.setSecuredParty(line.get(14));//担保方
            asset.setDebtorCusNum(line.get(15));//债务人客户编号
            asset.setContractNum(line.get(16));//合同编号
            asset.setPushParty(pushPartyMap.get(line.get(33)));//供应商编号
            if (StringUtils.isEmpty(line.get(17))) {
                asset.setPushPartyOgrName(pushPartyIdNameMap.get(asset.getPushParty()));
            }
            asset.setPushDate(StringUtils.isEmpty(line.get(18)) ? null : format.parse(line.get(18)));//资产推送日期
            asset.setUseDate(null);
            asset.setAssetExpireDate(StringUtils.isEmpty(line.get(19)) ? null : format.parse(line.get(19)));//资产到欺日
            asset.setPushingResidualMaturity(StringUtils.isEmpty(line.get(20)) ? null : Double.valueOf(line.get(20)).intValue());//推送时候剩余期限
//
//            资本采购成本
            asset.setRate(StringUtils.isEmpty(line.get(21)) ? null : BigDecimal.valueOf(Double.valueOf(line.get(21)) * 100));
            asset.setSlottingAllowance(StringUtils.isEmpty(line.get(22)) ? null : BigDecimal.valueOf(Double.valueOf(line.get(22)) * 100));//通道费
            asset.setAssetBalanceRepeat(null);//资产剩余金额，再发布
            asset.setAssetBalance(StringUtils.isEmpty(line.get(23)) || "-".equals(line.get(23)) ? null : BigDecimal.valueOf(Double.valueOf(line.get(23))));//资产剩余金额，原始
            asset.setResidualMaturity(StringUtils.isEmpty(line.get(24)) ? 0 : Double.valueOf(line.get(24)).intValue());//资产剩余期限
            if (asset.getResidualMaturity() < 0) {
                asset.setResidualMaturity(0);
            }
            asset.setStatus(getAssetStatus(line.get(25)));
            asset.setBaseCreditorRightsContract(getBoolean(line.get(26)));
            asset.setCreditorRightsTransferApplication(getBoolean(line.get(27)));
            asset.setReceivableCreditTransferApplication(getBoolean(line.get(28)));
            asset.setCreditAssetTransferContract(getBoolean(line.get(29)));
            asset.setTransferMoneyCertificate(getBoolean(line.get(30)));
            asset.setCreditorRightsTransferProtocol(getBoolean(line.get(31)));//债权转让协议

            asset.setRemark(line.get(32));
            String pushCode = line.get(33);
            asset.setDelFlag(false);
            asset.setCreateTime(new Date());
            asset.setModifyTime(new Date());
            StringBuffer buffer = new StringBuffer();
            if (StringUtils.isEmpty(assetNo)) {
                buffer.append(pushCode)
                        .append(asset.getAssetCtype())
                        .append(asset.getAssetGtype())
                        .append(generateAssetCode())
                        .append(++seqNo);
            } else {
                buffer.append(pushCode)
                        .append(assetNo);
            }


            asset.setAssetsCode(buffer.toString());
            baseDao.insert("data.factoringAsset.insert", asset);
//            factoringAssetService.save(asset);
            assetNoIdMap.put(asset.getAssetsCode(), asset.getId());
        }

        if (productData != null && productData.size() > 0) {
            productData.remove(0);
        }

        Map<Long, String> productIdAssetCodeMap = new HashMap<>();
        for (List<String> line : productData) {
            Product product = new Product();
            product.setProductType(2);
            product.setDelFlag(0);

//            处理还款来源
            String refundSource = line.get(3);
            if ("其他".equals(refundSource)) {
                product.setRefundSource(1);
            } else {
                product.setRefundSource(2);//债务人还款
            }
            product.setProductName(line.get(4));
            product.setStatus(getProductStatus(line.get(5)));//状态
            String assetCode = line.get(6);
            product.setProductCode(line.get(8));//产品编号
            if (StringUtils.isEmpty(product.getProductCode())) {
                product.setProductCode(generateProductCode());
            }
            product.setIssueAmount(BigDecimal.valueOf(Double.valueOf(line.get(9))));//发标金额
            product.setSettleAmount(BigDecimal.valueOf(Double.valueOf(line.get(10))));//结标金额
            product.setRaiseStartDate(format.parse(line.get(11)));//募集起始日
            product.setRaiseEndDate(format.parse(line.get(12)));//募集结束日

            product.setValueDate(format.parse(line.get(13)));//起息日
            product.setProductDeadline(Double.valueOf(line.get(14)).intValue());//产品期限
            product.setExpiringDate(format.parse(line.get(15)));//产品到期日
            product.setPlanAnnualYield(BigDecimal.valueOf(Double.valueOf(line.get(22)) * 100)); //预计年化收益率

            product.setPlanCashDay(StringUtils.isEmpty(line.get(27)) ? null : format.parse(line.get(27)));//计划兑付日
            product.setExtendDeadline(StringUtils.isEmpty(line.get(30)) ? null : Integer.valueOf(line.get(30)));//宽限期
            product.setInterestPenaltyRate(StringUtils.isEmpty(line.get(31)) ? null :
                    BigDecimal.valueOf(Double.valueOf(line.get(31)) * 100));//罚息利率

            if (product.getExpiringDate() != null && product.getExtendDeadline() != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(product.getExpiringDate());
                calendar.add(Calendar.DAY_OF_YEAR, product.getExtendDeadline());
                product.setExpiringDate(calendar.getTime());//宽限期到期日
            }


            product.setMaxInvestTotalAmount(null);//最大投资总额
            product.setStartBidAmount(null);//起投金额
            product.setIncrementAmount(null);//递增金额
            product.setMaxInvestAmount(null);//最大投资额
            product.setMaxInvestNum(null);//最大投资次数
            product.setNterestMode(78);//计息方式（到期一次性支付，在系统dic表中的数据id，谨慎起见应该先查询）
            product.setContractTemplate(1);//合同模板
            product.setRepaymentAmount(StringUtils.isEmpty(line.get(26)) ? null : BigDecimal.valueOf(Double.valueOf(line.get(26))));//还款金额（资产方还本息）
            product.setFactraiseTime(null);//实际募集时间
            product.setFactknotTime(null);//实际结标时间
            product.setRepaymentTime(null);//实际还款时间
            product.setCreateTime(new Date());//创建时间
            product.setModifyTime(new Date());//更新时间
            product.setNewOrOldType(0);//产品新旧标志 0 新， 1 再
            product.setContinueStatus(null);//接续标志
            product.setContinueAmount(null);//已接续金额
            product.setPushPartyId(pushPartyMap.get(line.get(2)));//推送方id

//            productService.save(product);
            baseDao.insert("data.product.insert", product);
            String pushCode = line.get(2);

            if (StringUtils.isEmpty(assetCode)) {
                assetCode = "中间数据0000001";
            } else {
                assetCode = pushCode + assetCode;
            }
            assetNoProdIdMap.put(assetCode, product.getId());
            productIdAssetCodeMap.put(product.getId(), assetCode);
        }

        Iterator<Map.Entry<Long, String>> iterator = productIdAssetCodeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, String> entry = iterator.next();
            Long productId = entry.getKey();
            String assetCode = entry.getValue();
            Long assetId = assetNoIdMap.get(assetCode);

            FactoringProductRelation relation = new FactoringProductRelation();
            relation.setAssetId(assetId);
            relation.setProductId(productId);
//            relationService.save(relation);
            baseDao.insert("data.factoringProductRelation.insert", relation);
        }


//        Iterator<Map.Entry<String, Long>> iterator = assetNoProdIdMap.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, Long> entry = iterator.next();
//            String assetCode = entry.getKey();
//            Long productId = entry.getValue();
//            Long assetId = assetNoIdMap.get(assetCode);
//            FactoringProductRelation relation = new FactoringProductRelation();
//            relation.setAssetId(assetId);
//            relation.setProductId(productId);
////            relationService.save(relation);
//            baseDao.insert("data.factoringProductRelation.insert", relation);
//        }


//        logger.info("执行插入资产信息成功,准备抛出异常，回滚数据");
//        throw new Exception("抛出异常，保持数据不入库");
    }

    @Override
    public void addTongji() {
        logger.info("=======================================资产对标统计启动!=============================================");
        //获取票据相关列表
        List<ProductTongji> list = baseDao.selectList("data.productTongji.selectAllForBill");
        if (list != null) {
            baseDao.delete("data.AssetBenchmarkingStatisticsDao.deleteAll");
//            assetBenchmarkingStatisticsDao.deleteAll();
        }
        for (ProductTongji product : list) {
            AssetBenchmarkingStatistics assetBenchmarkingStatistics = new AssetBenchmarkingStatistics();
            assetBenchmarkingStatistics.setProductStatus(product.getStatus());
            assetBenchmarkingStatistics.setAssetStatus(product.getAssetStatus());
            assetBenchmarkingStatistics.setPushPartyCode(product.getPushPartyCode());
            assetBenchmarkingStatistics.setProductCode(product.getProductCode());
            assetBenchmarkingStatistics.setProductName(product.getProductName());
            assetBenchmarkingStatistics.setCreateTime(new Date());
            assetBenchmarkingStatistics.setModifyTime(new Date());
            assetBenchmarkingStatistics.setRefundSource(product.getRefundSource());
//            assetBenchmarkingStatistics.setProductStatus(product.getStatus());
            assetBenchmarkingStatistics.setIssueAmount(product.getIssueAmount());
//            assetBenchmarkingStatistics.setRaiseAmount(product.getSettleAmount());
//            assetBenchmarkingStatistics.setRaiseStartDate(product.getRaiseStartDate());
//            assetBenchmarkingStatistics.setRaiseEndDate(product.getRaiseEndDate());
//            assetBenchmarkingStatistics.setProductValueDate(product.getValueDate());
            assetBenchmarkingStatistics.setProductDeadline(product.getProductDeadline());
            assetBenchmarkingStatistics.setProductExpiringDate(product.getExpiringDate());
//            assetBenchmarkingStatistics.setPlanAnnualYield(product.getPlanAnnualYield());
//            assetBenchmarkingStatistics.setPlanCashDay(product.getExpiringDate());
//            assetBenchmarkingStatistics.setPracticalCashDay(product.getRepaymentTime());
//            assetBenchmarkingStatistics.setExtendDeadline(product.getExtendDeadline());
//            assetBenchmarkingStatistics.setRemark(product.getRemark());
            assetBenchmarkingStatistics.setProductType(product.getProductType());
            assetBenchmarkingStatistics.setNewOrOldType(product.getNewOrOldType());
            assetBenchmarkingStatistics.setPushPartyName(product.getOriginalDebtor());
            assetBenchmarkingStatistics.setPushPartyCusNum(product.getDebtorCusNum());
            assetBenchmarkingStatistics.setContractNum(product.getContractNum());
            assetBenchmarkingStatistics.setCreditorRightsTransferCost(product.getCreditorRightsTransferCost());
            assetBenchmarkingStatistics.setFactknotTime(product.getFactknotTime());
            assetBenchmarkingStatistics.setValueDate(product.getValueDate());
            if (product.getPassiveId() != null) {
//                替票的处理，都是保理资产，不用处理
//                BillAssets assets = getSourceAsset(product.getPassiveId());
//                assetBenchmarkingStatistics.setAssetCode(assets.getAssetsCode());
//                assetBenchmarkingStatistics.setAssetExpiringDate(assets.getBillRateDueDate());
//                assetBenchmarkingStatistics.setAssetRemainingDays(assets.getBillRemainingDays());
//                assetBenchmarkingStatistics.setInterest(assets.getAssetsInterest());
            } else {
                assetBenchmarkingStatistics.setAssetCode(product.getAssetsCode());
                assetBenchmarkingStatistics.setAssetExpiringDate(product.getBillRateDueDate());
                assetBenchmarkingStatistics.setAssetRemainingDays(product.getBillRemainingDays());
//                assetBenchmarkingStatistics.setInterest(product.getAssetsInterest());
            }
            baseDao.insert("data.AssetBenchmarkingStatisticsDao.insertSelective", assetBenchmarkingStatistics);
//            assetBenchmarkingStatisticsDao.insertSelective(assetBenchmarkingStatistics);
        }
    }

    /**
     * 最新版的导入旧数据
     *
     * @param assetDate
     * @param productData
     */
    @Override
    public void addBatch(List<List<String>> assetDate, List<List<String>> productData) throws Exception {
        List<Dic> dics = dicService.findByObject(null);
        Map<String, Long> dicMap = new HashMap<>();
        for(Dic dic: dics){
            dicMap.put(dic.getName(), dic.getId());
        }
        Map<String, Long> pushPartyOrgNameIdMap = new HashMap<>();
        Map<String, Long> codeIdMap = new HashMap<>();
        List<PushParty> pushParties = pushPartyService.findByObject(null);
        for(PushParty party: pushParties){
            pushPartyOrgNameIdMap.put(party.getPushPartyOgrName(), party.getId());
            codeIdMap.put(party.getPushPartyOrgCode(), party.getId());
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        assetDate.remove(0);
        int codeIndex = 1;


        Map<String, Long> assetCodeIdMap = new HashMap<>();
        for (List<String> line : assetDate) {
            FactoringAsset asset = new FactoringAsset();
            asset.setAssetsCode(line.get(1).equals("") ? codeIndex++ + "" : line.get(1));//资产编号
            asset.setAssetsType(line.get(2).equals("债权转让") ? dicMap.get("债权转让").intValue() : dicMap.get("应收账款").intValue());//资产类型
            asset.setAssetCtype(null);//子类型
            asset.setValueDate(line.get(4).equals("")? null: format.parse(line.get(4)));
            asset.setExpiringDate(line.get(5).equals("") ? null : format.parse(line.get(5)));
            asset.setNewDeadline(null);
            asset.setDeadline(line.get(7).equals("")? null: Integer.parseInt(line.get(7)));
            asset.setRate(line.get(8).equals("") ? BigDecimal.valueOf(0.00) : BigDecimal.valueOf(Double.valueOf(line.get(8))));
            asset.setRePricingMode(line.get(9));
            asset.setAssetAmount(BigDecimal.valueOf(Double.valueOf(line.get(10))));
//            asset.setInterestCalculation(dicMap.get(line.get(11)).byteValue());//计息方式
            asset.setInterestCalculation(dicMap.get("到期一次还本付息").byteValue());//计息方式
            asset.setGuaranteeMode(line.get(12));
            asset.setCreditor(line.get(13));
            asset.setDebtor(line.get(14));
            asset.setOriginalDebtor(line.get(15));
            asset.setSecuredParty(line.get(16));
            asset.setDebtorCusNum(line.get(17));
            asset.setContractNum(line.get(18));
//            asset.setPushParty();
            asset.setPushPartyOgrName(line.get(20));

            asset.setPushParty(pushPartyOrgNameIdMap.get(line.get(20)));
            asset.setPushDate(line.get(21).equals("") ? null: format.parse(line.get(21)));
            asset.setUseDate(line.get(22).equals("") ? null: format.parse(line.get(22)));
            asset.setAssetExpireDate(line.get(23).equals("") ? null : format.parse(line.get(23)));
            asset.setPushingResidualMaturity(line.get(24).equals("") ? null : Integer.valueOf(line.get(24)));
            asset.setCreditorRightsTransferCost(line.get(25).equals("") ? null : BigDecimal.valueOf(Double.valueOf(line.get(25))));
            asset.setSlottingAllowance(line.get(26).equals("") ? null :BigDecimal.valueOf(Double.valueOf(line.get(26))));
            asset.setAssetBalanceRepeat(line.get(27).equals("") ? null : BigDecimal.valueOf(Double.valueOf(line.get(27))));
            asset.setAssetBalance(line.get(28).equals("") ? null : BigDecimal.valueOf(Double.valueOf(line.get(28))));
            asset.setResidualMaturity(line.get(29).equals("") ? null :Integer.valueOf(line.get(29)));
            asset.setStatus((byte) 10);//已到期
            asset.setBaseCreditorRightsContract(line.get(31).equals("√"));
            asset.setCreditorRightsTransferApplication(line.get(32).equals("√"));
            asset.setReceivableCreditTransferApplication(line.get(33).equals("√"));
            asset.setCreditAssetTransferContract(line.get(34).equals("√"));
            asset.setTransferMoneyCertificate(line.get(35).equals("√"));
            asset.setCreditorRightsTransferProtocol(line.get(36).equals("√"));
            asset.setRemark(line.get(37));
            asset.setUseableBalance(line.get(38).equals("") ? BigDecimal.ZERO: BigDecimal.valueOf(Double.valueOf(line.get(38))));
            asset.setFreezeAmount(line.get(39).equals("") ? BigDecimal.ZERO : BigDecimal.valueOf(Double.valueOf(line.get(39))));
            asset.setPauseAmount(line.get(40).equals("") ? BigDecimal.ZERO: BigDecimal.valueOf(Double.valueOf(line.get(40))));
            asset.setDelFlag(false);
            asset.setCreateTime(new Date());
            asset.setModifyTime(new Date());
            asset.setRejectReason(line.get(44));
            asset.setPauseReason(line.get(45));
            asset.setAssetGtype(null);
            asset.setOriginalRate(line.get(47).equals("") ? null : BigDecimal.valueOf(Double.valueOf(line.get(47))));
            baseDao.insert("data.factoringAsset.insert", asset);
            assetCodeIdMap.put(asset.getAssetsCode(), asset.getId());
        }


        Map<String, Long> productCodeIdMap = new HashMap<>();
        Map<String, String> assetProductCodeMap = new HashMap<>();
        codeIndex = 1;
        for(List<String> line : productData){
            Product product = new Product();
            product.setProductCode(line.get(1).equals("") ? "BL" + codeIndex++ : line.get(1));
            product.setProductType(2);//1 票据， 2 保理
            product.setProductName(line.get(3));
            product.setIssueAmount(line.get(4).equals("")? BigDecimal.ZERO: BigDecimal.valueOf(Double.valueOf(line.get(4))));
            product.setProductDeadline(line.get(5).equals("")? null: Integer.valueOf(line.get(5)));
            product.setPlanAnnualYield(line.get(6).equals("")? null: BigDecimal.valueOf(Double.valueOf(line.get(6))));
            product.setRaiseStartDate(line.get(7).equals("") ? null: format.parse(line.get(7)));
            product.setRaiseEndDate(line.get(8).equals("")? null: format.parse(line.get(8)));
            product.setStartBidAmount(line.get(9).equals("") ? null: BigDecimal.valueOf(Double.valueOf(line.get(9))));
            product.setIncrementAmount(line.get(10).equals("") ? null: BigDecimal.valueOf(Double.valueOf(line.get(10))));
            product.setExtendDeadline(line.get(11).equals("") ? null : Integer.valueOf(line.get(11)));
            product.setValueDate(line.get(12).equals("")? null: format.parse(line.get(12)));
            product.setExpiringDate(line.get(13).equals("")? null :format.parse(line.get(13)));
            product.setExtendExpiringDate(line.get(14).equals("")?null :format.parse(line.get(14)));
            product.setNterestMode(dicMap.get(line.get(15)).intValue());//按天计息
            product.setContractTemplate(null);//合同模板
            product.setPlanCashDay(line.get(17).equals("")? null :format.parse(line.get(17)));
            product.setInterestPenaltyRate(line.get(18).equals("")? null : BigDecimal.valueOf(Double.valueOf(line.get(18))));
            product.setStatus(8);//已结清，对应系统已还清
            product.setSettleAmount(line.get(20).equals("") ? null : BigDecimal.valueOf(Double.valueOf(line.get(20))));
            product.setProductDesc(line.get(21));
            product.setProjectDetail(line.get(22));
            product.setRefundSource(line.get(23).equals("其他")? 1: 2);
            product.setRepaymentAmount(line.get(24).equals("") ? null : BigDecimal.valueOf(Double.valueOf(line.get(24))));
            product.setFactraiseTime(line.get(25).equals("") ? null : format.parse(line.get(25)));
            product.setFactknotTime(line.get(26).equals("")? null : format.parse(line.get(26)));
            product.setRepaymentTime(line.get(27).equals("") ? null : format.parse(line.get(27)));
            product.setCreateTime(new Date());
            product.setModifyTime(new Date());
            product.setDelFlag(0);
            product.setExt(line.get(31));
            product.setNewOrOldType(line.get(32).equals("过渡户")? 1:0);
            product.setContractUrl(line.get(33));
            product.setContinueStatus(line.get(34));
            product.setContinueAmount(line.get(35).equals("") ? null: BigDecimal.valueOf(Double.valueOf(line.get(35))));
            product.setPushPartyId(codeIdMap.get(line.get(36)));
            product.setBillType(null);
            product.setBillTypeName(null);
            product.setMaxInvestAmount(line.get(39).equals("") ? null : BigDecimal.valueOf(Double.valueOf(line.get(39))));
            product.setMaxInvestTotalAmount(line.get(40).equals("") ? null : BigDecimal.valueOf(Double.valueOf(line.get(40))));
            product.setMaxInvestNum(line.get(41).equals("") ? null : Integer.valueOf(line.get(41)));
            String assetCode = line.get(44);
            if(StringUtils.isNotEmpty(assetCode)){
                assetProductCodeMap.put(assetCode, product.getProductCode());
            }

            baseDao.insert("data.product.insert", product);
            productCodeIdMap.put(product.getProductCode(), product.getId());

            Iterator<String> iterator = assetProductCodeMap.keySet().iterator();
            while(iterator.hasNext()){
                String assetCodeInfo = iterator.next();
                String productCodeInfo = assetProductCodeMap.get(assetCodeInfo);
                Long assetId = assetCodeIdMap.get(assetCodeInfo);
                Long productId = productCodeIdMap.get(productCodeInfo);
                FactoringProductRelation relation = new FactoringProductRelation();
                relation.setProductId(productId);
                relation.setAssetId(assetId);
                baseDao.insert("data.factoringProductRelation.insert", relation);

            }


        }
    }

    private void initPushPartyInfo(Map<Long, String> pushPartyIdNameMap, Map<String, Long> pushPartyMap) {
        Map<String, Long> map = new HashMap<>();
        List<PushParty> list = pushPartyService.findByObject(null);
        for (PushParty party : list) {
            pushPartyMap.put(party.getPushPartyOrgCode(), party.getId());
            pushPartyIdNameMap.put(party.getId(), party.getPushPartyOgrName());
        }
    }

    private String getCtype(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        if (name.contains("（")) {
            name = name.split("（")[0];
        }
        return name;
    }

    private String getGtype(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        if (name.contains("（")) {
            name = name.split("（")[1].replace("）", "");
        } else {
            name = null;
        }
        return name;
    }

    private Boolean getBoolean(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        if ("×".equals(s)) {
            return false;
        }
        if ("√".equals(s)) {
            return true;
        }
        return null;
    }

    /**
     * 根据状态查询资产状态码
     *
     * @param s
     * @return
     */
    private Byte getAssetStatus(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("初始登记", 1);
        map.put("待审核", 3);
        map.put("待复核", 5);
        map.put("已入库", 7);
        map.put("已分配", 8);
        map.put("分配完成", 9);
        map.put("已到期", 10);
        map.put("暂停使用", 11);
        map.put("已还款", 12);
        if (!map.containsKey(s)) {
            return null;
        }
        return map.get(s).byteValue();
    }

    private Integer getProductStatus(String name) {
        if (StringUtils.isEmpty(name)) {
            return 9;
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("编辑中", -1);
        map.put("未推送(资管初始)", 0);
        map.put("已推送", 1);
        map.put("已安排", 2);
        map.put("产品投放", 3);
        map.put("满标", 4);
        map.put("流标", 5);
        map.put("已结算", 6);
        map.put("还款中", 7);
        map.put("已还清", 8);
        map.put("已存档", 9);
        map.put("撤回中", 10);
        map.put("已取消", 11);
        return map.get(name);
    }


    /**
     * 根据计息方式描述获取计息方式的id
     * 需要查询dic字典表
     *
     * @param s
     * @return
     */
    private Byte getInterestCalculation(String s) {
        if ("到期一次性支付".equals(s) || "到期一次还本付息".equals(s)) {
            return 78;
        }
        return 0;
    }


    private String generateAssetCode() {

        if (StringUtils.isEmpty(dateInfo)) {
            SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
            Date date = new Date();
            dateInfo = format.format(date);
        }
        return dateInfo;
    }

    private String generateProductCode() {
        if (StringUtils.isEmpty(productDate)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            productDate = format.format(new Date());
        }
        prodNo++;
        DecimalFormat decimalFormat = new DecimalFormat("0000");
        return productDate + decimalFormat.format(prodNo);
    }


}