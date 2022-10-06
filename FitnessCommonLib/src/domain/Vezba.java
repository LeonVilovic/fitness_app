/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marija
 */
public class Vezba implements Serializable, OpstiDomenskiObjekat {

    private Long iDVezba;
    private String naziv;
    private String opis;
    private boolean moguceSamoUTeretani;
    private String putDoFajla;
    private TezinaVezbe tezinaVezbe;

    public Vezba(Long iDVezba, String naziv, String opis, boolean moguceSamoUTeretani, String putDoFajla, TezinaVezbe tezinaVezbe) {
        this.iDVezba = iDVezba;
        this.naziv = naziv;
        this.opis = opis;
        this.moguceSamoUTeretani = moguceSamoUTeretani;
        this.putDoFajla = putDoFajla;
        this.tezinaVezbe = tezinaVezbe;
    }

    public Vezba(String naziv, String opis, boolean moguceSamoUTeretani, String putDoFajla, TezinaVezbe tezinaVezbe) {
        this.naziv = naziv;
        this.opis = opis;
        this.moguceSamoUTeretani = moguceSamoUTeretani;
        this.putDoFajla = putDoFajla;
        this.tezinaVezbe = tezinaVezbe;
    }

    public Vezba() {
    }

    public Long getiDVezba() {
        return iDVezba;
    }

    public void setiDVezba(Long iDVezba) {
        this.iDVezba = iDVezba;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public boolean isMoguceSamoUTeretani() {
        return moguceSamoUTeretani;
    }

    public void setMoguceSamoUTeretani(boolean moguceSamoUTeretani) {
        this.moguceSamoUTeretani = moguceSamoUTeretani;
    }

    public String getPutDoFajla() {
        return putDoFajla;
    }

    public void setPutDoFajla(String putDoFajla) {
        this.putDoFajla = putDoFajla;
    }

    public TezinaVezbe getTezinaVezbe() {
        return tezinaVezbe;
    }

    public void setTezinaVezbe(TezinaVezbe tezinaVezbe) {
        this.tezinaVezbe = tezinaVezbe;
    }

    @Override
    public String dajNazivTabele() {
        return "vezba";
    }

    @Override
    public String dajNaziveAtributa() {
        return "naziv, opis, moguceSamoUTeretani, putDoFajla, tezinaVezbe";
    }

    @Override
    public String dajNazivAtributa(int brojKolone) {
        switch (brojKolone) {
            case 1:
                return "iDVezba";
            case 2:
                return "naziv";
            case 3:
                return "opis";
            case 4:
                return "moguceSamoUTeretani";
            case 5:
                return "putDoFajla";
            case 6:
                return "tezinaVezbe";
        }
        return "";
    }

    @Override
    public String dajVrednostiAtributa() {
        int i = moguceSamoUTeretani ? 1 : 0;
        return "'"+naziv+"', '"+opis+"', '"+i+"', '"+putDoFajla+"', '"+tezinaVezbe+"'";
        //bez autoincrementiranih polja
    }

    @Override
    public String postaviVrednostiAtributa() {
        int i = moguceSamoUTeretani ? 1 : 0;
        return "naziv='" + naziv + "', opis='" + opis +"', moguceSamoUTeretani='" + i +"', putDoFajla='" + putDoFajla +"', tezinaVezbe='" + tezinaVezbe + "'";
        //bez autoincrementiranih polja
    }

    @Override
    public boolean isAutoincrement() {
        return true;
    }

    @Override
    public void setId(Object id) {
        iDVezba = (Long) id;
    }

    @Override
    public String getID() {
        return iDVezba.toString();
    }

    @Override
    public String dajNazivPrimarnogKljuca() {
        return "iDVezba";
    }

    @Override
    public List<String> dajKompleksniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> dajAtributeZaPretragu() {
        List<String> lista = new ArrayList<>();
        lista.add("naziv");
        return lista;
    }

}
