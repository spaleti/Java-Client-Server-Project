/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.MedicinskoSredstvo;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.MedicinskoSredstvo;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @Korisnik
 */
public class SOGetAllMedicinskoSredstvo extends AbstractSO {

    private ArrayList<MedicinskoSredstvo> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof MedicinskoSredstvo)) {
            throw new Exception("Greska ... prosledjeni objekat nije instanca klase MedicinskoSredstvo!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ArrayList<AbstractDomainObject> listaMS = DBBroker.getInstance().select(ado);
        lista = (ArrayList<MedicinskoSredstvo>) (ArrayList<?>) listaMS;
    }

    public ArrayList<MedicinskoSredstvo> getLista() {
        return lista;
    }

}
