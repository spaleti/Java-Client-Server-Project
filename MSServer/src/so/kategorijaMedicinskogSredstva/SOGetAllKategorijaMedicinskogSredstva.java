/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.kategorijaMedicinskogSredstva;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.KategorijaMedicinskogSredstva;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @Korisnik
 */
public class SOGetAllKategorijaMedicinskogSredstva extends AbstractSO {

    private ArrayList<KategorijaMedicinskogSredstva> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof KategorijaMedicinskogSredstva)) {
            throw new Exception("Greska ... prosledjeni objekat nije instanca klase KategorijaMedicinskogSredstva!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ArrayList<AbstractDomainObject> kategorijeMS = DBBroker.getInstance().select(ado);
        lista = (ArrayList<KategorijaMedicinskogSredstva>) (ArrayList<?>) kategorijeMS;
    }

    public ArrayList<KategorijaMedicinskogSredstva> getLista() {
        return lista;
    }

}
