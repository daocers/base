package co.bugu.threadLocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by daocers on 2016/10/26.
 */
public class DBUtil {
    private static final String driver = "";
    private static final String url = "";
    private static final String username = "";
    private static final String password = "";

    private static Connection connection = null;

    public static Connection getConnection(){
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(){
        try{
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
