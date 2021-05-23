package org.example.Dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.example.model.Article;
import org.example.util.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao {
    private static List<Article> query;

    //查询文章
    public static List<Article> query(int userId) throws SQLException {
        //JDBC 查询用户关联的文章列表
        //1.创建链接：Connection对象:静态方法
        Connection c = DBUtil.getConnection();
        //2.根据连接创建操作命令对象Statement
        String sql = "select id,title from article where user_id <=> ?";//业务：根据用户的id查询文章
        PreparedStatement ps = c.prepareStatement(sql);//预编译工作
        //替换占位符：第一个参数表示占位符的索引（从1开始），第二个参数是要替换的值
        ps.setInt(1, userId);//parameterlndex:占位符索引
        //3.执行SQL,返回结果集对象
        ResultSet rs = ps.executeQuery();
        List<Article> list = new ArrayList<>();
        //4.如果是查询操作,要处理结果集
        while (rs.next()) {
            int id=rs.getInt("id");//获取ID字段的值
            String title=rs.getString("title");
            //每一行数据,对应一个对象
            Article a = new Article();
            a.setId(id);//获取ID字段的值
            a.setTitle(title);
            query.add(a);

        }
        //5.释放资源 这一部分最好写到finally里面,finally始终会被执行,这样避免资源的释放
        // TODO 在这之前的代码出问题,这里面的资源无法释放

        DBUtil.close(c, ps, rs);
        return query;
    }


    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws SQLException {
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    private static Connection getConnection() throws SQLException {
        DataSource ds = new MysqlDataSource();
        ((MysqlDataSource) ds).setUrl("jdbc:mysql://localhost:3306/servlet_blog");
        ((MysqlDataSource) ds).setUser("root");
        ((MysqlDataSource) ds).setPassword("948520");
        ((MysqlDataSource) ds).setUseSSL(false);
        Connection connection = ds.getConnection();
        System.out.println(connection);
        return connection;
    }

    public static void main(String[] args) throws SQLException {
        List<Article> a= query(1);
        System.out.println(a);
    }

    public static int insert(Article a,Integer userId) throws SQLException{
        Connection c=null;
        PreparedStatement ps=null;
        try{
            //1 获取数据库连接
            c=DBUtil.getConnection();
            //2 根据Connection+sql创建 操作命令对象
            String sql="insert into article(title,content,user_id) values (?,?,?)";
            ps=c.prepareStatement(sql);
            //3 先替换占位符的值,再执行SQL
            ps.setString(1,a.getTitle());
            ps.setString(2,a.getContent());
            ps.setInt(3,userId);
            //插入和删除都会调用executeUpdate的方法,返回值都为int
         return ps.executeUpdate();
//        }catch(Exception e){
//            e.printStackTrace();
        }finally {
            DBUtil.close(c,ps);
        }
    }
    public  static Article queryById(int id)throws SQLException{
        Connection c=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            //1获取对象
            c=DBUtil.getConnection();
            //2
            String sql="select * from article where id=?";
            ps=c.prepareStatement(sql);
            //3
            ps.setInt(1,id);
            //插入和删除都会调用executeUpdate的方法,返回值都为int
            rs=ps.executeQuery();
            //4
            Article a = null;
            while(rs.next()){
                a = new Article();
                a.setId(id);
                a.setTitle(rs.getString("title"));
                a.setContent(rs.getString("content"));
            }
            return a;
//        }catch(Exception e){
           // e.printStackTrace();
        }finally {
            c=DBUtil.getConnection();
        }
    }

    public static int update(Article a) throws SQLException {
        Connection c=null;
        PreparedStatement ps=null;
        try{
            //1 获取数据库连接
            c=DBUtil.getConnection();
            //2 根据Connection+sql创建 操作命令对象
            String sql="update article set title=?,content=? where id=?";
            ps=c.prepareStatement(sql);
            //3 先替换占位符的值,在执行SQL
            ps.setString(1,a.getTitle());
            ps.setString(2,a.getContent());
            ps.setInt(3,a.getId());
            //插入和删除都会调用executeUpdate的方法,返回值都为int
            return ps.executeUpdate();
//        }catch(Exception e){
//            e.printStackTrace();
        }finally {
            DBUtil.close(c,ps);
        }
    }

    public static int delete(String[] ids) throws SQLException {
        Connection c=null;
        PreparedStatement ps=null;
        try{
            //1获取对象
            c=DBUtil.getConnection();
            //2
           StringBuilder  sql=new StringBuilder("delete from article where id in (");//后面设计拼接占位符
            //"(?,?,?)"取决于每个数组的长度,拼接带占位符的SQL
            for(int i=0;i<ids.length;i++){
                if(i!=0){
                    sql.append(",");
                }else{
                    sql.append("?");
                }
                sql.append(")");
                ps=c.prepareStatement(sql.toString());
            }
                //3 先替换占位符的值,再执行SQL
                for(int i=0;i<ids.length;i++){
                    ps.setInt(i+1,Integer.parseInt(ids[i]));
                }
            //插入和删除都会调用executeUpdate的方法,返回值都为int
            return ps.executeUpdate();
//      //  }catch(Exception e){
//       //     e.printStackTrace();
        }finally {
            DBUtil.close(c,ps);
        }
    }
}