/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domain.AbstractDomainObject;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @Korisnik
 */
public class DBBroker {

    private static DBBroker instance;
    private Connection connection;

    public DBBroker() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("dbconfig.properties"));
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DBBroker getInstance() {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }

    public ArrayList<AbstractDomainObject> select(AbstractDomainObject ado) throws SQLException {
        String upit = "SELECT * FROM " + ado.tableName() + " " + ado.alias()
                + " " + ado.join() + " " + ado.selectCondition() + " ";
        System.out.println(upit);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        return ado.getList(rs);
    }

    public PreparedStatement insert(AbstractDomainObject ado) throws SQLException {
        String upit = "INSERT INTO " + ado.tableName() + " "
                + ado.InsertColumns() + " VALUES(" + ado.inputForInsert() + ")";
        System.out.println(upit);
        PreparedStatement ps = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        return ps;
    }

    public void update(AbstractDomainObject ado) throws SQLException {
        String upit = "UPDATE " + ado.tableName() + " SET "
                + ado.inputForUpdate() + " WHERE " + ado.condition();
        System.out.println(upit);
        Statement s = connection.createStatement();
        s.executeUpdate(upit);
    }

    public void delete(AbstractDomainObject ado) throws SQLException {
        String upit = "DELETE FROM " + ado.tableName() + " WHERE " + ado.condition();
        System.out.println(upit);
        Statement s = connection.createStatement();
        s.executeUpdate(upit);
    }

}
