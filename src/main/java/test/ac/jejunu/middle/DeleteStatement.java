package test.ac.jejunu.middle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStatement implements StatementStrategy {
    private Long id;

    public DeleteStatement(Long id) {
        this.id = id;
    }

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("delete from userinfo where id = ?");
        preparedStatement.setLong(1,  id);
        return preparedStatement;
    }
}