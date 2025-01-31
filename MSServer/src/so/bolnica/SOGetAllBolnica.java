/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.bolnica;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Bolnica;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @Korisnik
 */
public class SOGetAllBolnica extends AbstractSO {

    private ArrayList<Bolnica> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Bolnica)) {
            throw new Exception("Greska ... prosledjeni objekat nije instanca klase Bolnica!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ArrayList<AbstractDomainObject> bolnice = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Bolnica>) (ArrayList<?>) bolnice;
    }

    public ArrayList<Bolnica> getLista() {
        return lista;
    }

}
