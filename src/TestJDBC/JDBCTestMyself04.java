package TestJDBC;
//查
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class JDBCTestMyself04 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ResourceBundle bundle = ResourceBundle.getBundle("JDBCTestMyself01");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入查询员工的的id:");
        int id = scanner.nextInt();
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            String sql = "select *\n" +
                    "from users where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int staffId = resultSet.getInt("id");
                String staffName = resultSet.getString("name");
                int staffSalary = resultSet.getInt("salary");
                System.out.println("--------------------");
                System.out.println("\t"+"id"+"\t"+"姓名"+"\t"+"薪水");
                System.out.println("--------------------");
                System.out.println("\t"+staffId+"\t"+staffName+"\t"+staffSalary);
            }else {
                System.out.println("查询的员工不存在，请确认后重新输入！");
            }
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
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
