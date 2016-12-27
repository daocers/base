package co.bugu.generator.util;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

/**
 * 数据和模板合并
 *
 * @author duming
 */
public class MergeUtil {
    public static void outputCode(VelocityContext context, String templateFile, String path, String fileName) throws Exception {
        File file = new File(path);
        file.mkdirs();

        String fileDir = MergeUtil.class.getResource("/templates").getPath();
        VelocityEngine ve = new VelocityEngine();
        Properties properties = new Properties();
        properties.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, fileDir);
        properties.put("input.encoding", "UTF-8");
        properties.put("output.encoding", "UTF-8");
        ve.init(properties);
        Template template = ve.getTemplate(templateFile);


        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + fileName), "UTF-8"));
        if (template != null) {
            template.merge(context, writer);
        }
        writer.flush();
        writer.close();
        System.out.println(path + fileName);
    }
}
