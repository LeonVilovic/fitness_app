/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import domain.Korisnik;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marija
 */
public class TableModelKorisnik extends AbstractTableModel {

    boolean editable;
    List<Korisnik> korisnici;
    String[] columnNames = new String[]{"Id", "Ime", "Prezime", "Username", "E-mail"};

    public TableModelKorisnik(List<Korisnik> korisnici) {
        this.korisnici = korisnici;
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
        return korisnici.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int x, int y) {
        Korisnik korisnik = korisnici.get(x);
        switch (y) {
            case 0:
                return korisnik.getIDKorisnik();
            case 1:
                return korisnik.getIme();
            case 2:
                return korisnik.getPrezime();
            case 3:
                return korisnik.getUsername();
            case 4:
                return korisnik.getEmail();
            default:
                return "n/a";
        }
    }


    public Korisnik getKorisnikAt(int rowIndex) {
        return korisnici.get(rowIndex);
    }

}
