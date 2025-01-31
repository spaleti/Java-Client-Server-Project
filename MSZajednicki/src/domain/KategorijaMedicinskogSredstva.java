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
public class KategorijaMedicinskogSredstva extends AbstractDomainObject {
    
    private Long kategorijaID;
    private String nazivKategorije;

    @Override
    public String toString() {
        return nazivKategorije;
    }

    public KategorijaMedicinskogSredstva(Long kategorijaID, String nazivKategorije) {
        this.kategorijaID = kategorijaID;
        this.nazivKategorije = nazivKategorije;
    }

    public KategorijaMedicinskogSredstva() {
    }
    
    @Override
    public String tableName() {
        return " kategorijaMedicinskogSredstva ";
    }

    @Override
    public String alias() {
        return " kms ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            KategorijaMedicinskogSredstva kms = new KategorijaMedicinskogSredstva(rs.getLong("kategorijaID"),
                    rs.getString("nazivKategorije"));

            lista.add(kms);
        }

        rs.close();
        return lista;
    }

    @Override
    public String InsertColumns() {
        return " ";
    }

    @Override
    public String condition() {
        return "";
    }

    @Override
    public String inputForInsert() {
        return "";
    }

    @Override
    public String inputForUpdate() {
        return "";
    }

    @Override
    public String selectCondition() {
        return "";
    }

    public Long getKategorijaID() {
        return kategorijaID;
    }

    public void setKategorijaID(Long kategorijaID) {
        this.kategorijaID = kategorijaID;
    }

    public String getNazivKategorije() {
        return nazivKategorije;
    }

    public void setNazivKategorije(String nazivKategorije) {
        this.nazivKategorije = nazivKategorije;
    }
}
