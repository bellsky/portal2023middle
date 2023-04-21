package test.ac.jejunu.middle;

import java.sql.*;


public interface ConnectionMaker{

    public Connection getConnection()
            throws ClassNotFoundException, SQLException ;
}