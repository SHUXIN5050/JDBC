package TestJDBC;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 1.解决sql注入问题
 * 只要用户提供的信息不参与sql语句的编译过程，问题就解决了
 * 即使用户提供的的信息中含有sql关键字，但没有参与编译，不起作用
 * 要想用户信息不参与sql语句的编译，那么必须使用java.sql.PreparedStatement
 * PreparedStatement接口继承了java.sql.Statement
 * PreparedStatement是属于预编译的数据库操作对象
 * PreparedStatement的原理是：预先对sql语句的框架进行编译，染后再给sql语句传“值”
 * 2.对比一下Statement和 PreparedStatement
 * -Statement存在sql注入问题，PreparedStatement解决了sql注入问题
 * -Statement是编译N次执行N次，PreparedStatement是编译一次执行N次，PreparedStatement效率更高
 * -PreparedStatement会再编译阶段进行类型的安全检查
 * 综上：PreparedStatement使用较多，只有极少数的情况下会使用Statement，如业务方面必须要求sql注入的时候
 */
public class JDBCTest07 {
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
        PreparedStatement preparedStatement = null;//这里使用PreparedStatement（预编译的数据库操作对象）
        ResultSet resultSet = null;
        try {
            //第一步注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //第二步获取连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbctest?useSSL=false&serverTimezone=UTC",
                    "root","SHUXIN82111244822");
            //第三步获取预编译的数据库操作对象
            String sql = "select * from t_user where loginName = ? and loginPwd = ?";//sql语句的框架，?是占位符,一个?接收一个值，占位符不能使用单引号括起来
            //程序执行到此处，会发送sql语句的框架给DBMS，然后DBMS进行sql语句的预编译
            preparedStatement = connection.prepareStatement(sql);
            //给占位符?传值（第一个?下标是1，第二个?下标是2，JDBC中所有下标从1开始）
            preparedStatement.setString(1,loginName);//给第一个问号传值
            preparedStatement.setString(2,loginPwd);//给第二个问号传值
            //第四步执行sql语句
            //preparedStatement.executeQuery(sql);返回一个结果集，一定要赋给resultSet
            //上面那一句以已经把sql语句传给了preparedStatement，下面这句的括号里不用在写sql，否则会再编译一次
            resultSet = preparedStatement.executeQuery();
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
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
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
