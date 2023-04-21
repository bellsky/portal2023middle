package test.ac.jejunu.middle;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User findById(Long id) throws ClassNotFoundException, SQLException {
        String sql ="select id, name, password from userinfo where id = ?";

        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement
                    (sql);
            Object[] params = new Object[]{id};
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }

            return preparedStatement;
        };
        return jdbcContext.jdbcContextForFind(statementStrategy);
    }

    public void insert(User user) throws ClassNotFoundException, SQLException {
        String sql ="insert into userinfo (name, password) values ( ?, ? )";
        Object[] params = new Object[] {user.getName(), user.getPassword()};
                StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement
                    (sql, Statement.RETURN_GENERATED_KEYS);
                    for (int i = 0; i < params.length; i++) {
                        preparedStatement.setObject(i + 1, params[i]);
                    }
            return preparedStatement;
        };
        jdbcContext.jdbcContextForInsert(user, statementStrategy);
    }

    public void update(User user) throws SQLException {
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};

        StatementStrategy statementStrategy = getStatementStrategy(sql, params);
        jdbcContext.jdbcContextForUpdate(statementStrategy);

    }

    private static StatementStrategy getStatementStrategy(String sql, Object[] params) {
        return connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        };
    }

    public void delete(Long id) throws SQLException {
        String sql ="delete from userinfo where id = ?";
        Object[] params = new Object[]{id};

        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement
                    (sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        };
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

