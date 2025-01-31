/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Administrator;
import domain.KategorijaMedicinskogSredstva;
import domain.Bolnica;
import domain.MedicinskoSredstvo;
import domain.Porudzbina;
import domain.StavkaPorudzbine;
import java.util.ArrayList;
import so.AbstractSO;
import so.kategorijaMedicinskogSredstva.SOGetAllKategorijaMedicinskogSredstva;
import so.bolnica.SOAddBolnica;
import so.bolnica.SODeleteBolnica;
import so.bolnica.SOGetAllBolnica;
import so.bolnica.SOUpdateBolnica;
import so.login.SOLogin;
import so.MedicinskoSredstvo.SOGetAllMedicinskoSredstvo;
import so.porudzbina.SOAddPorudzbina;
import so.porudzbina.SODeletePorudzbina;
import so.porudzbina.SOGetAllPorudzbina;
import so.porudzbina.SOUpdatePorudzbina;
import so.stavkaPorudzbine.SOGetAllStavkaPorudzbine;

/**
 *
 * @Korisnik
 */
public class ServerController {

    private static ServerController instance;
    private ArrayList<Administrator> ulogovaniAdministratori = new ArrayList<>();

    public ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public void addBolnica(Bolnica bolnica) throws Exception {
        AbstractSO aso = new SOAddBolnica();
        aso.templateExecute(bolnica);
    }

    public void addPorudzbina(Porudzbina porudzbina) throws Exception {
        AbstractSO aso = new SOAddPorudzbina();
        aso.templateExecute(porudzbina);
    }

    public void deleteBolnica(Bolnica bolnica) throws Exception {
        AbstractSO aso = new SODeleteBolnica();
        aso.templateExecute(bolnica);
    }

    public void deletePorudzbina(Porudzbina porudzbina) throws Exception {
        AbstractSO aso = new SODeletePorudzbina();
        aso.templateExecute(porudzbina);
    }

    public void updateBolnica(Bolnica bolnica) throws Exception {
        AbstractSO aso = new SOUpdateBolnica();
        aso.templateExecute(bolnica);
    }

    public void updatePorudzbina(Porudzbina porudzbina) throws Exception {
        AbstractSO aso = new SOUpdatePorudzbina();
        aso.templateExecute(porudzbina);
    }

    public ArrayList<KategorijaMedicinskogSredstva> getAllKategorijaMedicinskogSredstva() throws Exception {
        SOGetAllKategorijaMedicinskogSredstva so = new SOGetAllKategorijaMedicinskogSredstva();
        so.templateExecute(new KategorijaMedicinskogSredstva());
        return so.getLista();
    }

    public ArrayList<Bolnica> getAllBolnica() throws Exception {
        SOGetAllBolnica so = new SOGetAllBolnica();
        so.templateExecute(new Bolnica());
        return so.getLista();
    }

    public ArrayList<MedicinskoSredstvo> getAllMedicinskoSredstvo(KategorijaMedicinskogSredstva kms) throws Exception {
        SOGetAllMedicinskoSredstvo so = new SOGetAllMedicinskoSredstvo();

        MedicinskoSredstvo ms = new MedicinskoSredstvo();
        ms.setKategorijaMedicinskogSredstva(kms);

        so.templateExecute(ms);
        return so.getLista();
    }

    public ArrayList<Porudzbina> getAllPorudzbina(Bolnica b) throws Exception {
        SOGetAllPorudzbina so = new SOGetAllPorudzbina();

        Porudzbina p = new Porudzbina();
        p.setBolnica(b);

        so.templateExecute(p);
        return so.getLista();
    }

    public ArrayList<StavkaPorudzbine> getAllStavkaPorudzbine(Porudzbina porudzbina) throws Exception {
        SOGetAllStavkaPorudzbine so = new SOGetAllStavkaPorudzbine();

        StavkaPorudzbine sp = new StavkaPorudzbine();
        sp.setPorudzbina(porudzbina);

        so.templateExecute(sp);
        return so.getLista();
    }

    public Administrator login(Administrator a) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(a);
        return so.getUlogovani();
    }

    public void logout(Administrator ulogovani) {
        ulogovaniAdministratori.remove(ulogovani);
    }

    public ArrayList<Administrator> getUlogovaniAdministratori() {
        return ulogovaniAdministratori;
    }

    public void setUlogovaniAdministratori(ArrayList<Administrator> ulogovaniAdministratori) {
        this.ulogovaniAdministratori = ulogovaniAdministratori;
    }

}
