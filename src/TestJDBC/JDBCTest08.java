package TestJDBC;

import java.sql.*;
import java.util.Scanner;

public class JDBCTest08 {
    public static void main(String[] args) {
        /*
        //用户在控制台输入desc就是降序，输入asc就是升序
        Scanner s = new Scanner(System.in);
        System.out.println("输入desc或asc,desc表示降序，asc表示升序");
        System.out.println("请输入：");
        String keyWords = s.nextLine();
        //执行sql
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctest02?useSSL=false&serverTimezone=UTC","root","SHUXIN82111244822");
            //获取预编译的数据库操作对象
            String sql = "select order_num from orderItems order by quantity ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,keyWords);
            //执行sql
             resultSet = preparedStatement.executeQuery();
             while (resultSet.next()){
                 System.out.println(resultSet.getString("order_num"));
             }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
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
         */
        //用户在控制台输入desc就是降序，输入asc就是升序
        Scanner s = new Scanner(System.in);
        System.out.println("输入desc或asc,desc表示降序，asc表示升序");
        System.out.println("请输入：");
        String keyWords = s.nextLine();
        //执行sql
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctest02?useSSL=false&serverTimezone=UTC","root","SHUXIN82111244822");
            //获取数据库操作对象
            statement = connection.createStatement();
            //执行sql
            String sql = "select order_num from orderItems order by quantity "+keyWords;
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                System.out.println(resultSet.getString("order_num"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
            if(statement != null){
                try {
                    statement.close();
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
