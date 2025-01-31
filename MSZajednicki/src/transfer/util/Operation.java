/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @Korisnik
 */
public interface Operation {

    public static final int LOGIN = 0;
    public static final int LOGOUT = 1;

    public static final int ADD_BOLNICA = 2; 
    public static final int DELETE_BOLNICA = 3; 
    public static final int UPDATE_BOLNICA = 4;
    public static final int GET_ALL_BOLNICE = 5;

    public static final int ADD_PORUDZBINA = 6;
    public static final int DELETE_PORUDZBINA = 7;
    public static final int UPDATE_PORUDZBINA = 8;
    public static final int GET_ALL_PORUDZBINA = 9;

    public static final int GET_ALL_STAVKA_PORUDZBINE = 10;

    public static final int GET_ALL_KATEGORIJA_MEDICINSKOG_SREDSTVA = 11;
    
    public static final int GET_ALL_MEDICINSKO_SREDSTVO = 12;

}
