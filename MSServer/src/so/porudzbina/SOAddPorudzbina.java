/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.porudzbina;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Porudzbina;
import domain.StavkaPorudzbine;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import so.AbstractSO;

/**
 *
 * @Korisnik
 */
public class SOAddPorudzbina extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Porudzbina)) {
            throw new Exception("Greska ... prosledjeni objekat nije instanca klase Porudzbina!");
        }

        Porudzbina p = (Porudzbina) ado;

        if (p.getDatumIsporuke().before(new Date()) || p.getDatumIsporuke().equals(new Date())) {
            throw new Exception("Greska ... datum isporuke mora biti posle danasnjeg datuma!");
        }

        if (p.getStavkePorudzbine().isEmpty()) {
            throw new Exception("Greska ... porudzbina mora imati barem jednu stavku!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long porudzbinaID = tableKeys.getLong(1);
        
        Porudzbina p = (Porudzbina) ado;
        p.setPorudzbinaID(porudzbinaID);
        
        for (StavkaPorudzbine stavkaPorudzbine : p.getStavkePorudzbine()) {
            stavkaPorudzbine.setPorudzbina(p);
            DBBroker.getInstance().insert(stavkaPorudzbine);
        }
        
    }

}
