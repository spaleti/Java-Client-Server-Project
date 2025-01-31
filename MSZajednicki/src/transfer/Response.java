/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;
import transfer.util.ResponseStatus;

/**
 *
 * @Korisnik
 */
public class Response implements Serializable {

    private Object response;
    private Exception error;
    private ResponseStatus responseStatus;

    public Response() {
    }

    public Response(Object response, Exception error, ResponseStatus responseStatus) {
        this.response = response;
        this.error = error;
        this.responseStatus = responseStatus;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public Exception getError() {
        return error;
    }

    public void setError(Exception error) {
        this.error = error;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

}
