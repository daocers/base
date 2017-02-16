package co.bugu.framework.core.mybatis;

import co.bugu.framework.core.util.ReflectUtil;
import com.sun.org.apache.bcel.internal.generic.IDIV;
import com.sun.org.apache.regexp.internal.RE;
import jdk.nashorn.internal.ir.IfNode;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.scripting.xmltags.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

/**
 * Created by user on 2017/1/4.
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class SearchInterceptor implements Interceptor {
    private Logger logger = LoggerFactory.getLogger(SearchInterceptor.class);

    Map<Class, Map<String, String>> resultMappingInfo = new HashMap<>();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Long begin = System.currentTimeMillis();
        Map<String, Object> searchParameter = ThreadLocalUtil.get();
//        没有额外的查询参数
        if (searchParameter == null) {
            return invocation.proceed();
        }
        if (invocation.getTarget() instanceof StatementHandler) {
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
            // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环
            // 可以分离出最原始的的目标类)
            while (metaStatementHandler.hasGetter("h")) {
                Object object = metaStatementHandler.getValue("h");
                metaStatementHandler = SystemMetaObject.forObject(object);
            }
            // 分离最后一个代理对象的目标类
            while (metaStatementHandler.hasGetter("target")) {
                Object object = metaStatementHandler.getValue("target");
                metaStatementHandler = SystemMetaObject.forObject(object);
            }
//            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
//           没有初始化，新进行初始化操作。
            if(resultMappingInfo.size() == 0){
                initPropertyColumnMap(mappedStatement);

            }
            boolean sqlMode = false;
            Object parameterObject = null;
            String sql = "";
//            需要修改sql语句
            if(mappedStatement.getResultMaps().size() == 1){
                Class type = mappedStatement.getResultMaps().get(0).getType();
                BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
                parameterObject = boundSql.getParameterObject();
                // 分页参数作为参数对象parameterObject的一个属性
                sql = boundSql.getSql();
                String whereFrag = "";
                String newSql = "";
//            1 去掉where子句
                if(sql.toLowerCase().contains("where")){
                    newSql = sql.substring(0, sql.toLowerCase().indexOf("where"));
                    whereFrag = sql.substring(sql.toLowerCase().indexOf("where") + 5);
                }else{
                    newSql = sql;
                }

                List<SqlNode> ifNodes = new ArrayList<>();
                Iterator<String> iterator = searchParameter.keySet().iterator();
                while(iterator.hasNext()){
                    String key = iterator.next();
//                添加where子句
                    if(key.contains("_")){
                        String relation = key.split("_")[0];
                        String property = key.split("_")[1];

                        String column = resultMappingInfo.get(type).get(property);
//                        whereNodes.add(new TextSqlNode(processRelation(relation, column, property)));

                        StaticTextSqlNode staticTextSqlNode = new StaticTextSqlNode(processRelation(relation, column, property));
                        IfSqlNode ifSqlNode = new IfSqlNode(staticTextSqlNode, property + " != null");
                        ifNodes.add(ifSqlNode);
                    }else{
//                    不添加where子句，直接修改sql语句
                        if("SQL".equals(key)){
                            sqlMode = true;
                            sql = (String) searchParameter.get(key);
                        }else{
//                            无效参数，不处理
                            logger.info("无效的查询参数名称：{}", key);
                        }
                    }
                }


                DynamicContext dynamicContext = new DynamicContext(mappedStatement.getConfiguration(), parameterObject);

                List<SqlNode> content = new ArrayList<>();

                if(sqlMode){//直接修改sql语句模式
                    content.add(new StaticTextSqlNode(sql));
                }else{//添加查询参数模式
                    content.add(new StaticTextSqlNode(newSql));
                    content.add(new WhereSqlNode(mappedStatement.getConfiguration(), new MixedSqlNode(ifNodes)));
                }

//                content.add(new StaticTextSqlNode(ifNodes.size() > 0 ? " and " + whereFrag: whereFrag));
                MixedSqlNode mixedSqlNode = new MixedSqlNode(content);
                mixedSqlNode.apply(dynamicContext);
                sql = dynamicContext.getSql();
                logger.debug("最终的sql语句为: {}", sql);

                //动态修改
                DynamicSqlSource dynamicSqlSource = new DynamicSqlSource(mappedStatement.getConfiguration(), mixedSqlNode);
//                Field sqlSource = mappedStatement.getClass().getDeclaredField("sqlSource");
//                sqlSource.setAccessible(true);
//                sqlSource.set(mappedStatement, dynamicSqlSource);
                ReflectUtil.setValue(mappedStatement, "sqlSource", dynamicSqlSource);
            }else{
//                不需要处理sql语句
            }

            /**
             * 清除掉查询参数，防止对后续请求造成干扰
             */
            ThreadLocalUtil.remove();
            long end = System.currentTimeMillis();

            logger.debug("执行时长：{}毫秒", end - begin);
            // 将执行权交给下一个拦截器
            return invocation.proceed();
        } else if (invocation.getTarget() instanceof ResultSetHandler) {
            Object result = invocation.proceed();
            return result;
        }
        return invocation.proceed();
    }

    /**
     * 初始化property和column的对应关系
     * @param mappedStatement
     */
    private void initPropertyColumnMap(MappedStatement mappedStatement) {
        Collection<MappedStatement> mappedStatementList = mappedStatement.getConfiguration().getMappedStatements();
        Iterator iter = mappedStatementList.iterator();
        while(iter.hasNext()){
            Object obj = iter.next();
            if(obj instanceof MappedStatement){
                MappedStatement ms = (MappedStatement) obj;
                if(ms.getResultMaps().size() == 1){
                    Class type = ms.getResultMaps().get(0).getType();
                    Map<String, String> map = new HashMap<>();
                    List<ResultMapping> resultMappings = ms.getResultMaps().get(0).getResultMappings();
                    for(ResultMapping resultMapping: resultMappings){
                        if(resultMapping.getJdbcType() != null){
                            String column = resultMapping.getColumn();
                            String property = resultMapping.getProperty();
//                            boolean isId = false;
//                            if(resultMapping.getFlags().contains(ResultFlag.ID)){
//                                isId = true;
//                            }
                            map.put(property, column);
                        }
                    }
                    resultMappingInfo.put(type, map);
                }
            }
        }
    }

    /**
     * 根据传入的关系数值，获取到最终的sql关系
     * @param relation
     * @return
     */
    private String processRelation(String relation, String column, String property) throws Exception {
        String res = null;
        if(StringUtils.isNotEmpty(relation)){
            if("EQ".equals(relation)){
                res = "=";
            }
            if("NEQ".equals(relation)){
                res = "!=";
            }
            if("LT".equals(relation)){
//                res = "<![CDATA[<]]>";
                res = "<";
            }
            if("LTE".equals(relation)){
//                res = "<![CDATA[<=]]>";
                res = "<=";
            }
            if("GT".equals(relation)){
//                res = "<![CDATA[>]]>";
                res = ">";
            }
            if("GTE".equals(relation)){
//                res = "<![CDATA[>=]]>";
                res = ">=";
            }
            if("IN".equals(relation)){
                res = "in";
            }
            if("NIN".equals(relation)){
                res = "not in";
            }
            if("LK".equals(relation)){
                res = "like";
            }
            if(res == null){
                throw new Exception("不能识别的数据库关系");
            }

            if("IN".equals(relation) || "NIN".equals(relation)){
                res = " and " + column + " " +  res + " (#{" + property + "})";
            }else{
                res = " and " + column + " " + res + " #{" + property + "}";
            }
            return res;
        }else{
            throw new Exception("不能识别的数据库关系");
        }
    }

    @Override
    public Object plugin(Object target) {

        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String name = properties.getProperty("name");
    }


//    private void setPageParameter(String sql, Connection connection, MappedStatement mappedStatement,
//                                  BoundSql boundSql, Page page) {
//        // 记录总记录数
//        String countSql = "select count(0) from (" + sql + ")";
//        PreparedStatement countStmt = null;
//        ResultSet rs = null;
//        try {
//            countStmt = connection.prepareStatement(countSql);
//            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql,
//                    boundSql.getParameterMappings(), boundSql.getParameterObject());
//            setParameters(countStmt, mappedStatement, countBS, boundSql.getParameterObject());
//            rs = countStmt.executeQuery();
//            int totalCount = 0;
//            if (rs.next()) {
//                totalCount = rs.getInt(1);
//            }
//            page.setTotal(totalCount);
//            int totalPage = totalCount / page.getPageSize() + ((totalCount % page.getPageSize() == 0) ? 0 : 1);
//            page.setPages(totalPage);
//        } catch (SQLException e) {
//            logger.error("Ignore this exception", e);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                rs.close();
//            } catch (SQLException e) {
//                logger.error("Ignore this exception", e);
//            }
//            try {
//                countStmt.close();
//            } catch (SQLException e) {
//                logger.error("Ignore this exception", e);
//            }
//        }
//    }
}
