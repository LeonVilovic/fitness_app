/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import domain.Korisnik;
import domain.Vezba;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marija
 */
public class TableModelVezba extends AbstractTableModel {

    boolean editable;
    List<Vezba> vezbe;
    String[] columnNames = new String[]{"Id", "Naziv", "Tezina Vezbe"};

    public TableModelVezba(List<Vezba> vezbe) {
        this.vezbe = vezbe;
    }

   

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    @Override
    public String getColumnName(int i) {
        return columnNames[i];
    }

    @Override
    public int getRowCount() {
        return vezbe.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int x, int y) {
        Vezba v = vezbe.get(x);
        switch (y) {
            case 0:
                return v.getiDVezba();
            case 1:
                return v.getNaziv();
            case 2:
                return v.getTezinaVezbe();
            
            default:
                return "n/a";
        }
    }


    public Vezba getVezbaAt(int rowIndex) {
        return vezbe.get(rowIndex);
    }

}
