package co.bugu.data.service.impl;

import co.bugu.data.model.Dic;
import co.bugu.data.model.FactoringAsset;
import co.bugu.data.service.IDataService;
import co.bugu.data.service.IDicService;
import co.bugu.data.service.IFactoringAssetService;
import co.bugu.data.service.IProductService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by user on 2017/2/23.
 */
@Service
public class DataServiceImpl implements IDataService{
    @Autowired
    IFactoringAssetService factoringAssetService;
    @Autowired
    IDicService dicService;
    @Autowired
    IProductService productService;

    private static Logger logger = LoggerFactory.getLogger(IDataService.class);

    @Override
    public void add(List<List<String>> assetData, List<List<String>> productData) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Integer> type = new HashMap<>();
        Map<String, Integer> cType = new HashMap<>();
        Map<String, Long> assetNoIdMap = new HashMap<>();

        Dic dic = new Dic();
        dic.setName("内部资产");
        List<Dic> dics = dicService.findByObject(dic);
        if(dics.size() != 1){
            logger.error("数据字典信息异常");
        }
        dic = new Dic();
        dic.setPid(dics.get(0).getId());
        dics = dicService.findByObject(dic);
        for(Dic dic1: dics){
            type.put(dic1.getName(), dic1.getId().intValue());
            dic = new Dic();
            dic.setPid(dic1.getId());
            List<Dic> dics1 = dicService.findByObject(dic);
            for(Dic dic2: dics1){
                cType.put(dic2.getName(), dic2.getId().intValue());
            }
        }

        assetData.remove(0);
        for(List<String> line: assetData){
            String assetNo = line.get(1);
            if(StringUtils.isEmpty(assetNo)){
                logger.warn("资产编号为空，数据行为： {}", JSON.toJSONString(line, true));
            }
            FactoringAsset asset = new FactoringAsset();
            asset.setAssetsCode(assetNo);
            asset.setAssetsType(type.get(getType(line.get(2))));//资产类型
            asset.setAssetCtype(cType.get(line.get(2)));//子类型
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
            asset.setPushParty(getPushPartyId(line.get(17)));
            asset.setPushPartyOgrName(line.get(17));
            asset.setPushDate(StringUtils.isEmpty(line.get(18)) ? null : format.parse(line.get(18)));//资产推送日期
            asset.setUseDate(null);
            asset.setAssetExpireDate(StringUtils.isEmpty(line.get(19)) ? null : format.parse(line.get(19)));//资产到欺日
            asset.setPushingResidualMaturity(StringUtils.isEmpty(line.get(20)) ? null : Double.valueOf(line.get(20)).intValue());//推送时候剩余期限
//
//            资本采购成本
            asset.setOriginalRate(StringUtils.isEmpty(line.get(21)) ? null : BigDecimal.valueOf(Double.valueOf(line.get(21)) * 100));
            asset.setSlottingAllowance(StringUtils.isEmpty(line.get(22)) ? null :BigDecimal.valueOf(Double.valueOf(line.get(22)) * 100));//通道费
            asset.setAssetBalanceRepeat(null);//资产剩余金额，再发布
            asset.setAssetBalance(StringUtils.isEmpty(line.get(23)) || "-".equals(line.get(23)) ? null : BigDecimal.valueOf(Double.valueOf(line.get(23))));//资产剩余金额，原始
            asset.setResidualMaturity(StringUtils.isEmpty(line.get(24)) ? null : Double.valueOf(line.get(24)).intValue());//资产剩余期限
            if(asset.getResidualMaturity() < 0){
                asset.setResidualMaturity(0);
            }
            asset.setStatus(getStatus(line.get(25)));
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



            factoringAssetService.save(asset);
            assetNoIdMap.put(assetNo, asset.getId());


        }
    }

    private void processType(String name, FactoringAsset asset){
        Map<String, Integer> type = new HashMap<>();
        Map<String, Integer> ctype = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("保理（小贷转博盛）");
        list.add("保理（小贷转信达）");
        list.add("保理资产（信达）");
        list.add("博盛保理");
        list.add("天津博盛");
        list.add("小贷转博盛");
        list.add("信达保理");
        list.add("债权转让");


    }

    private String getType(String name){
        if(StringUtils.isEmpty(name)){
            return null;
        }
        if(name.contains("（")){
            name = name.split("（")[0];
        }
        return name;
    }

    private String getCtype(String name){
        if(StringUtils.isEmpty(name)){
            return null;
        }
        if(name.contains("（")){
            name = name.split("（")[1];
        }else{
            name = null;
        }
        return name;
    }

    private Boolean getBoolean(String s) {
        if(StringUtils.isEmpty(s)){
            return null;
        }
        if("×".equals(s)){
            return false;
        }
        if("√".equals(s)){
            return true;
        }
        return null;
    }

    /**
     * 根据状态查询状态码
     * @param s
     * @return
     */
    private Byte getStatus(String s) {
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
        if(!map.containsKey(s)){
            return null;
        }
        return map.get(s).byteValue();
    }

    /**
     * 根据推送方名称返回推送方id
     * 需要查询dic字典表
     * @param s
     * @return
     */
    private Long getPushPartyId(String s) {
        return 0L;
    }

    /**
     * 根据计息方式描述获取计息方式的id
     * 需要查询dic字典表
     * @param s
     * @return
     */
    private Byte getInterestCalculation(String s) {
        if("到期一次性支付".equals(s)){
            return 78;
        }
        return 0;
    }

}
