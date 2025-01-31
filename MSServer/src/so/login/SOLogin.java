/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.login;

import controller.ServerController;
import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Administrator;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @Korisnik
 */
public class SOLogin extends AbstractSO {

    private Administrator ulogovani;
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Administrator)) {
            throw new Exception("Greska ... prosledjeni objekat nije instanca klase Administrator!");
        }

        Administrator a = (Administrator) ado;

        for (Administrator administrator : ServerController.getInstance().getUlogovaniAdministratori()) {
            if (administrator.getUsername().equals(a.getUsername())) {
                throw new Exception("Greska ... ovaj administrator je vec ulogovan na sistem!");
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        Administrator a = (Administrator) ado;
        
        ArrayList<Administrator> administratori =(ArrayList<Administrator>)(ArrayList<?>) DBBroker.getInstance().select(ado);
        
        for (Administrator administrator : administratori) {
            if(administrator.getUsername().equals(a.getUsername())
                    && administrator.getPassword().equals(a.getPassword())){
                this.ulogovani = administrator;
                ServerController.getInstance().getUlogovaniAdministratori().add(administrator);
                return;
            }
        }
        
        throw new Exception("Greska ... ne postoji administrator sa ovim kredencijalima!");
        
    }

    public Administrator getUlogovani() {
        return ulogovani;
    }

}
