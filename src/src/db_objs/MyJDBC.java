package db_objs;

import java.math.BigDecimal;
import java.sql.*;

public class MyJDBC {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/bankapp";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "password";

    public static User validateLogin(String username, String password){
        try{
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            //create query
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                int userId = resultSet.getInt(("id"));
                BigDecimal currentBalance = resultSet.getBigDecimal("current_balance");

                return new User(userId, username, password, currentBalance);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }
}