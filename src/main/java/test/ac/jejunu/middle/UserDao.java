package test.ac.jejunu.middle;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User findById(Long id) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new FindStatementStrategy(id);
        return jdbcContext.jdbcContextForFind(statementStrategy);
    }

    public void insert(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new InsertStatementStrategy(user);
        jdbcContext.jdbcContextForInsert(user, statementStrategy);
    }

    public void update(User user) throws SQLException {
        StatementStrategy statementStrategy = new UpdateStatement(user);
        jdbcContext.jdbcContextForUpdate(statementStrategy);

    }
    public void delete(Long id) throws SQLException {
        StatementStrategy statementStrategy = new DeleteStatement(id);
        jdbcContext.jdbcContextForUpdate(statementStrategy);

    }
//
//    private void jdbcContextForUpdate(StatementStrategy statementStrategy) throws SQLException {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = dataSource.getConnection();
//            preparedStatement = statementStrategy.makeStatement(connection);
//            preparedStatement.executeUpdate();
//        } finally {
//            try {
//                preparedStatement.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}

