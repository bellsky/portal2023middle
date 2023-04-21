package test.ac.jejunu.middle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HallaConnectionMaker implements ConnectionMaker {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("org.mariadb.jdbc.Driver");

        return DriverManager.getConnection
                ("jdbc:mariadb://138.2.32.29:3306/USERINFO", "bellsky", "test1234");
    }
}
