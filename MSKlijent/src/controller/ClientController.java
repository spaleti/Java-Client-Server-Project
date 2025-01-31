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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @Korisnik
 */
public class ClientController {

    private static ClientController instance;

    public ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        return (Administrator) sendRequest(Operation.LOGIN, administrator);
    }

    public void addBolnica(Bolnica bolnica) throws Exception {
        sendRequest(Operation.ADD_BOLNICA, bolnica);
    }

    public void addPorudzbina(Porudzbina porudzbina) throws Exception {
        sendRequest(Operation.ADD_PORUDZBINA, porudzbina);
    }

    public void deleteBolnica(Bolnica bolnica) throws Exception {
        sendRequest(Operation.DELETE_BOLNICA, bolnica);
    }

    public void deletePorudzbina(Porudzbina porudzbina) throws Exception {
        sendRequest(Operation.DELETE_PORUDZBINA, porudzbina);
    }

    public void updateBolnica(Bolnica bolnica) throws Exception {
        sendRequest(Operation.UPDATE_BOLNICA, bolnica);
    }

    public void updatePorudzbina(Porudzbina porudzbina) throws Exception {
        sendRequest(Operation.UPDATE_PORUDZBINA, porudzbina);
    }

    public ArrayList<KategorijaMedicinskogSredstva> getAllKategorijaMedicinskogSredstva() throws Exception {
        return (ArrayList<KategorijaMedicinskogSredstva>) sendRequest(Operation.GET_ALL_KATEGORIJA_MEDICINSKOG_SREDSTVA, null);
    }

    public ArrayList<Bolnica> getAllBolnice() throws Exception {
        return (ArrayList<Bolnica>) sendRequest(Operation.GET_ALL_BOLNICE, null);
    }

    public ArrayList<Porudzbina> getAllPorudzbina(Bolnica b) throws Exception {
        return (ArrayList<Porudzbina>) sendRequest(Operation.GET_ALL_PORUDZBINA, b);
    }

    public ArrayList<MedicinskoSredstvo> getAllMedicinskoSredstvo(KategorijaMedicinskogSredstva kms) throws Exception {
        return (ArrayList<MedicinskoSredstvo>) sendRequest(Operation.GET_ALL_MEDICINSKO_SREDSTVO, kms);
    }

    public ArrayList<StavkaPorudzbine> getAllStavkaPorudzbine(Porudzbina p) throws Exception {
        return (ArrayList<StavkaPorudzbine>) sendRequest(Operation.GET_ALL_STAVKA_PORUDZBINE, p);
    }

    private Object sendRequest(int operation, Object param) throws Exception {
        Request request = new Request(operation, param);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) in.readObject();

        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getError();
        } else {
            return response.getResponse();
        }

    }

    public void logout(Administrator ulogovani) throws Exception {
        sendRequest(Operation.LOGOUT, ulogovani);
    }
}
