/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;


import domain.TezinaNaDan;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marija
 */
public class TableModelTezinaNaDan extends AbstractTableModel {

    boolean editable;
    List<TezinaNaDan> tezine;
    String[] columnNames = new String[]{"Datum", "Tezina"};

    public TableModelTezinaNaDan(List<TezinaNaDan> tezine) {
        this.tezine = tezine;
    }

    @Override
    public int getRowCount() {
        return tezine.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int i) {
        return columnNames[i];
    }
    
    public void poredjajTezinePoDatumima() {
        for (int i = 0; i < tezine.size(); i++) {
            for (int j = i+1; j < tezine.size(); j++) {
                if (tezine.get(i).getDatum().after(tezine.get(j).getDatum())) {
                    Collections.swap(tezine, i, j);    
                }
            }
        }  
    }
    
    
    public void addTezinaNaDan(TezinaNaDan z) {
        tezine.add(z);
        
    }
    public void removeLastTezinaNaDan() {
        tezine.remove(tezine.size()-1);
        
    }
    public void removeTezinaNaDanAt(int rowindex) {
        tezine.remove(rowindex);
        
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TezinaNaDan z = tezine.get(rowIndex);
        switch (columnIndex) {
            case 0:
                SimpleDateFormat s = new SimpleDateFormat("yyyy.MM.dd");
                return s.format(z.getDatum());
            case 1:
                return z.getTezina();
            default:
                return "n/a";
        }
    }

    public TezinaNaDan getTezinaNaDanAt(int rowindex) {
        return tezine.get(rowindex);
    }

    public List<TezinaNaDan> getTezineNaDan(){
        return tezine;
    }

    
}
