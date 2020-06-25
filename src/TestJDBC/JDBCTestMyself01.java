package TestJDBC;
//增
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class JDBCTestMyself01 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResourceBundle bundle = ResourceBundle.getBundle("JDBCTestMyself01");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入id:");
        int id = scanner.nextInt();
        System.out.println("请输入姓名:");
        String name = scanner.next();
        System.out.println("请输入薪水");
        int salary = scanner.nextInt();
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            String sql = "insert into users (id, name, salary)\n" +
                    "values (\n" +
                    "        ?,\n" +
                    "        ?,\n" +
                    "        ?\n" +
                    "       );";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setInt(3,salary);
            int count = preparedStatement.executeUpdate();
            System.out.println(count == 1 ? "录入成功" : "录入失败");
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
