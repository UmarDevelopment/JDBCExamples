package me.umar;

import org.junit.After;
import org.junit.Before;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SqlBaseTest {

    protected Connection connection;

    @Before
    public void before() throws SQLException, IOException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src//test//resources//mysql.yml"));
        String url = properties.getProperty("mysql.url");
        String user = properties.getProperty("mysql.user");
        String pass = properties.getProperty("mysql.password");

        connection = DriverManager.getConnection(url, user, pass);
    }

    @After
    public void after() throws SQLException {
        connection.close();
    }
}
