package TestJDBC;
//改
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class JDBCTestMyself03 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResourceBundle bundle = ResourceBundle.getBundle("JDBCTestMyself01");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入薪水调整者的id:");
        int id = scanner.nextInt();
        System.out.println("请设置薪水");
        int salary = scanner.nextInt();
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            String sql = "update users set salary = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,salary);
            preparedStatement.setInt(2,id);
            int count = preparedStatement.executeUpdate();
            System.out.println(count == 1 ? "修改成功" : "修改失败");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
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
        }
    }
}
