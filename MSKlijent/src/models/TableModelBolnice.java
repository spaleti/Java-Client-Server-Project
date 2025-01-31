/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Bolnica;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @Korisnik
 */
public class TableModelBolnice extends AbstractTableModel implements Runnable {

    private ArrayList<Bolnica> lista;
    private String[] kolone = {"ID", "Naziv bolnice", "Ovlasceno lice", "Email", "Telefon"};
    private String parametar = "";

    public TableModelBolnice() {
        try {
            lista = ClientController.getInstance().getAllBolnice();
        } catch (Exception ex) {
            Logger.getLogger(TableModelBolnice.class.getName()).log(Level.SEVERE, null, ex);
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
        Bolnica b = lista.get(row);

        switch (column) {
            case 0: return b.getBolnicaID();
            case 1: return b.getNazivBolnice();
            case 2: return b.getOvlascenoLice();
            case 3: return b.getEmail();
            case 4: return b.getTelefon();

            default: return null;
        }
    }

    public Bolnica getSelectedBolnica(int row) {
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
            Logger.getLogger(TableModelBolnice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllBolnice();
            if (!parametar.equals("")) {
                ArrayList<Bolnica> novaLista = new ArrayList<>();
                for (Bolnica b : lista) {
                    if (b.getNazivBolnice().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(b);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
