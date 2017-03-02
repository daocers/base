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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            file.transferTo(tarFile);
            Map<String, Integer> type = new HashMap<>();
            Map<String, Integer> cType = new HashMap<>();
            Dic dic = new Dic();
            dic.setName("内部资产");
            List<Dic> list =  dicService.findByObject(dic);
            if(list.size() > 0){
                dic = list.get(0);
            }

            Long id = dic.getId();
            dic = new Dic();
            dic.setPid(id);
            List<Dic> dics = dicService.findByObject(dic);
            for (Dic dic1 : dics) {
                type.put(dic1.getName(), dic1.getId().intValue());
                dic = new Dic();
                dic.setPid(dic1.getId());
                List<Dic> dics1 = dicService.findByObject(dic);
                for (Dic dic2 : dics1) {
                    cType.put(dic2.getName(), dic2.getId().intValue());
                }
            }

            List<List<String>> assetData = ExcelUtilNew.getData(tarFile);

            List<List<String>> productData = ExcelUtilNew.getData(tarFile, 1);
//            logger.debug("type信息： {}", JSON.toJSONString(type, true));
//            logger.debug("ctype信息： {}", JSON.toJSONString(cType, true));
//            logger.debug("资产信息：{}", JSON.toJSONString(assetData, true));
//            logger.debug("产品信息：{}", JSON.toJSONString(productData, true));
            dataService.add(assetData, productData, type, cType);
//            dataService.add(null, null);
        }catch (Exception e){
            logger.error("处理数据失败", e);
        }finally {
            tarFile.delete();
        }
        return "redirect:toImport.do";

    }



}
