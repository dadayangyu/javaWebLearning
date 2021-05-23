package org.example.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.example.exception.AppException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
    //类初始化的时候，创建连接池对象，及设置属性（使用同一个连接池复用连接对象）
    //多线程学完以后，调整为双重校验锁的单例模式 TODO
    //提供一个静态方法//代码重构技能:Refactor/Extract Method(把一段代码提取出来当做一个方法去使用)
    private  static MysqlDataSource ds=new MysqlDataSource();
    static{
        DataSource ds = new MysqlDataSource();  //每次创建新的连接池,所创建的对象都在不同的连接池中,所以解决方法是将对象放在同一个池中
        ((MysqlDataSource) ds).setUrl("jdbc:mysql://localhost:3306/servlet_blog");//连接到服务器端,localhost不用改,因为Tomcat上是本机上传到本地
        ((MysqlDataSource) ds).setUser("root");
        ((MysqlDataSource) ds).setCharacterEncoding("UTF-8");
        ((MysqlDataSource) ds).setPassword("948520");
        ((MysqlDataSource) ds).setUseSSL(false);

    }
    public static Connection getConnection() throws SQLException {
        /*DataSource ds = new MysqlDataSource();  //每次创建新的连接池,所创建的对象都在不同的连接池中,所以解决方法是将对象放在同一个池中
        ((MysqlDataSource) ds).setUrl("jdbc:mysql://localhost:3306/servlet_blog");
        ((MysqlDataSource) ds).setUser("root");
        ((MysqlDataSource) ds).setCharacterEncoding("UTF-8");
        ((MysqlDataSource) ds).setPassword("948520");
        ((MysqlDataSource) ds).setUseSSL(false);

         */
        Connection connection = ds.getConnection();
        System.out.println(connection);
        return connection;
    }
        public static void close(Connection c, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        }catch(SQLException e){
                    throw new AppException("DB002","数据库释放资源出错",e);
            }
            }
            //方法的重载
            public static void close(Connection c, PreparedStatement ps) {
                close(c, ps, null);
            }
}
