package co.bugu.generator.util;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.util.List;
import java.util.Properties;

/**
 * 代码生成
 *
 * @author duming
 */
public class CodeFactory {

    private static VelocityContext context = new VelocityContext();

    /**
     * 根据模板生成 action service dao add.htm
     *
     * @param pb
     * @throws Exception
     */
    public static void production(Parameter pb) throws Exception {

        String codePath = pb.getCodePath();// 代码输出路径
        String webPath = pb.getWebPath();// 页面输出路径


        Properties props = new Properties();
        props.put("input.encoding", "UTF-8");
        props.put("output.encoding", "UTF-8");
        Velocity.init(props);

//        String className = GenStringUtil.toUpperCase(pb.getTableName());
//        String variableName = GenStringUtil.toVariableName(pb.getTableName());
//        //去掉前缀
//        if(variableName.indexOf(pb.getTablePrefix()) == 0){
//            className = GenStringUtil.toUpperCase(pb.getTableName().replace(pb.getTablePrefix(), ""));
//            variableName = GenStringUtil.toVariableName(pb.getTableName().replace(pb.getTablePrefix(), ""));
//        }

        String className = pb.getClassName();
        context.put("mainPkg", pb.getFullPack());
        context.put("rootPkg", pb.getRootPackName());
        context.put("className", pb.getClassName());
        context.put("variableName", pb.getVariableName());
        context.put("webDir", pb.getWebDir());
        context.put("prefix", pb.getPrefix());

        context.put("mapperNameSpace", pb.getNsName());
        context.put("nmPrefix", pb.getNsName());
        // 根据实体类遍历方法
        DatabaseUtil.init(pb.getHost(), pb.getUser(), pb.getPwd(), pb.getDb());
        List<String> fields = DatabaseUtil.execute(pb.getTableName());
        context.put("fields", fields);
        if(pb.getApiVersion() == null || pb.getApiVersion().equals("1") || pb.getApiVersion().equals("")){
            pb.setApiVersion("");
        }
        context.put("apiVersion", pb.getApiVersion());

        // CODE
        String codePackPath = codePath + GenStringUtil.toPkgPath(pb.getFullPack());
//        MergeUtil.outputCode(context, "/code/dao.vm", codePackPath + "/dao/", className + "Mapper.java");
        MergeUtil.outputCode(context, "/code/service-bugu.vm", codePackPath + "/service/", "I" + className + "Service.java");
        MergeUtil.outputCode(context, "/code/serviceImpl-bugu.vm", codePackPath + "/service/impl/", className + "ServiceImpl.java");

        if (!"api".equals(pb.getProType())) {
            MergeUtil.outputCode(context, "/code/action-bugu.vm", codePackPath + "/controller/", className + "Controller.java");
            MergeUtil.outputCode(context, "/page/list-bugu.vm", webPath + pb.getWebDir() + "/", "list.jsp");
            MergeUtil.outputCode(context, "/page/edit-bugu.vm", webPath + pb.getWebDir() + "/", "edit.jsp");
//            MergeUtil.outputCode(context, "/page/add.vm", webPath + pb.getWebDir() + "/", "add.vm");
//            MergeUtil.outputCode(context, "/page/modify.vm", webPath + pb.getWebDir() + "/", "edit.vm");

//            MergeUtil.outputCode(context, "/page/list.vm", webPath + pb.getWebDir() + "/", pb.getPrefix() + "_list.vm");
//            MergeUtil.outputCode(context, "/page/add.vm", webPath + pb.getWebDir() + "/", pb.getPrefix() + "_add.vm");
//            MergeUtil.outputCode(context, "/page/modify.vm", webPath + pb.getWebDir() + "/", pb.getPrefix() + "_modify.vm");
        } else {
            MergeUtil.outputCode(context, "/code/action-api-bugu.vm", codePackPath + "/controller/", className + "Controller.java");
        }


        //resources
        FileUtil.create(pb.getResources() + "\\mapper");


    }

}
