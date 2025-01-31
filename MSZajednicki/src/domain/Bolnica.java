/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @Korisnik
 */
public class Bolnica extends AbstractDomainObject {
    
    private Long bolnicaID;
    private String nazivBolnice;
    private String ovlascenoLice;
    private String email;
    private String telefon;

    @Override
    public String toString() {
        return nazivBolnice;
    }

    public Bolnica(Long bolnicaID, String nazivBolnice, String ovlascenoLice, String email, String telefon) {
        this.bolnicaID = bolnicaID;
        this.nazivBolnice = nazivBolnice;
        this.ovlascenoLice = ovlascenoLice;
        this.email = email;
        this.telefon = telefon;
    }

    public Bolnica() {
    }
    
    @Override
    public String tableName() {
        return " bolnica ";
    }

    @Override
    public String alias() {
        return " b ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Bolnica b = new Bolnica(rs.getLong("bolnicaID"),
                    rs.getString("nazivBolnice"), rs.getString("ovlascenoLice"),
                    rs.getString("email"), rs.getString("telefon"));

            lista.add(b);
        }

        rs.close();
        return lista;
    }

    @Override
    public String InsertColumns() {
        return " (nazivBolnice, ovlascenoLice, email, telefon) ";
    }

    @Override
    public String condition() {
        return " bolnicaID = " + bolnicaID;
    }

    @Override
    public String inputForInsert() {
        return "'" + nazivBolnice + "', '" + ovlascenoLice + "', "
                + "'" + email + "', '" + telefon + "'";
    }

    @Override
    public String inputForUpdate() {
        return "ovlascenoLice ='" + ovlascenoLice +"', email = '" + email + "', telefon = '" + telefon + "' ";
    }

    @Override
    public String selectCondition() {
        return "";
    }

    public Long getBolnicaID() {
        return bolnicaID;
    }

    public void setBolnicaID(Long bolnicaID) {
        this.bolnicaID = bolnicaID;
    }

    public String getNazivBolnice() {
        return nazivBolnice;
    }

    public void setNazivBolnice(String nazivBolnice) {
        this.nazivBolnice = nazivBolnice;
    }

    public String getOvlascenoLice() {
        return ovlascenoLice;
    }

    public void setOvlascenoLice(String ovlascenoLice) {
        this.ovlascenoLice = ovlascenoLice;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
