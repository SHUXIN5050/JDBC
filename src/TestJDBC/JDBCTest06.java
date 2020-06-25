package TestJDBC;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 实现功能：
 * 1、需求：模拟用户登陆功能实现
 * 2、业务描述：程序运行时提供一个输入的入口，可以让用户输入用户名和密码
 * 用户输入用户名和密码后，提交信息，Java程序收集到用户信息
 * Java程序连接数据库验证用户名和密码是否合法
 * 合法：显示登陆成功
 * 不合法：显示登陆失败
 * 3、数据准备：
 * 在实际开发中，表的设计会使用专业的建模工具
 * 4.当前程序存在问题：
 * 用户名：fdsa
 * 密码：fdsa' or '1'='1
 * 登陆成功
 * 这种现象称为sql注入（安全隐患）
 * 5.导致sql注入的根本原因
 * sql语句变成了：
 * select * from t_user where loginName = 'fdsa' and loginPwd = 'fdsa' or '1'='1'
 * 1=1恒成立，所以1=1前的内容相当于没用
 * 用户输入的信息中含有sql语句的关键字，并且这些关键字参与sql语句的编译过程
 * 导致sql语句的原意被扭曲，从而达到sql注入
 */
public class JDBCTest06 {
    public static void main(String[] args) {
        //初始化一个界面
        Map<String,String> userLogInfo = initUI();
        //验证用户名和密码
        boolean loginSuccess = login(userLogInfo);
        //输出结果
        System.out.println(loginSuccess ? "登陆成功" : "登陆失败");
    }

    /**
     * 用户登陆
     * @param userLogInfo 用户登陆信息
     * @return false表示失败，true表示成功
     */
    private static boolean login(Map<String, String> userLogInfo) {
        //标记
        boolean loginSuccess = false;
        //单独定义变量
        String loginName = userLogInfo.get("loginName");
        String loginPwd = userLogInfo.get("loginPwd");
        //JDBC代码
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            //第一步注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //第二步获取连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctest?useSSL=false&serverTimezone=UTC",
                    "root","SHUXIN82111244822");
            //第三步获取数据库操作对象
            statement = connection.createStatement();
            //第四步执行sql语句
            String sql = "select * from t_user where loginName = '"+loginName+"' and loginPwd = '"+loginPwd+"'";
            //以上正好完成了sql语句的拼接，以下代码的含义是，发送sql语句给DBMS，DBMS进行sql编译
            //正好将用户提供的“非法信息”编译进去，导致原sql语句含义被扭曲
            //statement.executeQuery(sql);返回一个结果集，一定要赋给resultSet
            resultSet = statement.executeQuery(sql);
            //第五步处理结果集
            if(resultSet.next()){
                //用户名密码正确登陆成功
                loginSuccess = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            //第六步释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return loginSuccess;
    }

    /**
     * 初始化用户界面
     * @return 返回用户输入的用户名和密码等登陆信息
     */
    private static Map<String, String> initUI() {
        Scanner s = new Scanner(System.in);
        System.out.println("用户名：");
        String loginName = s.nextLine();
        System.out.println("密码：");
        String loginPwd = s.nextLine();
        Map<String,String> userLoginInfo = new HashMap<>();
        userLoginInfo.put("loginName",loginName);
        userLoginInfo.put("loginPwd",loginPwd);
        return userLoginInfo;
    }

}
