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
public class StavkaPorudzbine extends AbstractDomainObject {

    private Porudzbina porudzbina;
    private int rbStavke;
    private int kolicina;
    private double cenaStavke;
    private MedicinskoSredstvo medicinskoSredstvo;

    public StavkaPorudzbine(Porudzbina porudzbina, int rbStavke, int kolicina, double cenaStavke,
            MedicinskoSredstvo medicinskoSredstvo) {
        this.porudzbina = porudzbina;
        this.rbStavke = rbStavke;
        this.kolicina = kolicina;
        this.cenaStavke = cenaStavke;
        this.medicinskoSredstvo = medicinskoSredstvo;
    }

    public StavkaPorudzbine() {
    }

    @Override
    public String tableName() {
        return " stavkaPorudzbine ";
    }

    @Override
    public String alias() {
        return " sp ";
    }

    @Override
    public String join() {
        return " JOIN porudzbina p ON (p.porudzbinaid = sp.porudzbinaid) "
                + "JOIN bolnica b ON (b.bolnicaid = p.bolnicaid) "
                + "JOIN administrator a ON (a.administratorid = p.administratorid) "
                + "JOIN medicinskosredstvo ms ON (ms.msid = sp.medicinskosredstvoid) "
                + "JOIN kategorijamedicinskogsredstva kms ON (kms.kategorijaid = ms.kategorijaid) ";
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
            
            Porudzbina p = new Porudzbina(rs.getLong("porudzbinaID"), 
                    rs.getDate("datumPorucivanja"), rs.getDate("datumIsporuke"), 
                    rs.getDouble("ukupnaCena"), b, a, null);
            
            KategorijaMedicinskogSredstva kms = new KategorijaMedicinskogSredstva(rs.getLong("kategorijaID"),
                    rs.getString("nazivKategorije"));
            
            MedicinskoSredstvo ms = new MedicinskoSredstvo(rs.getLong("msID"), rs.getString("nazivSredstva"), 
                    rs.getString("opis"), rs.getDouble("cena"), kms);
            
            StavkaPorudzbine sp = new StavkaPorudzbine(p, rs.getInt("rbStavke"), 
                    rs.getInt("kolicina"), rs.getDouble("cenaStavke"), ms);

            lista.add(sp);
        }

        rs.close();
        return lista;
    }

    @Override
    public String InsertColumns() {
        return " (porudzbinaID, rbStavke, kolicina, cenaStavke, medicinskoSredstvoID) ";
    }

    @Override
    public String condition() {
        return " porudzbinaID = " + porudzbina.getPorudzbinaID();
    }

    @Override
    public String inputForInsert() {
        return porudzbina.getPorudzbinaID() + ", " + rbStavke + ", "
                + kolicina + ", " + cenaStavke + ", " + medicinskoSredstvo.getMedicinskoSredstvoID();
    }

    @Override
    public String inputForUpdate() {
        return "";
    }

    @Override
    public String selectCondition() {
        return " WHERE P.PORUDZBINAID = " + porudzbina.getPorudzbinaID() + " ORDER BY RBSTAVKE";
    }

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    public int getRbStavke() {
        return rbStavke;
    }

    public void setRbStavke(int rbStavke) {
        this.rbStavke = rbStavke;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCenaStavke() {
        return cenaStavke;
    }

    public void setCenaStavke(double cenaStavke) {
        this.cenaStavke = cenaStavke;
    }

    public MedicinskoSredstvo getMedicinskoSredstvo() {
        return medicinskoSredstvo;
    }

    public void setMedicinskoSredstvo(MedicinskoSredstvo medicinskoSredstvo) {
        this.medicinskoSredstvo = medicinskoSredstvo;
    }
}
