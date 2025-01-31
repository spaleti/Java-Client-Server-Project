/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @Korisnik
 */
public class Request implements Serializable {

    private int operation;
    private Object parametar;

    public Request() {
    }

    public Request(int operation, Object parametar) {
        this.operation = operation;
        this.parametar = parametar;
    }

    public Object getParametar() {
        return parametar;
    }

    public int getOperation() {
        return operation;
    }

    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

}
