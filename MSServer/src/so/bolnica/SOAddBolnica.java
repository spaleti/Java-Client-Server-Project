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
public class SOAddBolnica extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Bolnica)) {
            throw new Exception("Greska ... prosledjeni objekat nije instanca klase Bolnica!");
        }
        
        Bolnica b = (Bolnica) ado;
        
        ArrayList<Bolnica> bolnice = (ArrayList<Bolnica>)(ArrayList<?>) DBBroker.getInstance().select(ado);
        
        for (Bolnica bolnica : bolnice) {
            if(bolnica.getEmail().equals(b.getEmail())){
                throw new Exception("Greksa ... vec postoji bolnica sa ovom email adresom!");
            }
            if(bolnica.getTelefon().equals(b.getTelefon())){
                throw new Exception("Greska ... vec postoji bolnica sa ovim telefonom!");
            }
        }
        
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        DBBroker.getInstance().insert(ado);
    }

}
