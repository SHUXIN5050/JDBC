package TestJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * PreparedStatement完成增删改(insert,delete,update)
 *
 */
public class JDBCTest09 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctest?useSSL=false&serverTimezone=UTC","root","SHUXIN82111244822");
            //获取预编译的数据库操作对象
//            String sql = "insert into t_user (loginName, loginPwd, realName)\n" +
//                    "values (\n" +
//                    "        ?,\n" +
//                    "        ?,\n" +
//                    "        ?\n" +
//                    "       )";
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1,"laoqi");
//            preparedStatement.setString(2,"555");
//            preparedStatement.setString(3,"老七");

//            String sql = "update t_user set loginPwd = ? where id = ?";
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1,"777");
//            preparedStatement.setString(2,"5");

            String sql = "delete from t_user where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"5");
            //执行sql语句
            int count = preparedStatement.executeUpdate();
            System.out.println(count);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
