/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @Korisnik
 */
public class Porudzbina extends AbstractDomainObject {

    private Long porudzbinaID;
    private Date datumPorucivanja;
    private Date datumIsporuke;
    private double ukupnaCena;
    private Bolnica bolnica;
    private Administrator administrator;
    private ArrayList<StavkaPorudzbine> stavkePorudzbine;

    public Porudzbina(Long porudzbinaID, Date datumPorucivanja, Date datumIsporuke, double ukupnaCena,
            Bolnica bolnica, Administrator administrator, ArrayList<StavkaPorudzbine> stavkePorudzbine) {
        this.porudzbinaID = porudzbinaID;
        this.datumPorucivanja = datumPorucivanja;
        this.datumIsporuke = datumIsporuke;
        this.ukupnaCena = ukupnaCena;
        this.bolnica = bolnica;
        this.administrator = administrator;
        this.stavkePorudzbine = stavkePorudzbine;
    }

    public Porudzbina() {
    }

    @Override
    public String tableName() {
        return " porudzbina ";
    }

    @Override
    public String alias() {
        return " p ";
    }

    @Override
    public String join() {
        return " JOIN bolnica b ON (b.bolnicaid = p.bolnicaid) "
                + "JOIN administrator a ON (a.administratorid = p.administratorid) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("administratorID"),
                    rs.getString("name"), rs.getString("surname"),
                    rs.getString("username"), rs.getString("password"));

            Bolnica b = new Bolnica(rs.getLong("bolnicaID"),
                    rs.getString("nazivBolnice"), rs.getString("ovlascenoLice"),
                    rs.getString("email"), rs.getString("telefon"));

            Porudzbina p = new Porudzbina(rs.getLong("PorudzbinaID"),
                    rs.getTimestamp("DatumPorucivanja"), rs.getDate("datumIsporuke"),
                    rs.getDouble("UkupnaCena"), b, a, null);

            lista.add(p);
        }

        rs.close();
        return lista;
    }

    @Override
    public String InsertColumns() {
        return " (DatumPorucivanja, datumIsporuke, UkupnaCena, bolnicaID, administratorID) ";
    }

    @Override
    public String condition() {
        return " PorudzbinaID = " + porudzbinaID;
    }

    @Override
    public String inputForInsert() {
        return "'" + new java.sql.Timestamp(datumPorucivanja.getTime()) + "', "
                + "'" + new java.sql.Date(datumIsporuke.getTime()) + "', "
                + ukupnaCena + ", " + bolnica.getBolnicaID() + ", " + administrator.getAdministratorID();
    }

    @Override
    public String inputForUpdate() {
        return " datumIsporuke = '" + new java.sql.Date(datumIsporuke.getTime())
                + "', ukupnaCena = " + ukupnaCena;
    }

    @Override
    public String selectCondition() {
        if (bolnica != null) {
            return " WHERE b.bolnicaID = " + bolnica.getBolnicaID();
        }
        return "";
    }

    public Long getPorudzbinaID() {
        return porudzbinaID;
    }

    public void setPorudzbinaID(Long porudzbinaID) {
        this.porudzbinaID = porudzbinaID;
    }

    public Date getDatumPorucivanja() {
        return datumPorucivanja;
    }

    public void setDatumPorucivanja(Date datumPorucivanja) {
        this.datumPorucivanja = datumPorucivanja;
    }

    public Date getDatumIsporuke() {
        return datumIsporuke;
    }

    public void setDatumIsporuke(Date datumIsporuke) {
        this.datumIsporuke = datumIsporuke;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public Bolnica getBolnica() {
        return bolnica;
    }

    public void setBolnica(Bolnica bolnica) {
        this.bolnica = bolnica;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public ArrayList<StavkaPorudzbine> getStavkePorudzbine() {
        return stavkePorudzbine;
    }

    public void setStavkePorudzbine(ArrayList<StavkaPorudzbine> stavkePorudzbine) {
        this.stavkePorudzbine = stavkePorudzbine;
    }

}
