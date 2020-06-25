package TestJDBC;
//删
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class JDBCTestMyself02 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResourceBundle bundle = ResourceBundle.getBundle("JDBCTestMyself01");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入离职者id:");
        int id = scanner.nextInt();
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            //删除
            String sql = "delete from users where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            int count = preparedStatement.executeUpdate();
            System.out.println(count == 1 ? "删除成功" : "删除失败");
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
