package co.bugu.generator.util;

import java.io.*;

/**
 * 精简mapper.xml 与模板中mapper.java文件匹配 完成分页
 *
 * @author duming
 */
public class XmlUtil {

    /**
     * 生成mbn配置文件
     *
     * @param pb
     * @return
     */
    public static boolean createMBGXml(Parameter pb) {
        try {
            System.out.println("---MBG is creating");
            InputStream is = FileUtil.getStream(FileUtil.getAbsPath() + "/src/main/resources/templates/generatorConfig_temp.xml");
            System.out.println("---MBG get template：" + FileUtil.getAbsPath() + "/src/main/resources/generate/templates/generatorConfig_temp.xml");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String linetext = "";
            StringBuffer sbf = new StringBuffer();
            while ((linetext = br.readLine()) != null) {
                sbf.append(linetext);
                sbf.append("\r\n");
            }
            String context = sbf.toString();
            System.out.println("---MBG setting");
            context = context.replace("t_mysqlDriver", GenStringUtil.toJavaPath(FileUtil.getAbsPath()));// 替换mysql驱动
            context = context.replace("t_ip", pb.getHost());// 数据库IP
            context = context.replace("t_db", pb.getDb());// 数据库
            context = context.replace("t_user", pb.getUser());// 用户名
            context = context.replace("t_pwd", pb.getPwd());// 密码
            context = context.replace("t_package", pb.getRootPackName() + "." + pb.getBusinessName());// 包
            context = context.replace("t_table", pb.getTableName());// 表
//            context = context.replace("t_class", GenStringUtil.toUpperCase(pb.getTableName()));// 类
            context = context.replace("t_class", pb.getClassName());// 类
            context = context.replace("target_project", pb.getCodePath());
            context = context.replace("t_resources", pb.getResources());
            context = context.replace("t_alias", pb.getAlias());

            context = context.replace("t_namespace", pb.getNsName());
            context = context.replace("t_tableName", pb.getTableName());
            context = context.replace("t_variable", pb.getVariableName());

            System.out.println("      -driver：" + GenStringUtil.toJavaPath(FileUtil.getAbsPath()) + "\\lib\\mysql-connector-java-5.1.36.jar");
            System.out.println("      -connection：" + "jdbc:mysql://" + pb.getHost() + ":3306/" + pb.getDb() + "?user=" + pb.getUser() + "&password=" + pb.getPwd());
            System.out.println("      -mapper：" + "Table:" + pb.getTableName() + " ClassInfo:" + GenStringUtil.toUpperCase(pb.getTableName()));
            saveFile(FileUtil.getAbsPath() + "/src/main/resources/", "generatorConfig.xml", context);
            System.out.println("---MBG create end：" + FileUtil.getAbsPath() + "/mybatis-generator.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 规范化mybatis生成的mapper文件
     *
     * @param spath
     * @param tPath
     * @param name
     * @return
     */
    public static boolean reloadMapperXml(String spath, String tPath, String name, Parameter pb) {
        try {
            String linetext = "";
            String resultMapText = "";
            StringBuffer sbf = new StringBuffer();
            File f = new File(spath + name);
            BufferedReader br = new BufferedReader(new FileReader(f));
            int lineNum = 1;
            while ((linetext = br.readLine()) != null) {
                sbf.append(linetext);
                sbf.append("\r\n");
                if (lineNum == 4) {
                    resultMapText = linetext;
                }
                lineNum++;
            }
            br.close();
            String text = sbf.toString();

            if (text.contains("queryPage")) {
                return false;
            }

            int si = text.indexOf("<insert id=\"insert\"");
            int ei = text.indexOf("</insert>");
            String noinsert = text.substring(si, ei + 9);
            text = text.replace(noinsert, "");

            int su = text.indexOf("<update id=\"updateByPrimaryKey\"");
            int eu = text.indexOf("</update>", su);
            if (su > 0 && eu > 0) {
                String noupdate = text.substring(su, eu + 9);
                text = text.replace(noupdate, "");
            }

            text = text.replace("selectByPrimaryKey", "selectById");
            text = text.replace("deleteByPrimaryKey", "deleteById");
            text = text.replace("insertSelective\"", "insert\"  useGeneratedKeys=\"true\" keyProperty=\"id\" ");
            text = text.replace("updateByPrimaryKeySelective", "updateById");

            int sp = text.indexOf("<select");
            int ep = text.indexOf("</select>");

            String pageSql = "";
            if (sp > 0 && ep > 0) {
                pageSql = text.substring(sp, ep + 9);
            }else{
                pageSql = text;
            }
            pageSql = pageSql.replace("selectById", "findByObject");
            pageSql = pageSql.substring(0, pageSql.indexOf("where"));
            pageSql = "\t" + pageSql + "</select>";

            int sc = resultMapText.indexOf("type=");
            if (sc > 0) {
                resultMapText = resultMapText.substring(sc + 6);
            }

            int ec = resultMapText.lastIndexOf("\"");
            if (ec > 0) {
                resultMapText = resultMapText.substring(0, ec);
            }

            pageSql = pageSql.replace("java.lang.Integer", resultMapText);


            String regex = "namespace=\"\\S+\"";

            text = text.replace("</mapper>", "");

            StringBuffer newSql = new StringBuffer(text);
            newSql.append(pageSql);
            newSql.append("\n</mapper>");

            String finalSql = newSql.toString();
            finalSql = finalSql.replaceAll(regex, "namespace=\"" + pb.getNsName() + "." + pb.getVariableName() + "\"");
            finalSql = finalSql.replaceAll("BaseResultMap", pb.getClassName() + "Map");
            finalSql = finalSql.replaceAll("Base_Column_List", GenStringUtil.toVariableName(pb.getTableName()) + "_list");

            saveFile(tPath, name, finalSql.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void saveFile(String tPath, String name, String text) {
        try {
            File file = new File(tPath);
            file.mkdirs();
            FileWriter fw = new FileWriter(tPath + name, false);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
