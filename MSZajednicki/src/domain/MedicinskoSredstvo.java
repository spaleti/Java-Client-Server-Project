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
public class MedicinskoSredstvo extends AbstractDomainObject {

    private Long medicinskoSredstvoID;
    private String nazivMedicinskogSredstva;
    private String opis;
    private double cena;
    private KategorijaMedicinskogSredstva kategorijaMedicinskogSredstva;

    @Override
    public String toString() {
        return nazivMedicinskogSredstva + " ( " + cena + " rsd )";
    }

    public MedicinskoSredstvo(Long medicinskoSredstvoID, String nazivMedicinskogSredstva,
            String opis, double cena, KategorijaMedicinskogSredstva kategorijaMedicinskogSredstva) {
        this.medicinskoSredstvoID = medicinskoSredstvoID;
        this.nazivMedicinskogSredstva = nazivMedicinskogSredstva;
        this.opis = opis;
        this.cena = cena;
        this.kategorijaMedicinskogSredstva = kategorijaMedicinskogSredstva;
    }

    public MedicinskoSredstvo() {
    }

    @Override
    public String tableName() {
        return " medicinskosredstvo "; 
    }

    @Override
    public String alias() {
        return " ms ";
    }

    @Override
    public String join() {
        return " JOIN kategorijamedicinskogsredstva kms ON (kms.kategorijaid = ms.kategorijaid) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            KategorijaMedicinskogSredstva kms = new KategorijaMedicinskogSredstva(rs.getLong("kategorijaID"),
                    rs.getString("nazivKategorije"));

            MedicinskoSredstvo ms = new MedicinskoSredstvo(rs.getLong("msID"), rs.getString("nazivSredstva"),
                    rs.getString("opis"), rs.getDouble("cena"), kms);

            lista.add(ms);
        }

        rs.close();
        return lista;
    }

    @Override
    public String InsertColumns() {
        return " (nazivSredstva, opis, cena, kategorijaID) ";
    }

    @Override
    public String condition() {
        return " msID = " + medicinskoSredstvoID;
    }

    @Override
    public String inputForInsert() {
        return " '" + nazivMedicinskogSredstva + "', '" + opis + "', " + cena + ", "
                + kategorijaMedicinskogSredstva.getKategorijaID();
    }

    @Override
    public String inputForUpdate() {
        return " opis = '" + opis + "', cena = " + cena + " ";
    }

    @Override
    public String selectCondition() {
        return " WHERE kms.kategorijaid = " + kategorijaMedicinskogSredstva.getKategorijaID();
    }

    public Long getMedicinskoSredstvoID() {
        return medicinskoSredstvoID;
    }

    public void setMedicinskoSredstvoID(Long medicinskoSredstvoID) {
        this.medicinskoSredstvoID = medicinskoSredstvoID;
    }

    public String getNazivMedicinskogSredstva() {
        return nazivMedicinskogSredstva;
    }

    public void setNazivMedicinskogSredstva(String nazivMedicinskogSredstva) {
        this.nazivMedicinskogSredstva = nazivMedicinskogSredstva;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public KategorijaMedicinskogSredstva getKategorijaMedicinskogSredstva() {
        return kategorijaMedicinskogSredstva;
    }

    public void setKategorijaMedicinskogSredstva(KategorijaMedicinskogSredstva kategorijaMedicinskogSredstva) {
        this.kategorijaMedicinskogSredstva = kategorijaMedicinskogSredstva;
    }
}
