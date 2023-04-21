package test.ac.jejunu.middle;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import javax.xml.datatype.DatatypeFactory;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class usertest {


    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "bellsky";
        String password = "test1234";

        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.getUserDao();
//        ConnectionMaker connectionMaker = new JejuConnectionMaker();
//        UserDao userDao = new UserDao(connectionMaker);

        User user = userDao.findById(id);
        assertThat(String.valueOf(user.getId()), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }
    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        String name = "종호선";
        String password = "1234";
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.getUserDao();

//        ConnectionMaker connectionMaker = new JejuConnectionMaker();
//        UserDao userDao = new UserDao(connectionMaker);

        userDao.insert(user);
        assertThat(user.getId(), greaterThan(1l));

        User insertedUser = userDao.findById(user.getId());
        assertThat(insertedUser.getId(),  is(user.getId()));
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));
    }

    @Test
    public void getHalla() throws SQLException, ClassNotFoundException {
        Long id = 1l;
        String name = "bellsky";
        String password = "test1234";

        ConnectionMaker connectionMaker = new HallaConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);

        User user = userDao.findById(id);
        assertThat(String.valueOf(user.getId()), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }
    @Test
    public void insertHalla() throws SQLException, ClassNotFoundException {
        String name = "종호선";
        String password = "1234";
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        ConnectionMaker connectionMaker = new HallaConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);

        userDao.insert(user);
        assertThat(user.getId(), greaterThan(1l));

        User insertedUser = userDao.findById(user.getId());
        assertThat(insertedUser.getId(),  is(user.getId()));
        assertThat(insertedUser.getName(), is(name));
        assertThat(insertedUser.getPassword(), is(password));
    }

}
