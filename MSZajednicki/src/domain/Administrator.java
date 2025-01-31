/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @Korisnik
 */
public class Administrator extends AbstractDomainObject {

    private Long administratorID;
    private String name;
    private String surname;
    private String username;
    private String password;

    public Administrator() {
    }

    public Administrator(Long administratorID, String ime, String prezime, String username, String password) {
        this.administratorID = administratorID;
        this.name = ime;
        this.surname = prezime;
        this.username = username;
        this.password = password;
    }

    public Long getAdministratorID() {
        return administratorID;
    }

    public void setAdministratorID(Long administratorID) {
        this.administratorID = administratorID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Administrator other = (Administrator) obj;
        if (!Objects.equals(this.administratorID, other.administratorID)) {
            return false;
        }
        return true;
    }

    @Override
    public String tableName() {
        return " administrator ";
    }

    @Override
    public String alias() {
        return " a ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("administratorID"),
                    rs.getString("name"), rs.getString("surname"),
                    rs.getString("username"), rs.getString("password"));

            lista.add(a);
        }

        rs.close();
        return lista;
    }

    @Override
    public String InsertColumns() {
        return " (name, surname, username, password) ";
    }

    @Override
    public String condition() {
        return " administratorID = " + administratorID;
    }

    @Override
    public String inputForInsert() {
        return "'" + name + "', '" + surname + "', "
                + "'" + username + "', '" + password + "'";
    }

    @Override
    public String inputForUpdate() {
        return " name = '" + name + "', surname = '" + surname + "', "
                + "username = '" + username + "', password = '" + password + "' ";
    }

    @Override
    public String selectCondition() {
        return "";
    }


}
