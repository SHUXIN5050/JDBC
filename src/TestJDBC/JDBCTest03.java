package TestJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest03 {
    /**
     *注册驱动的另一种方式(这种方式更常用)
     */
    public static void main(String[] args) {
        try {
            //1，注册驱动
            //这是注册驱动的第一种写法
            //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //注册驱动的第二种方式
            Class.forName("com.mysql.jdbc.Driver");
            /**
             * Class.forName()这个方法的执行会使后面的类被加载，类加载的时候静态代码块会执行，这种方式常用，
             * 因为双引号中的字符串可以写到配置文件中 xxx.properties
             * 这个方法不需要接收返回值，因为我们只想用它的类加载动作
             */
            //2，获取连接
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&serverTimezone=UTC",
                    "root",
                    "SHUXIN82111244822");
            System.out.println(conn);
        }catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
