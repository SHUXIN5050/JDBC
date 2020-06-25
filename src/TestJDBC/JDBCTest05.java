package TestJDBC;
//处理查询结果集(遍历结果集)

import java.sql.*;

public class JDBCTest05 {
    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            //1，注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2，获取连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&serverTimezone=UTC",
                    "root","SHUXIN82111244822");
            //3，获取数据库操作对象
            statement = connection.createStatement();
            //4，执行sql语句
            String sql = "select id,name,url,alexa,country from websites;";
            resultSet = statement.executeQuery(sql);// statement.executeQuery()专门执行DQL语句
            //statement.executeUpdate(insert/delete/update);返回int,表示受影响的行数
            //statement.executeQuery(select);返回resultSet，结果集
            //5，处理处理查询结果集
//            boolean flag1 = resultSet.next();
//            //resultSet.next();这个方法表示指向表的下一行，如果该行有数据则返回true否则返回false
//            if(flag1){
//                //getString();方法的特点，不管数据库中的数据是什么类型的，都以String形式取出,括号中填下标表示第几列，JDBC中所有下标从1开始
//                String id = resultSet.getString(1);
//                String name = resultSet.getString(2);
//                String weburl = resultSet.getString(3);
//                String alexa = resultSet.getString(4);
//                String country = resultSet.getString(5);
//                System.out.println(id+","+name+","+weburl+","+alexa+","+country);
//            }
            while (resultSet.next()){
//                String id = resultSet.getString(1);
//                String name = resultSet.getString(2);
//                String weburl = resultSet.getString(3);
//                String alexa = resultSet.getString(4);
//                String country = resultSet.getString(5);
//                System.out.println(id+","+name+","+weburl+","+alexa+","+country);
                //不建议在括号中填入列的下标，建议直接填列名
                //重点注意：列名称不是表中的列名称，是查询结果集的列名称
                //也可以使用getInt(); getDouble();等方法返回不同的数据类型
//                String id = resultSet.getString("id");
//                String name = resultSet.getString("name");
//                String weburl = resultSet.getString("url");
//                String alexa = resultSet.getString("alexa");
//                String country = resultSet.getString("country");
//                System.out.println(id+","+name+","+weburl+","+alexa+","+country);
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String weburl = resultSet.getString("url");
                int alexa = resultSet.getInt("alexa");
                String country = resultSet.getString("country");
                System.out.println(id+","+name+","+weburl+","+alexa+","+country);
            }
        }catch (SQLException | ClassNotFoundException exception){
            exception.printStackTrace();
        }finally {
            //6，释放资源
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
