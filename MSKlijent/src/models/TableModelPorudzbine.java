/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Bolnica;
import domain.Porudzbina;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @Korisnik
 */
public class TableModelPorudzbine extends AbstractTableModel implements Runnable {

    private ArrayList<Porudzbina> lista;
    private String[] kolone = {"ID", "Bolnica", "Datum isporuke", "Ukupna cena"};
    private String parametar = "";

    public TableModelPorudzbine() {
        try {
            lista = ClientController.getInstance().getAllPorudzbina(null);
        } catch (Exception ex) {
            Logger.getLogger(TableModelPorudzbine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TableModelPorudzbine(Bolnica b) {
        try {
            lista = ClientController.getInstance().getAllPorudzbina(b);
        } catch (Exception ex) {
            Logger.getLogger(TableModelPorudzbine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Porudzbina p = lista.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        
        switch (column) {
            case 0: return p.getPorudzbinaID();
            case 1: return p.getBolnica();
            case 2: return sdf.format(p.getDatumIsporuke());
            case 3: return p.getUkupnaCena() + "rsd";

            default: return null;
        }
    }

    public Porudzbina getSelectedPorudzbina(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelPorudzbine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllPorudzbina(null);
            if (!parametar.equals("")) {
                ArrayList<Porudzbina> novaLista = new ArrayList<>();
                for (Porudzbina p : lista) {
                    if (p.getBolnica().getNazivBolnice().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(p);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Porudzbina> getLista() {
        return lista;
    }

}
