package TestJDBC;
import com.mysql.jdbc.Driver;

import java.sql.*;

public class JDBCTest01 {
    public static void main(String[] args) {
        Statement statement = null;
        Connection conn = null;
        Driver driver = null;
        try {
            driver = new Driver();
            DriverManager.registerDriver(driver);
            String url = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false";
            String user = "root";
            String password = "SHUXIN82111244822";
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("数据库连接对象"+conn);
            statement = conn.createStatement();
            String sql = "insert into websites (id, name, url, alexa, country)\n" +
                    "values (\n" +
                    "        7,\n" +
                    "        '腾讯云',\n" +
                    "        'https://www.cloud.tencent.com',\n" +
                    "        9999,\n" +
                    "        'CHINA'\n" +
                    "       );";
            int count = statement.executeUpdate(sql);
            System.out.println(count == 1? "保存成功" : "保存失败");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //释放资源
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ;
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
