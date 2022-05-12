import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.util.ArrayList;
import java.sql.CallableStatement;

public class mysqlConnector {
    public static Connection connection = null;
    public static CallableStatement myStatement = null;

    public static Connection ConnectDB() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/account", "root", "root");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<users> getAllUsers() throws SQLException {
        connection = mysqlConnector.ConnectDB();

        myStatement = connection.prepareCall("{call get_all_users()}");

        try (ResultSet resultset = myStatement.executeQuery()) {
            List<users> results = new ArrayList<>();

            while (resultset.next()) {
                results.add(new users(
                        resultset.getString("Username"),
                        resultset.getString("firstName"),
                        resultset.getString("lastName"),
                        resultset.getString("email"),
                        resultset.getString("password")));
            }
            return results;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public int addUser(String fName, String lName, String userName, String Email, String passWord) {
        try {
            connection = mysqlConnector.ConnectDB();
            myStatement = connection.prepareCall("{call submit_sign_up(?, ?, ?, ?, ?)}");

            myStatement.setString(1, fName);
            myStatement.setString(2, lName);
            myStatement.setString(3, userName);
            myStatement.setString(4, Email);
            myStatement.setString(5, passWord);

            return myStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

}