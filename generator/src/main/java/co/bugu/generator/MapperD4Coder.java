package co.bugu.generator;


import co.bugu.generator.util.*;

public class MapperD4Coder {

    public static void main(String[] args) throws Exception {

//        ParamterBean pb = new ParamterBean();
//        GeneratorPropertiesUtil.init(pb);
        Parameter pb = null;

        // 整理mapper
        String codePackPath = pb.getCodePath() + GenStringUtil.toPkgPath(pb.getFullPack());
        XmlUtil.reloadMapperXml(codePackPath + "/dao/", pb.getResources() + "/mapper/", GenStringUtil.toUpperCase(pb.getTableName()) + "Mapper.xml", pb);

        //删除原xml
        FileUtil.del(codePackPath + "/dao/" + GenStringUtil.toUpperCase(pb.getTableName()) + "Mapper.xml");

    }
}
