package DB;

import java.sql.*;

/**
 * Created by fawad.tariq on 9/26/2019.
 */
public class DbConnection {
    private String dbURL = "jdbc:mysql://localhost:3306/students";
    private String username = "root";
    private String password = "";
    private Connection connection;
    public DbConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL,username,password);
            if(connection!=null){
                System.out.println("Success");
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRecord(String regno,String username,String password,String email,String dob,String phone,String fee,String java,String jdbc,String ejb,String jsp,String gender,String qualification){
        try {
            String sqlQuery = "INSERT INTO student(regno,username,password,email,dob,phone,fee,java,jdbc,ejb,jsp,gender,qualification) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, regno);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, dob);
            preparedStatement.setString(6, phone);
            preparedStatement.setString(7, fee);
            preparedStatement.setString(8, java);
//            preparedStatement.setString(9, jdbc);
//            preparedStatement.setString(10, ejb);
//            preparedStatement.setString(11, jsp);
            preparedStatement.setString(9, gender);
            preparedStatement.setString(10, qualification);

            int noOfRowsInserted = preparedStatement.executeUpdate();
            if(noOfRowsInserted>0){
                System.out.println(noOfRowsInserted + " rows inserted!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRecord(int id,String regno,String username,String password,String email,String dob,String phone,String fee,String java,String jdbc,String ejb,String jsp,String gender,String qualification){
        try {
            String sqlQuery = "UPDATE student SET regno=?,username=?,password=?,email=?,dob=?,phone=?,fee=?,java=?,jdbc=?,ejb=?,jsp=?,gender=?,qualification=?  WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setString(1, regno);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, dob);
            preparedStatement.setString(6, phone);
            preparedStatement.setString(7, fee);
            preparedStatement.setString(8, java);
//            preparedStatement.setString(9, jdbc);
//            preparedStatement.setString(10, ejb);
//            preparedStatement.setString(11, jsp);
            preparedStatement.setString(9, gender);
            preparedStatement.setString(10, qualification);
            preparedStatement.setInt(11, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecord(String id){
        try {
            String sqlQuery = "DELETE from student WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,Integer.parseInt(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getRecord(int id){
        try {
            String sqlQuery = "SELECT * FROM student where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,id);
            ResultSet result = preparedStatement.executeQuery();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getRecords(){
        try {
            String sqlQuery = "SELECT * FROM student";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sqlQuery);
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
