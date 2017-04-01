package co.bugu.data.controller;

import co.bugu.data.model.Dic;
import co.bugu.data.service.IDataService;
import co.bugu.data.service.IDicService;
import co.bugu.framework.util.ExcelUtilNew;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/2/23.
 */
@Controller
@RequestMapping("/data")
public class DataController {
    @Autowired
    IDataService dataService;
    @Autowired
    IDicService dicService;

    private static Logger logger = LoggerFactory.getLogger(DataController.class);
    @RequestMapping("/test")
    public String test(){

        return "data/import";
    }

    @RequestMapping("/toImport")
    public String toImport(){
        return "data/import";
    }

    @RequestMapping("/import")
    public String processImport(MultipartFile file){


        File tarFile = new File(System.currentTimeMillis() + file.getOriginalFilename());
        try{
            file.transferTo(tarFile);

            List<List<String>> assetData = ExcelUtilNew.getData(tarFile);

            List<List<String>> productData = ExcelUtilNew.getData(tarFile, 1);

            dataService.addBatch(assetData, productData);
            dataService.addRelation(productData);
        }catch (Exception e){
            logger.error("处理数据失败", e);
        }finally {
            tarFile.delete();
        }
        return "redirect:toImport.do";

    }



    @RequestMapping("/tongji")
    @ResponseBody
    public String tongji(){
        dataService.addTongji();
        return "godos";
    }


}
