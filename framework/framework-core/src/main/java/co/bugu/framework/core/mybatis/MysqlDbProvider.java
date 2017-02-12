package co.bugu.framework.core.mybatis;

import org.apache.ibatis.scripting.xmltags.SqlNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daocers on 2017/2/8.
 */
public class MysqlDbProvider extends MapperTemplate {
    @Override
    public String dynamicSQL(Object object){

        List<SqlNode> sqlNodes = new ArrayList<>();
        return null;
    }

}
