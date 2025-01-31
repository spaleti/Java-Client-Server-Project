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
import so.AbstractSO;

/**
 *
 * @Korisnik
 */
public class SODeleteBolnica extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Bolnica)) {
            throw new Exception("Greska ... prosledjeni objekat nije instanca klase Bolnica!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        DBBroker.getInstance().delete(ado);
    }

}
