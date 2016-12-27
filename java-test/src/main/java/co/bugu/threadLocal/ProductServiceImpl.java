package co.bugu.threadLocal;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by daocers on 2016/10/26.
 */
public class ProductServiceImpl implements ProductService {
    @Override
    public void updateProductPrice(long productId, int price) {
        try{
            Connection connection = DBUtil.getConnection();
            connection.setAutoCommit(false);
            
            updateProduct(connection, "sql", productId, price);
            insertLog(connection, "insert sql", "create product");
            
            connection.commit();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection();
        }
    }

    private void insertLog(Connection connection, String s, String s1) {
    }

    private void updateProduct(Connection connection, String sql, long productId, int price) {
    }
}
