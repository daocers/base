package co.bugu.data.service.impl;

import co.bugu.data.model.*;
import co.bugu.data.service.*;
import co.bugu.framework.core.dao.BaseDao;
import co.bugu.framework.core.service.IBaseService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by user on 2017/2/23.
 */
@Service
public class DataServiceImpl implements IDataService {
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
    public void add(List<List<String>> assetData, List<List<String>> productData) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Integer> type = new HashMap<>();
        Map<String, Integer> cType = new HashMap<>();
        Map<String, Long> assetNoIdMap = new HashMap<>();
        Map<String, Long> assetNoProdIdMap = new HashMap<>();
        Map<Long, String> pushPartyIdNameMap = new HashMap<>();

        Map<String, Long> pushPartyMap = new HashMap<>();
        initPushPartyInfo(pushPartyIdNameMap, pushPartyMap);

        Dic dic = new Dic();
        dic.setName("内部资产");
        dic = (Dic) baseDao.selectOne("data.dic.findByObject", dic);
        Long id = dic.getId();
        dic = new Dic();
        dic.setPid(id);
        List<Dic> dics = baseDao.selectList("data.dic.findByObject", dic);
        for (Dic dic1 : dics) {
            type.put(dic1.getName(), dic1.getId().intValue());
            dic = new Dic();
            dic.setPid(dic1.getId());
            List<Dic> dics1 = baseDao.selectList("data.dic.findByObject", dic);
            for (Dic dic2 : dics1) {
                cType.put(dic2.getName(), dic2.getId().intValue());
            }
        }

        if (assetData != null && assetData.size() > 0) {
            assetData.remove(0);
        }
        for (List<String> line : assetData) {
            String assetNo = line.get(1);
            if (StringUtils.isEmpty(assetNo)) {
                logger.warn("资产编号为空，数据行为： {}", JSON.toJSONString(line, true));
                continue;
            }
            FactoringAsset asset = new FactoringAsset();
            asset.setAssetsCode(assetNo);
            asset.setAssetsType(type.get(getType(line.get(2))));//资产类型
            asset.setAssetCtype(cType.get(getCtype(line.get(2))));//子类型
            asset.setValueDate(StringUtils.isEmpty(line.get(3)) ? null : format.parse(line.get(3)));
            asset.setExpiringDate(StringUtils.isEmpty(line.get(4)) ? null : format.parse(line.get(4)));
            asset.setNewDeadline(0);//资产到期日 - 第一个新产品的起息日
            asset.setDeadline(StringUtils.isEmpty(line.get(5)) ? null : Double.valueOf(line.get(5)).intValue());//资产原始期限
            asset.setRate(StringUtils.isEmpty(line.get(6)) ? null : BigDecimal.valueOf(Double.valueOf(line.get(6)) * 100));
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
            if(StringUtils.isEmpty(line.get(17))){
                asset.setPushPartyOgrName(pushPartyIdNameMap.get(asset.getPushParty()));
            }
            asset.setPushDate(StringUtils.isEmpty(line.get(18)) ? null : format.parse(line.get(18)));//资产推送日期
            asset.setUseDate(null);
            asset.setAssetExpireDate(StringUtils.isEmpty(line.get(19)) ? null : format.parse(line.get(19)));//资产到欺日
            asset.setPushingResidualMaturity(StringUtils.isEmpty(line.get(20)) ? null : Double.valueOf(line.get(20)).intValue());//推送时候剩余期限
//
//            资本采购成本
            asset.setOriginalRate(StringUtils.isEmpty(line.get(21)) ? null : BigDecimal.valueOf(Double.valueOf(line.get(21)) * 100));
            asset.setSlottingAllowance(StringUtils.isEmpty(line.get(22)) ? null : BigDecimal.valueOf(Double.valueOf(line.get(22)) * 100));//通道费
            asset.setAssetBalanceRepeat(null);//资产剩余金额，再发布
            asset.setAssetBalance(StringUtils.isEmpty(line.get(23)) || "-".equals(line.get(23)) ? null : BigDecimal.valueOf(Double.valueOf(line.get(23))));//资产剩余金额，原始
            asset.setResidualMaturity(StringUtils.isEmpty(line.get(24)) ? null : Double.valueOf(line.get(24)).intValue());//资产剩余期限
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
            asset.setDelFlag(false);
            asset.setCreateTime(new Date());
            asset.setModifyTime(new Date());

            baseDao.insert("data.factoringAsset.insert", asset);
//            factoringAssetService.save(asset);
            assetNoIdMap.put(assetNo, asset.getId());
        }

        if (productData != null && productData.size() > 0) {
            productData.remove(0);
        }
        for (List<String> line : productData) {
            Product product = new Product();
            product.setProductType(1);
            product.setDelFlag(0);
            String shoukuanren = line.get(1);

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
            product.setProductCode(line.get(7));
            if (StringUtils.isEmpty(product.getProductCode())) {
                continue;
            }
            product.setIssueAmount(BigDecimal.valueOf(Double.valueOf(line.get(8))));//发标金额
            product.setSettleAmount(BigDecimal.valueOf(Double.valueOf(line.get(9))));//结标金额
            product.setRaiseStartDate(format.parse(line.get(10)));//募集起始日
            product.setRaiseEndDate(format.parse(line.get(11)));//募集结束日

            product.setValueDate(format.parse(line.get(12)));//起息日
            product.setProductDeadline(Double.valueOf(line.get(13)).intValue());//产品期限
            product.setExpiringDate(format.parse(line.get(14)));//产品到期日
            product.setPlanAnnualYield(BigDecimal.valueOf(Double.valueOf(line.get(21)) * 100)); //预计年化收益率

            product.setPlanCashDay(StringUtils.isEmpty(line.get(24)) ? null : format.parse(line.get(24)));//计划兑付日
            product.setExtendDeadline(StringUtils.isEmpty(line.get(26)) ? null : Integer.valueOf(line.get(26)));//宽限期
            product.setInterestPenaltyRate(StringUtils.isEmpty(line.get(27)) ? null :
                    BigDecimal.valueOf(Double.valueOf(line.get(27)) * 100));//罚息利率

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
            product.setRepaymentAmount(BigDecimal.valueOf(Double.valueOf(line.get(22))));//还款金额
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
            assetNoProdIdMap.put(assetCode, product.getId());
        }

        Iterator<Map.Entry<String, Long>> iterator = assetNoProdIdMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Long> entry = iterator.next();
            String assetCode = entry.getKey();
            Long productId = entry.getValue();
            Long assetId = assetNoIdMap.get(assetCode);
            FactoringProductRelation relation = new FactoringProductRelation();
            relation.setAssetId(assetId);
            relation.setProductId(productId);
//            relationService.save(relation);
            baseDao.insert("data.factoringProductRelation.insert", relation);
        }


//        logger.info("执行插入资产信息成功,准备抛出异常，回滚数据");
//        throw new Exception("抛出异常，保持数据不入库");
    }

    private void initPushPartyInfo(Map<Long, String> pushPartyIdNameMap, Map<String, Long> pushPartyMap) {
        Map<String, Long> map = new HashMap<>();
        List<PushParty> list = pushPartyService.findByObject(null);
        for (PushParty party : list) {
            pushPartyMap.put(party.getPushPartyOrgCode(), party.getId());
            pushPartyIdNameMap.put(party.getId(), party.getPushPartyOgrName());
        }
    }

    private String getType(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        if (name.contains("（")) {
            name = name.split("（")[0];
        }
        return name;
    }

    private String getCtype(String name) {
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
        map.put("已过期", 10);
        map.put("暂停使用", 11);
        map.put("已还款", 12);
        if (!map.containsKey(s)) {
            return null;
        }
        return map.get(s).byteValue();
    }

    private Integer getProductStatus(String name) {
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
        if ("到期一次性支付".equals(s)) {
            return 78;
        }
        return 0;
    }

}
