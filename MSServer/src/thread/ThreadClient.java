/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Administrator;
import domain.KategorijaMedicinskogSredstva;
import domain.Bolnica;
import domain.Porudzbina;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @Korisnik
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                // primiZahtev
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request req = (Request) in.readObject();
                // switch
                Response res = handleRequest(req);
                // posaljiOdgovor
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request req) {
        Response res = new Response(null, null, ResponseStatus.Success);
        try {
            switch (req.getOperation()) {
                case Operation.ADD_BOLNICA:
                    ServerController.getInstance().addBolnica((Bolnica) req.getParametar());
                    break;
                case Operation.ADD_PORUDZBINA:
                    ServerController.getInstance().addPorudzbina((Porudzbina) req.getParametar());
                    break;
                case Operation.DELETE_BOLNICA:
                    ServerController.getInstance().deleteBolnica((Bolnica) req.getParametar());
                    break;
                case Operation.DELETE_PORUDZBINA:
                    ServerController.getInstance().deletePorudzbina((Porudzbina) req.getParametar());
                    break;
                case Operation.UPDATE_BOLNICA:
                    ServerController.getInstance().updateBolnica((Bolnica) req.getParametar());
                    break;
                case Operation.UPDATE_PORUDZBINA:
                    ServerController.getInstance().updatePorudzbina((Porudzbina) req.getParametar());
                    break;
                case Operation.GET_ALL_BOLNICE:
                    res.setResponse(ServerController.getInstance().getAllBolnica());
                    break;
                case Operation.GET_ALL_KATEGORIJA_MEDICINSKOG_SREDSTVA:
                    res.setResponse(ServerController.getInstance().getAllKategorijaMedicinskogSredstva());
                    break;
                case Operation.GET_ALL_PORUDZBINA:
                    res.setResponse(ServerController.getInstance().getAllPorudzbina((Bolnica) req.getParametar()));
                    break;
                case Operation.GET_ALL_STAVKA_PORUDZBINE:
                    res.setResponse(ServerController.getInstance().getAllStavkaPorudzbine((Porudzbina) req.getParametar()));
                    break;
                case Operation.GET_ALL_MEDICINSKO_SREDSTVO:
                    res.setResponse(ServerController.getInstance().getAllMedicinskoSredstvo((KategorijaMedicinskogSredstva) req.getParametar()));
                    break;
                case Operation.LOGIN:
                    Administrator a = (Administrator) req.getParametar();
                    Administrator administrator = ServerController.getInstance().login(a);
                    res.setResponse(administrator);
                    break;
                case Operation.LOGOUT:
                    Administrator ulogovani = (Administrator) req.getParametar();
                    ServerController.getInstance().logout(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception e) {
            res.setError(e);
            res.setResponseStatus(ResponseStatus.Error);
        }
        return res;
    }

}
