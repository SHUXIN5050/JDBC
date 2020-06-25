package TestJDBC;
//将连接数据库的所有信息配置到配置文件中
//不建议将连接数据库的信息写死在Java程序中
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class JDBCTest04 {
    public static void main(String[] args) {
        //使用资源绑定器绑定属性配置文件
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc01");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        Connection conn = null;
        Statement statement = null;
        try {
            //1，注册驱动
            Class.forName(driver);
            //2，获取连接
            conn = DriverManager.getConnection(url,user,password);
            //3，获取数据库操作对象
            statement = conn.createStatement();
            //4，执行sql语句
            String sql = "update websites set name = '良心云' where id = 7";
            int count = statement.executeUpdate(sql);
            System.out.println(count == 1? "删除成功" : "删除失败");
        }catch (SQLException | ClassNotFoundException throwables) {
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
