package co.bugu.data.controller;

import co.bugu.data.model.Dic;
import co.bugu.data.model.FactoringAsset;
import co.bugu.data.service.*;
import co.bugu.framework.util.ExcelUtilNew;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by user on 2017/2/23.
 */
@Controller
@RequestMapping("/data")
public class DataController {
    @Autowired
    IDataService dataService;

    private static Logger logger = LoggerFactory.getLogger(DataController.class);
    @RequestMapping("/toImport")
    public String toImport(){
        return "data/import";
    }

    @RequestMapping("/import")
    public String processImport(MultipartFile file){


        File tarFile = new File(file.getOriginalFilename());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            file.transferTo(tarFile);
            List<List<String>> assetData = ExcelUtilNew.getData(tarFile);

//            List<List<String>> productData = ExcelUtilNew.getData(tarFile, 1);
            logger.debug("资产信息：{}", JSON.toJSONString(assetData, true));
//            logger.debug("产品信息：{}", JSON.toJSONString(productData, true));
            dataService.add(assetData, null);
        }catch (Exception e){
            logger.error("处理数据失败", e);
        }finally {
            tarFile.delete();
        }
        return "redirect:toImport.do";

    }



}
