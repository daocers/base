package co.bugu.generator.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取数据表属性
 *
 * @author duming
 */
public class DatabaseUtil {

    private static String t_url;
    private static String t_user;
    private static String t_pwd;

    public static void init(String ip, String user, String pwd, String db) {
        t_url = "jdbc:mysql://" + ip + ":3306/" + db + "?useUnicode=true&characterEncoding=UTF8";
        t_user = user;
        t_pwd = pwd;
    }


    public static List<String> execute(String table) {
        Connection conn = null;
        List<String> fields = new ArrayList<String>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(t_url, t_user, t_pwd);
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("desc " + table);
            while (rs.next()) {
                if (!rs.getString(1).contains("gmt") && !"id".equals(rs.getString(1))) {
                    fields.add(GenStringUtil.toLowerCase(rs.getString(1)));
                }
            }
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return fields;
    }

    public static void main(String args[]) {
        DatabaseUtil.init("127.0.0.1", "root", "123456", "test");
        DatabaseUtil.execute("product");
    }
}