/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.porudzbina;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Porudzbina;
import java.sql.SQLException;
import so.AbstractSO;

/**
 *
 * @Korisnik
 */
public class SODeletePorudzbina extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Porudzbina)) {
            throw new Exception("Greska ... prosledjeni objekat nije instanca klase Porudzbina!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        DBBroker.getInstance().delete(ado);
    }

}
