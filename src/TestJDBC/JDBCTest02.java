package TestJDBC;
import java.sql.*;

/*
 * 1.注册驱动
 * 2.获取连接
 * 3.获取数据库操作对象
 * 4.执行sql语句
 * 5.处理数据（如果有返回数据的话）
 * 6.释放资源
 */

public class JDBCTest02 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;
        try {
            //1，注册驱动
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //2，获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&serverTimezone=UTC",
                    "root",
                    "SHUXIN82111244822");
            //3，获取数据库操作对象
            statement = conn.createStatement();
            //4，执行sql语句
            String sql = "update websites set name = '腾讯云' where id = 7";
            int count = statement.executeUpdate(sql);
            System.out.println(count == 1? "删除成功" : "删除失败");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //6，释放资源
            if(statement != null){
                try {
                    statement.close();
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
