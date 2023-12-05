package me.umar;

import org.junit.Test;

import java.sql.*;

public class ExamplesTest extends SqlBaseTest{

    /**
     * Create a simple query and get ResultSet
     */
    @Test
    public void simpleQuery1() throws SQLException {
        ResultSet res = connection.createStatement().executeQuery("SELECT * FROM country");
        /**
         * res.next() - return boolean value which means there is a next line
         * and switch to this line.
         */
        while (res.next()){
            String val1 = res.getString(1);
            System.out.println(val1);
        }
    }

    /**
     * Create a simple insert
     */
    @Test
    public void simpleInsert1() throws SQLException {
        long res = connection.createStatement().executeUpdate("INSERT INTO country VALUES ('Germany', 'DEU')");
        System.out.println(res);
    }

    /**
     * Create a simple update
     */
    @Test
    public void simpleUpdate1() throws SQLException {
        long res = connection.createStatement().executeUpdate("UPDATE country SET `name` = 'Deutschland' WHERE `name` = 'Germany'");
        System.out.println(res);
    }


    /**
     * PreparedStatement example
     */
    @Test
    public void preparedStatement() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM country WHERE name = ? or name = ?");
        ps.setString(1, "Russia");
        ps.setString(2, "JAPAN");
        ResultSet res = ps.executeQuery();
        while (res.next()){
            System.out.println(res.getString(2));
        }
    }

    /**
     * commit rollback examples
     */
    @Test
    public void commitTest() throws SQLException {
        connection.setAutoCommit(false);
        long res1 = connection.createStatement().executeUpdate("INSERT INTO country VALUES ('Hungry', 'HUN')");
        long res2 = connection.createStatement().executeUpdate("INSERT INTO country VALUES ('Cuba', 'CUB')");
        connection.commit();
        //connection.rollback();
        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM country");
        while (rs.next()){
            System.out.println(rs.getString(1));
        }
    }
}
