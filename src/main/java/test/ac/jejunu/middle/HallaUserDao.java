package test.ac.jejunu.middle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HallaUserDao extends UserDao {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection
                ("jdbc:mariadb://138.2.32.29:3306/USERINFO", "bellsky", "test1234");
    }
}
